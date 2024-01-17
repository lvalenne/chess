package me.abeilles.chess.dal.repositories;

import me.abeilles.chess.dal.entities.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {

    Optional<Inscription> findByUserPseudo(String pseudo);


    Optional<Inscription> findByUserPseudoAndTournoiId(String pseudo, Integer id);
}
