package com.example.cmd.service;

import com.example.cmd.model.StatuCommande;
import com.example.cmd.repository.StatuCommandeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatuCommandeService {
    private StatuCommandeRepository statuCommandeRepository;

    public StatuCommande recupererStatusCommande(long id) {
        return statuCommandeRepository.findById(id).orElseThrow(()-> new RuntimeException("Status commande n'existe pas"));
    }

    public StatuCommande creer(StatuCommande statu) {
        return this.statuCommandeRepository.save(statu);
    }

    public StatuCommande modifier(long id, StatuCommande statu){
        StatuCommande statuCommande = this.statuCommandeRepository.findById(id).orElse(null);
        if(statuCommande != null) {
            statuCommande.setLibelle(statu.getLibelle());
            return this.creer(statuCommande);
        }
        return null;
    }

    public void supprimer(long id) {
        this.statuCommandeRepository.deleteById(id);
    }
}
