package dev.borisenko.productioncost.repository;

import dev.borisenko.productioncost.model.CostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostItemRepo extends JpaRepository<CostItem, Integer> {
    List<CostItem> findAllByTypeId(int typeId);
}
