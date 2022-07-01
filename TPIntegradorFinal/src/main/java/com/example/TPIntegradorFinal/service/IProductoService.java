
package com.example.TPIntegradorFinal.service;

import com.example.TPIntegradorFinal.model.Producto;
import java.util.List;


public interface IProductoService {
    
    public List<Producto> getProductos();
    
    public void saveProducto(Producto producto);
    
    public String deleteProducto(Long id);
    
    public Producto findProducto(Long id);
    
    public void editProducto(Long id,
                            String nombre, String marca,
                            Double costo, Double cantidad_disponible);
    
    public void editProducto(Producto producto);
    
    public List<Producto> stockProducto();

}
