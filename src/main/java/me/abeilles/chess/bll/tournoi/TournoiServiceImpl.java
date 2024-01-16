package me.abeilles.chess.bll.tournoi;
import me.abeilles.chess.bll.exceptions.ForbiddenExeption;
import me.abeilles.chess.bll.exceptions.NotFoundException;
import me.abeilles.chess.dal.entities.Inscription;
import me.abeilles.chess.dal.entities.Statut;
import me.abeilles.chess.dal.entities.Tournoi;
import me.abeilles.chess.dal.repositories.InscriptionRepository;
import me.abeilles.chess.dal.repositories.TournoiRepsoitory;
import me.abeilles.chess.pl.model.tournoi.TournoiDTO;
import me.abeilles.chess.pl.model.tournoi.TournoiDTOReadAll;
import me.abeilles.chess.pl.model.tournoi.TournoiFormCreate;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TournoiServiceImpl implements TournoiService{
    private final TournoiRepsoitory tournoiRepsoitory;
    private final InscriptionRepository inscriptionRepository;


    public TournoiServiceImpl(TournoiRepsoitory tournoiRepsoitory, InscriptionRepository inscriptionRepository) {
        this.tournoiRepsoitory = tournoiRepsoitory;
        this.inscriptionRepository = inscriptionRepository;
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

    @Override
    public void delete(Integer id) {
        Tournoi tournoi = getOneById(id);
        if(Objects.equals(tournoi.getStatut(), EnumSet.of(Statut.EN_ATTENTE_DE_JOUEURS))){
            tournoiRepsoitory.delete(tournoi);
        } else {
        throw new ForbiddenExeption("Action interdite, tournoi en cours ou terminé");}
    }

    @Override
    public List<TournoiDTOReadAll> getAll() {
        return new HashSet<>(tournoiRepsoitory.findAll()
        )           .stream().map(TournoiDTOReadAll::fromEntity)
                    .filter(e -> e.statut() != Statut.TERMINE)
                    .sorted(Comparator.comparing(TournoiDTOReadAll::datemaj).reversed())
                    .limit(10)
                    .collect(Collectors.toList());
    }

    @Override
    public Tournoi getOneById(Integer id) {
        return tournoiRepsoitory.findById(id).orElseThrow(() -> new NotFoundException("tournoi pas trouvé"));
    }

}
