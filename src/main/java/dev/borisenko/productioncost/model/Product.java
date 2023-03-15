package dev.borisenko.productioncost.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "product", schema = "production_cost", catalog = "")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    /*@Basic
    @Column(name = "datе", nullable = false)
    private Date creationDate;*/
    @Basic
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic
    @Column(name = "warehouse_id", nullable = true)
    private Integer warehouseId;
    @Basic
    @Column(name = "department_id", nullable = true)
    private Integer departmentId;
    @Basic
    @Column(name = "status_id", nullable = true)
    private Integer statusId;
}
