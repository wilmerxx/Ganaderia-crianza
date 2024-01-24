package com.grupo1.ganaderiagrupo1.Excepciones;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String code;
    private HttpStatus status;
    public ResourceNotFoundException(String code, String message, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }
}
