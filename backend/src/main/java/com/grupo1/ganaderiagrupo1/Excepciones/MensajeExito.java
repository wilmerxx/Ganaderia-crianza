package com.grupo1.ganaderiagrupo1.Excepciones;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajeExito {
    private Date fecha;
    private String message;
    private HttpStatus estado;
}
