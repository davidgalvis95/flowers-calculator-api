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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(nullable = false, columnDefinition = "decimal(19, 5) default 0")
    private Double cubesPerCarrier;

    @Column(nullable = false, columnDefinition = "numeric(5)")
    private Integer pack;

    @Column(nullable = false, columnDefinition = "decimal(10, 5) default 0")
    private Double basePrice;

    public Inventory(){}
}
