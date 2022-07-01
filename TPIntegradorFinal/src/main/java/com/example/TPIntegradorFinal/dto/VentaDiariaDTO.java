
package com.example.TPIntegradorFinal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VentaDiariaDTO {
    private Integer cantidad_ventas_diarias;
    private Double total_ventas_diarias;

    public VentaDiariaDTO() {
    }

    public VentaDiariaDTO(Integer cantidad_ventas_diarias, Double total_ventas_diarias) {
        this.cantidad_ventas_diarias = cantidad_ventas_diarias;
        this.total_ventas_diarias = total_ventas_diarias;
    }
    
    
}
