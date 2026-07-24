// DTO para la actualización de una materia (incluye estado)
package com.PMAP.demo.dto.materia;

import com.PMAP.demo.entity.EstadoMateria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MateriaUpdateRequest(
        @NotBlank @Size(max = 120) String nombre,
        @NotBlank @Size(max = 500) String descripcion,
        @NotBlank @Size(max = 80) String categoria,
        @NotNull EstadoMateria estado
) {
}