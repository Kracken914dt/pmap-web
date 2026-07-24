// DTO para la creación de una sesión de estudio
package com.PMAP.demo.dto.sesion;

import com.PMAP.demo.entity.EstadoSesionEstudio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public record SesionEstudioCreateRequest(
        @NotNull Long usuarioId,
        @NotNull Long materiaId,
        @NotNull LocalDate fecha,
        @NotNull LocalTime horaInicio,
        @NotNull LocalTime horaFin,
        @NotBlank @Size(max = 500) String objetivo,
        @NotNull EstadoSesionEstudio estado
) {
}