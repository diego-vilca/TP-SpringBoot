
package com.example.TPIntegradorFinal.controller;

import com.example.TPIntegradorFinal.model.Producto;
import com.example.TPIntegradorFinal.service.IProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {

    @Autowired
    private IProductoService producto_serv;
    
    //1- crear un producto;
    
    @PostMapping("/productos/crear")
    public String crearProducto(@RequestBody Producto prod){
        producto_serv.saveProducto(prod);
        
        return "Producto creado correctamente.";
    }
    
    //2- traer productos
    
    @GetMapping("/productos")
    public List<Producto> traerProductos(){
        return producto_serv.getProductos();
    }
    
    //3- buscar un producto
    
    @GetMapping("/productos/{codigo_producto}")
    public Producto buscarProducto(@PathVariable Long codigo_producto){
        return producto_serv.findProducto(codigo_producto);
    }
    
    //4- eliminar un producto
    
    @DeleteMapping("/productos/eliminar/{codigo_producto}")
    public String eliminarProducto(@PathVariable Long codigo_producto){
        return producto_serv.deleteProducto(codigo_producto);
    }
    
    //5- modificar un producto
    
    @PutMapping("/productos/editar/{codigo_producto}")
    public Producto editarProducto(@PathVariable Long codigo_producto,
            @RequestParam( required = false, name = "nombre") String nombre,
            @RequestParam( required = false, name = "marca") String marca,
            @RequestParam( required = false, name = "costo") Double costo,
            @RequestParam( required = false, name = "cantidad") Double cantidad_disponible){
        
        producto_serv.editProducto(codigo_producto, nombre, marca, costo, cantidad_disponible);
        
        return producto_serv.findProducto(codigo_producto);
    }
    
    //6- Obtener todos los productos cuya cantidad_disponible sea menor a 5
    
    @GetMapping("/productos/falta_stock")
    public List<Producto> stockProductos(){
        return producto_serv.stockProducto();
    }
    
}
