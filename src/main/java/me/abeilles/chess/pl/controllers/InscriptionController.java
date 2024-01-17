package me.abeilles.chess.pl.controllers;


import me.abeilles.chess.bll.inscription.InscriptionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}/{pseudo}")
    public void supprimerInscription(@PathVariable Integer id, @PathVariable String pseudo){
        inscriptionService.supprimerInscriptionTournoi(id,pseudo);
    }
}
