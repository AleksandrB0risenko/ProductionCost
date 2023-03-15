package dev.borisenko.productioncost.controller.impl;

import dev.borisenko.productioncost.controller.UnitController;
import dev.borisenko.productioncost.model.Unit;
import dev.borisenko.productioncost.payload.response.MessageResponse;
import dev.borisenko.productioncost.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UnitControllerImpl implements UnitController {
    private final UnitService unitService;
    private final String ERR_MSG = "Не удалось найти единицу измерения, id: ";
    private final String SUC_MSG = "Данные единицы измерения успешно обновлены";

    @Autowired
    public UnitControllerImpl(UnitService unitService) {
        this.unitService = unitService;
    }

    @Override
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(unitService.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(int id) {
        Optional<Unit> t = unitService.getById(id);

        if (t.isPresent()) {
            return new ResponseEntity<>(t.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<?> add(Unit unit) {
        unitService.save(unit);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(int id) {
        if (unitService.exist(id)) {
            unitService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<?> update(int id, Unit unit) {
        if (unitService.exist(id)) {
            unit.setId(id);
            unitService.save(unit);
            return new ResponseEntity<>(new MessageResponse(SUC_MSG), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NO_CONTENT);
        }
    }
}
