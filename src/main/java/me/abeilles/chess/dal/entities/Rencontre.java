package me.abeilles.chess.dal.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rencontre", schema = "public")
public class Rencontre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_joueur_blanc")
    private User idJoueurBlanc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_joueur_noir")
    private User idJoueurNoir;

    @Column(name = "numero_ronde")
    private Integer numeroRonde;

    @Column(name = "resultat")
    @Enumerated(value = EnumType.STRING)
    private Resultat resultat;


}