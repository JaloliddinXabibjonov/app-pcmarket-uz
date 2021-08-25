package uz.pcmarket.apppcmarketuz.service;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pcmarket.apppcmarketuz.entity.Attachment;
import uz.pcmarket.apppcmarketuz.payload.template.Result;
import uz.pcmarket.apppcmarketuz.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    public static final String uploadDirectory="Product photo";

    /**
     * ADD NEW PHOTO
     * @param request
     * @return
     */
    @SneakyThrows
    public Result addPhoto(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            Attachment attachment=new Attachment();
            attachment.setOriginalName(file.getName());
            attachment.setSize(file.getSize());
            attachment.setContentType(file.getContentType());
            String[] split=file.getOriginalFilename().split("\\.");
            String name= UUID.randomUUID().toString()+"."+split[split.length-1];
            attachment.setName(name);
            Attachment savedAttachment = attachmentRepository.save(attachment);
            byte[] bytes = file.getBytes();
            Path path= Paths.get(uploadDirectory+"/"+name);
            Files.copy(file.getInputStream(), path);
            return new Result("File saved", true);
        }
        return new Result("File not saved",false);
    }

    /**
     * GET PHOTO BY ID
     * @return Result
     */
    public Result getPhotoById(Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent())
            return new Result("File not found", false);
        Attachment attachment = optionalAttachment.get();
        response.setHeader("Content-Disposition", "attachment; fileName=\""+attachment.getOriginalName()+"\"");
        response.setContentType(attachment.getContentType());
        FileInputStream fileInputStream=new FileInputStream(uploadDirectory+"/"+attachment.getName());
        FileCopyUtils.copy(fileInputStream, response.getOutputStream());

        return new Result("File not found", false);
    }




}
