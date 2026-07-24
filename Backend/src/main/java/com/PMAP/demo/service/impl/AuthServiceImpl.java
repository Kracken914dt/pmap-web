// Implementación del servicio de autenticación
// Gestiona el registro de nuevos usuarios y el inicio de sesión con generación de JWT
package com.PMAP.demo.service.impl;

import com.PMAP.demo.dto.auth.AuthResponse;
import com.PMAP.demo.dto.auth.LoginRequest;
import com.PMAP.demo.dto.auth.RegisterRequest;
import com.PMAP.demo.dto.usuario.UsuarioResponse;
import com.PMAP.demo.entity.EstadoUsuario;
import com.PMAP.demo.entity.Usuario;
import com.PMAP.demo.exception.DuplicateResourceException;
import com.PMAP.demo.mapper.UsuarioMapper;
import com.PMAP.demo.repository.UsuarioRepository;
import com.PMAP.demo.security.JwtService;
import com.PMAP.demo.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioMapper usuarioMapper;

    public AuthServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
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

        Usuario savedUser = usuarioRepository.save(usuario);
        String token = jwtService.generateToken(new org.springframework.security.core.userdetails.User(
                savedUser.getCorreo(),
                savedUser.getContraseña(),
                java.util.List.of(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + savedUser.getRol().name()))
        ));
        return new AuthResponse(token, "Bearer", usuarioMapper.toResponse(savedUser));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.correo(), request.contraseña()));
        Usuario usuario = usuarioRepository.findByCorreoIgnoreCase(request.correo())
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas"));
        UsuarioResponse response = usuarioMapper.toResponse(usuario);
        String token = jwtService.generateToken(new org.springframework.security.core.userdetails.User(
                usuario.getCorreo(),
                usuario.getContraseña(),
                java.util.List.of(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + usuario.getRol().name()))
        ));
        return new AuthResponse(token, "Bearer", response);
    }
}