package org.wilmer.colamuertamongo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogsDto {
    private String id;
    private String message;
    private LocalDateTime fecha_creacion;
}
