
package com.example.TPIntegradorFinal.service;

import com.example.TPIntegradorFinal.model.Producto;
import com.example.TPIntegradorFinal.model.Venta;
import com.example.TPIntegradorFinal.repository.IProductoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService{
    
    @Autowired
    private IProductoRepository producto_repo;

    @Override
    public List<Producto> getProductos() {
        
        List<Producto> productos_existentes = new ArrayList<Producto>();
        
        for (Producto producto : producto_repo.findAll()) {
            if (!producto.getBorrado()) {
                //agrego a mi lista los productos que no borré para mostrarlos
                productos_existentes.add(producto);
            }
        }
        
        return productos_existentes;
    }

    @Override
    public void saveProducto(Producto producto) {
        //asigno 'borrado = false' a los productos recién creados
        if (producto.getBorrado() == null) {
            producto.setBorrado(Boolean.FALSE);
        }
        producto_repo.save(producto);
    }

    @Override
    public String deleteProducto(Long id) {
        //busco el objeto a borrar
        Producto producto = this.findProducto(id);
        
        if (producto.getLista_ventas().size() > 0) {
            
            for (Venta venta : producto.getLista_ventas()) {
                //si el producto esta asociado a una venta activa, no lo borro
                if(!venta.getBorrado()){
                    return "No se puede borrar este producto, esta asociado a una venta.";
                }
            }
            
        }
        //cambio su atributo 'borrado'
        producto.setBorrado(Boolean.TRUE);
        this.saveProducto(producto);
        
        return "Producto borrado exitosamente.";        
    }

    @Override
    public Producto findProducto(Long id) {
        Producto prod = producto_repo.findById(id).orElse(null);
        
        //si el objeto fue previamente borrado no lo muestro
        if (Objects.equals(prod.getBorrado(), Boolean.TRUE)) {
            return null;
        }
        
        return prod;
    }

    @Override
    public void editProducto(Long id, 
                            String nombre, String marca, 
                            Double costo, Double cantidad_disponible) {
        
        Producto producto = this.findProducto(id);
                
        if (nombre != null) {
            producto.setNombre(nombre);
        }
        if(marca != null){
            producto.setMarca(marca);
        }
        if(cantidad_disponible != null){
            producto.setCantidad_disponible(cantidad_disponible);
        }
        if(costo != null){
            producto.setCosto(costo);
        }
        
        this.saveProducto(producto);
    }

    @Override
    public void editProducto(Producto producto) {
        this.saveProducto(producto);
    }

    @Override
    public List<Producto> stockProducto() {
        List<Producto> productos_sin_stock = new ArrayList<Producto>();
        
        for (Producto producto : this.getProductos()) {
            //si un producto tiene stock menor a 5 lo agrego a mi lista
            if (producto.getCantidad_disponible() < 5) {
                productos_sin_stock.add(producto);
            }
        }
        
        return productos_sin_stock;
    }

    

}
