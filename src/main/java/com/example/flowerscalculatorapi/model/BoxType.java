package com.example.flowerscalculatorapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String code;

    @NotNull
    private Double width;

    @NotNull
    private Double height;

    @NotNull
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
