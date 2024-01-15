package me.abeilles.chess.pl.controllers;

import jakarta.validation.Valid;
import me.abeilles.chess.bll.joueur.JoueurService;
import me.abeilles.chess.pl.model.joueur.JoueurForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/joueur")
public class JoueurController {
    private final JoueurService joueurService;

    public JoueurController(JoueurService joueurService) {
        this.joueurService = joueurService;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public void create(@RequestBody @Valid JoueurForm form, Authentication authentication){
        joueurService.create(form, authentication);
    }
}
