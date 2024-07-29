package com.example.cmd.repository;

import com.example.cmd.model.StatuCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatuCommandeRepository extends JpaRepository<StatuCommande, Long> {
    StatuCommande findByLibelle(String libelle);
}
