package me.abeilles.chess.bll.tournoi;
import me.abeilles.chess.bll.exceptions.ForbiddenExeption;
import me.abeilles.chess.bll.exceptions.NotFoundException;
import me.abeilles.chess.bll.exceptions.TournoiException;
import me.abeilles.chess.dal.entities.*;
import me.abeilles.chess.dal.repositories.InscriptionRepository;
import me.abeilles.chess.dal.repositories.TournoiRepsoitory;
import me.abeilles.chess.pl.model.tournoi.TournoiDTO;
import me.abeilles.chess.pl.model.tournoi.TournoiDTOGetOne;
import me.abeilles.chess.pl.model.tournoi.TournoiDTOReadAll;
import me.abeilles.chess.pl.model.tournoi.TournoiFormCreate;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        Tournoi tournoi = tournoiRepsoitory.findById(id).orElseThrow(() -> new NotFoundException("tournoi pas trouvé"));
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
    public TournoiDTOGetOne getOneById(Integer id) {
        return TournoiDTOGetOne.fromEntity(tournoiRepsoitory.findById(id).orElseThrow(() -> new NotFoundException("tournoi pas trouvé")));
    }

    @Override
    public void lance(Integer id) {
        Tournoi tournoi = tournoiRepsoitory.findById(id).orElseThrow(() -> new NotFoundException("tournoi n'existe pas"));
        Rencontre rencontre = new Rencontre();
        Score score = new Score();

        if(tournoi.getNombreInscriptions() < tournoi.getNbMinJoueurs() && tournoi.getDateFinInscriptions().isBefore(LocalDate.now())){
            throw new TournoiException("Conditions pour demarer tournoi pas satisfaites");
        }
        tournoi.setRondeCourante(tournoi.getRondeCourante()+1);
        tournoi.setDateMaj(LocalDateTime.now());
        tournoiRepsoitory.save(tournoi);


    }

}
