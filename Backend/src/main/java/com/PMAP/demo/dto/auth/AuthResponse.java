// DTO para la respuesta de autenticación (token + datos del usuario)
package com.PMAP.demo.dto.auth;

import com.PMAP.demo.dto.usuario.UsuarioResponse;

public record AuthResponse(
        String token,
        String tipo,
        UsuarioResponse usuario
) {
}