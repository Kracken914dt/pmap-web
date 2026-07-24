// DTO para la respuesta con datos completos de una sesión (incluye usuario y materia)
package com.PMAP.demo.dto.sesion;

import com.PMAP.demo.entity.EstadoSesionEstudio;
import com.PMAP.demo.dto.materia.MateriaResponse;
import com.PMAP.demo.dto.usuario.UsuarioResponse;

import java.time.LocalDate;
import java.time.LocalTime;

public record SesionEstudioResponse(
        Long id,
        UsuarioResponse usuario,
        MateriaResponse materia,
        LocalDate fecha,
        LocalTime horaInicio,
        LocalTime horaFin,
        String objetivo,
        EstadoSesionEstudio estado
) {
}