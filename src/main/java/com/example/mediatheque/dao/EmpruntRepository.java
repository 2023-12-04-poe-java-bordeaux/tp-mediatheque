package com.example.mediatheque.dao;

import com.example.mediatheque.metier.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Integer> {
}
