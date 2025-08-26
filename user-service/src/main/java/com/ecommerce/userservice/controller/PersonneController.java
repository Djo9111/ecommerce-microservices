package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.entity.Personne;
import com.ecommerce.userservice.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class PersonneController {

    private final PersonneService personneService;

    @Autowired
    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
    }

    @GetMapping
    public ResponseEntity<List<Personne>> getAllUsers() {
        List<Personne> personnes = personneService.getAllPersonnes();
        return new ResponseEntity<>(personnes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personne> getUserById(@PathVariable Long id) {
        return personneService.getPersonneById(id)
                .map(personne -> new ResponseEntity<>(personne, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Personne> createUser(@RequestBody Personne personne) {
        Personne savedPersonne = personneService.savePersonne(personne);
        return new ResponseEntity<>(savedPersonne, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personne> updateUser(@PathVariable Long id, @RequestBody Personne personne) {
        // Implémentez la logique de mise à jour ici (vérification de l'existence, etc.)
        personne.setIdPers(id);
        Personne updatedPersonne = personneService.savePersonne(personne);
        return new ResponseEntity<>(updatedPersonne, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        personneService.deletePersonne(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}