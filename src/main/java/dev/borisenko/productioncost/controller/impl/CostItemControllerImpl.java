package dev.borisenko.productioncost.controller.impl;

import dev.borisenko.productioncost.controller.CostItemController;
import dev.borisenko.productioncost.model.CostItem;
import dev.borisenko.productioncost.payload.response.MessageResponse;
import dev.borisenko.productioncost.service.CostItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CostItemControllerImpl implements CostItemController {
    private final CostItemService costItemService;
    private final String ERR_MSG = "Не удалось найти статью затрат, id: ";
    private final String SUC_MSG = "Данные статьи затрат успешно обновлены";

    @Autowired
    public CostItemControllerImpl(CostItemService costItemService) {
        this.costItemService = costItemService;
    }

    @Override
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(costItemService.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(int id) {
        Optional<CostItem> t = costItemService.getById(id);

        if (t.isPresent()) {
            return new ResponseEntity<>(t.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> add(CostItem costItem) {
        costItemService.save(costItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(int id) {
        if (costItemService.exist(id)) {
            costItemService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> update(int id, CostItem costItem) {
        if (costItemService.exist(id)) {
            costItem.setId(id);
            costItemService.save(costItem);
            return new ResponseEntity<>(new MessageResponse(SUC_MSG), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NOT_FOUND);
        }
    }
}
