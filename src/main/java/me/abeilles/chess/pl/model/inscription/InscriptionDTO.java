package me.abeilles.chess.pl.model.inscription;

import me.abeilles.chess.dal.entities.Inscription;
import me.abeilles.chess.dal.entities.Statut;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record InscriptionDTO(
        String nomTournoi,


        Statut statut,

        LocalDate dateFinInscription,
        String nomUtilisateur


) {
    public static InscriptionDTO fromEntity(Inscription inscription){
        return new InscriptionDTO(inscription.getTournoi().getNom(),inscription.getTournoi().getStatut(), inscription.getTournoi().getDateFinInscriptions(),inscription.getUser().getPseudo());
    }
}
