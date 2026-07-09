package com.PMAP.demo.controller;

import com.PMAP.demo.dto.sesion.SesionEstudioCreateRequest;
import com.PMAP.demo.dto.sesion.SesionEstudioResponse;
import com.PMAP.demo.dto.sesion.SesionEstudioUpdateRequest;
import com.PMAP.demo.entity.EstadoSesionEstudio;
import com.PMAP.demo.service.SesionEstudioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sesiones")
public class SesionEstudioController {

    private final SesionEstudioService sesionEstudioService;

    public SesionEstudioController(SesionEstudioService sesionEstudioService) {
        this.sesionEstudioService = sesionEstudioService;
    }

    @PostMapping
    public ResponseEntity<SesionEstudioResponse> create(@Valid @RequestBody SesionEstudioCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sesionEstudioService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SesionEstudioResponse> update(@PathVariable Long id, @Valid @RequestBody SesionEstudioUpdateRequest request) {
        return ResponseEntity.ok(sesionEstudioService.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SesionEstudioResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(sesionEstudioService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<SesionEstudioResponse>> findAll(
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) Long materiaId,
            @RequestParam(required = false) EstadoSesionEstudio estado
    ) {
        if (usuarioId != null) {
            return ResponseEntity.ok(sesionEstudioService.findByUsuario(usuarioId));
        }
        if (materiaId != null) {
            return ResponseEntity.ok(sesionEstudioService.findByMateria(materiaId));
        }
        if (estado != null) {
            return ResponseEntity.ok(sesionEstudioService.filterByEstado(estado));
        }
        return ResponseEntity.ok(sesionEstudioService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sesionEstudioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}