package com.sms.common.helper;

import com.sms.model.Fee;
import com.sms.model.Payment;
import com.sms.vo.FeeVO;
import com.sms.vo.PaymentVO;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class PaymentDataHelper {
	public static List<PaymentVO> convertPaymentsToPaymentVOs(List<Payment> payments) {
		List<PaymentVO> paymentVOS = new ArrayList<>();
		payments.stream().forEach(payment -> {
			paymentVOS.add(convertPaymentToPaymentVO(payment));
		});
		return paymentVOS;
	}

	public static PaymentVO convertPaymentToPaymentVO(Payment payment) {
		PaymentVO paymentVO = new PaymentVO();
		BeanUtils.copyProperties(payment, paymentVO);
		return paymentVO;
	}

	public static Payment convertPaymentVOToPayment(PaymentVO paymentVO) {
		Payment payment = new Payment();
		BeanUtils.copyProperties(paymentVO, payment);
		return payment;
	}

	public static List<JSONObject> convertPaymentVOsToJSONObjects(List<PaymentVO> paymentVOs) {
		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
		paymentVOs.stream().forEach(paymentVO -> {
			jsonObjects.add(paymentVO.serializeToJSONObject());
		});
		return jsonObjects;
	}
}
