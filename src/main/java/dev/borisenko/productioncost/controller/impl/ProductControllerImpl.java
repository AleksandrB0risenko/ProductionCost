package dev.borisenko.productioncost.controller.impl;

import dev.borisenko.productioncost.controller.ProductController;
import dev.borisenko.productioncost.model.CostItem;
import dev.borisenko.productioncost.model.CostItemToProduct;
import dev.borisenko.productioncost.model.Product;
import dev.borisenko.productioncost.payload.response.MessageResponse;
import dev.borisenko.productioncost.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

@RestController
public class ProductControllerImpl implements ProductController {
    private final ProductService productService;
    private final String ERR_MSG = "Не удалось найти продукт, id: ";
    private final String SUC_MSG = "Данные продукта успешно обновлены";
    private final String RPT_MSG = "Отчет по продукту успешно создан";

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
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> update(int id, Product product) {
        if (productService.exist(id)) {
            product.setId(id);
            productService.save(product);
            return new ResponseEntity<>(new MessageResponse(SUC_MSG), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> report(int id) {
        if (productService.exist(id)) {
            try {
                File file = new File("Report.txt");
                file.createNewFile();
                FileWriter writer = new FileWriter(file);

                String str = "";
                Product p = productService.getById(id).get();
                str += "[ Продукт: ] " + p.getName() + "\n";
                str += "[ Количество: ] " + p.getQuantity() + "\n";
                /*str += "[ Статус: ] " + statusService.getStatus(p.getStatusId()).getName() + "\n";
                str += "[ Склад: ] " + warehouseService.getWarehouse(p.getWarehouseId()).getName() + "\n";
                str += "[ Отдел: ] " + departmentService.getDepartment(p.getDepartmentId()).getName() + "\n";
                str += "[ Статьи затрат: ]\n";
                for (CostItemToProduct item : costItemToProductService.findAllByProductId(id)) {
                    CostItem ci = costItemService.getCostItem(item.getCostItemId());
                    str += ci.getId() + ". ";
                    str += ci.getName() + " ";
                    str += item.getQuantity() + " ";
                    str += unitService.getUnit(ci.getUnitId()).getSymbol() + "\n";
                }*/

                writer.write(str);
                writer.flush();
                writer.close();

                return new ResponseEntity<>(new MessageResponse(RPT_MSG), HttpStatus.OK);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            return new ResponseEntity<>(new MessageResponse(ERR_MSG + id), HttpStatus.NOT_FOUND);
        }
    }
}
