package com.ecommerce.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProduitDTO {

    private Long id;
    private String libelle;
    private String description;
    private Long stock;
    private BigDecimal prix;
    private String image;

    // On ne garde que l'id et le nom de la catégorie
    private Long categorieId;
    private String categorieNom;
}
