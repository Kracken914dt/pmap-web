// Implementación del servicio de usuarios
// CRUD completo con búsqueda, activación/desactivación y validación de unicidad de correo
package com.PMAP.demo.service.impl;

import com.PMAP.demo.dto.usuario.UsuarioCreateRequest;
import com.PMAP.demo.dto.usuario.UsuarioResponse;
import com.PMAP.demo.dto.usuario.UsuarioUpdateRequest;
import com.PMAP.demo.entity.EstadoUsuario;
import com.PMAP.demo.entity.Usuario;
import com.PMAP.demo.exception.DuplicateResourceException;
import com.PMAP.demo.exception.ResourceNotFoundException;
import com.PMAP.demo.mapper.UsuarioMapper;
import com.PMAP.demo.repository.UsuarioRepository;
import com.PMAP.demo.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public UsuarioResponse create(UsuarioCreateRequest request) {
        if (usuarioRepository.existsByCorreoIgnoreCase(request.correo())) {
            throw new DuplicateResourceException("El correo ya está registrado");
        }
        Usuario usuario = new Usuario();
        usuario.setNombres(request.nombres());
        usuario.setApellidos(request.apellidos());
        usuario.setCorreo(request.correo());
        usuario.setContraseña(passwordEncoder.encode(request.contraseña()));
        usuario.setRol(request.rol());
        usuario.setEstado(EstadoUsuario.ACTIVO);
        usuario.setFechaRegistro(LocalDateTime.now());
        return usuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioResponse update(Long id, UsuarioUpdateRequest request) {
        Usuario usuario = findEntityById(id);
        if (!usuario.getCorreo().equalsIgnoreCase(request.correo()) && usuarioRepository.existsByCorreoIgnoreCase(request.correo())) {
            throw new DuplicateResourceException("El correo ya está registrado");
        }
        usuario.setNombres(request.nombres());
        usuario.setApellidos(request.apellidos());
        usuario.setCorreo(request.correo());
        usuario.setRol(request.rol());
        usuario.setEstado(request.estado());
        if (request.contraseña() != null && !request.contraseña().isBlank()) {
            usuario.setContraseña(passwordEncoder.encode(request.contraseña()));
        }
        return usuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioResponse findById(Long id) {
        return usuarioMapper.toResponse(findEntityById(id));
    }

    @Override
    public List<UsuarioResponse> findAll() {
        return usuarioRepository.findAll().stream().map(usuarioMapper::toResponse).toList();
    }

    @Override
    public List<UsuarioResponse> search(String query) {
        return usuarioRepository.findByNombresContainingIgnoreCaseOrApellidosContainingIgnoreCaseOrCorreoContainingIgnoreCase(query, query, query)
                .stream().map(usuarioMapper::toResponse).toList();
    }

    @Override
    public void activate(Long id) {
        Usuario usuario = findEntityById(id);
        usuario.setEstado(EstadoUsuario.ACTIVO);
        usuarioRepository.save(usuario);
    }

    @Override
    public void deactivate(Long id) {
        Usuario usuario = findEntityById(id);
        usuario.setEstado(EstadoUsuario.INACTIVO);
        usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Long id) {
        Usuario usuario = findEntityById(id);
        usuarioRepository.delete(usuario);
    }

    private Usuario findEntityById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }
}