package me.abeilles.chess.pl.model.tournoi;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import me.abeilles.chess.dal.entities.Categorie;
import me.abeilles.chess.dal.entities.Statut;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TournoiDTO(
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
}
