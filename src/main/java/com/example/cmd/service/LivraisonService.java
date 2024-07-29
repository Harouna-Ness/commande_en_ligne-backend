package com.example.cmd.service;

import com.example.cmd.model.Commande;
import com.example.cmd.model.Livraison;
import com.example.cmd.repository.LivraisonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LivraisonService {
    private LivraisonRepository livraisonRepository;
    public List<Livraison> getAllLivraison() {
        return livraisonRepository.findAll();
    }
    public Livraison addLivraison(Livraison livraison) {
        return livraisonRepository.save(livraison);
    }
    public String affecterLivraison(Livraison livraison, Commande commande) {
        return "";
    }
}
