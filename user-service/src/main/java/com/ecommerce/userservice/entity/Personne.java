package com.ecommerce.userservice.entity;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Table(name = "personne")
@Data
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // S'il s'agit d'une clé auto-incrémentée
    @Column(name = "id_pers")
    private Long idPers;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "role")
    private String role;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    // La relation avec Panier sera gérée plus tard dans un autre microservice
}