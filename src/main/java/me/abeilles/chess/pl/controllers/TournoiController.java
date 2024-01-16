package me.abeilles.chess.pl.controllers;

import jakarta.validation.Valid;
import me.abeilles.chess.bll.tournoi.TournoiService;
import me.abeilles.chess.dal.entities.Tournoi;
import me.abeilles.chess.dal.entities.User;
import me.abeilles.chess.pl.model.tournoi.TournoiDTOGetOne;
import me.abeilles.chess.pl.model.tournoi.TournoiDTOReadAll;
import me.abeilles.chess.pl.model.tournoi.TournoiFormCreate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/tournoi")
public class TournoiController {
    private final TournoiService tournoiService;

    public TournoiController(TournoiService tournoiService) {
        this.tournoiService = tournoiService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public void create(@RequestBody @Valid TournoiFormCreate form){
        tournoiService.create(form);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        tournoiService.delete(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/all")
    public ResponseEntity<List<TournoiDTOReadAll>> getAll(){
        return ResponseEntity.ok(tournoiService.getAll());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public TournoiDTOGetOne getOne(@PathVariable Integer id){
        return tournoiService.getOneById(id);
    }

}
