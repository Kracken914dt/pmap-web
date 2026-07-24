// Repositorio JPA para la entidad SesionEstudio
// Proporciona filtros por usuario, materia y estado
package com.PMAP.demo.repository;

import com.PMAP.demo.entity.EstadoSesionEstudio;
import com.PMAP.demo.entity.SesionEstudio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SesionEstudioRepository extends JpaRepository<SesionEstudio, Long> {
    List<SesionEstudio> findByUsuarioId(Long usuarioId);

    List<SesionEstudio> findByMateriaId(Long materiaId);

    List<SesionEstudio> findByEstado(EstadoSesionEstudio estado);

    List<SesionEstudio> findByUsuarioIdAndEstado(Long usuarioId, EstadoSesionEstudio estado);

    List<SesionEstudio> findByMateriaIdAndEstado(Long materiaId, EstadoSesionEstudio estado);
}