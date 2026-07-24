// Interfaz del servicio de sesiones de estudio
// Define las operaciones CRUD y filtros por usuario, materia y estado
package com.PMAP.demo.service;

import com.PMAP.demo.dto.sesion.SesionEstudioCreateRequest;
import com.PMAP.demo.dto.sesion.SesionEstudioResponse;
import com.PMAP.demo.dto.sesion.SesionEstudioUpdateRequest;
import com.PMAP.demo.entity.EstadoSesionEstudio;

import java.util.List;

public interface SesionEstudioService {
    SesionEstudioResponse create(SesionEstudioCreateRequest request);

    SesionEstudioResponse update(Long id, SesionEstudioUpdateRequest request);

    SesionEstudioResponse findById(Long id);

    List<SesionEstudioResponse> findAll();

    List<SesionEstudioResponse> findByUsuario(Long usuarioId);

    List<SesionEstudioResponse> findByMateria(Long materiaId);

    List<SesionEstudioResponse> filterByEstado(EstadoSesionEstudio estado);

    void delete(Long id);
}