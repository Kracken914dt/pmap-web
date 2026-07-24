// Servicio personalizado de UserDetailsService para Spring Security
// Carga los datos del usuario desde la base de datos por correo electrónico
package com.PMAP.demo.security;

import com.PMAP.demo.entity.Usuario;
import com.PMAP.demo.entity.EstadoUsuario;
import com.PMAP.demo.exception.ResourceNotFoundException;
import com.PMAP.demo.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreoIgnoreCase(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (usuario.getEstado() == EstadoUsuario.INACTIVO) {
            throw new ResourceNotFoundException("El usuario se encuentra inactivo");
        }

        return new User(
                usuario.getCorreo(),
                usuario.getContraseña(),
                List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name()))
        );
    }
}