package com.PMAP.demo.service.impl;

import com.PMAP.demo.dto.sesion.SesionEstudioCreateRequest;
import com.PMAP.demo.dto.sesion.SesionEstudioResponse;
import com.PMAP.demo.dto.sesion.SesionEstudioUpdateRequest;
import com.PMAP.demo.entity.Materia;
import com.PMAP.demo.entity.SesionEstudio;
import com.PMAP.demo.entity.Usuario;
import com.PMAP.demo.entity.EstadoSesionEstudio;
import com.PMAP.demo.exception.ResourceNotFoundException;
import com.PMAP.demo.mapper.SesionEstudioMapper;
import com.PMAP.demo.repository.MateriaRepository;
import com.PMAP.demo.repository.SesionEstudioRepository;
import com.PMAP.demo.repository.UsuarioRepository;
import com.PMAP.demo.service.SesionEstudioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SesionEstudioServiceImpl implements SesionEstudioService {

    private final SesionEstudioRepository sesionEstudioRepository;
    private final UsuarioRepository usuarioRepository;
    private final MateriaRepository materiaRepository;
    private final SesionEstudioMapper sesionEstudioMapper;

    public SesionEstudioServiceImpl(SesionEstudioRepository sesionEstudioRepository, UsuarioRepository usuarioRepository, MateriaRepository materiaRepository, SesionEstudioMapper sesionEstudioMapper) {
        this.sesionEstudioRepository = sesionEstudioRepository;
        this.usuarioRepository = usuarioRepository;
        this.materiaRepository = materiaRepository;
        this.sesionEstudioMapper = sesionEstudioMapper;
    }

    @Override
    public SesionEstudioResponse create(SesionEstudioCreateRequest request) {
        SesionEstudio sesionEstudio = new SesionEstudio();
        sesionEstudio.setUsuario(findUsuario(request.usuarioId()));
        sesionEstudio.setMateria(findMateria(request.materiaId()));
        sesionEstudio.setFecha(request.fecha());
        sesionEstudio.setHoraInicio(request.horaInicio());
        sesionEstudio.setHoraFin(request.horaFin());
        sesionEstudio.setObjetivo(request.objetivo());
        sesionEstudio.setEstado(request.estado());
        return sesionEstudioMapper.toResponse(sesionEstudioRepository.save(sesionEstudio));
    }

    @Override
    public SesionEstudioResponse update(Long id, SesionEstudioUpdateRequest request) {
        SesionEstudio sesionEstudio = findEntityById(id);
        sesionEstudio.setUsuario(findUsuario(request.usuarioId()));
        sesionEstudio.setMateria(findMateria(request.materiaId()));
        sesionEstudio.setFecha(request.fecha());
        sesionEstudio.setHoraInicio(request.horaInicio());
        sesionEstudio.setHoraFin(request.horaFin());
        sesionEstudio.setObjetivo(request.objetivo());
        sesionEstudio.setEstado(request.estado());
        return sesionEstudioMapper.toResponse(sesionEstudioRepository.save(sesionEstudio));
    }

    @Override
    public SesionEstudioResponse findById(Long id) {
        return sesionEstudioMapper.toResponse(findEntityById(id));
    }

    @Override
    public List<SesionEstudioResponse> findAll() {
        return sesionEstudioRepository.findAll().stream().map(sesionEstudioMapper::toResponse).toList();
    }

    @Override
    public List<SesionEstudioResponse> findByUsuario(Long usuarioId) {
        return sesionEstudioRepository.findByUsuarioId(usuarioId).stream().map(sesionEstudioMapper::toResponse).toList();
    }

    @Override
    public List<SesionEstudioResponse> findByMateria(Long materiaId) {
        return sesionEstudioRepository.findByMateriaId(materiaId).stream().map(sesionEstudioMapper::toResponse).toList();
    }

    @Override
    public List<SesionEstudioResponse> filterByEstado(EstadoSesionEstudio estado) {
        return sesionEstudioRepository.findByEstado(estado).stream().map(sesionEstudioMapper::toResponse).toList();
    }

    @Override
    public void delete(Long id) {
        sesionEstudioRepository.delete(findEntityById(id));
    }

    private SesionEstudio findEntityById(Long id) {
        return sesionEstudioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sesión de estudio no encontrada"));
    }

    private Usuario findUsuario(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }

    private Materia findMateria(Long id) {
        return materiaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Materia no encontrada"));
    }
}