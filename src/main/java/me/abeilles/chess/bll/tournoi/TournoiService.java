package me.abeilles.chess.bll.tournoi;

import me.abeilles.chess.dal.entities.Tournoi;
import me.abeilles.chess.pl.model.tournoi.TournoiFormCreate;

public interface TournoiService {
void create(TournoiFormCreate form);
void delete(Integer id);

Tournoi getOneById(Integer id);
}
