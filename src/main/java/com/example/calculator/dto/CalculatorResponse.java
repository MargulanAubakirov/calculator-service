package com.example.calculator.dto;


/**
 * Data Transfer Object for calculator responses.
 */
public class CalculatorResponse {
    /** Input number a */
    public int a;


    /** Input number b */
    public int b;


    /** Operator string (p, m, x, d) */
    public String operator;


    /** Calculation result */
    public int result;


    /** Error message, if any */
    public String error;
}