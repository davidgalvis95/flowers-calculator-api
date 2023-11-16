package com.example.flowerscalculatorapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "tblcustomerpt")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "varchar(80)")
    private String name;

    @Column(nullable = false, columnDefinition = "decimal(5, 2) default 0")
    private Double markDown;

    public Customer() {}
}
