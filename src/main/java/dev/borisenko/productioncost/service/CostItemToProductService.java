package dev.borisenko.productioncost.service;

import dev.borisenko.productioncost.model.CostItemToProduct;
import dev.borisenko.productioncost.repository.CostItemToProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CostItemToProductService {
    private final CostItemToProductRepo costItemToProductRepo;

    public List<CostItemToProduct> findAllByProductId(int productId) {
        return costItemToProductRepo.findAllByProductId(productId);
    }

    public CostItemToProduct getByCostItemIdAndProductId(int costItemId, int productId) {
        return costItemToProductRepo.getCostItemToProductByCostItemIdAndProductId(costItemId, productId);
    }

    public void save(CostItemToProduct costItemToProduct) {

        costItemToProductRepo.save(costItemToProduct);
    }

    public void delete(int id) {
        //costItemToProductRepo.deleteById(id);
        //costItemToProductRepo.delete(costItemToProductRepo.getCostItemToProductByCostItemIdAndProductId(costItemId, productId));
    }
}
