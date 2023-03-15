package dev.borisenko.productioncost.service;

import dev.borisenko.productioncost.model.CostItem;
import dev.borisenko.productioncost.model.Type;
import dev.borisenko.productioncost.repository.TypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypeService {
    private final TypeRepo typeRepo;

    public List<Type> getAll() {
        return typeRepo.findAll();
    }

    public Optional<Type> getById(int id) {
        return typeRepo.findById(id);
    }

    public boolean exist(int id) {
        return typeRepo.existsById(id);
    }

    public void save(Type type) {
        typeRepo.save(type);
    }

    public void delete(int id) {
        typeRepo.deleteById(id);
    }
}
