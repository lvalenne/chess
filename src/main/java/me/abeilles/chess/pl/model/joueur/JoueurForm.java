package me.abeilles.chess.pl.model.joueur;

import jakarta.validation.constraints.*;
import me.abeilles.chess.dal.entities.Genre;
import me.abeilles.chess.dal.entities.UserRole;

import java.time.LocalDate;
import java.util.Set;

public record JoueurForm(
    @NotNull
    @NotBlank
    String pseudo,

    @NotNull
    @NotBlank
    @Email(message = "Adresse email invalide")
    String email,

    @NotBlank
    @NotNull
    String password,

    @NotNull

    LocalDate dateNaissance,

    @NotNull

    Genre genre,

    @Min(value = 0, message = "Le chiffre ne peut pas être inférieur à 0")
    @Max(value = 3000, message = "Le chiffre ne peut pas être supérieur à 3000")

   Integer elo,

    //@Size(max = 20)
    @NotNull
   Set<UserRole> roles,

    boolean enabled,

    boolean deleted
) {
}
