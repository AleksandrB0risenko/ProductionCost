package dev.borisenko.productioncost.service;

import dev.borisenko.productioncost.model.Unit;
import dev.borisenko.productioncost.model.Warehouse;
import dev.borisenko.productioncost.repository.WarehouseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseRepo warehouseRepo;

    public List<Warehouse> getAll() {
        return warehouseRepo.findAll();
    }

    public Optional<Warehouse> getById(int id) {
        return warehouseRepo.findById(id);
    }

    public boolean exist(int id) {
        return warehouseRepo.existsById(id);
    }

    public void save(Warehouse warehouse) {
        warehouseRepo.save(warehouse);
    }

    public void delete(int id) {
        warehouseRepo.deleteById(id);
    }
}
