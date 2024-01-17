package me.abeilles.chess.pl.controllers;


import me.abeilles.chess.bll.inscription.InscriptionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inscription")
public class InscriptionController {

 private final InscriptionService inscriptionService;

    public InscriptionController(InscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/{pseudo}")
    public void inscription(@PathVariable Integer id, @PathVariable String pseudo) {
        inscriptionService.inscriptionTournoi(id,pseudo);
    }
}
