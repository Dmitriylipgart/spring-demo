package com.example.demo.services;


import com.example.demo.entity.FileRecord;
import com.example.demo.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileRecordService {



    void addFileRecord(MultipartFile multipartFile, String description);
    FileRecord getByName(String fileName);
    FileRecord updateFileRecord(FileRecord FileRecord);
    void delete(long id);
    List<FileRecord> getAll();




}
