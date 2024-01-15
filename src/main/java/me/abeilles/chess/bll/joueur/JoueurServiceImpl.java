package me.abeilles.chess.bll.joueur;


import me.abeilles.chess.bll.exceptions.NotFoundException;
import me.abeilles.chess.dal.entities.User;
import me.abeilles.chess.dal.repositories.UserRepository;
import me.abeilles.chess.pl.model.joueur.JoueurDTO;
import me.abeilles.chess.pl.model.joueur.JoueurForm;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
//@SpringBootApplication(scanBasePackages={"me.abeilles.chess.dal.entities"})

public class JoueurServiceImpl implements JoueurService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //  @Autowired
    //private final JPAStreamer jpaStreamer;

    public JoueurServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void create(JoueurForm form) {
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
    public void delete(Integer id) {
        User joueur = getOneById(id);
        joueur.setDeleted(true);
        joueur.setEnabled(false);
        userRepository.save(joueur);
    }

    @Override
    public User getOneById(Integer id) {

       return userRepository.findById(id).orElseThrow(()-> new NotFoundException("Propriétaire non trouvé"));
    }

    @Override
    public void update(Integer id, JoueurForm form) {
        if (form == null) throw new IllegalArgumentException("le formulaire ne peut etre vide");
        User joueur = userRepository.findById(id).orElseThrow(() -> new NotFoundException("joueur pas trouvé"));
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
    public Set<JoueurDTO> getAll() {

        return new HashSet<>(userRepository.findAll().stream().map(JoueurDTO::fromEntity).collect(Collectors.toSet()));
    }

    @Override
    public Set<JoueurDTO> getAllActif() {
        //return userRepository.findAll().stream().map(JoueurDTO::fromEntity).filter(e -> e.enabled() == true).collect(Collectors.toSet());
        return getAllEnabled().stream().filter(e -> e.enabled() == true).collect(Collectors.toSet());
    }

    @Override
    public Set<JoueurDTO> getAllEnabled() {
        return userRepository.findAll().stream().map(JoueurDTO::fromEntity).filter(e -> e.deleted() == false).collect(Collectors.toSet());
    }


}
