package com.bfhl.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bfhl.service.GeminiService;



@RestController
public class BfhlController {

    private static final String EMAIL = "khushpreet1873.be23@chitkara.edu.in";
    @Autowired
    private GeminiService geminiService;

    // ---------------- GET /health ----------------
    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> res = new LinkedHashMap<>();
        res.put("is_success", true);
        res.put("official_email", EMAIL);
        return res;
    }

    // ---------------- POST /bfhl ----------------
    @PostMapping("/bfhl")
    public ResponseEntity<Map<String, Object>> bfhl(@RequestBody Map<String, Object> body) {

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("official_email", EMAIL);

        try {
            if (body.containsKey("fibonacci")) {
                int n = (int) body.get("fibonacci");
                response.put("is_success", true);
                response.put("data", fibonacci(n));
                return ResponseEntity.ok(response);
            }

            if (body.containsKey("prime")) {
                List<Integer> nums = (List<Integer>) body.get("prime");
                response.put("is_success", true);
                response.put("data", primes(nums));
                return ResponseEntity.ok(response);
            }

            if (body.containsKey("lcm")) {
                List<Integer> nums = (List<Integer>) body.get("lcm");
                response.put("is_success", true);
                response.put("data", lcm(nums));
                return ResponseEntity.ok(response);
            }

            if (body.containsKey("hcf")) {
                List<Integer> nums = (List<Integer>) body.get("hcf");
                response.put("is_success", true);
                response.put("data", hcf(nums));
                return ResponseEntity.ok(response);
            }

            if (body.containsKey("AI")) {
                response.put("is_success", true);
                response.put("data", "Mumbai"); // placeholder (AI API baad me)
                return ResponseEntity.ok(response);
            }

            response.put("is_success", false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        } catch (Exception e) {
            response.put("is_success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // ---------------- Helper Methods ----------------

    private List<Integer> fibonacci(int n) {
        List<Integer> res = new ArrayList<>();
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            res.add(a);
            int c = a + b;
            a = b;
            b = c;
        }
        return res;
    }

    private List<Integer> primes(List<Integer> nums) {
        List<Integer> res = new ArrayList<>();
        for (int n : nums) {
            if (n > 1 && isPrime(n)) res.add(n);
        }
        return res;
    }

    private boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0) return false;
        return true;
    }

    private int lcm(List<Integer> nums) {
        int res = nums.get(0);
        for (int n : nums)
            res = res * n / gcd(res, n);
        return res;
    }

    private int hcf(List<Integer> nums) {
        int res = nums.get(0);
        for (int n : nums)
            res = gcd(res, n);
        return res;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}