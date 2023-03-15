package dev.borisenko.productioncost.controller.impl;

import dev.borisenko.productioncost.controller.WarehouseController;
import dev.borisenko.productioncost.model.Warehouse;
import dev.borisenko.productioncost.payload.response.MessageResponse;
import dev.borisenko.productioncost.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class WarehouseControllerImpl implements WarehouseController {
    private final WarehouseService warehouseService;
    private final String ERR_MSG = "Не удалось найти склад, id: ";
    private final String SUC_MSG = "Данные склада успешно обновлены";

    @Autowired
    public WarehouseControllerImpl(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Override
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(warehouseService.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(int id) {
        Optional<Warehouse> t = warehouseService.getById(id);

        if (t.isPresent()) {
            return new ResponseEntity<>(t.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> add(Warehouse warehouse) {
        warehouseService.save(warehouse);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(int id) {
        if (warehouseService.exist(id)) {
            warehouseService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> update(int id, Warehouse warehouse) {
        if (warehouseService.exist(id)) {
            warehouse.setId(id);
            warehouseService.save(warehouse);
            return new ResponseEntity<>(new MessageResponse(SUC_MSG), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NOT_FOUND);
        }
    }
}
