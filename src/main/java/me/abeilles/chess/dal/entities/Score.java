package me.abeilles.chess.dal.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "score", schema = "public")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_joueur")
    private User idJoueur;

    @Column(name = "numero_ronde")
    private Integer numeroRonde;

    @Column(name = "rencontres_jouees")
    private Integer rencontresJouees;

    @Column(name = "victoires")
    private Integer victoires;

    @Column(name = "defaites")
    private Integer defaites;

    @Column(name = "egalites")
    private Integer egalites;

    @Column(name = "score", precision = 3, scale = 1)
    private BigDecimal score;

}