package dev.borisenko.productioncost.service;

import dev.borisenko.productioncost.model.Cost;
import dev.borisenko.productioncost.model.CostItem;
import dev.borisenko.productioncost.model.CostItemToProduct;
import dev.borisenko.productioncost.model.Status;
import dev.borisenko.productioncost.repository.CostItemRepo;
import dev.borisenko.productioncost.repository.CostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CostService {
    private final CostRepo costRepo;

    public List<Cost> findAllByCostItemId(int costItemId) {
        return costRepo.findAllByCostItemId(costItemId);
    }

    public String getCostByCostItemId(int costItemId) {
        List<Cost> costs = costRepo.findAllByCostItemId(costItemId);
        costs.sort(Comparator.comparing(Cost::getCurrentDate).reversed());
        return !costs.isEmpty() ? Integer.toString(costs.get(0).getPrice()) : "Нет значений";
    }

    public String getFullCostByCostItemId(CostItemToProduct costItemToProduct) {
        String costStr = getCostByCostItemId(costItemToProduct.getCostItemId());

        if (!costStr.equals("Нет значений")) {
            int cost = Integer.parseInt(costStr);
            return Integer.toString(cost * costItemToProduct.getQuantity()) + " / " + Integer.toString(cost) + " ед.";
        } else {
            return costStr;
        }
    }

    public List<Cost> getAll() {
        return costRepo.findAll();
    }

    public Optional<Cost> getById(int id) {
        return costRepo.findById(id);
    }

    public boolean exist(int id) {
        return costRepo.existsById(id);
    }

    public void save(Cost cost) {
        costRepo.save(cost);
    }

    public void delete(int id) {
        costRepo.deleteById(id);
    }
}
