package com.project.caref.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "garagimage")
public class Garagimage {

    public Garagimage(String fileId, String fileName, String fileType, byte[] fileData, String filePath) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
        this.fileData = fileData;
    }

    public Garagimage(String filename, String contentType, byte[] bytes, String path) {
        this.fileName = filename;
        this.fileType = contentType;
        this.filePath = path;
        this.fileData = bytes;
    }

    public String getFileId() {
        return fileId;
    }

    public String getFileUri() {
        return this.filePath;
    }

    public Garagimage setFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public Garagimage setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFileType() {
        return fileType;
    }

    public Garagimage setFileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public Garagimage setFileData(byte[] fileData) {
        this.fileData = fileData;
        return this;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")

    private String fileId;

    private String fileName;

    private String fileType;

    public String getFilePath() {
        return filePath;
    }

    public Garagimage setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    private String filePath;

    @Lob
    private byte[] fileData;

    public Garagimage() {}

}
