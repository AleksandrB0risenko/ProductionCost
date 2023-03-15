package dev.borisenko.productioncost.controller.impl;

import dev.borisenko.productioncost.controller.CostController;
import dev.borisenko.productioncost.model.Cost;
import dev.borisenko.productioncost.payload.response.MessageResponse;
import dev.borisenko.productioncost.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
public class CostControllerImpl implements CostController {
    private final CostService costService;
    private final String ERR_MSG = "Не удалось найти стоимость, id: ";
    private final String SUC_MSG = "Данные стоимости успешно обновлены";

    @Autowired
    public CostControllerImpl(CostService costService) {
        this.costService = costService;
    }

    @Override
    public ResponseEntity<?> getAll(int id) { // id статьи затрат
        List<Cost> costs = costService.findAllByCostItemId(id);
        costs.sort(Comparator.comparing(Cost::getCurrentDate));
        return new ResponseEntity<>(costService.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(int id) {
        Optional<Cost> t = costService.getById(id);

        if (t.isPresent()) {
            return new ResponseEntity<>(t.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> add(Cost cost) {
        costService.save(cost);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(int id) {
        if (costService.exist(id)) {
            costService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> update(int id, Cost cost) {
        if (costService.exist(id)) {
            cost.setId(id);
            costService.save(cost);
            return new ResponseEntity<>(new MessageResponse(SUC_MSG), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NOT_FOUND);
        }
    }
}
