package com.example.flowerscalculatorapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "tblboxtypept")
public class BoxType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "varchar(80)")
    private String code;

    @Column(nullable = false, columnDefinition = "decimal(10, 5) default 0")
    private Double width;

    @Column(nullable = false, columnDefinition = "decimal(10, 5) default 0")
    private Double height;

    @Column(nullable = false, columnDefinition = "decimal(10, 5) default 0")
    private Double length;

    @OneToMany(
            mappedBy = "boxType",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<Inventory> inventories;

    public BoxType() {
    }
}
