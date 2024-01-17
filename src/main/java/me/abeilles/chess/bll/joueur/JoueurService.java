package me.abeilles.chess.bll.joueur;

import me.abeilles.chess.dal.entities.User;
import me.abeilles.chess.pl.model.joueur.JoueurDTO;
import me.abeilles.chess.pl.model.joueur.JoueurForm;


import java.util.Set;

public interface JoueurService {
    void create(JoueurForm form);
    void delete(Integer id);
    User getOneById(Integer id);
    void update(Integer id, JoueurForm form);
    Set<JoueurDTO> getAll();
    Set<JoueurDTO> getAllActif();
    Set<JoueurDTO> getAllEnabled();
}
