package me.abeilles.chess.pl.model.inscription;

import jakarta.validation.constraints.NotNull;
import me.abeilles.chess.dal.entities.Tournoi;
import me.abeilles.chess.dal.entities.User;

import java.time.LocalDate;

public record InscriptionForm(
        @NotNull
        LocalDate DateInscription,
        @NotNull
        User user,
        @NotNull
        Tournoi tournoi
) {
}
