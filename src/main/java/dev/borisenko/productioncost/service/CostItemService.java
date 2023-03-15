package dev.borisenko.productioncost.service;

import dev.borisenko.productioncost.model.CostItem;
import dev.borisenko.productioncost.repository.CostItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CostItemService {
    private final CostItemRepo costItemRepo;

    public List<CostItem> getAll() {
        return costItemRepo.findAll();
    }

    public Optional<CostItem> getById(int id) {
        return costItemRepo.findById(id);
    }

    public boolean exist(int id) {
        return costItemRepo.existsById(id);
    }

    public void save(CostItem costItem) {
        costItemRepo.save(costItem);
    }

    public void delete(int id) {
        costItemRepo.deleteById(id);
    }
}
