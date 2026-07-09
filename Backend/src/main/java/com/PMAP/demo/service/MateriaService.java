package com.PMAP.demo.service;

import com.PMAP.demo.dto.materia.MateriaCreateRequest;
import com.PMAP.demo.dto.materia.MateriaResponse;
import com.PMAP.demo.dto.materia.MateriaUpdateRequest;

import java.util.List;

public interface MateriaService {
    MateriaResponse create(MateriaCreateRequest request);

    MateriaResponse update(Long id, MateriaUpdateRequest request);

    MateriaResponse findById(Long id);

    List<MateriaResponse> findAll();

    List<MateriaResponse> search(String nombre);

    List<MateriaResponse> filterByCategory(String categoria);

    void activate(Long id);

    void deactivate(Long id);

    void delete(Long id);
}