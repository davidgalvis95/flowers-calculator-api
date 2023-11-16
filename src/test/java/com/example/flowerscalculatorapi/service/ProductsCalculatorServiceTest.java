package com.example.flowerscalculatorapi.service;

import com.example.flowerscalculatorapi.dto.*;
import com.example.flowerscalculatorapi.model.*;
import com.example.flowerscalculatorapi.respository.CustomerRepository;
import com.example.flowerscalculatorapi.respository.InventoryRepository;
import com.example.flowerscalculatorapi.respository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductsCalculatorServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private ProductRepository productRepository;

    private ProductsCalculatorService productsCalculatorService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productsCalculatorService = new ProductsCalculatorService(customerRepository, inventoryRepository, productRepository);
    }

    @Test
    public void getFreightForInventoryProductsTest() {
        final Integer companyId = 12345;
        final List<Inventory> inventories = buildInventories(companyId);
        final FinalProductsFreight expectedProductsFreight = buildExpectedProductsFreight(companyId);

        when(inventoryRepository.findInventoryByCompanyId(companyId)).thenReturn(inventories);

        final FinalProductsFreight productsFreight = productsCalculatorService.getFreightForInventoryProducts(companyId);
        assertEquals(expectedProductsFreight, productsFreight);
    }

    @Test
    public void getFreightForInventoryProductsWhenInventoryIsEmptyTest() {
        final Integer companyId = 12345;
        final FinalProductsFreight expectedProductsFreight = FinalProductsFreight.builder()
                .companyId(companyId)
                .products(Collections.emptyList())
                .build();

        when(inventoryRepository.findInventoryByCompanyId(companyId)).thenReturn(Collections.emptyList());

        final FinalProductsFreight productsFreight = productsCalculatorService.getFreightForInventoryProducts(companyId);
        assertEquals(expectedProductsFreight, productsFreight);
    }

    @Test
    public void getProductsPriceForCustomerTest() {
        final Integer customerId = 12345;
        final Customer customer = Customer.builder()
                .id(1)
                .markDown(15.0)
                .name("Fake Customer")
                .build();
        final List<Inventory> inventories = buildInventories(null);
        final ProductsPriceWrapper expectedProductsPriceWrapper = ProductsPriceWrapper.builder()
                .products(Arrays.asList(
                        ProductPriceDto.builder()
                                .companyName(inventories.get(0).getCompany().getName())
                                .productName(inventories.get(0).getProduct().getName())
                                .price(Double.toString(8.5))
                                .build(),
                        ProductPriceDto.builder()
                                .companyName(inventories.get(1).getCompany().getName())
                                .productName(inventories.get(1).getProduct().getName())
                                .price(Double.toString(6.8))
                                .build()
                ))
                .build();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(inventoryRepository.findAll()).thenReturn(inventories);

        final ProductsPriceWrapper productsPriceForCustomer = productsCalculatorService.getProductsPriceForCustomer(customerId);
        assertEquals(expectedProductsPriceWrapper, productsPriceForCustomer);
    }

    @Test
    public void getProductsPriceForCustomerWhenNoCustomerTest() {
        final Integer customerId = 12345;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        final ProductsPriceWrapper productsPriceForCustomer = productsCalculatorService.getProductsPriceForCustomer(customerId);
        assertNull(productsPriceForCustomer);
    }

    @Test
    public void getProductsPriceForCustomerWhenEmptyInventoryTest() {
        final Integer customerId = 12345;
        final Customer customer = Customer.builder()
                .id(1)
                .markDown(15.0)
                .name("Fake Customer")
                .build();
        final ProductsPriceWrapper expectedProductsPriceWrapper = ProductsPriceWrapper.builder()
                .products(Collections.emptyList())
                .build();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(inventoryRepository.findAll()).thenReturn(Collections.emptyList());

        final ProductsPriceWrapper productsPriceForCustomer = productsCalculatorService.getProductsPriceForCustomer(customerId);
        assertEquals(expectedProductsPriceWrapper, productsPriceForCustomer);
    }

    @Test
    public void getProductCodeTest(){
        final Integer productId = 12345;
        final Product product = Product.builder()
                .id(1)
                .name("Black Gira%sol 17Inch")
                .freshCutValue(10.0)
                .build();

        final ProductCode expectedProductCode = ProductCode.builder()
                .productName(product.getName())
                .productCode("B3k-G6%l-14h")
                .build();

        try (MockedStatic<ProductNameTransformer> utilities = Mockito.mockStatic(ProductNameTransformer.class)) {
            utilities.when(() -> ProductNameTransformer.transformFlowerName(product.getName()))
                    .thenReturn(expectedProductCode.getProductCode());
            when(productRepository.findById(productId)).thenReturn(Optional.of(product));
            final ProductCode productCodeResponse = productsCalculatorService.getProductCode(productId);
            assertEquals(expectedProductCode, productCodeResponse);
        }
    }

    @Test
    public void getProductCodeWhenNoProductTest(){
        final Integer productId = 12345;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());
        final ProductCode productCodeResponse = productsCalculatorService.getProductCode(productId);
        assertNull(productCodeResponse);
    }

    private FinalProductsFreight buildExpectedProductsFreight(Integer companyId) {

        return FinalProductsFreight.builder()
                .companyId(companyId)
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

    private List<Inventory> buildInventories(final Integer companyId) {
        return Arrays.asList(
                Inventory.builder()
                        .id(1)
                        .product(Product.builder()
                                .id(1)
                                .name("abcd")
                                .freshCutValue(10.0)
                                .build())
                        .company(Company.builder()
                                .id(companyId == null? 123: companyId)
                                .name("The company")
                                .build())
                        .boxType(BoxType.builder()
                                .code("fghi")
                                .height(5.0)
                                .width(3.0)
                                .length(2.0)
                                .build())
                        .pack(2)
                        .cubesPerCarrier(35.0)
                        .basePrice(10.0)
                        .build(),
                Inventory.builder()
                        .id(1)
                        .product(Product.builder()
                                .id(2)
                                .name("ouiy")
                                .freshCutValue(14.0)
                                .build())
                        .company(Company.builder()
                                .id(companyId == null? 123: companyId)
                                .name("The company")
                                .build())
                        .boxType(BoxType.builder()
                                .code("hlkj")
                                .height(4.0)
                                .width(2.0)
                                .length(3.0)
                                .build())
                        .pack(3)
                        .cubesPerCarrier(32.0)
                        .basePrice(8.0)
                        .build()
        );
    }
}
