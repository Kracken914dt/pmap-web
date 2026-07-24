// Poblador inicial de datos de prueba
// Se ejecuta al arrancar la aplicación si las tablas están vacías
package com.PMAP.demo.config;

import com.PMAP.demo.entity.EstadoMateria;
import com.PMAP.demo.entity.EstadoUsuario;
import com.PMAP.demo.entity.Materia;
import com.PMAP.demo.entity.Role;
import com.PMAP.demo.entity.Usuario;
import com.PMAP.demo.repository.MateriaRepository;
import com.PMAP.demo.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final MateriaRepository materiaRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UsuarioRepository usuarioRepository, MateriaRepository materiaRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.materiaRepository = materiaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.count() == 0) {
            usuarioRepository.saveAll(List.of(
                    createUsuario("Admin", "Principal", "admin@pmap.com", Role.ADMINISTRADOR),
                    createUsuario("Ana", "Pérez", "ana@pmap.com", Role.ESTUDIANTE),
                    createUsuario("Luis", "Gómez", "luis@pmap.com", Role.ESTUDIANTE),
                    createUsuario("Sara", "Martínez", "sara@pmap.com", Role.ESTUDIANTE),
                    createUsuario("Carlos", "López", "carlos@pmap.com", Role.ESTUDIANTE),
                    createUsuario("Marta", "Ruiz", "marta@pmap.com", Role.ESTUDIANTE),
                    createUsuario("Pedro", "Santos", "pedro@pmap.com", Role.ESTUDIANTE),
                    createUsuario("Laura", "Díaz", "laura@pmap.com", Role.ESTUDIANTE),
                    createUsuario("Julián", "Torres", "julian@pmap.com", Role.ESTUDIANTE),
                    createUsuario("Elena", "Vargas", "elena@pmap.com", Role.ESTUDIANTE)
            ));
        }

        if (materiaRepository.count() == 0) {
            materiaRepository.saveAll(List.of(
                    createMateria("Matemáticas", "Álgebra y cálculo", "Ciencias"),
                    createMateria("Lenguaje", "Lectura y escritura", "Humanidades"),
                    createMateria("Programación", "Fundamentos de desarrollo", "Tecnología"),
                    createMateria("Historia", "Historia universal", "Ciencias Sociales"),
                    createMateria("Física", "Mecánica y energía", "Ciencias"),
                    createMateria("Química", "Materia y reacciones", "Ciencias"),
                    createMateria("Inglés", "Idioma extranjero", "Idiomas"),
                    createMateria("Ética", "Valores y ciudadanía", "Humanidades"),
                    createMateria("Bases de Datos", "Modelado y SQL", "Tecnología"),
                    createMateria("Estadística", "Análisis de datos", "Ciencias")
            ));
        }
    }

    private Usuario createUsuario(String nombres, String apellidos, String correo, Role rol) {
        Usuario usuario = new Usuario();
        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);
        usuario.setCorreo(correo);
        usuario.setContraseña(passwordEncoder.encode("Password123"));
        usuario.setRol(rol);
        usuario.setEstado(EstadoUsuario.ACTIVO);
        usuario.setFechaRegistro(LocalDateTime.now());
        return usuario;
    }

    private Materia createMateria(String nombre, String descripcion, String categoria) {
        Materia materia = new Materia();
        materia.setNombre(nombre);
        materia.setDescripcion(descripcion);
        materia.setCategoria(categoria);
        materia.setEstado(EstadoMateria.ACTIVA);
        materia.setFechaCreacion(LocalDateTime.now());
        return materia;
    }
}