package me.abeilles.chess.pl.controllers;

import jakarta.validation.Valid;
import me.abeilles.chess.bll.joueur.JoueurService;
import me.abeilles.chess.dal.entities.User;
import me.abeilles.chess.pl.model.joueur.JoueurDTO;
import me.abeilles.chess.pl.model.joueur.JoueurForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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
        joueurService.create(form);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public User getOne(@PathVariable Integer id){
        return joueurService.getOneById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        joueurService.delete(id);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody @Valid JoueurForm form){
        joueurService.update(id,form);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<Set<JoueurDTO>> getAll(){
        return ResponseEntity.ok(joueurService.getAll());
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/allActif")
    public ResponseEntity<Set<JoueurDTO>> getAllActif(){
        return ResponseEntity.ok(joueurService.getAllActif());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/allEnabled")
    public ResponseEntity<Set<JoueurDTO>> getAllEnabled(){
        return ResponseEntity.ok(joueurService.getAllEnabled());
    }

}
