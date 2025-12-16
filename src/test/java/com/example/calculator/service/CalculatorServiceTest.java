package com.example.calculator.service;

import com.example.calculator.dto.CalculatorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculatorServiceTest {

    @Autowired
    private CalculatorService service;

    @Test
    void testAddition() {
        CalculatorResponse result = service.calculate(2, 3, "p");
        assertEquals(5, result.result);
        assertEquals("", result.error);
    }

    @Test
    void testSubtraction() {
        CalculatorResponse result = service.calculate(5, 3, "m");
        assertEquals(2, result.result);
        assertEquals("", result.error);
    }

    @Test
    void testMultiplication() {
        CalculatorResponse result = service.calculate(4, 3, "x");
        assertEquals(12, result.result);
        assertEquals("", result.error);
    }

    @Test
    void testDivision() {
        CalculatorResponse result = service.calculate(6, 3, "d");
        assertEquals(2, result.result);
        assertEquals("", result.error);
    }

    @Test
    void testDivisionByZero() {
        CalculatorResponse result = service.calculate(6, 0, "d");
        assertEquals(0, result.result);
        assertEquals("/ by zero", result.error);
    }

    @Test
    void testUnsupportedOperator() {
        CalculatorResponse result = service.calculate(1, 2, "v");
        assertEquals(0, result.result);
        assertTrue(result.error.contains("Supported operators"));
    }

    @Test
    void testNullOperator() {
        CalculatorResponse result = service.calculate(1, 2, null);
        assertEquals(0, result.result);
        assertTrue(result.error.contains("Supported operators"));
    }
}
