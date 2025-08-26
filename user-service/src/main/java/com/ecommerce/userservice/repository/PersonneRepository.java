package com.ecommerce.userservice.repository;

import com.ecommerce.userservice.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {
    // Des méthodes de recherche spécifiques peuvent être ajoutées ici
    // exemple : Optional<Personne> findByEmail(String email);
}