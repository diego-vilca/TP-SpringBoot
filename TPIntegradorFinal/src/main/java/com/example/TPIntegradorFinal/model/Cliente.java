package com.example.TPIntegradorFinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_cliente;
    private String nombre;
    private String apellido;
    private String dni;
    @JsonIgnore
    private Boolean borrado;
    
    @OneToOne (mappedBy = "un_cliente")
    @JsonIgnore
    private Venta una_venta;
   

    public Cliente() {
    }

    public Cliente(Long id_cliente, String nombre, String apellido, String dni, Venta una_venta) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.una_venta = una_venta;
        this.borrado = Boolean.FALSE;
    }
        
    
}
