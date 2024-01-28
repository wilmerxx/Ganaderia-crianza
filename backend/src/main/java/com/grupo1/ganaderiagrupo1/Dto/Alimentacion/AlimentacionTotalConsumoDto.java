package com.grupo1.ganaderiagrupo1.Dto.Alimentacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlimentacionTotalConsumoDto {
    private String nombre_suplemento;
    private int total_consumo;
}
