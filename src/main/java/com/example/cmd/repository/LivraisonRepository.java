package com.example.cmd.repository;

import com.example.cmd.model.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivraisonRepository extends JpaRepository<Livraison, Long> {
}
