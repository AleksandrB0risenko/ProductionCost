package dev.borisenko.productioncost.service;

import dev.borisenko.productioncost.model.CostItem;
import dev.borisenko.productioncost.model.Department;
import dev.borisenko.productioncost.model.Product;
import dev.borisenko.productioncost.repository.CostItemRepo;
import dev.borisenko.productioncost.repository.DepartmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepo departmentRepo;

    public List<Department> getAll() {
        return departmentRepo.findAll();
    }

    public Optional<Department> getById(int id) {
        return departmentRepo.findById(id);
    }

    public boolean exist(int id) {
        return departmentRepo.existsById(id);
    }

    public void save(Department department) {
        departmentRepo.save(department);
    }

    public void delete(int id) {
        departmentRepo.deleteById(id);
    }
}
