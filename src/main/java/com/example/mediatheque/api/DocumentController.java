package com.example.mediatheque.api;

import com.example.mediatheque.metier.model.Adherent;
import com.example.mediatheque.metier.model.Document;
import com.example.mediatheque.service.MediathequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class DocumentController {

    @Autowired
    MediathequeService mediathequeService;

    @GetMapping("documents")
    public List<Document> getAll(){
        return mediathequeService.getDocuments();
    }

    @PostMapping("documents")
    @ResponseStatus( HttpStatus.CREATED )
    public void add(@RequestBody Document document){
        mediathequeService.addDocument(document);
    }
}