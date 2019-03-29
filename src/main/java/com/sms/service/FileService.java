package com.sms.service;


import com.sms.model.File;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class FileService extends ServiceBase implements IFileService {
    @Override
    public synchronized Integer createFile(File file) {
        fileMapper.insert(file);
        return  file.getId();
    }
}
