package com.example.TPIntegradorFinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo_producto;
    private String nombre;
    private String marca;
    private Double costo;
    private Double cantidad_disponible;
    @JsonIgnore
    private Boolean borrado;
    
    @ManyToMany (mappedBy = "lista_productos")
    @JsonIgnore
    private List<Venta> lista_ventas;

    public Producto() {
        this.lista_ventas = new ArrayList<Venta>();
    }

    public Producto(Long codigo_producto, String nombre, String marca, Double costo, Double cantidad_disponible, List<Venta> lista_ventas) {
        this.codigo_producto = codigo_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidad_disponible = cantidad_disponible;
        this.lista_ventas = lista_ventas;
        this.borrado = Boolean.FALSE;
    }
    
    
    
    
    
}
