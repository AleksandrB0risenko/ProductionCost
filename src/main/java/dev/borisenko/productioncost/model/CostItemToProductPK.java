package dev.borisenko.productioncost.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class CostItemToProductPK implements Serializable {
    @Id
    @Column(name = "product_id")
    private int productId;
    @Id
    @Column(name = "cost_item_id")
    private int costItemId;

    @Override
    public int hashCode() {
        return Objects.hash(costItemId, productId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CostItemToProductPK that = (CostItemToProductPK) o;
        return costItemId == that.costItemId && productId == that.productId;
    }
}
