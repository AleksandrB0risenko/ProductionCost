package dev.borisenko.productioncost.service;

import dev.borisenko.productioncost.model.CostItem;
import dev.borisenko.productioncost.model.Status;
import dev.borisenko.productioncost.repository.StatusRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatusService {
    private final StatusRepo statusRepo;

    public List<Status> getAll() {
        return statusRepo.findAll();
    }

    public Optional<Status> getById(int id) {
        return statusRepo.findById(id);
    }

    public boolean exist(int id) {
        return statusRepo.existsById(id);
    }

    public void save(Status status) {
        statusRepo.save(status);
    }

    public void delete(int id) {
        statusRepo.deleteById(id);
    }
}
