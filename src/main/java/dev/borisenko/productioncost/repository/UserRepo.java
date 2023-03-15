package dev.borisenko.productioncost.repository;

import dev.borisenko.productioncost.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String login);

    User getByUsername(String username);
}
