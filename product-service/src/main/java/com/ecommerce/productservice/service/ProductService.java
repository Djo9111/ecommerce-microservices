package com.ecommerce.productservice.service;

import com.ecommerce.productservice.dto.ProduitDTO;
import com.ecommerce.productservice.entity.Categorie;
import com.ecommerce.productservice.entity.Produit;
import com.ecommerce.productservice.repository.CategorieRepository;
import com.ecommerce.productservice.repository.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;

    public ProductService(ProduitRepository produitRepository, CategorieRepository categorieRepository) {
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
    }

    // --- Méthodes qui gèrent les Entités (pour la persistance) ---
    public Produit saveProduit(Produit produit) { return produitRepository.save(produit); }

    public void deleteProduit(Long id) { produitRepository.deleteById(id); }

    public Produit updateProduit(Long id, Produit p) {
        Produit prod = produitRepository.findById(id).orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        prod.setLibelle(p.getLibelle());
        prod.setDescription(p.getDescription());
        prod.setPrix(p.getPrix());
        prod.setStock(p.getStock());
        prod.setImage(p.getImage());
        if (p.getCategorie()!=null) prod.setCategorie(p.getCategorie());
        return produitRepository.save(prod);
    }

    public Categorie saveCategorie(Categorie c) { return categorieRepository.save(c); }

    public List<Categorie> getAllCategories() { return categorieRepository.findAll(); }

    // --- Méthodes qui retournent des DTO (pour les requêtes GET) ---
    public Optional<ProduitDTO> getProduitById(Long id) {
        return produitRepository.findById(id)
                .map(this::convertToDto);
    }

    public List<ProduitDTO> getAllProduits() {
        return produitRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProduitDTO> search(String q) {
        return produitRepository.findByLibelleContainingIgnoreCase(q).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ProduitDTO> byCategorie(Long idCat) {
        return produitRepository.findByCategorie_Id(idCat).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // --- Méthode de conversion interne ---
    private ProduitDTO convertToDto(Produit produit) {
        if (produit == null) return null;

        return ProduitDTO.builder()
                .id(produit.getId())
                .libelle(produit.getLibelle())
                .description(produit.getDescription())
                .stock(produit.getStock())
                .prix(produit.getPrix())
                .image(produit.getImage())
                .categorieId(produit.getCategorie() != null ? produit.getCategorie().getId() : null)
                .categorieNom(produit.getCategorie() != null ? produit.getCategorie().getNom() : null)
                .build();
    }

}