package com.project.caref.controllers;

import com.project.caref.models.Garagimage;
import com.project.caref.payload.response.UploadFileResponse;
import com.project.caref.repository.GaragimageRepository;
import com.project.caref.service.GaragimageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class GaragimageController {

    //private static final Logger logger = LoggerFactory.getLogger(GaragimageController.class);

    @Autowired
    private GaragimageRepository garagimageRepository;

    @Autowired
    private GaragimageService garagimageService;

    @PostMapping("/garage/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {

        Garagimage model = garagimageService.saveFile(file);

        String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/auth/garage/download/").
                path(model.getFileId()).toUriString();
        model.setFilePath(fileUri);
        // je récupére le service qui update le path(chemin d'accéss)
        garagimageService.updateFile(model);
        System.out.println("le model es " +model);
        return new UploadFileResponse(model.getFileName(), model.getFileType(), model.getFileUri());
    }

    @PostMapping("/garage/UploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files).
                stream().
                map(file -> uploadFile(file)).
                collect(Collectors.toList());
    }

    @GetMapping("/garage/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
           Garagimage model = garagimageService.getFile(fileName);
           return ResponseEntity.ok().
                   contentType(MediaType.parseMediaType(model.getFileType()))
                   .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + model.getFileName() + "\"").
                   body(new ByteArrayResource(model.getFileData()));
    }

    @GetMapping("/garage/Allfiles")
    public List<Garagimage> getListFiles(Model model) {
        List<Garagimage> fileDetails = garagimageService.getListOfFiles();

        return fileDetails;
    }



}
