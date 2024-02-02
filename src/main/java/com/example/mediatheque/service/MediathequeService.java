package com.example.mediatheque.service;

import com.example.mediatheque.dao.AdherentRepository;
import com.example.mediatheque.dao.DocumentRepository;
import com.example.mediatheque.dao.EmpruntRepository;
import com.example.mediatheque.metier.Mediatheque;
import com.example.mediatheque.metier.model.Adherent;
import com.example.mediatheque.metier.model.Document;
import com.example.mediatheque.metier.model.Emprunt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediathequeService {

    @Autowired
    AdherentRepository adherentRepository;

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    EmpruntRepository empruntRepository;


    Mediatheque mediatheque = new Mediatheque();
    

    public List<Adherent> getAdherents() {
        return adherentRepository.findAll();
    }

    public void addAdherent(Adherent adherent) {
        adherentRepository.save(adherent);
    }

    public List<Document> getDocuments(){
        return documentRepository.findAll();
    }
    public void addDocument(Document document){
        documentRepository.save(document);
    }

    public List<Emprunt> getEmprunts(){
        return empruntRepository.findAll();
    }


    public Optional<Document> getDocumentById(Integer id) {
        return documentRepository.findById(id);
    }

    public Optional<Adherent> getAdherentById(Integer id) {
        return adherentRepository.findById(id);
    }


    public DemandeEmpruntStatut canEmprunter(Adherent adherent, Document document) {
        return mediatheque.canEmprunter(adherent, document);
    }

    public boolean realiserEmprunt(Emprunt empruntJSON, Adherent adherentFromDatabase, Document documentFromDatabase){
        DemandeEmpruntStatut statut = mediatheque.canEmprunter(adherentFromDatabase, documentFromDatabase);
        if(statut.equals(DemandeEmpruntStatut.ACCEPTE)){
            Emprunt newEmprunt = mediatheque
                    .realiseEmprunt(adherentFromDatabase, documentFromDatabase);

            empruntRepository.save(newEmprunt);
            adherentRepository.save(adherentFromDatabase);
            documentRepository.save(documentFromDatabase);

            return true;
        }
        else {
            return false;
        }
    }
}
