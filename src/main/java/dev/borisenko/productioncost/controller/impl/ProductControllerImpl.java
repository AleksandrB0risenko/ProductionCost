package dev.borisenko.productioncost.controller.impl;

import dev.borisenko.productioncost.controller.ProductController;
import dev.borisenko.productioncost.model.Cost;
import dev.borisenko.productioncost.model.Product;
import dev.borisenko.productioncost.payload.response.MessageResponse;
import dev.borisenko.productioncost.repository.CostRepo;
import dev.borisenko.productioncost.repository.ProductRepo;
import dev.borisenko.productioncost.service.CostService;
import dev.borisenko.productioncost.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductControllerImpl implements ProductController {
    private final ProductService productService;
    private final String ERR_MSG = "Не удалось найти продукт, id: ";
    private final String SUC_MSG = "Данные продукта успешно обновлены";

    @Autowired
    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getById(int id) {
        Optional<Product> t = productService.getById(id);

        if (t.isPresent()) {
            return new ResponseEntity<>(t.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<?> add(Product product) {
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(int id) {
        if (productService.exist(id)) {
            productService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public ResponseEntity<?> update(int id, Product product) {
        if (productService.exist(id)) {
            product.setId(id);
            productService.save(product);
            return new ResponseEntity<>(new MessageResponse(SUC_MSG), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NO_CONTENT);
        }
    }
}
