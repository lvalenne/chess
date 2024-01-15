package me.abeilles.chess.pl.model.joueur;

import me.abeilles.chess.dal.entities.Genre;
import me.abeilles.chess.dal.entities.User;
import me.abeilles.chess.dal.entities.UserRole;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public record JoueurDTO(
        Integer id,
        String pseudo,
        String email,
        String password,
        LocalDate dateDeNaissance,
        Genre genre,
        Integer elo,
        Set<String> roles,
        boolean enabled

) {
    public static JoueurDTO fromEntity(User user){
        return new JoueurDTO((user.getId()),user.getPseudo(),user.getEmail(),user.getPassword(), user.getDateNaissance(),
                user.getGenre(),user.getElo(),user.getRoles().stream()
                                            .map(UserRole::name)
                                            .collect(Collectors.toSet()), user.isEnabled()
        );
    }
}
