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

        Tournoi tournoi = new Tournoi();
        tournoi.setNom("tournois un");
        tournoi.setLieu("forem");
        tournoi.setNbMinJoueurs(2);
        tournoi.setNbMaxJoueurs(3);
        tournoi.setEloMin(0);
        tournoi.setEloMax(3000);
        tournoi.setCategorie(Categorie.SENIOR);
        tournoi.setWomenOnly(false);
        tournoiRepsoitory.save(tournoi);

    }
}
