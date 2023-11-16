package com.example.flowerscalculatorapi.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "tblinventorypt")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "boxTypeId", referencedColumnName = "id")
    private BoxType boxType;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "companyId", referencedColumnName = "id")
    private Company company;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

    @NotNull
    private Double cubesPerCarrier;

    @NotNull
    private Integer pack;

    @NotNull
    private Double basePrice;

    public Inventory(){}
}
