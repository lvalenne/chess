package me.abeilles.chess.pl.model.tournoi;

import jakarta.validation.constraints.*;

import me.abeilles.chess.dal.entities.Categorie;
import me.abeilles.chess.dal.entities.Statut;


import java.time.LocalDate;
import java.time.LocalDateTime;

public record TournoiFormCreate(
        @NotNull
        @NotBlank
        String nom,


        String lieu,
        @NotBlank
        @Min(value = 2, message = "nombre minimum doit etre de 2 joueurs")
        Integer nbMinJoueurs,

        @Max(value = 32,message = "nombre maximum est de 32 joueurs")
        @NotBlank
        Integer nbMaxJoueurs,

        @Min(value = 0, message = "Le niveau min ne peut pas être inférieur à 0")
        @Max(value = 3000, message = "Le niveau min ne peut pas être supérieur à 3000")

        @NotBlank
        Integer eloMin,

        @Min(value = 0, message = "Le niveau max ne peut pas être inférieur à 0")
        @Max(value = 3000, message = "Le niveau max ne peut pas être supérieur à 3000")
        @NotBlank
        Integer eloMax,


        Categorie categories,


        @NotNull

        Statut statut,

        Boolean womenOnly,

        LocalDate dateFinInscriptions

) {
}
