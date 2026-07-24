// Repositorio JPA para la entidad Usuario
// Proporciona métodos de búsqueda por correo y filtro por nombre/apellido/correo
package com.PMAP.demo.repository;

import com.PMAP.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreoIgnoreCase(String correo);

    boolean existsByCorreoIgnoreCase(String correo);

    List<Usuario> findByNombresContainingIgnoreCaseOrApellidosContainingIgnoreCaseOrCorreoContainingIgnoreCase(
            String nombres,
            String apellidos,
            String correo
    );
}