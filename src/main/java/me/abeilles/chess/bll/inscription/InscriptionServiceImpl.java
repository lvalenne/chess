package me.abeilles.chess.bll.inscription;

import me.abeilles.chess.bll.exceptions.InscriptionException;
import me.abeilles.chess.bll.exceptions.NotFoundException;
import me.abeilles.chess.dal.entities.*;
import me.abeilles.chess.dal.repositories.InscriptionRepository;
import me.abeilles.chess.dal.repositories.TournoiRepsoitory;
import me.abeilles.chess.dal.repositories.UserRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.Period;

@Service
public class InscriptionServiceImpl implements InscriptionService {
    private final UserRepository userRepository;
    private final TournoiRepsoitory tournoiRepsoitory;
    private final InscriptionRepository inscriptionRepository;

    public InscriptionServiceImpl(UserRepository userRepository,
                                  TournoiRepsoitory tournoiRepsoitory,
                                  InscriptionRepository inscriptionRepository) {
        this.userRepository = userRepository;
        this.tournoiRepsoitory = tournoiRepsoitory;
        this.inscriptionRepository = inscriptionRepository;
    }


    @Override
    public void inscriptionTournoi(Integer id, String pseudo) {
        Tournoi tournoi = tournoiRepsoitory.findById(id).orElseThrow(() -> new NotFoundException("tournoi pas trouvé"));
        User user = userRepository.findByPseudo(pseudo).orElseThrow(() -> new NotFoundException("utilisateur pas trouvé"));
        Inscription inscription = new Inscription();

        int age = Period.between(user.getDateNaissance(), tournoi.getDateFinInscriptions()).getYears();

        if (tournoi.getDateFinInscriptions().isBefore(LocalDate.now())) {
            throw new InscriptionException("Date d'inscription dépassée");
        } else if (tournoi.getStatut() != Statut.EN_ATTENTE_DE_JOUEURS) {
            throw new InscriptionException("Le trounoi n'est pas en cours");

        } else if (tournoi.getNombreInscriptions() >= tournoi.getNbMaxJoueurs()) {
            throw new InscriptionException("Le nombre maximum d'inscription est atteint");

        } else if (tournoi.getCategorie().equals(Categorie.JUNIOR) && age > 18) {
            throw new InscriptionException("Tournoi réservé au moins de 18 ans");

        } else if (tournoi.getCategorie().equals(Categorie.SENIOR) && (age < 18 || age >= 60)) {
            throw new InscriptionException("Tournoi réseervé aux senior");

        } else if (tournoi.getCategorie().equals(Categorie.VETERAN) && age < 60) {
            throw new InscriptionException("Il faut au moins 60 ans pour participer");

        } else if ((user.getElo() != null) && ((tournoi.getEloMin() > user.getElo() || tournoi.getEloMax() < user.getElo()))) {
            throw new InscriptionException("votre nniveau ne corespond pas a celui du tournoi");

        } else if (tournoi.getWomenOnly() && user.getGenre().equals(Genre.GARCON)) {
            throw new InscriptionException("tournoi réservé aux filles");

        }

        tournoi.setNombreInscriptions(tournoi.getNombreInscriptions() + 1);
        inscription.setDateInscription(LocalDate.now());
        inscription.setTournoi(tournoi);
        inscription.setUser(user);


        inscriptionRepository.save(inscription);
        tournoiRepsoitory.save(tournoi);

    }

    @Override
    public void supprimerInscriptionTournoi(Integer id, String pseudo) {
        Tournoi tournoi = tournoiRepsoitory.findById(id).orElseThrow(() -> new NotFoundException("tournoi pas trouvé"));
        User user = userRepository.findByPseudo(pseudo).orElseThrow(() -> new NotFoundException("utilisateur pas trouvé"));
        Inscription inscription = inscriptionRepository.findByUserPseudoAndTournoiId(pseudo, id).orElseThrow(() -> new NotFoundException("inscription pas trouvé"));

        if (tournoi.getStatut() != (Statut.EN_ATTENTE_DE_JOUEURS)) {
            throw new InscriptionException("le tournoi est déjà en cours ou tyerminé");

        }
        tournoi.setNombreInscriptions(tournoi.getNombreInscriptions() -1);
        inscriptionRepository.delete(inscription);
        tournoiRepsoitory.save(tournoi);
    }
}
