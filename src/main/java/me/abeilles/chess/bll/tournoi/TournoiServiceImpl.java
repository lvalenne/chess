package me.abeilles.chess.bll.tournoi;

import me.abeilles.chess.dal.entities.Tournoi;
import me.abeilles.chess.dal.repositories.TournoiRepsoitory;
import me.abeilles.chess.pl.model.tournoi.TournoiFormCreate;
import org.springframework.stereotype.Service;

@Service
public class TournoiServiceImpl implements TournoiService{
    public final TournoiRepsoitory tournoiRepsoitory;

    public TournoiServiceImpl(TournoiRepsoitory tournoiRepsoitory) {
        this.tournoiRepsoitory = tournoiRepsoitory;
    }

    @Override
    public void create(TournoiFormCreate form) {
        if (form == null) throw new IllegalArgumentException("le formulaire ne peut etre vide");
        Tournoi tournoi = new Tournoi();
        tournoi.setNom(form.nom());
        tournoi.setLieu((form.lieu()));
        tournoi.setNbMinJoueurs(form.nbMinJoueurs());
        tournoi.setNbMaxJoueurs(form.nbMaxJoueurs());
        tournoi.setEloMin(form.eloMin());
        tournoi.setEloMax(form.eloMax());
        tournoi.setCategorie(form.categories());
        tournoi.setWomenOnly(form.womenOnly());
        tournoi.setDateFinInscriptions(form.dateFinInscriptions());
        tournoiRepsoitory.save(tournoi);
    }

}
