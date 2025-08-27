package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.dto.ProduitDTO;
import com.ecommerce.productservice.entity.Categorie;
import com.ecommerce.productservice.entity.Produit;
import com.ecommerce.productservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // --- Produits ---
    @PostMapping("/produits")
    public Produit createProduit(@RequestBody Produit produit) {
        return productService.saveProduit(produit);
    }

    @GetMapping("/produits/{id}")
    public ResponseEntity<ProduitDTO> getProduitById(@PathVariable Long id) {
        return productService.getProduitById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/produits")
    public List<ProduitDTO> getAllProduits() {
        return productService.getAllProduits();
    }

    @PutMapping("/produits/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody Produit produit) {
        return ResponseEntity.ok(productService.updateProduit(id, produit));
    }

    @DeleteMapping("/produits/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        productService.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/produits/search")
    public List<ProduitDTO> search(@RequestParam String q) {
        return productService.search(q);
    }

    @GetMapping("/produits/by-categorie/{idCategorie}")
    public List<ProduitDTO> byCategorie(@PathVariable Long idCategorie) {
        return productService.byCategorie(idCategorie);
    }

    // --- Catégories ---
    @PostMapping("/categories")
    public Categorie createCategorie(@RequestBody Categorie categorie) {
        return productService.saveCategorie(categorie);
    }

    @GetMapping("/categories")
    public List<Categorie> getAllCategories() {
        return productService.getAllCategories();
    }
}