package com.example.mediatheque.metier;

import com.example.mediatheque.metier.model.Adherent;
import com.example.mediatheque.metier.model.Document;
import com.example.mediatheque.metier.model.Emprunt;
import com.example.mediatheque.service.DemandeEmpruntStatut;

import java.time.LocalDate;

public class Mediatheque {

    public DemandeEmpruntStatut canEmprunter(Adherent adherent, Document document){

        if(document.isEmprunte())
            return DemandeEmpruntStatut.REFUSE_DOCUMENT_NON_DISPONIBLE;
        if(adherent.getFinAdhesion().isBefore(LocalDate.now()))
            return DemandeEmpruntStatut.REFUSE_ADHESION_PERIMEE;
        if(adherent.getEmprunts().size() == 3)
            return DemandeEmpruntStatut.REFUSE_QUOTA_ATTEINT;

        return DemandeEmpruntStatut.ACCEPTE;
    }

    public Emprunt realiseEmprunt(Adherent adherent, Document document){
        Emprunt emprunt = new Emprunt();
        emprunt.setDocument(document);
        emprunt.setAdherent(adherent);

        document.setEmprunte(true);

        adherent.addEmprunt(emprunt);

        return emprunt;
    }
}
