package dev.borisenko.productioncost.controller;

import dev.borisenko.productioncost.model.Cost;
import dev.borisenko.productioncost.model.CostItem;
import dev.borisenko.productioncost.model.CostItemToProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cost_item_to_product")
@CrossOrigin(origins = "*", maxAge = 3600)
public interface CostItemToProductController {
    /*@GetMapping("/get/all")
        //@PreAuthorize("hasRole('USER')")
    ResponseEntity<?> getAll();*/

    @GetMapping("/get/{id}")
        //@PreAuthorize("hasRole('USER')")
    ResponseEntity<?> getById(@PathVariable("id") int id);

    @PostMapping("/add")
        //@PreAuthorize("hasRole('USER')")
    ResponseEntity<?> add(@Valid @RequestBody CostItemToProduct costItemToProduct);

    @DeleteMapping("/delete/{id}")
        //@PreAuthorize("hasRole('USER')")
    ResponseEntity<?> delete(@PathVariable("id") int id);

    /*@PutMapping("/update/{id}")
        //@PreAuthorize("hasRole('USER')")
    ResponseEntity<?> update(@PathVariable("id") int id, @Valid @RequestBody List<CostItemToProduct> costItemToProducts);*/
}
