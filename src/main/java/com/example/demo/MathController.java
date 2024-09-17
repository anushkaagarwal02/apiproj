package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow all origins
public class MathController {

    @PostMapping("/calculate")
    public ResponseEntity<Map<String, Object>> calculate(@RequestBody CalculationRequest request) {
        Map<String, Object> response = new HashMap<>();
        int result;

        // Perform the calculation based on the method provided
        switch (request.getMethod()) {
            case "add":
                result = request.getNum1() + request.getNum2();
                break;
            case "subtract":
                result = request.getNum1() - request.getNum2();
                break;
            case "multiply":
                result = request.getNum1() * request.getNum2();
                break;
            default:
                // Handle invalid method with an error message
                response.put("error", "Invalid method. Please use 'add', 'subtract', or 'multiply'.");
                return ResponseEntity.badRequest().body(response);
        }

        // Prepare a proper JSON response with the result
        response.put("result", result); // Frontend expects "result" field
        return ResponseEntity.ok(response); // Send back as a JSON response
    }
}