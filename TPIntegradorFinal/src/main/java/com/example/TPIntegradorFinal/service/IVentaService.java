
package com.example.TPIntegradorFinal.service;

import com.example.TPIntegradorFinal.dto.VentaClienteProductoDTO;
import com.example.TPIntegradorFinal.dto.VentaDiariaDTO;
import com.example.TPIntegradorFinal.model.Cliente;
import com.example.TPIntegradorFinal.model.Producto;
import com.example.TPIntegradorFinal.model.Venta;
import java.time.LocalDate;
import java.util.List;


public interface IVentaService {
    
    public List<Venta> getVentas();
    
    public void saveVenta(Venta venta);
    
    public void deleteVenta(Long id);
    
    public Venta findVenta(Long id);
    
    public void editVenta(Long id_original, Long id_nueva,
                            LocalDate fecha, Double total,
                            List<Producto> lista_productos, Cliente cliente);
    
    public void editVenta(Long id_venta, Venta venta_mod);
    
    public void editVenta(Venta venta);
    
    public List<Producto> listaProductos(Long id_venta);
    
    public VentaDiariaDTO ventaDiaria(LocalDate fecha);
    
    public VentaClienteProductoDTO ventaMaxima();
        
}
