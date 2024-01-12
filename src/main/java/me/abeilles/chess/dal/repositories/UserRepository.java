package me.abeilles.chess.dal.repositories;

import me.abeilles.chess.dal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByLogin(String pseudo);
}
