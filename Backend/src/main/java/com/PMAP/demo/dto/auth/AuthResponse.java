package com.PMAP.demo.dto.auth;

import com.PMAP.demo.dto.usuario.UsuarioResponse;

public record AuthResponse(
        String token,
        String tipo,
        UsuarioResponse usuario
) {
}