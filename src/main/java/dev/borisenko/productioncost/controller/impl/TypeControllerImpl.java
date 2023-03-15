package dev.borisenko.productioncost.controller.impl;

import dev.borisenko.productioncost.controller.TypeController;
import dev.borisenko.productioncost.model.Type;
import dev.borisenko.productioncost.payload.response.MessageResponse;
import dev.borisenko.productioncost.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TypeControllerImpl implements TypeController {
    private final TypeService typeService;
    private final String ERR_MSG = "Не удалось найти тип, id: ";
    private final String SUC_MSG = "Данные типа успешно обновлены";

    @Autowired
    public TypeControllerImpl(TypeService typeService) {
        this.typeService = typeService;
    }

    @Override
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(typeService.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(int id) {
        Optional<Type> t = typeService.getById(id);

        if (t.isPresent()) {
            return new ResponseEntity<>(t.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> add(Type type) {
        typeService.save(type);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(int id) {
        if (typeService.exist(id)) {
            typeService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> update(int id, Type type) {
        if (typeService.exist(id)) {
            type.setId(id);
            typeService.save(type);
            return new ResponseEntity<>(new MessageResponse(SUC_MSG), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NOT_FOUND);
        }
    }
}
