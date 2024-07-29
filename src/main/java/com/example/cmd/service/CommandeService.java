package com.example.cmd.service;

import com.example.cmd.model.Commande;
import com.example.cmd.model.Facture;
import com.example.cmd.model.Produit;
import com.example.cmd.model.StatuCommande;
import com.example.cmd.repository.CommandeRepository;
import com.example.cmd.repository.FactureRepository;
import com.example.cmd.repository.LivraisonRepository;
import com.example.cmd.repository.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommandeService {
    private final CommandeRepository commandeRepository;
    private final FactureRepository factureRepository;
    private final ProduitRepository produitRepository;
    private final StatuCommandeService statuCommandeService;

    @Autowired
    public CommandeService(CommandeRepository commandeRepository, FactureRepository factureRepository, ProduitRepository produitRepository, StatuCommandeService statuCommandeService) {
        this.commandeRepository = commandeRepository;
        this.factureRepository = factureRepository;
        this.produitRepository = produitRepository;
        this.statuCommandeService = statuCommandeService;
    }

    @Transactional
    public Commande passerCommande(List<Produit> produits) {
        Commande commande = new Commande();
        float total = 0;
        List<Produit> produitsList = new ArrayList<>();
        for(Produit p:produits) {
            total += p.getPrix() * p.getQuantite();
            Produit produit = this.produitRepository.findById(p.getId()).orElseThrow(()-> new RuntimeException("produit non trouvré"));
            produitsList.add(produit);
        }
        commande.setProduit(produitsList);
        StatuCommande statu= this.statuCommandeService.recupererStatusCommande(1);
        commande.setStatu(statu);
        Facture facture = new Facture();
        facture.setTotal(total);
        facture.setCommande(commande);
        this.factureRepository.save(facture);
        return this.commandeRepository.save(commande);
    }

    public Commande getCommande(long id) {
        return this.commandeRepository.findById(id).orElseThrow(()-> new RuntimeException("commande non trouvré"));
    }

    public String changerStatus(Commande commande, StatuCommande statu) {
        Commande commande2 = commandeRepository.findById(commande.getId()).get();
        StatuCommande statuCommande = this.statuCommandeService.recupererStatusCommande(statu.getId());
        commande2.setStatu(statuCommande);
        return "Statu modifier";
    }

    public List<Commande> getCommandes() {
        return this.commandeRepository.findAll();
    }
}
