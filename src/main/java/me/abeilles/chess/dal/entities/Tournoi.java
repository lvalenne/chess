package me.abeilles.chess.dal.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tournoi", schema = "public")
public class Tournoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @Size(max = 255)
    @Column(name = "lieu")
    private String lieu;

    @Column(name = "nb_min_joueurs")
    private Integer nbMinJoueurs;

    @Column(name = "nb_max_joueurs")
    private Integer nbMaxJoueurs;

    @Column(name = "elo_min")
    private Integer eloMin;

    @Column(name = "elo_max")
    private Integer eloMax;

    @Size(max = 255)
    @NotNull
    @Column(name = "categories", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Set<Categorie> categories;

    @Size(max = 255)
    @NotNull
    @Column(name = "statut")
    private String statut;

    @Column(name = "ronde_courante")
    private Integer rondeCourante;

    @Column(name = "women_only")
    private Boolean womenOnly;

    @NotNull
    @Column(name = "date_fin_inscriptions", nullable = false)
    private LocalDate dateFinInscriptions;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "date_maj")
    private LocalDate dateMaj;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "tournoi_id")
    private Set<Rencontre> rencontres = new LinkedHashSet<>();

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "tournoi_id")
    private Set<Inscription> inscriptions = new LinkedHashSet<>();

}