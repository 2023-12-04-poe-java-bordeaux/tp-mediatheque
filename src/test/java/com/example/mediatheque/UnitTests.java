package com.example.mediatheque;

import com.example.mediatheque.metier.Mediatheque;
import com.example.mediatheque.metier.model.Adherent;
import com.example.mediatheque.metier.model.Document;
import com.example.mediatheque.service.DemandeEmpruntStatut;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UnitTests {

	Mediatheque mediatheque = new Mediatheque();

	@Test
	void testIsEmpruntableMaxDateAdhesionOK() {

		Adherent adherent = new Adherent("JC", "Dominguez", "", LocalDate.now().plusYears(1));
		Document livre1 = new Document("Livre 1", "auteur 1");

		assertEquals(DemandeEmpruntStatut.ACCEPTE, mediatheque.canEmprunter(adherent, livre1));
	}

	@Test
	void testIsEmpruntableMaxDateAdhesionPasOK() {

		Adherent adherent = new Adherent("JC", "Dominguez", "",
				LocalDate.of(2022, 10, 12));
		Document livre1 = new Document("Livre 1", "auteur 1");

		assertEquals(DemandeEmpruntStatut.REFUSE_ADHESION_PERIMEE,  mediatheque.canEmprunter(adherent, livre1));
	}

	@Test
	void testAvecDocumentDejaEmprunte(){
		Adherent adherent1 = new Adherent("JC", "Dominguez", "",
				LocalDate.now());
		Adherent adherent2 = new Adherent("Tom", "Mike", "",
				LocalDate.now());
		Document livre1 = new Document("Livre 1", "auteur 1");

		mediatheque.realiseEmprunt(adherent1, livre1);
		assertEquals(DemandeEmpruntStatut.REFUSE_DOCUMENT_NON_DISPONIBLE, mediatheque.canEmprunter(adherent2, livre1));
	}

	@Test
	void testNombreMaxDocuments(){
		Adherent adherent1 = new Adherent("JC", "Dominguez", "",
				LocalDate.now());

		Document livre1 = new Document("Livre 1", "auteur 1");
		Document livre2 = new Document("Livre 2", "auteur 2");
		Document livre3 = new Document("Livre 3", "auteur 3");
		Document livre4 = new Document("Livre 4", "auteur 4");

		mediatheque.realiseEmprunt(adherent1, livre1);

		assertEquals(DemandeEmpruntStatut.ACCEPTE, mediatheque.canEmprunter(adherent1, livre2));
		mediatheque.realiseEmprunt(adherent1, livre2);

		assertEquals(DemandeEmpruntStatut.ACCEPTE, mediatheque.canEmprunter(adherent1, livre3));
		mediatheque.realiseEmprunt(adherent1, livre3);

		assertEquals(DemandeEmpruntStatut.REFUSE_QUOTA_ATTEINT, mediatheque.canEmprunter(adherent1, livre4));


	}

}
