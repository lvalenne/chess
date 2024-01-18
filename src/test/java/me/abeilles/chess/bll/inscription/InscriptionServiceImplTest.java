package me.abeilles.chess.bll.inscription;
import me.abeilles.chess.bll.exceptions.InscriptionException;
import me.abeilles.chess.bll.exceptions.NotFoundException;
import me.abeilles.chess.dal.entities.*;
import me.abeilles.chess.dal.repositories.InscriptionRepository;
import me.abeilles.chess.dal.repositories.TournoiRepsoitory;
import me.abeilles.chess.dal.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import me.abeilles.chess.dal.entities.*;
import me.abeilles.chess.dal.repositories.InscriptionRepository;
import me.abeilles.chess.dal.repositories.TournoiRepsoitory;
import me.abeilles.chess.dal.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class InscriptionServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TournoiRepsoitory tournoiRepsoitory;

    @Mock
    private InscriptionRepository inscriptionRepository;

    @InjectMocks
    private InscriptionServiceImpl inscriptionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInscriptionTournoi() {
        Tournoi tournoi = new Tournoi();
        tournoi.setId(1);
        tournoi.setNbMinJoueurs(2);
        tournoi.setDateFinInscriptions(LocalDate.now().plusDays(4));
        tournoi.setStatut(Statut.EN_ATTENTE_DE_JOUEURS);
        tournoi.setNombreInscriptions(0);
        tournoi.setNbMaxJoueurs(10);
        tournoi.setCategorie(Categorie.JUNIOR);
        tournoi.setEloMin(0);
        tournoi.setEloMax(2000);
        tournoi.setWomenOnly(false);

        User user = new User();
        user.setPseudo("JohnDoe");
        user.setDateNaissance(LocalDate.of(2000, 1, 1));
        user.setElo(1500);
        user.setGenre(Genre.GARCON);

        Inscription inscription = new Inscription();
        inscription.setDateInscription(LocalDate.now());
        inscription.setTournoi(tournoi);
        inscription.setUser(user);

        when(userRepository.findByPseudo("JohnDoe")).thenReturn(Optional.of(user));
        when(tournoiRepsoitory.findById(1)).thenReturn(Optional.of(tournoi));
        when(inscriptionRepository.save(any(Inscription.class))).thenReturn(inscription);

        assertDoesNotThrow(() -> inscriptionService.inscriptionTournoi(1, "JohnDoe"));
        assertEquals(1, tournoi.getNombreInscriptions());
    }

    @Test
    void testInscriptionTournoi_TournoiNotFound() {
        when(tournoiRepsoitory.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> inscriptionService.inscriptionTournoi(1, "JohnDoe"));
    }

    @Test
    void testInscriptionTournoi_UserNotFound() {
        when(userRepository.findByPseudo(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> inscriptionService.inscriptionTournoi(1, "JohnDoe"));
    }

    @Test
    void testInscriptionTournoi_DateInscriptionDepassee() {
        Tournoi tournoi = new Tournoi();
        tournoi.setId(1);
        tournoi.setDateFinInscriptions(LocalDate.now().minusDays(1));

        when(tournoiRepsoitory.findById(1)).thenReturn(Optional.of(tournoi));

        assertThrows(InscriptionException.class, () -> inscriptionService.inscriptionTournoi(1, "JohnDoe"));
    }

    @Test
    void testInscriptionTournoi_TournoiEnCours() {
        Tournoi tournoi = new Tournoi();
        tournoi.setId(1);
        tournoi.setStatut(Statut.EN_COURS);

        when(tournoiRepsoitory.findById(1)).thenReturn(Optional.of(tournoi));

        assertThrows(InscriptionException.class, () -> inscriptionService.inscriptionTournoi(1, "JohnDoe"));
    }

    @Test
    void testInscriptionTournoi_NombreMaxInscriptionsAtteint() {
        Tournoi tournoi = new Tournoi();
        tournoi.setId(1);
        tournoi.setNombreInscriptions(10);
        tournoi.setNbMaxJoueurs(10);

        when(tournoiRepsoitory.findById(1)).thenReturn(Optional.of(tournoi));

        assertThrows(InscriptionException.class, () -> inscriptionService.inscriptionTournoi(1, "JohnDoe"));
    }

    @Test
    void testInscriptionTournoi_CategorieJuniorAgeSup18() {
        Tournoi tournoi = new Tournoi();
        tournoi.setId(1);
        tournoi.setCategorie(Categorie.JUNIOR);

        User user = new User();
        user.setPseudo("JohnDoe");
        user.setDateNaissance(LocalDate.now().minusYears(19));

        when(userRepository.findByPseudo("JohnDoe")).thenReturn(Optional.of(user));
        when(tournoiRepsoitory.findById(1)).thenReturn(Optional.of(tournoi));

        assertThrows(InscriptionException.class, () -> inscriptionService.inscriptionTournoi(1, "JohnDoe"));
    }

    @Test
    void testInscriptionTournoi_CategorieSeniorAgeInf18() {
        Tournoi tournoi = new Tournoi();
        tournoi.setId(1);
        tournoi.setCategorie(Categorie.SENIOR);

        User user = new User();
        user.setPseudo("JohnDoe");
        user.setDateNaissance(LocalDate.now().minusYears(17));

        when(userRepository.findByPseudo("JohnDoe")).thenReturn(Optional.of(user));
        when(tournoiRepsoitory.findById(1)).thenReturn(Optional.of(tournoi));

        assertThrows(InscriptionException.class, () -> inscriptionService.inscriptionTournoi(1, "JohnDoe"));
    }

    @Test
    void testInscriptionTournoi_CategorieSeniorAgeSup60() {
        Tournoi tournoi = new Tournoi();
        tournoi.setId(1);
        tournoi.setCategorie(Categorie.SENIOR);

        User user = new User();
        user.setPseudo("JohnDoe");
        user.setDateNaissance(LocalDate.now().minusYears(65));

        when(userRepository.findByPseudo("JohnDoe")).thenReturn(Optional.of(user));
        when(tournoiRepsoitory.findById(1)).thenReturn(Optional.of(tournoi));

        assertThrows(InscriptionException.class, () -> inscriptionService.inscriptionTournoi(1, "JohnDoe"));
    }

    @Test
    void testInscriptionTournoi_CategorieVeteranAgeInf60() {
        Tournoi tournoi = new Tournoi();
        tournoi.setId(1);
        tournoi.setCategorie(Categorie.VETERAN);

        User user = new User();
        user.setPseudo("JohnDoe");
        user.setDateNaissance(LocalDate.now().minusYears(55));

        when(userRepository.findByPseudo("JohnDoe")).thenReturn(Optional.of(user));
        when(tournoiRepsoitory.findById(1)).thenReturn(Optional.of(tournoi));

        assertThrows(InscriptionException.class, () -> inscriptionService.inscriptionTournoi(1, "JohnDoe"));
    }

    @Test
    void testInscriptionTournoi_EloNotInRange() {
        Tournoi tournoi = new Tournoi();
        tournoi.setId(1);
        tournoi.setEloMin(1800);
        tournoi.setEloMax(2000);

        User user = new User();
        user.setPseudo("JohnDoe");
        user.setElo(1700);

        when(userRepository.findByPseudo("JohnDoe")).thenReturn(Optional.of(user));
        when(tournoiRepsoitory.findById(1)).thenReturn(Optional.of(tournoi));

        assertThrows(InscriptionException.class, () -> inscriptionService.inscriptionTournoi(1, "JohnDoe"));
    }

    @Test
    void testInscriptionTournoi_WomenOnlyButUserIsGarcon() {
        Tournoi tournoi = new Tournoi();
        tournoi.setId(1);
        tournoi.setWomenOnly(true);

        User user = new User();
        user.setPseudo("JohnDoe");
        user.setGenre(Genre.GARCON);

        when(userRepository.findByPseudo("JohnDoe")).thenReturn(Optional.of(user));
        when(tournoiRepsoitory.findById(1)).thenReturn(Optional.of(tournoi));

        assertThrows(InscriptionException.class, () -> inscriptionService.inscriptionTournoi(1, "JohnDoe"));
    }

    @Test
    void testSupprimerInscriptionTournoi() {
        Tournoi tournoi = new Tournoi();
        tournoi.setId(1);
        tournoi.setStatut(Statut.EN_ATTENTE_DE_JOUEURS);
        tournoi.setNombreInscriptions(1);

        User user = new User();
        user.setPseudo("JohnDoe");

        Inscription inscription = new Inscription();
        inscription.setTournoi(tournoi);
        inscription.setUser(user);

        when(userRepository.findByPseudo("JohnDoe")).thenReturn(Optional.of(user));
        when(tournoiRepsoitory.findById(1)).thenReturn(Optional.of(tournoi));
        when(inscriptionRepository.findByUserPseudoAndTournoiId("JohnDoe", 1)).thenReturn(Optional.of(inscription));

        assertDoesNotThrow(() -> inscriptionService.supprimerInscriptionTournoi(1, "JohnDoe"));
        assertEquals(0, tournoi.getNombreInscriptions());
        verify(inscriptionRepository, times(1)).delete(inscription);
    }

    @Test
    void testSupprimerInscriptionTournoi_TournoiEnCours() {
        Tournoi tournoi = new Tournoi();
        tournoi.setId(1);
        tournoi.setStatut(Statut.EN_COURS);

        when(tournoiRepsoitory.findById(1)).thenReturn(Optional.of(tournoi));

        assertThrows(InscriptionException.class, () -> inscriptionService.supprimerInscriptionTournoi(1, "JohnDoe"));
    }

    @Test
    void testSupprimerInscriptionTournoi_TournoiNotFound() {
        when(tournoiRepsoitory.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> inscriptionService.supprimerInscriptionTournoi(1, "JohnDoe"));
    }

    @Test
    void testSupprimerInscriptionTournoi_UserNotFound() {
        when(userRepository.findByPseudo(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> inscriptionService.supprimerInscriptionTournoi(1, "JohnDoe"));
    }

    @Test
    void testSupprimerInscriptionTournoi_InscriptionNotFound() {
        Tournoi tournoi = new Tournoi();
        tournoi.setId(1);
        tournoi.setStatut(Statut.EN_ATTENTE_DE_JOUEURS);

        User user = new User();
        user.setPseudo("JohnDoe");

        when(userRepository.findByPseudo("JohnDoe")).thenReturn(Optional.of(user));
        when(tournoiRepsoitory.findById(1)).thenReturn(Optional.of(tournoi));
        when(inscriptionRepository.findByUserPseudoAndTournoiId("JohnDoe", 1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> inscriptionService.supprimerInscriptionTournoi(1, "JohnDoe"));
    }
}
