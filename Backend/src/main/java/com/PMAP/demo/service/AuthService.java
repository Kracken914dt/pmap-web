// Interfaz del servicio de autenticación
// Define las operaciones de registro e inicio de sesión
package com.PMAP.demo.service;

import com.PMAP.demo.dto.auth.AuthResponse;
import com.PMAP.demo.dto.auth.LoginRequest;
import com.PMAP.demo.dto.auth.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}