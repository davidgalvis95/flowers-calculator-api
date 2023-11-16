package com.example.flowerscalculatorapi.service;

import com.example.flowerscalculatorapi.dto.*;
import com.example.flowerscalculatorapi.model.BoxType;
import com.example.flowerscalculatorapi.model.Customer;
import com.example.flowerscalculatorapi.model.Inventory;
import com.example.flowerscalculatorapi.model.Product;
import com.example.flowerscalculatorapi.respository.*;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class ProductsCalculatorService {

    private final CustomerRepository customerRepository;

    private final InventoryRepository inventoryRepository;

    private final ProductRepository productRepository;


    public ProductsCalculatorService(final CustomerRepository customerRepository,
                                     final InventoryRepository inventoryRepository,
                                     final ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    public FinalProductsFreight getFreightForInventoryProducts(final Integer companyId) {

        //Fetches all the products considering that one won't get same product repeated for the same company
        final List<Inventory> inventoryProducts = inventoryRepository.findInventoryByCompanyId(companyId);
        final List<ProductFreightDto> products = inventoryProducts.stream()
                .map(this::mapInventoryToProductFreight)
                .toList();

        return FinalProductsFreight.builder()
                .companyId(companyId)
                .products(products)
                .build();
    }

    public ProductsPriceWrapper getProductsPriceForCustomer(final Integer customerId) {
        //Only if there's a customer the adjusted price for products calculation is performed
        return customerRepository.findById(customerId)
                .map(c -> {
                    final List<Inventory> inventoryProducts = inventoryRepository.findAll();
                    final List<ProductPriceDto> products = calculateProductPrice(c, inventoryProducts);
                    return ProductsPriceWrapper.builder()
                            .products(products)
                            .build();
                })
                .orElse(null);

    }

    public ProductCode getProductCode(Integer productId) {
        //Only if there's a product the codified name calculation is performed
        return productRepository.findById(productId)
                .map(p -> ProductCode.builder()
                        .productName(p.getName())
                        .productCode(ProductNameTransformer.transformFlowerName(p.getName()))
                        .build())
                .orElse(null);
    }

    private ProductFreightDto mapInventoryToProductFreight(final Inventory inventory) {
        //Applying the formula to get the product freight and mapping to the dto
        final Product product = inventory.getProduct();
        final BoxType boxType = inventory.getBoxType();
        final Double cubesPerBox = (boxType.getHeight() * boxType.getWidth() * boxType.getLength()) / 1728;
        final double outboundFreight = (cubesPerBox * inventory.getCubesPerCarrier()) / inventory.getPack();
        final Double finalFreight = Math.round((outboundFreight * (product.getFreshCutValue() / 100))* 100.0) / 100.0;

        return ProductFreightDto.builder()
                .productName(product.getName())
                .basePrice(inventory.getBasePrice().toString())
                .finalFreight(finalFreight)
                .build();
    }

    private List<ProductPriceDto> calculateProductPrice(final Customer customer,
                                                        final List<Inventory> inventoryProducts) {
        //Applying the formula to calculate price and rounding to 2 decimals for each product in the inventory
        return inventoryProducts.stream().map(inventory -> {
            final double basePrice = inventory.getBasePrice();
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            final double price = basePrice - (basePrice * (customer.getMarkDown() / 100));
            final String roundedValueAsString = decimalFormat.format(price);
            return ProductPriceDto.builder()
                    .productName(inventory.getProduct().getName())
                    .companyName(inventory.getCompany().getName())
                    .price(roundedValueAsString)
                    .build();
        }).toList();
    }
}
