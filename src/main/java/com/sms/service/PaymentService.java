package com.sms.service;

import com.sms.common.CommandCode;
import com.sms.common.CommandCodeDictionary;
import com.sms.common.CommandResult;
import com.sms.common.DataQueryResult;
import com.sms.common.helper.PaymentDataHelper;
import com.sms.common.pagination.PaginationData;
import com.sms.model.*;
import com.sms.vo.PaymentVO;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class PaymentService extends ServiceBase implements IPaymentService {
    @Override
    public DataQueryResult<JSONObject> getPaymentsByPaginationData(PaginationData paginationData) {
        DataQueryResult<JSONObject> result = new DataQueryResult<JSONObject>(0);

        Integer totalPaymentCount = 0;
        List<Payment> payments = null;
        switch (paginationData.getPageMode()){
            case PRE_PAGE:
                payments = paymentMapper.selectByPageEndIdAndLimitAndDesc(paginationData.getQueryId(), paginationData.getCountPerPage());
                break;
            case NEXT_PAGE:
                payments = paymentMapper.selectByPageStartIdAndLimitAndAsc(paginationData.getQueryId(), paginationData.getCountPerPage());
                break;
            default:
                return result;
        }
        totalPaymentCount = paymentMapper.getCount();

        if (payments.size() > 0){
            result = new DataQueryResult<>(totalPaymentCount);
            List<PaymentVO> paymentVOS = PaymentDataHelper.convertPaymentsToPaymentVOs(payments);
            List<JSONObject> jsonObjects = PaymentDataHelper.convertPaymentVOsToJSONObjects(paymentVOS);
            result.setDataset(jsonObjects);
        }
        return result;
    }

    @Override
    public CommandResult createPayment(PaymentVO paymentVO) {
        if (null == paymentVO || null == paymentVO.getFeeId() || paymentVO.getFeeId() <= 0){
            return new CommandResult(CommandCode.FEE_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.FEE_NOT_EXIST));
        }

        Fee fee = feeMapper.selectByPrimaryKey(paymentVO.getFeeId());
        if (null == fee){
            return new CommandResult(CommandCode.FEE_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.FEE_NOT_EXIST));
        }

        Payment payment = PaymentDataHelper.convertPaymentVOToPayment(paymentVO);
        paymentMapper.insert(payment);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK));
    }

    @Override
    public CommandResult getPayment(Integer id) {
        if (id == null) {
            return new CommandResult(CommandCode.MISSING_ID.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.MISSING_ID));
        }
        Payment payment = paymentMapper.selectByPrimaryKey(id);
        if (null == payment){
            return new CommandResult(CommandCode.PAYMENT_NOT_EXIST.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.PAYMENT_NOT_EXIST));
        }

        Member member = memberMapper.selectByPrimaryKey(payment.getMemberId());
        if (null != member){
            payment.setMemberName(member.getName());
        }
        Fee fee = feeMapper.selectByPrimaryKey(payment.getFeeId());
        if (null != fee){
            payment.setFeeName(fee.getName());
            payment.setPrice(fee.getPrice());
        }
        PaymentVO paymentVO = PaymentDataHelper.convertPaymentToPaymentVO(payment);
        return new CommandResult(CommandCode.OK.getCode(), CommandCodeDictionary.getCodeMessage(CommandCode.OK), paymentVO.serializeToJSONObject());
    }
}
