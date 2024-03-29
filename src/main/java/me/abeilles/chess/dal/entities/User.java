package me.abeilles.chess.dal.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "joueur", schema = "public", indexes = {
        @Index(name = "joueur_pseudo_key", columnList = "pseudo", unique = true),
        @Index(name = "joueur_email_key", columnList = "email", unique = true)
})
public class User implements UserDetails {
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


    @NotNull
    @Column(name = "genre", nullable = false, length = 10)
    @Enumerated(value = EnumType.STRING)
    Genre genre;

    @Column(name = "elo")
    @Min(value = 0, message = "Le chiffre ne peut pas être inférieur à 0")
    @Max(value = 3000, message = "Le chiffre ne peut pas être supérieur à 3000")

    private Integer elo = 1200;

    //@Size(max = 20)
    @NotNull
    @Column(name = "roles", nullable = false, length = 20)
    @Enumerated(value = EnumType.STRING)
    private Set<UserRole> roles;

    private boolean enabled;
    private boolean deleted;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Score> scores = new LinkedHashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role))
                .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return pseudo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }



    private static String generateRandomPassword() {

        // Génère une chaîne de caractères aléatoire
        String randomString = RandomStringUtils.randomAlphanumeric(16);

        return randomString;
    }

    private static String sendMailPasswordAndSetPassword(){
        return null;
    }

}