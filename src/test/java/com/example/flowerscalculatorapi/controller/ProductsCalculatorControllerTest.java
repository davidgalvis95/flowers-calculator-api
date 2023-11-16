package com.example.flowerscalculatorapi.controller;

import com.example.flowerscalculatorapi.dto.*;
import com.example.flowerscalculatorapi.service.ProductsCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductsCalculatorControllerTest {

    @Mock
    private ProductsCalculatorService productsCalculatorService;

    private ProductsCalculatorController productsCalculatorController;

    @BeforeEach
    public void setUp() {
        productsCalculatorController = new ProductsCalculatorController(productsCalculatorService);
    }

    @Test
    public void getFreightForProductsTest() {

        final int companyId = 12345;
        final FinalProductsFreight finalProductsFreight = buildProductsFinalFreight();

        final StandardResponseDto<FinalProductsFreight> expectedResponseDto = new StandardResponseDto<>(
                finalProductsFreight,
                null,
                null
        );
        when(productsCalculatorService.getFreightForInventoryProducts(companyId)).thenReturn(finalProductsFreight);
        final ResponseEntity<StandardResponseDto<FinalProductsFreight>> response = productsCalculatorController.getFreightForProducts(companyId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponseDto, response.getBody());
    }

    @Test
    public void getFreightForProductsWhenExceptionInServiceTest() {
        final int companyId = 12345;
        when(productsCalculatorService.getFreightForInventoryProducts(companyId))
                .thenThrow(new RuntimeException("Something went wrong while getting freight for products"));
        final RuntimeException exception =
                assertThrows(RuntimeException.class, () -> productsCalculatorController.getFreightForProducts(companyId));
        assertEquals("Something went wrong while getting freight for products", exception.getMessage());
    }

    @Test
    public void getPriceForProductsTest() {

        final int customerId = 12345;
        final ProductsPriceWrapper productsPriceWrapper = buildProductsPriceWrapper();

        final StandardResponseDto<ProductsPriceWrapper> expectedResponseDto = new StandardResponseDto<>(
                productsPriceWrapper,
                null,
                null
        );
        when(productsCalculatorService.getProductsPriceForCustomer(customerId)).thenReturn(productsPriceWrapper);
        final ResponseEntity<StandardResponseDto<ProductsPriceWrapper>> response = productsCalculatorController.getPriceForProducts(customerId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponseDto, response.getBody());
    }

    @Test
    public void getPriceForProductsWhenServiceExceptionTest() {
        final int customerId = 12345;
        when(productsCalculatorService.getProductsPriceForCustomer(customerId))
                .thenThrow(new RuntimeException("Something went wrong while calculating products price"));
        final RuntimeException exception = assertThrows(RuntimeException.class, () -> productsCalculatorController.getPriceForProducts(customerId));
        assertEquals("Something went wrong while calculating products price", exception.getMessage());
    }

    @Test
    public void getProductCodeTest() {

        final int productId = 12345;
        final ProductCode productCode = ProductCode.builder()
                .productName("Black Gira%sol 17Inch")
                .productCode("B3k-G6%l-14h")
                .build();

        final StandardResponseDto<ProductCode> expectedResponseDto = new StandardResponseDto<>(
                productCode,
                null,
                null
        );
        when(productsCalculatorService.getProductCode(productId)).thenReturn(productCode);
        final ResponseEntity<StandardResponseDto<ProductCode>> response = productsCalculatorController.getProductCode(productId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponseDto, response.getBody());
    }

    @Test
    public void getProductCodeWhenServiceExceptionTest() {
        final int productId = 12345;
        when(productsCalculatorService.getProductCode(productId))
                .thenThrow(new RuntimeException("Something went wrong while getting the product code"));
        final RuntimeException exception = assertThrows(RuntimeException.class, () -> productsCalculatorController.getProductCode(productId));
        assertEquals("Something went wrong while getting the product code", exception.getMessage());
    }

    private FinalProductsFreight buildProductsFinalFreight() {

        return FinalProductsFreight.builder()
                .companyId(12345)
                .products(Arrays.asList(
                        ProductFreightDto.builder()
                                .productName("abcd")
                                .basePrice("10.0")
                                .finalFreight(0.03)
                                .build(),
                        ProductFreightDto.builder()
                                .productName("ouiy")
                                .basePrice("8.0")
                                .finalFreight(0.02)
                                .build()
                ))
                .build();

    }

    private ProductsPriceWrapper buildProductsPriceWrapper() {
        return ProductsPriceWrapper.builder()
                .products(Arrays.asList(
                        ProductPriceDto.builder()
                                .companyName("Company 1")
                                .productName("P1")
                                .price(Double.toString(9.0))
                                .build(),
                        ProductPriceDto.builder()
                                .companyName("Company 2")
                                .productName("P2")
                                .price(Double.toString(8.5))
                                .build()
                ))
                .build();
    }
}
