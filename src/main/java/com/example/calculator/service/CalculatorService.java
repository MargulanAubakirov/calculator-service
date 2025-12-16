package com.example.calculator.service;


import com.example.calculator.dto.CalculatorResponse;
import org.springframework.stereotype.Service;


/**
 * Service class responsible for calculator operations.
 */
@Service
public class CalculatorService {


    /**
     * Performs calculation based on operator.
     * @param a input number a
     * @param b input number b
     * @param operator operator (p, m, x, d)
     * @return CalculatorResponse containing result or error message
     */
    public CalculatorResponse calculate(int a, int b, String operator) {
        CalculatorResponse r = new CalculatorResponse();
        r.a = a;
        r.b = b;
        r.operator = operator;
        r.result = 0;
        r.error = "";


        if (operator == null) {
            r.error = "Supported operators: +=p, -=m, /=d, x=x";
            return r;
        }


        switch (operator) {
            case "p" -> r.result = a + b;
            case "m" -> r.result = a - b;
            case "x" -> r.result = a * b;
            case "d" -> {
                if (b == 0) r.error = "/ by zero";
                else r.result = a / b;
            }
            default -> r.error = "Supported operators: +=p, -=m, /=d, x=x";
        }
        return r;
    }
}