package me.abeilles.chess.pl.controllers;

import jakarta.validation.Valid;
import me.abeilles.chess.bll.tournoi.TournoiService;
import me.abeilles.chess.pl.model.tournoi.TournoiFormCreate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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


}
