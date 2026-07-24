// Excepción lanzada cuando se intenta crear un recurso con un valor único ya existente
package com.PMAP.demo.exception;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}