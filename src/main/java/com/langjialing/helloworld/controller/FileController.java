package com.langjialing.helloworld.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 郎家岭伯爵
 */
@RestController
public class FileController {
    @RequestMapping(value = "/upload", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        File convertedFile = new File("src/main/resources/files/" + file.getOriginalFilename());
        convertedFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertedFile);
        fout.write(file.getBytes());
        fout.close();
        return "File is upload successfully";
    }
}
