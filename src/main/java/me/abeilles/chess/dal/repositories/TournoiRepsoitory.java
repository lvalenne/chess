package me.abeilles.chess.dal.repositories;

import me.abeilles.chess.dal.entities.Tournoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournoiRepsoitory extends JpaRepository<Tournoi,Integer> {
}
