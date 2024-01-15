package me.abeilles.chess.bll.joueur;

import me.abeilles.chess.dal.entities.User;
import me.abeilles.chess.dal.repositories.UserRepository;
import me.abeilles.chess.pl.model.joueur.JoueurForm;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service

public class JoueurServiceImpl implements JoueurService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public JoueurServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void create(JoueurForm form, Authentication authentication) {
        if (form == null) throw new IllegalArgumentException("le formulaire ne peut etre vide");
        User joueur = new User();
        joueur.setPseudo(form.pseudo());
        joueur.setEmail(form.email());
        joueur.setPassword(passwordEncoder.encode(form.password()));
        joueur.setDateNaissance(form.dateNaissance());
        joueur.setGenre(form.genre());
        joueur.setElo(form.elo());
        joueur.setRoles(form.roles());
        joueur.setEnabled(form.enabled());
        userRepository.save(joueur);
    }

    @Override
    public void delete(Integer id, Authentication authentication) {

    }

    @Override
    public User getOneById(Integer id, Authentication authentication) {
        return null;
    }

    @Override
    public void update(Integer id, JoueurForm form, Authentication authentication) {

    }

    @Override
    public Set<User> getAll() {
        return null;
    }



}
