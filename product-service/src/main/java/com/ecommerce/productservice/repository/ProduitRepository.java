package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByLibelleContainingIgnoreCase(String q);
    List<Produit> findByCategorie_Id(Long idCategorie);
}
