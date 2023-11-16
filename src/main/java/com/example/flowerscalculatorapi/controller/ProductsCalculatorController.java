package com.example.flowerscalculatorapi.controller;

import com.example.flowerscalculatorapi.dto.FinalProductsFreight;
import com.example.flowerscalculatorapi.dto.ProductCode;
import com.example.flowerscalculatorapi.dto.ProductsPriceWrapper;
import com.example.flowerscalculatorapi.dto.StandardResponseDto;
import com.example.flowerscalculatorapi.service.ProductsCalculatorService;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
public class ProductsCalculatorController {

    private final ProductsCalculatorService productsCalculatorService;

    public ProductsCalculatorController(final ProductsCalculatorService productsCalculatorService) {
        this.productsCalculatorService = productsCalculatorService;
    }

    @GetMapping("/freight-cost")
    public ResponseEntity<StandardResponseDto<FinalProductsFreight>> getFreightForProducts(@RequestParam @NotNull final int companyId) {
        final FinalProductsFreight productsFreightResult = productsCalculatorService.getFreightForInventoryProducts(companyId);

        final StandardResponseDto<FinalProductsFreight> standardResponseDto = new StandardResponseDto<>(
                productsFreightResult,
                null,
                null
        );
        return ResponseEntity.ok().body(standardResponseDto);
    }

    @GetMapping("/price")
    public ResponseEntity<StandardResponseDto<ProductsPriceWrapper>> getPriceForProducts(@RequestParam @NotNull final int customerId) {
        final ProductsPriceWrapper productsFreightResult = productsCalculatorService.getProductsPriceForCustomer(customerId);

        final StandardResponseDto<ProductsPriceWrapper> standardResponseDto = new StandardResponseDto<>(
                productsFreightResult,
                null,
                null
        );
        return ResponseEntity.ok().body(standardResponseDto);
    }

    @GetMapping("/code")
    public ResponseEntity<StandardResponseDto<ProductCode>> getProductCode(@RequestParam @NotNull final int productId) {
        final ProductCode productsFreightResult = productsCalculatorService.getProductCode(productId);

        final StandardResponseDto<ProductCode> standardResponseDto = new StandardResponseDto<>(
                productsFreightResult,
                null,
                null
        );
        return ResponseEntity.ok().body(standardResponseDto);
    }
}
