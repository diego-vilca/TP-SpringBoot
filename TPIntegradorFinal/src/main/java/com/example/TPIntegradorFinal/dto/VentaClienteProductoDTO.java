
package com.example.TPIntegradorFinal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VentaClienteProductoDTO {
    private Long codigo_venta;
    private Double total;
    private Integer cantidad_productos;
    private String nombre_cliente;
    private String apellido_cliente;

    public VentaClienteProductoDTO() {
    }

    public VentaClienteProductoDTO(Long codigo_venta, Double total, Integer cantidad_productos, String nombre_cliente, String apellido_cliente) {
        this.codigo_venta = codigo_venta;
        this.total = total;
        this.cantidad_productos = cantidad_productos;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
    }
    
    
}
