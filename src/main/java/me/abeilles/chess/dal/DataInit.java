package me.abeilles.chess.dal;

import me.abeilles.chess.dal.entities.*;
import me.abeilles.chess.dal.repositories.TournoiRepsoitory;
import me.abeilles.chess.dal.repositories.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
import java.time.LocalDate;
import java.util.HashSet;

@Component
public class DataInit implements InitializingBean {
    private final UserRepository userRepository;
    private final TournoiRepsoitory tournoiRepsoitory;
    private final PasswordEncoder passwordEncoder;

    public DataInit(UserRepository userRepository, TournoiRepsoitory tournoiRepsoitory, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tournoiRepsoitory = tournoiRepsoitory;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        User user = new User();
        user.setPseudo("mate");
        user.setElo(3000);
        user.setGenre(Genre.GARCON);
        user.setEmail("mate@mate.com");
        HashSet<UserRole> roles = new HashSet<>();
        roles.add(UserRole.ADMIN);
        roles.add(UserRole.JOUEUR);
        user.setRoles(roles);
        user.setEnabled(true);
        user.setDateNaissance(LocalDate.parse("1990-01-01"));
        user.setPassword(passwordEncoder.encode("mate"));
        userRepository.save(user);

        User user2 = new User();
        user2.setPseudo("j1");
        user2.setElo(500);
        user2.setGenre(Genre.FILLE);
        user2.setEmail("j1@mate.com");
        HashSet<UserRole> roles2 = new HashSet<>();
        roles2.add(UserRole.JOUEUR);
        user2.setRoles(roles2);
        user2.setEnabled(true);
        user2.setDateNaissance(LocalDate.parse("1999-10-01"));
        user2.setPassword(passwordEncoder.encode("j1"));
        userRepository.save(user2);

        Tournoi tournoi = new Tournoi();
        tournoi.setNom("tournois un");
        tournoi.setLieu("forem");
        tournoi.setNbMinJoueurs(2);
        tournoi.setNbMaxJoueurs(3);
        tournoi.setEloMin(200);
        tournoi.setEloMax(3000);
        tournoi.setCategorie(Categorie.SENIOR);
        tournoi.setDateFinInscriptions(LocalDate.parse("2024-10-18"));
        tournoi.setWomenOnly(false);
        tournoiRepsoitory.save(tournoi);

    }
}
