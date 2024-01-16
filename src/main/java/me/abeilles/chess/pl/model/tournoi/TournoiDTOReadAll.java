package me.abeilles.chess.pl.model.tournoi;

import me.abeilles.chess.dal.entities.Categorie;
import me.abeilles.chess.dal.entities.Statut;
import me.abeilles.chess.dal.entities.Tournoi;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TournoiDTOReadAll(
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

        LocalDateTime datemaj

) {
    public static TournoiDTOReadAll fromEntity(Tournoi tournoi){
        return new TournoiDTOReadAll(tournoi.getId(), tournoi.getNom(), tournoi.getLieu(), tournoi.getNombreInscriptions(),
                tournoi.getNbMinJoueurs(), tournoi.getNbMaxJoueurs(), tournoi.getCategorie(), tournoi.getEloMin(), tournoi.getEloMax(),
                tournoi.getStatut(),tournoi.getDateFinInscriptions(),tournoi.getRondeCourante(),tournoi.getDateMaj());
    }
}
