package dev.borisenko.productioncost.controller.impl;

import dev.borisenko.productioncost.controller.DepartmentController;
import dev.borisenko.productioncost.model.Cost;
import dev.borisenko.productioncost.model.Department;
import dev.borisenko.productioncost.payload.response.MessageResponse;
import dev.borisenko.productioncost.repository.CostRepo;
import dev.borisenko.productioncost.repository.DepartmentRepo;
import dev.borisenko.productioncost.service.CostService;
import dev.borisenko.productioncost.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentControllerImpl implements DepartmentController {
    private final DepartmentService departmentService;
    private final String ERR_MSG = "Не удалось найти отдел, id: ";
    private final String SUC_MSG = "Данные отдела успешно обновлены";

    @Autowired
    public DepartmentControllerImpl(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(departmentService.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(int id) {
        Optional<Department> t = departmentService.getById(id);

        if (t.isPresent()) {
            return new ResponseEntity<>(t.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<?> add(Department department) {
        departmentService.save(department);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(int id) {
        if (departmentService.exist(id)) {
            departmentService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<?> update(int id, Department department) {
        if (departmentService.exist(id)) {
            department.setId(id);
            departmentService.save(department);
            return new ResponseEntity<>(new MessageResponse(SUC_MSG), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NO_CONTENT);
        }
    }
}
