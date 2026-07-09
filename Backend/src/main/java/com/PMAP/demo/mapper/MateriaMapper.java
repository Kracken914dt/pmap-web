package com.PMAP.demo.mapper;

import com.PMAP.demo.dto.materia.MateriaResponse;
import com.PMAP.demo.entity.Materia;
import org.springframework.stereotype.Component;

@Component
public class MateriaMapper {

    public MateriaResponse toResponse(Materia materia) {
        return new MateriaResponse(
                materia.getId(),
                materia.getNombre(),
                materia.getDescripcion(),
                materia.getCategoria(),
                materia.getEstado(),
                materia.getFechaCreacion()
        );
    }
}