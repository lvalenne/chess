/*package me.abeilles.chess.dal;

import me.abeilles.chess.dal.entities.*;
import me.abeilles.chess.dal.repositories.InscriptionRepository;
import me.abeilles.chess.dal.repositories.TournoiRepsoitory;
import me.abeilles.chess.dal.repositories.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;

@Component
public class DataInit implements InitializingBean {
    private final UserRepository userRepository;
    private final TournoiRepsoitory tournoiRepsoitory;
    private final InscriptionRepository inscriptionRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInit(UserRepository userRepository, TournoiRepsoitory tournoiRepsoitory, InscriptionRepository inscriptionRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tournoiRepsoitory = tournoiRepsoitory;
        this.inscriptionRepository = inscriptionRepository;
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
        user2.setDateNaissance(LocalDate.parse("2000-10-01"));
        user2.setPassword(passwordEncoder.encode("j1"));
        userRepository.save(user2);

        User user3 = new User();
        user3.setPseudo("j2");
        user3.setElo(500);
        user3.setGenre(Genre.FILLE);
        user3.setEmail("j2@mate.com");
        HashSet<UserRole> roles3 = new HashSet<>();
        roles3.add(UserRole.JOUEUR);
        user3.setRoles(roles3);
        user3.setEnabled(true);
        user3.setDateNaissance(LocalDate.parse("2000-10-01"));
        user3.setPassword(passwordEncoder.encode("j2"));
        userRepository.save(user3);

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

        Tournoi tournoi2 = new Tournoi();
        tournoi2.setNom("tournoi deux");
        tournoi2.setLieu("forem");
        tournoi2.setNbMinJoueurs(2);
        tournoi2.setNbMaxJoueurs(3);
        tournoi2.setEloMin(100);
        tournoi2.setEloMax(3000);
        tournoi2.setCategorie(Categorie.SENIOR);
        tournoi2.setDateFinInscriptions(LocalDate.parse("2024-10-30"));
        tournoi2.setWomenOnly(true);
        tournoiRepsoitory.save(tournoi2);

        Tournoi tournoi3 = new Tournoi();
        tournoi3.setNom("tournoi 3");
        tournoi3.setLieu("forem");
        tournoi3.setNbMinJoueurs(10);
        tournoi3.setNbMaxJoueurs(15);
        tournoi3.setEloMin(500);
        tournoi3.setEloMax(3000);
        tournoi3.setCategorie(Categorie.VETERAN);
        tournoi3.setDateFinInscriptions(LocalDate.parse("2024-10-30"));
        tournoi3.setWomenOnly(false);
        tournoiRepsoitory.save(tournoi3);

        Tournoi tournoi4 = new Tournoi();
        tournoi4.setNom("tournoi 4");
        tournoi4.setLieu("forem");
        tournoi4.setNbMinJoueurs(2);
        tournoi4.setNbMaxJoueurs(3);
        tournoi4.setEloMin(500);
        tournoi4.setEloMax(3000);
        tournoi4.setCategorie(Categorie.SENIOR);
        tournoi4.setDateFinInscriptions(LocalDate.parse("2024-10-30"));
        tournoi4.setWomenOnly(false);
        tournoiRepsoitory.save(tournoi4);

        Tournoi tournoi5 = new Tournoi();
        tournoi5.setNom("tournoi 5");
        tournoi5.setLieu("forem");
        tournoi5.setNbMinJoueurs(2);
        tournoi5.setNbMaxJoueurs(3);
        tournoi5.setEloMin(500);
        tournoi5.setEloMax(3000);
        tournoi5.setCategorie(Categorie.VETERAN);
        tournoi5.setDateFinInscriptions(LocalDate.parse("2024-10-30"));
        tournoi5.setWomenOnly(false);
        tournoiRepsoitory.save(tournoi5);

        Tournoi tournoi6 = new Tournoi();
        tournoi6.setNom("tournoi 6");
        tournoi6.setLieu("forem");
        tournoi6.setNbMinJoueurs(10);
        tournoi6.setNbMaxJoueurs(20);
        tournoi6.setEloMin(500);
        tournoi6.setEloMax(3000);
        tournoi6.setCategorie(Categorie.VETERAN);
        tournoi6.setDateFinInscriptions(LocalDate.parse("2024-10-30"));
        tournoi6.setWomenOnly(false);
        tournoiRepsoitory.save(tournoi6);

        Tournoi tournoi7 = new Tournoi();
        tournoi7.setNom("tournoi 7");
        tournoi7.setLieu("forem");
        tournoi7.setNbMinJoueurs(10);
        tournoi7.setNbMaxJoueurs(20);
        tournoi7.setEloMin(500);
        tournoi7.setEloMax(3000);
        tournoi7.setCategorie(Categorie.VETERAN);
        tournoi7.setDateFinInscriptions(LocalDate.parse("2024-10-30"));
        tournoi7.setWomenOnly(false);
        tournoiRepsoitory.save(tournoi7);

        Tournoi tournoi8 = new Tournoi();
        tournoi8.setNom("tournoi 8");
        tournoi8.setLieu("forem");
        tournoi8.setNbMinJoueurs(10);
        tournoi8.setNbMaxJoueurs(20);
        tournoi8.setEloMin(500);
        tournoi8.setEloMax(3000);
        tournoi8.setCategorie(Categorie.VETERAN);
        tournoi8.setDateFinInscriptions(LocalDate.parse("2024-10-30"));
        tournoi8.setWomenOnly(false);
        tournoiRepsoitory.save(tournoi8);

        Tournoi tournoi9 = new Tournoi();
        tournoi9.setNom("tournoi 9");
        tournoi9.setLieu("forem");
        tournoi9.setNbMinJoueurs(10);
        tournoi9.setNbMaxJoueurs(20);
        tournoi9.setEloMin(500);
        tournoi9.setEloMax(3000);
        tournoi9.setCategorie(Categorie.VETERAN);
        tournoi9.setDateFinInscriptions(LocalDate.parse("2024-10-30"));
        tournoi9.setWomenOnly(false);
        tournoiRepsoitory.save(tournoi9);

        Tournoi tournoi10 = new Tournoi();
        tournoi10.setNom("tournoi 10");
        tournoi10.setLieu("forem");
        tournoi10.setNbMinJoueurs(10);
        tournoi10.setNbMaxJoueurs(20);
        tournoi10.setEloMin(500);
        tournoi10.setEloMax(3000);
        tournoi10.setCategorie(Categorie.VETERAN);
        tournoi10.setDateFinInscriptions(LocalDate.parse("2024-10-30"));
        tournoi10.setWomenOnly(false);
        tournoiRepsoitory.save(tournoi10);

        Tournoi tournoi11 = new Tournoi();
        tournoi11.setNom("tournoi 11");
        tournoi11.setLieu("forem");
        tournoi11.setNbMinJoueurs(10);
        tournoi11.setNbMaxJoueurs(20);
        tournoi11.setEloMin(500);
        tournoi11.setEloMax(3000);
        tournoi11.setCategorie(Categorie.VETERAN);
        tournoi11.setDateFinInscriptions(LocalDate.parse("2024-10-30"));
        tournoi11.setWomenOnly(false);
        tournoiRepsoitory.save(tournoi11);

        Tournoi tournoi12 = new Tournoi();
        tournoi12.setNom("tournoi 12");
        tournoi12.setLieu("forem");
        tournoi12.setNbMinJoueurs(10);
        tournoi12.setNbMaxJoueurs(20);
        tournoi12.setEloMin(500);
        tournoi12.setEloMax(3000);
        tournoi12.setCategorie(Categorie.VETERAN);
        tournoi12.setDateFinInscriptions(LocalDate.parse("2024-10-30"));
        tournoi12.setWomenOnly(false);
        tournoiRepsoitory.save(tournoi12);

        Tournoi tournoi13 = new Tournoi();
        tournoi13.setNom("tournoi 13");
        tournoi13.setLieu("forem");
        tournoi13.setNbMinJoueurs(2);
        tournoi13.setNbMaxJoueurs(20);
        tournoi13.setEloMin(500);
        tournoi13.setEloMax(3000);
        tournoi13.setCategorie(Categorie.SENIOR);
        tournoi13.setDateCreation(LocalDateTime.now().minusDays(tournoi13.getNbMinJoueurs()));
        tournoi13.setDateFinInscriptions(LocalDate.now().plusDays(2));
        tournoi13.setWomenOnly(false);
        tournoiRepsoitory.save(tournoi13);

        Inscription inscription = new Inscription();
        inscription.setTournoi(tournoi13);
        inscription.setUser(user2);
        tournoi13.setNombreInscriptions(tournoi13.getNombreInscriptions()+1);
        inscriptionRepository.save(inscription);
        tournoiRepsoitory.save(tournoi13);

        Inscription inscription2 = new Inscription();
        inscription2.setTournoi(tournoi13);
        inscription2.setUser(user3);
        tournoi13.setNombreInscriptions(tournoi13.getNombreInscriptions()+1);
        inscriptionRepository.save(inscription2);
        tournoiRepsoitory.save(tournoi13);


    }

}
*/