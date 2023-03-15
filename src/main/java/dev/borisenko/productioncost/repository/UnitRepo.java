package dev.borisenko.productioncost.repository;

import dev.borisenko.productioncost.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepo extends JpaRepository<Unit, Integer> {
}
