package dev.borisenko.productioncost.controller.impl;

import dev.borisenko.productioncost.controller.StatusController;
import dev.borisenko.productioncost.model.Cost;
import dev.borisenko.productioncost.model.Status;
import dev.borisenko.productioncost.payload.response.MessageResponse;
import dev.borisenko.productioncost.repository.CostRepo;
import dev.borisenko.productioncost.repository.StatusRepo;
import dev.borisenko.productioncost.service.CostService;
import dev.borisenko.productioncost.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class StatusControllerImpl implements StatusController {
    private final StatusService statusService;
    private final String ERR_MSG = "Не удалось найти статус, id: ";
    private final String SUC_MSG = "Данные статуса успешно обновлены";

    @Autowired
    public StatusControllerImpl(StatusService statusService) {
        this.statusService = statusService;
    }

    @Override
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(statusService.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(int id) {
        Optional<Status> t = statusService.getById(id);

        if (t.isPresent()) {
            return new ResponseEntity<>(t.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<?> add(Status status) {
        statusService.save(status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(int id) {
        if (statusService.exist(id)) {
            statusService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<?> update(int id, Status status) {
        if (statusService.exist(id)) {
            status.setId(id);
            statusService.save(status);
            return new ResponseEntity<>(new MessageResponse(SUC_MSG), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NO_CONTENT);
        }
    }
}
