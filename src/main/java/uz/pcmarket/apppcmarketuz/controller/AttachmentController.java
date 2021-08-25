package uz.pcmarket.apppcmarketuz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pcmarket.apppcmarketuz.payload.template.Result;
import uz.pcmarket.apppcmarketuz.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    @PostMapping
    public ResponseEntity<Result> add(MultipartHttpServletRequest request){
        Result result = attachmentService.addPhoto(request);
        return ResponseEntity.status(result.isSucces()?201:409).body(result);
    }

    @GetMapping
    public ResponseEntity<Result> get(Integer id, HttpServletResponse response) throws IOException {
        Result result = attachmentService.getPhotoById(id, response);
        return ResponseEntity.ok(result);
    }
}
