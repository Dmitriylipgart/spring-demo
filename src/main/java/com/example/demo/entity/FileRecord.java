package com.example.demo.entity;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "file")
public class FileRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 20, nullable = false)
    Long id;

    @Column(name = "fileName")
    String fileName;

    @Column(name = "description")
    String description;

    @Column(name = "filePath")
    String filePath;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public FileRecord(String fileName, String description, String filePath) {
        this.fileName = fileName;
        this.description = description;
        this.filePath = filePath;
    }

    public FileRecord() {

    }
}
