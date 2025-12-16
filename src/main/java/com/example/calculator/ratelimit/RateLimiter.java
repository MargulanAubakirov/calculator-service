package com.example.calculator.ratelimit;


import java.util.concurrent.ConcurrentHashMap;


/**
 * Simple IP-based rate limiter without external libraries.
 */
public class RateLimiter {


    private static final int LIMIT = 10; // requests per time window
    private static final long WINDOW = 60_000; // 1 minute in milliseconds


    private static final ConcurrentHashMap<String, Window> STORE = new ConcurrentHashMap<>();


    /**
     * Checks if the request from a specific key (IP) is allowed.
     * @param key IP address
     * @return true if allowed, false if rate limit exceeded
     */
    public static boolean allow(String key) {
        long now = System.currentTimeMillis();
        Window w = STORE.computeIfAbsent(key, k -> new Window(now));


        synchronized (w) {
            if (now - w.start > WINDOW) {
                w.start = now;
                w.count = 0;
            }
            w.count++;
            return w.count <= LIMIT;
        }
    }


    /** Internal window object to track request count */
    private static class Window {
        long start;
        int count;
        Window(long start) { this.start = start; }
    }
}