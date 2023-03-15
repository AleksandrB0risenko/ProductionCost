package dev.borisenko.productioncost.service;

import dev.borisenko.productioncost.model.CostItem;
import dev.borisenko.productioncost.model.Unit;
import dev.borisenko.productioncost.repository.UnitRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnitService {
    private final UnitRepo unitRepo;

    public List<Unit> getAll() {
        return unitRepo.findAll();
    }

    public Optional<Unit> getById(int id) {
        return unitRepo.findById(id);
    }

    public boolean exist(int id) {
        return unitRepo.existsById(id);
    }

    public void save(Unit unit) {
        unitRepo.save(unit);
    }

    public void delete(int id) {
        unitRepo.deleteById(id);
    }
}
