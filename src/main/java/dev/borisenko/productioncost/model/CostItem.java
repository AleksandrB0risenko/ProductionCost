package dev.borisenko.productioncost.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "cost_item", schema = "production_cost", catalog = "")
public class CostItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic
    @Column(name = "type_id", nullable = true)
    private Integer typeId;
    @Basic
    @Column(name = "unit_id", nullable = true)
    private Integer unitId;
}
