package me.abeilles.chess.bll.tournoi;

import me.abeilles.chess.dal.entities.Tournoi;
import me.abeilles.chess.pl.model.tournoi.TournoiDTOGetOne;
import me.abeilles.chess.pl.model.tournoi.TournoiDTOReadAll;
import me.abeilles.chess.pl.model.tournoi.TournoiFormCreate;

import java.util.List;
import java.util.Set;

public interface TournoiService {
void create(TournoiFormCreate form);
void delete(Integer id);

List<TournoiDTOReadAll> getAll();

TournoiDTOGetOne getOneById(Integer id);
}
