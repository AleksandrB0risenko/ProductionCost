package dev.borisenko.productioncost.repository;

import dev.borisenko.productioncost.model.Cost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostRepo extends JpaRepository<Cost, Integer> {
    List<Cost> findAllByCostItemId(int costItemId);
}
