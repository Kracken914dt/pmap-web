// Implementación del servicio de materias
// CRUD completo con búsqueda por nombre, filtrado por categoría y validación de unicidad
package com.PMAP.demo.service.impl;

import com.PMAP.demo.dto.materia.MateriaCreateRequest;
import com.PMAP.demo.dto.materia.MateriaResponse;
import com.PMAP.demo.dto.materia.MateriaUpdateRequest;
import com.PMAP.demo.entity.EstadoMateria;
import com.PMAP.demo.entity.Materia;
import com.PMAP.demo.exception.DuplicateResourceException;
import com.PMAP.demo.exception.ResourceNotFoundException;
import com.PMAP.demo.mapper.MateriaMapper;
import com.PMAP.demo.repository.MateriaRepository;
import com.PMAP.demo.service.MateriaService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MateriaServiceImpl implements MateriaService {

    private final MateriaRepository materiaRepository;
    private final MateriaMapper materiaMapper;

    public MateriaServiceImpl(MateriaRepository materiaRepository, MateriaMapper materiaMapper) {
        this.materiaRepository = materiaRepository;
        this.materiaMapper = materiaMapper;
    }

    @Override
    public MateriaResponse create(MateriaCreateRequest request) {
        if (materiaRepository.existsByNombreIgnoreCase(request.nombre())) {
            throw new DuplicateResourceException("Ya existe una materia con ese nombre");
        }
        Materia materia = new Materia();
        materia.setNombre(request.nombre());
        materia.setDescripcion(request.descripcion());
        materia.setCategoria(request.categoria());
        materia.setEstado(EstadoMateria.ACTIVA);
        materia.setFechaCreacion(LocalDateTime.now());
        return materiaMapper.toResponse(materiaRepository.save(materia));
    }

    @Override
    public MateriaResponse update(Long id, MateriaUpdateRequest request) {
        Materia materia = findEntityById(id);
        if (!materia.getNombre().equalsIgnoreCase(request.nombre()) && materiaRepository.existsByNombreIgnoreCase(request.nombre())) {
            throw new DuplicateResourceException("Ya existe una materia con ese nombre");
        }
        materia.setNombre(request.nombre());
        materia.setDescripcion(request.descripcion());
        materia.setCategoria(request.categoria());
        materia.setEstado(request.estado());
        return materiaMapper.toResponse(materiaRepository.save(materia));
    }

    @Override
    public MateriaResponse findById(Long id) {
        return materiaMapper.toResponse(findEntityById(id));
    }

    @Override
    public List<MateriaResponse> findAll() {
        return materiaRepository.findAll().stream().map(materiaMapper::toResponse).toList();
    }

    @Override
    public List<MateriaResponse> search(String nombre) {
        return materiaRepository.findByNombreContainingIgnoreCase(nombre).stream().map(materiaMapper::toResponse).toList();
    }

    @Override
    public List<MateriaResponse> filterByCategory(String categoria) {
        return materiaRepository.findByCategoriaIgnoreCase(categoria).stream().map(materiaMapper::toResponse).toList();
    }

    @Override
    public void activate(Long id) {
        Materia materia = findEntityById(id);
        materia.setEstado(EstadoMateria.ACTIVA);
        materiaRepository.save(materia);
    }

    @Override
    public void deactivate(Long id) {
        Materia materia = findEntityById(id);
        materia.setEstado(EstadoMateria.INACTIVA);
        materiaRepository.save(materia);
    }

    @Override
    public void delete(Long id) {
        Materia materia = findEntityById(id);
        materiaRepository.delete(materia);
    }

    private Materia findEntityById(Long id) {
        return materiaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Materia no encontrada"));
    }
}