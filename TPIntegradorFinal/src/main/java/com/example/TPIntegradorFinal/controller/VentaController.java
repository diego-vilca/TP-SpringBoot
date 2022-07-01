
package com.example.TPIntegradorFinal.controller;

import com.example.TPIntegradorFinal.dto.VentaClienteProductoDTO;
import com.example.TPIntegradorFinal.dto.VentaDiariaDTO;
import com.example.TPIntegradorFinal.model.Producto;
import com.example.TPIntegradorFinal.model.Venta;
import com.example.TPIntegradorFinal.service.IVentaService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentaController {
    
    @Autowired
    private IVentaService venta_serv;
    
    
    //1- crear una venta;
    
    @PostMapping("/ventas/crear")
    public String crearVenta(@RequestBody Venta venta){
        venta_serv.saveVenta(venta);
        
        return "Venta creada correctamente.";
    }
    
    //2- traer ventas
    
    @GetMapping("/ventas")
    public List<Venta> traerVentas(){
        return venta_serv.getVentas();
    }
    
    //3- buscar una venta
    
    @GetMapping("/ventas/{codigo_venta}")
    public Venta buscarVenta(@PathVariable Long codigo_venta){
        return venta_serv.findVenta(codigo_venta);
    }
    
    //4- eliminar una venta
    
    @DeleteMapping("/ventas/eliminar/{codigo_venta}")
    public String eliminarVenta(@PathVariable Long codigo_venta){
        venta_serv.deleteVenta(codigo_venta);
        
        return "Venta eliminada correctamente.";
    }
 
    
    //5- modificar una venta
    
    @PutMapping("/ventas/editar/{codigo_venta}")
    public Venta editarVenta(@PathVariable Long codigo_venta,
            @RequestBody Venta venta_mod){
                
        venta_serv.editVenta(codigo_venta, venta_mod);
        
        return venta_serv.findVenta(codigo_venta);
    }
    
    //6- obtener lista de productos de una venta
    
    @GetMapping("/ventas/productos/{codigo_venta}")
    public List<Producto> listaProductos(@PathVariable Long codigo_venta){
        return venta_serv.listaProductos(codigo_venta);
    }
    
    //7- obtener la sumatoria del monto y también cantidad total 
    //   de ventas de un determinado día
    
    //modifico el path "/ventas/{fecha_venta}" para evitar ambiguedad de métodos
    @GetMapping("/ventas/date/{fecha_venta}")
    public VentaDiariaDTO ventaDiaria(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha_venta){
        return venta_serv.ventaDiaria(fecha_venta);
    }
     
    //8- Obtener el codigo_venta, el total, la cantidad de productos, el nombre del cliente y el
    //apellido del cliente de la venta con el monto más alto de todas.
    
    @GetMapping("/ventas/mayor_venta")
    public VentaClienteProductoDTO ventaMaxima(){
        return venta_serv.ventaMaxima();
    }

}
