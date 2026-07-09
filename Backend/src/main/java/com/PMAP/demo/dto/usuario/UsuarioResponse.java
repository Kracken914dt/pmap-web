package com.PMAP.demo.dto.usuario;

import com.PMAP.demo.entity.EstadoUsuario;
import com.PMAP.demo.entity.Role;

import java.time.LocalDateTime;

public record UsuarioResponse(
        Long id,
        String nombres,
        String apellidos,
        String correo,
        Role rol,
        EstadoUsuario estado,
        LocalDateTime fechaRegistro
) {
}