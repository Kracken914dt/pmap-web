package com.PMAP.demo.service;

import com.PMAP.demo.dto.usuario.UsuarioCreateRequest;
import com.PMAP.demo.dto.usuario.UsuarioResponse;
import com.PMAP.demo.dto.usuario.UsuarioUpdateRequest;

import java.util.List;

public interface UsuarioService {
    UsuarioResponse create(UsuarioCreateRequest request);

    UsuarioResponse update(Long id, UsuarioUpdateRequest request);

    UsuarioResponse findById(Long id);

    List<UsuarioResponse> findAll();

    List<UsuarioResponse> search(String query);

    void activate(Long id);

    void deactivate(Long id);

    void delete(Long id);
}