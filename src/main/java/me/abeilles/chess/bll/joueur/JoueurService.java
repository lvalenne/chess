package me.abeilles.chess.bll.joueur;

import me.abeilles.chess.dal.entities.User;
import me.abeilles.chess.pl.model.joueur.JoueurForm;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface JoueurService {
    void create(JoueurForm form, Authentication authentication);
    void delete(Integer id,  Authentication authentication);
    User getOneById(Integer id,  Authentication authentication);
    void update(Integer id, JoueurForm form,  Authentication authentication);
    Set<User> getAll();
}
