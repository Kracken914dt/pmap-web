// Mapper para convertir la entidad SesionEstudio a su DTO de respuesta SesionEstudioResponse
// Reutiliza UsuarioMapper y MateriaMapper para las relaciones
package com.PMAP.demo.mapper;

import com.PMAP.demo.dto.sesion.SesionEstudioResponse;
import com.PMAP.demo.entity.SesionEstudio;
import org.springframework.stereotype.Component;

@Component
public class SesionEstudioMapper {

    private final UsuarioMapper usuarioMapper;
    private final MateriaMapper materiaMapper;

    public SesionEstudioMapper(UsuarioMapper usuarioMapper, MateriaMapper materiaMapper) {
        this.usuarioMapper = usuarioMapper;
        this.materiaMapper = materiaMapper;
    }

    public SesionEstudioResponse toResponse(SesionEstudio sesionEstudio) {
        return new SesionEstudioResponse(
                sesionEstudio.getId(),
                usuarioMapper.toResponse(sesionEstudio.getUsuario()),
                materiaMapper.toResponse(sesionEstudio.getMateria()),
                sesionEstudio.getFecha(),
                sesionEstudio.getHoraInicio(),
                sesionEstudio.getHoraFin(),
                sesionEstudio.getObjetivo(),
                sesionEstudio.getEstado()
        );
    }
}