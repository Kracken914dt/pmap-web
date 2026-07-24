// Mapper para convertir la entidad Usuario a su DTO de respuesta UsuarioResponse
package com.PMAP.demo.mapper;

import com.PMAP.demo.dto.usuario.UsuarioResponse;
import com.PMAP.demo.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getCorreo(),
                usuario.getRol(),
                usuario.getEstado(),
                usuario.getFechaRegistro()
        );
    }
}