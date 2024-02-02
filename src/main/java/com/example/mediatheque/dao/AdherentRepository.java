package com.example.mediatheque.dao;

import com.example.mediatheque.metier.model.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdherentRepository extends JpaRepository<Adherent, Integer> {
}
