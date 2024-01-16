package me.abeilles.chess.pl.model.tournoi;

import me.abeilles.chess.dal.entities.Categorie;
import me.abeilles.chess.dal.entities.Statut;
import me.abeilles.chess.dal.entities.Tournoi;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TournoiDTO(
                        Integer id,
                        String nom,


                         String lieu,

                         Integer nbMinJoueurs,

                         Integer nbMaxJoueurs,

                         Integer eloMin,

                         Integer eloMax,


                         Categorie categories,


                         Statut statut,

                         Boolean womenOnly,

                         LocalDate dateFinInscriptions,


                         LocalDateTime dateCreation,

                         LocalDateTime dateMaj) {
    public static TournoiDTO fromEntity(Tournoi tournoi){
        return new TournoiDTO(tournoi.getId(), tournoi.getNom(), tournoi.getLieu(), tournoi.getNbMinJoueurs(), tournoi.getNbMinJoueurs(), tournoi.getEloMin(), tournoi.getEloMax(), tournoi.getCategorie(),tournoi.getStatut(),tournoi.getWomenOnly(),tournoi.getDateFinInscriptions(),tournoi.getDateCreation(),tournoi.getDateMaj());
    }
}
