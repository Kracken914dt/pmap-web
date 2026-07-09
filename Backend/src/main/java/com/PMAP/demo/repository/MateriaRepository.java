package com.PMAP.demo.repository;

import com.PMAP.demo.entity.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
    boolean existsByNombreIgnoreCase(String nombre);

    List<Materia> findByNombreContainingIgnoreCase(String nombre);

    List<Materia> findByCategoriaIgnoreCase(String categoria);

    List<Materia> findByNombreContainingIgnoreCaseAndCategoriaIgnoreCase(String nombre, String categoria);
}