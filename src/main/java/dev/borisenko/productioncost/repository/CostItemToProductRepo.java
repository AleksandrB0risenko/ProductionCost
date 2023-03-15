package dev.borisenko.productioncost.repository;

import dev.borisenko.productioncost.model.CostItem;
import dev.borisenko.productioncost.model.CostItemToProduct;
import dev.borisenko.productioncost.model.CostItemToProductPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostItemToProductRepo extends JpaRepository<CostItemToProduct, CostItemToProductPK> {
    List<CostItemToProduct> findAllByProductId(int productId);
    CostItemToProduct getCostItemToProductByCostItemIdAndProductId(int costItemId, int productId);
}
