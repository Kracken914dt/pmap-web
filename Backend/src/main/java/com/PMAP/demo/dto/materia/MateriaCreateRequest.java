package com.PMAP.demo.dto.materia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MateriaCreateRequest(
        @NotBlank @Size(max = 120) String nombre,
        @NotBlank @Size(max = 500) String descripcion,
        @NotBlank @Size(max = 80) String categoria
) {
}