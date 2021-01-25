package com.project.caref.service;

import com.project.caref.exeption.FileErrors;
import com.project.caref.exeption.GaragimageException;
import com.project.caref.exeption.MyFileNotFoundException;
import com.project.caref.models.Garagimage;
import com.project.caref.repository.GaragimageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class GaragimageService {

    @Autowired
    private GaragimageRepository garagimageRepository;


    public  Garagimage saveFile(MultipartFile file) {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try {

            if (filename.contains("...")) {
                throw new GaragimageException(FileErrors.INVALID_FILE + filename);
            }

            String path = "";
            Garagimage garagimage = new Garagimage(filename, file.getContentType(), file.getBytes(), path);


            System.out.println("////******" + filename);
            return garagimageRepository.save(garagimage);

            /* String path = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").
                    path(garagimage.getFileId()).toUriString(); */

        } catch (Exception e) {

            throw new GaragimageException(FileErrors.FILE_NOT_STORED, e);
        }
    }

    public Garagimage updateFile(Garagimage garagimage) {
        return garagimageRepository.saveAndFlush(garagimage);
    }

    public Garagimage getFile(String fileId) {

        return garagimageRepository.findById(fileId).orElseThrow(() -> 
                new MyFileNotFoundException(FileErrors.FILE_NOT_FOUND + fileId));
    }

    public List<Garagimage> getListOfFiles(){

        return garagimageRepository.findAll();
    }



}
