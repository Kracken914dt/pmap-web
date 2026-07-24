// DTO para la creación de un usuario
package com.PMAP.demo.dto.usuario;

import com.PMAP.demo.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioCreateRequest(
        @NotBlank @Size(max = 100) String nombres,
        @NotBlank @Size(max = 100) String apellidos,
        @NotBlank @Email @Size(max = 120) String correo,
        @NotBlank @Size(min = 6, max = 100) String contraseña,
        @NotNull Role rol
) {
}