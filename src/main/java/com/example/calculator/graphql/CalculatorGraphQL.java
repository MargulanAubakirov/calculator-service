package com.example.calculator.graphql;


import com.example.calculator.dto.CalculatorResponse;
import com.example.calculator.service.CalculatorService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;


/**
 * GraphQL Controller exposing calculator query.
 */
@Controller
public class CalculatorGraphQL {


    private final CalculatorService service;


    public CalculatorGraphQL(CalculatorService service) {
        this.service = service;
    }


    /**
     * GraphQL query mapping for calculator
     * @param a input number a
     * @param b input number b
     * @param operator operator (p, m, x, d)
     * @return CalculatorResponse
     */
    @QueryMapping
    public CalculatorResponse calculator(
            @Argument int a,
            @Argument int b,
            @Argument String operator) {
        return service.calculate(a, b, operator);
    }
}