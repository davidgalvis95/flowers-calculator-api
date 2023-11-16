package com.example.flowerscalculatorapi.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductNameTransformerTest {

    @Test
    public void testTransformFlowerName() {
        assertEquals("R3s", ProductNameTransformer.transformFlowerName("Roses"));
        assertEquals("R1d-R3s-22m", ProductNameTransformer.transformFlowerName("Red Roses 23cm"));
        assertEquals("I0L-H7a-B2e", ProductNameTransformer.transformFlowerName("IL Hydrangea Blue"));
        assertEquals("B3k-G6%l-14h", ProductNameTransformer.transformFlowerName("Black Gira%sol 17Inch"));
        assertEquals("&4e-p1m-33h", ProductNameTransformer.transformFlowerName("&White pom 3Inch"));
    }

    @Test
    public void testTransformFlowerNameWithEmptyString() {
        assertEquals("", ProductNameTransformer.transformFlowerName(""));
    }

    @Test
    public void testTransformFlowerNameWithSpecialCharacters() {
        assertEquals("A1@B-C1#D-E1$F", ProductNameTransformer.transformFlowerName("A@B C#D E$F"));
    }

    @Test
    public void testTransformFlowerNameWithSpaces() {
        assertEquals("A0A-B0B-C0C-D0D-E0E-F0F", ProductNameTransformer.transformFlowerName("A B C D E F"));
    }

    @Test
    public void testTransformFlowerNameWithNullInput() {
        assertEquals("", ProductNameTransformer.transformFlowerName(null));
    }

    @Test
    public void testGetDistinctCharacterCount() {
        assertEquals(3, ProductNameTransformer.getDistinctCharacterCountInBetween("Roses"));
        assertEquals(7, ProductNameTransformer.getDistinctCharacterCountInBetween("Hydrangea"));
        assertEquals(6, ProductNameTransformer.getDistinctCharacterCountInBetween("Gira%sol"));
        assertEquals(1, ProductNameTransformer.getDistinctCharacterCountInBetween("Aˆg"));
        assertEquals(0, ProductNameTransformer.getDistinctCharacterCountInBetween("A"));
    }
}