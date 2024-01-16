package me.abeilles.chess.pl.model.tournoi;

import me.abeilles.chess.dal.entities.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record TournoiDTOGetOne(

        Integer id,
        String nom,
        String lieu,
        Integer nombreInscriptions,
        Integer nbMinJoueurs,

        Integer nbMaxJoueurs,
        Categorie categories,

        Integer eloMin,

        Integer eloMax,

        Statut statut,
        LocalDate dateFinInscriptions,

        Integer rondeCourante,

        LocalDateTime datemaj,

        List<User> inscriptions

) {
    public static TournoiDTOGetOne fromEntity(Tournoi tournoi){
        return new TournoiDTOGetOne(tournoi.getId(), tournoi.getNom(), tournoi.getLieu(), tournoi.getNombreInscriptions(),
                tournoi.getNbMinJoueurs(), tournoi.getNbMaxJoueurs(), tournoi.getCategorie(), tournoi.getEloMin(), tournoi.getEloMax(),
                tournoi.getStatut(),tournoi.getDateFinInscriptions(),tournoi.getRondeCourante(),tournoi.getDateMaj(),
                tournoi.getInscriptions().stream().map(Inscription::getUser).collect(Collectors.toList()));
    }
}
