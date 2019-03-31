package com.sms.controller;


import com.sms.common.CommandCode;
import com.sms.common.CommandCodeDictionary;
import com.sms.common.CommandResult;
import com.sms.common.ControllerBase;
import com.sms.model.Fee;
import com.sms.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@Api
@Controller
public class FileController extends ControllerBase {
    @Autowired
    private IFileService fileService;

    @Value("#{prop.path}")
    private String path;

    @Autowired
    private CommonsMultipartResolver multipartResolver;

    @ApiOperation(value = "Create File", notes = "Create File")
    @RequestMapping(value = "/File/{type}", method = RequestMethod.POST)
    @ResponseBody
    public CommandResult createFile(HttpServletRequest request, @PathVariable("type") Integer type) {
        try {
            com.sms.model.File fileModel = new com.sms.model.File();
            fileModel.setType(type);
            Integer id = fileService.createFile(fileModel);
            File f = null;
            if (multipartResolver.isMultipart(request)) {
               File pathname = new File(path);
               if(!pathname.exists()){
                   pathname.mkdir();
               }
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                //获取multiRequest 中所有的文件名
                Iterator iter = multiRequest.getFileNames();

                while (iter.hasNext()) {
                    //一次遍历所有文件
                    MultipartFile file = multiRequest.getFile(iter.next().toString());
                    if (file != null) {
                        //上传
                        String fileName = file.getOriginalFilename();
                        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                        f = new File(pathname,id.toString() + "." + suffix);
                        file.transferTo(f);
                    }
                }

            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("path",f.getName());
            jsonObject.put("type",type);
            return new CommandResult(CommandCode.OK.getCode(),CommandCodeDictionary.getCodeMessage(CommandCode.OK),jsonObject);
        } catch (Exception ex) {
            logger.error("Exception : " + ex.getMessage());
            return new CommandResult(CommandCode.INTERNAL_ERROR.getCode(), ex.getMessage());
        }
    }
}
