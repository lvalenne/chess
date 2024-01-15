package me.abeilles.chess.dal.repositories;

import me.abeilles.chess.dal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByPseudo(String pseudo);

    Optional<User> findByPseudoOrEmail(String pseudo, String Email);


}
