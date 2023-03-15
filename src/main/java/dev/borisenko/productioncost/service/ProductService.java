package dev.borisenko.productioncost.service;

import dev.borisenko.productioncost.model.CostItem;
import dev.borisenko.productioncost.model.CostItemToProduct;
import dev.borisenko.productioncost.model.Product;
import dev.borisenko.productioncost.model.Warehouse;
import dev.borisenko.productioncost.repository.CostItemRepo;
import dev.borisenko.productioncost.repository.CostItemToProductRepo;
import dev.borisenko.productioncost.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final CostItemToProductRepo costItemToProductRepo;
    private final CostService costService;

    public String getCost(Product product) {
        int cost = 0;
        String costStr = "";

        List<CostItemToProduct> costItemToProducts = costItemToProductRepo.findAllByProductId(product.getId());
        for (CostItemToProduct item: costItemToProducts) {
            costStr = costService.getCostByCostItemId(item.getCostItemId());
            if (!costStr.equals("Нет значений")) {
                cost += Integer.parseInt(costStr) * item.getQuantity();
            }
        }

        if (cost != 0) {
            return Integer.toString(cost * product.getQuantity()) + " / " + Integer.toString(cost) + " ед.";
        } else {
            return "Нет значений";
        }
    }

    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public Optional<Product> getById(int id) {
        return productRepo.findById(id);
    }

    public boolean exist(int id) {
        return productRepo.existsById(id);
    }

    public void save(Product product) {
        productRepo.save(product);
    }

    public void delete(int id) {
        productRepo.deleteById(id);
    }
}
