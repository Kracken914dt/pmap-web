// Controller para la ruta raíz y verificación de estado (Health Check)
package com.PMAP.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RootController {

    @GetMapping("/")
    public ResponseEntity<Map<String, String>> healthCheck() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "application", "PMAP Backend API",
                "message", "El backend de PMAP está funcionando correctamente."
        ));
    }
}
