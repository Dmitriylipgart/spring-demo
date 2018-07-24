package com.example.demo.services;

import com.example.demo.entity.FileRecord;
import com.example.demo.repositories.FileRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class FileRecordServiceImpl implements FileRecordService{

    private final String path = "E:\\spring-demo\\src\\main\\resources\\static\\files\\";

    @Autowired
    FileRecordRepository fileRecordRepository;

    @Override
    public void addFileRecord(MultipartFile multipartFile, String description) {

        String filename = multipartFile.getOriginalFilename();

        saveFileToDB(filename, description);
        saveFileToDir(multipartFile, filename);

    }

    @Override
    public FileRecord getByName(String fileName) {
        return null;
    }

    @Override
    public FileRecord updateFileRecord(FileRecord FileRecord) {
        return null;
    }

    @Override
    public void delete(long id) {

        fileRecordRepository.deleteById(Long.valueOf(id));
        new File(path + fileRecordRepository.getOne(id)).delete();

    }

    @Override
    public List<FileRecord> getAll() {
       return fileRecordRepository.findAll();
    }

    private void saveFileToDB(String filename, String description){

        String filePathForDb = "/files/" + filename;
        fileRecordRepository.saveAndFlush(new FileRecord(filename, description, filePathForDb));
    }

    private void saveFileToDir(MultipartFile multipartFile, String filename){

        String filePath = path + filename;

        File dest = new File(filePath);
        int i = 0;
        while (dest.exists()) {
            if (i == 0) {
                filename = filename.substring(0, filename.lastIndexOf(".")) + "(" + ++i + ")" + filename.substring(filename.lastIndexOf("."));
            } else {
                filename = filename.substring(0, filename.lastIndexOf("(") + 1) + ++i + ")" + filename.substring(filename.lastIndexOf("."));
            }
            dest = new File(path + filename);
        }
        try {
            multipartFile.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
