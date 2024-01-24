package com.grupo1.ganaderiagrupo1.Excepciones;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class ApiError {
    private HttpStatus status;
    private String message;
    private String code;
    private Date timestamp;

    public ApiError(Date tiemestamp,String code, String message,HttpStatus status) {
        this.timestamp = tiemestamp;
        this.status = status;
        this.message = message;
        this.code = code;
    }

    // getters and setters
}
