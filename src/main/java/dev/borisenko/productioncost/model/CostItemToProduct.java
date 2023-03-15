package dev.borisenko.productioncost.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "cost_item_to_product", schema = "production_cost", catalog = "")
@IdClass(CostItemToProductPK.class)
public class CostItemToProduct {
    @Id
    @Column(name = "cost_item_id")
    private int costItemId;
    @Id
    @Column(name = "product_id")
    private int productId;
    @Basic
    @Column(name = "quantity")
    private int quantity;

    @Override
    public int hashCode() {
        return Objects.hash(costItemId, productId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CostItemToProduct that = (CostItemToProduct) o;
        return costItemId == that.costItemId && productId == that.productId;
    }
}
