package dev.borisenko.productioncost.repository;

import dev.borisenko.productioncost.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepo extends JpaRepository<Type, Integer> {
}
