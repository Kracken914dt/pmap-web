package com.PMAP.demo.dto.materia;

import com.PMAP.demo.entity.EstadoMateria;

import java.time.LocalDateTime;

public record MateriaResponse(
        Long id,
        String nombre,
        String descripcion,
        String categoria,
        EstadoMateria estado,
        LocalDateTime fechaCreacion
) {
}