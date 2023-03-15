package dev.borisenko.productioncost.repository;

import dev.borisenko.productioncost.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepo extends JpaRepository<Warehouse, Integer> {
}
