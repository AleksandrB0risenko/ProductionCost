package dev.borisenko.productioncost.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "department", schema = "production_cost", catalog = "")
public class Department {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;
}
