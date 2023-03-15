package dev.borisenko.productioncost.controller.impl;

import dev.borisenko.productioncost.controller.CostItemToProductController;
import dev.borisenko.productioncost.model.CostItemToProduct;
import dev.borisenko.productioncost.payload.response.MessageResponse;
import dev.borisenko.productioncost.repository.CostItemToProductRepo;
import dev.borisenko.productioncost.service.CostItemToProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CostItemToProductControllerImpl implements CostItemToProductController {
    private final CostItemToProductService costItemToProductService;

    @Autowired
    public CostItemToProductControllerImpl(CostItemToProductService costItemToProductService) {
        this.costItemToProductService = costItemToProductService;
    }

    /*@Override
    public ResponseEntity<?> getAll() {
        List<CostItemToProduct> costItemToProducts = costItemToProductRepo.findAll();
        return new ResponseEntity<>(costItemToProducts, HttpStatus.OK);
    }*/

    @Override
    public ResponseEntity<?> add(CostItemToProduct costItemToProduct) {
        costItemToProductService.save(costItemToProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(int id) {
        return new ResponseEntity<>(costItemToProductService.findAllByProductId(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(int id) {
        //costItemToProductService.delete(id);
        return new ResponseEntity<>(new MessageResponse("Удаление"), HttpStatus.OK);
    }

    /*@Override
    public ResponseEntity<?> update(int id, List<CostItemToProduct> costItemToProducts) {
        *//*costItemToProductService.delete(id);
        List<CostItemToProduct> citcs = new ArrayList<>();
        for (CostItemToProduct citc: costItemToProducts) {
            citc.setProductId(citc.getProductId());
            citc.setCostItemId(citc.getCostItemId());
            citcs.add(citc);
        }

        costItemToProductService.save(citcs);*//*
        return new ResponseEntity<>(new MessageResponse("Успешно"), HttpStatus.OK);
    }*/
}
