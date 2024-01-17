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
public class InscriptionServiceImpl {
    private final UserRepository userRepository;
    private final TournoiRepsoitory tournoiRepsoitory;

    private final InscriptionRepository inscriptionRepository;

    public InscriptionServiceImpl(UserRepository userRepository, TournoiRepsoitory tournoiRepsoitory, InscriptionRepository inscriptionRepository) {
        this.userRepository = userRepository;
        this.tournoiRepsoitory = tournoiRepsoitory;
        this.inscriptionRepository = inscriptionRepository;
    }

    public void inscriptionTournoi(Integer id, String pseudo){
        Tournoi tournoi = tournoiRepsoitory.findById(id).orElseThrow(() -> new NotFoundException("tournoi pas trouvé"));
        User user = userRepository.findByPseudo(pseudo).orElseThrow(() -> new NotFoundException("utilisateur pas trouvé"));
        Inscription inscription = new Inscription();

        int age = Period.between(user.getDateNaissance(), tournoi.getDateFinInscriptions()).getYears();

        if(tournoi.getDateFinInscriptions().isBefore(LocalDate.now())){new InscriptionException("Date d'inscription dépassée");
        } else if (tournoi.getStatut() != Statut.EN_COURS) {new InscriptionException("Le trounoi n'est pas en cours");

        } else if (tournoi.getNombreInscriptions() >= tournoi.getNbMaxJoueurs()) {new InscriptionException("Le nombre maximum d'inscription est atteint");

        } else if (tournoi.getCategorie().equals(Categorie.JUNIOR) && age > 18) {new InscriptionException("Tournoi réservé au mooins de 18 ans");

        } else if (tournoi.getCategorie().equals(Categorie.SENIOR) && (age < 18 || age >= 60)) {new InscriptionException("Tournoi réseervé aux senior");

        } else if (tournoi.getCategorie().equals(Categorie.VETERAN) && age < 60) {new InscriptionException("Il faut au moins 60 ans pour participer");

        } else if ((user.getElo() != null) && ((tournoi.getEloMin() > user.getElo() || tournoi.getEloMax() < user.getElo()))){new InscriptionException("votre nniveau ne corespond pas a celui du tournoi");

        } else if (tournoi.getWomenOnly() && ((user.getGenre().equals(Genre.GARCON)) || user.getGenre().equals(Genre.AUTRE))) {new InscriptionException("tournoi réservé aux fiklkles");

        }
        inscription.setDateInscription(LocalDate.now());
        inscription.setTournoi(tournoi);
        inscription.setUser(user);

        inscriptionRepository.save(inscription);



    }
}
