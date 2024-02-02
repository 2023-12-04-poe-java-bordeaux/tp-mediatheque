package com.example.mediatheque.api;

import com.example.mediatheque.metier.model.Adherent;
import com.example.mediatheque.service.MediathequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class AdherentController {

    @Autowired
    MediathequeService mediathequeService;

    @GetMapping("adherents")
    public List<Adherent> getAll(){
        return mediathequeService.getAdherents();
    }

    @PostMapping("adherents")
    @ResponseStatus( HttpStatus.CREATED )
    public void add(@RequestBody Adherent adherent){
        mediathequeService.addAdherent(adherent);
    }
}