package dev.borisenko.productioncost.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "cost", schema = "production_cost", catalog = "")
public class Cost {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "datе", nullable = false)
    private Date currentDate;
    @Basic
    @Column(name = "price", nullable = false)
    private int price;
    @Basic
    @Column(name = "cost_item_id", nullable = false)
    private int costItemId;
}
