package me.abeilles.chess.dal.repositories;

import me.abeilles.chess.dal.entities.Rencontre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RencontreRepository extends JpaRepository<Rencontre,Integer> {
}
