package me.abeilles.chess.dal.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "joueur", schema = "public", indexes = {
        @Index(name = "joueur_pseudo_key", columnList = "pseudo", unique = true),
        @Index(name = "joueur_email_key", columnList = "email", unique = true)
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "pseudo", nullable = false)
    private String pseudo;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @Size(max = 10)
    @NotNull
    @Column(name = "genre", nullable = false, length = 10)
    @Enumerated
    Genre genre;

    @Column(name = "elo")
    private Integer elo;

    @Size(max = 20)
    @NotNull
    @Column(name = "role", nullable = false, length = 20)
    @Enumerated
    UserRole role;

}