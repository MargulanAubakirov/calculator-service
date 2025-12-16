package com.example.calculator.controller;


import com.example.calculator.dto.CalculatorResponse;
import com.example.calculator.ratelimit.RateLimiter;
import com.example.calculator.service.CalculatorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * REST Controller exposing calculator endpoint with proper exception handling.
 */
@RestController
@RequestMapping("/api")
public class CalculatorController {


    private final CalculatorService service;


    public CalculatorController(CalculatorService service) {
        this.service = service;
    }


    /**
     * GET /api/calculator endpoint
     * @param a number a (default 0)
     * @param b number b (default 0)
     * @param operator operation
     * @param request HttpServletRequest for IP-based rate limiting
     * @return ResponseEntity with CalculatorResponse and HTTP status
     */
    @GetMapping("/calculator")
    public ResponseEntity<CalculatorResponse> calculate(
            @RequestParam(defaultValue = "0") int a,
            @RequestParam(defaultValue = "0") int b,
            @RequestParam(required = false) String operator,
            HttpServletRequest request) {


        try {
            if (!RateLimiter.allow(request.getRemoteAddr())) {
                CalculatorResponse r = new CalculatorResponse();
                r.error = "Please try after some time.";
                return new ResponseEntity<>(r, HttpStatus.TOO_MANY_REQUESTS);
            }


            CalculatorResponse response = service.calculate(a, b, operator);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            CalculatorResponse r = new CalculatorResponse();
            r.a = a;
            r.b = b;
            r.operator = operator;
            r.result = 0;
            r.error = "Internal server error: " + e.getMessage();
            return new ResponseEntity<>(r, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}