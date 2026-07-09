package com.PMAP.demo.controller;

import com.PMAP.demo.dto.materia.MateriaCreateRequest;
import com.PMAP.demo.dto.materia.MateriaResponse;
import com.PMAP.demo.dto.materia.MateriaUpdateRequest;
import com.PMAP.demo.service.MateriaService;
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
@RequestMapping("/api/materias")
public class MateriaController {

    private final MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @PostMapping
    public ResponseEntity<MateriaResponse> create(@Valid @RequestBody MateriaCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(materiaService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaResponse> update(@PathVariable Long id, @Valid @RequestBody MateriaUpdateRequest request) {
        return ResponseEntity.ok(materiaService.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(materiaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<MateriaResponse>> findAll(@RequestParam(required = false) String nombre, @RequestParam(required = false) String categoria) {
        if (nombre != null && !nombre.isBlank()) {
            return ResponseEntity.ok(materiaService.search(nombre));
        }
        if (categoria != null && !categoria.isBlank()) {
            return ResponseEntity.ok(materiaService.filterByCategory(categoria));
        }
        return ResponseEntity.ok(materiaService.findAll());
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        materiaService.activate(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        materiaService.deactivate(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        materiaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}