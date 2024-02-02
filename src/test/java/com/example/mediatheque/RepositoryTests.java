package com.example.mediatheque;

import com.example.mediatheque.dao.AdherentRepository;
import com.example.mediatheque.dao.DocumentRepository;
import com.example.mediatheque.dao.EmpruntRepository;
import com.example.mediatheque.metier.model.Adherent;
import com.example.mediatheque.metier.model.Document;
import com.example.mediatheque.metier.model.Emprunt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
class RepositoryTests {

	@Autowired
	AdherentRepository adherentRepository;

	@Autowired
	DocumentRepository documentRepository;

	@Autowired
	EmpruntRepository empruntRepository;

	@Test
	void testAdherent() {

		Adherent adherent = new Adherent("JC", "Domnguez", "", LocalDate.now());
		adherentRepository.save(adherent);
	}


	@Test
	void testDocument() {

		Document doc = new Document("Java pour les Nuls", "James");
		documentRepository.save(doc);
	}

	@Test
	void testEmprunt(){

		Optional<Adherent> optionalAdherent = adherentRepository.findById(1);
		Optional<Document> optionalDocument = documentRepository.findById(1);
		if(optionalAdherent.isPresent() && optionalDocument.isPresent()){
			Adherent adherent = optionalAdherent.get();
			Document doc = optionalDocument.get();

			Emprunt emprunt = new Emprunt();
			emprunt.setDateEmprunt(LocalDate.now());
			emprunt.setDocument(doc);
			emprunt.setAdherent(adherent);
			empruntRepository.save(emprunt);
		}
	}

}
