package com.example.mediatheque.api;

import com.example.mediatheque.metier.model.Adherent;
import com.example.mediatheque.metier.model.Document;
import com.example.mediatheque.metier.model.Emprunt;
import com.example.mediatheque.service.DemandeEmpruntStatut;
import com.example.mediatheque.service.MediathequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class EmpruntController {

    @Autowired
    MediathequeService mediathequeService;

    @GetMapping("emprunts")
    public List<Emprunt> getAll(){
        return mediathequeService.getEmprunts();
    }

    @PostMapping("emprunts")
    public ResponseEntity addEmprunt(@RequestBody Emprunt empruntJSON){
        Optional<Document> documentOptional = mediathequeService.getDocumentById(empruntJSON.getDocument().getId());
        if(documentOptional.isEmpty())
            return ResponseEntity.badRequest().body("Document inexitant");

        Optional<Adherent> adherentOptional = mediathequeService.getAdherentById(empruntJSON.getAdherent().getId());
        if(adherentOptional.isEmpty())
            return ResponseEntity.badRequest().body("Adherent inexitant");


        Adherent adherentFromDatabase = adherentOptional.get();
        Document documentFromDatabase = documentOptional.get();
        DemandeEmpruntStatut statut = mediathequeService.canEmprunter(adherentFromDatabase, documentFromDatabase);
        if(statut.equals(DemandeEmpruntStatut.ACCEPTE)) {
            mediathequeService.realiserEmprunt(empruntJSON, adherentFromDatabase, documentFromDatabase);
            return ResponseEntity.status(201).build();
        }
        else {
            return ResponseEntity.badRequest().body(statut);
        }
    }
}