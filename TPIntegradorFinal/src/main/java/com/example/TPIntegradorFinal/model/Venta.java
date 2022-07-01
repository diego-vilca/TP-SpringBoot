package com.example.TPIntegradorFinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total;
    @JsonIgnore
    private Boolean borrado;
    
    @ManyToMany
    @JoinTable(
            name = "venta_producto",
            joinColumns = @JoinColumn(name = "FK_VENTA", nullable = false),
            inverseJoinColumns = @JoinColumn (name = "FK_PRODUCTO", nullable = false)
    )
    private List<Producto> lista_productos;
    
    @OneToOne
    @JoinColumn (name = "FK_CLIENTE", referencedColumnName = "id_cliente")
    private Cliente un_cliente;
    
       
    
    public Venta() {
        this.lista_productos = new ArrayList<Producto>();
    }

    public Venta(Long codigo_venta, LocalDate fecha_venta, Double total, List<Producto> lista_productos, Cliente un_cliente) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.lista_productos = lista_productos;
        this.un_cliente = un_cliente;
        this.borrado = Boolean.FALSE;
    }
    
}
