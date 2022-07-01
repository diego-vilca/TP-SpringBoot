
package com.example.TPIntegradorFinal.service;

import com.example.TPIntegradorFinal.dto.VentaClienteProductoDTO;
import com.example.TPIntegradorFinal.dto.VentaDiariaDTO;
import com.example.TPIntegradorFinal.model.Cliente;
import com.example.TPIntegradorFinal.model.Producto;
import com.example.TPIntegradorFinal.model.Venta;
import com.example.TPIntegradorFinal.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService {
    
    @Autowired
    private IVentaRepository venta_repo;
    @Autowired
    private IProductoService producto_serv;

    @Override
    public List<Venta> getVentas() {
        List<Venta> ventas_existentes = new ArrayList<Venta>();
        
        for (Venta venta : venta_repo.findAll()) {
            if (Objects.equals(venta.getBorrado(), Boolean.FALSE)) {
                //agrego a mi lista los clientes que no borré para mostrarlos
                ventas_existentes.add(venta);
            }
        }
        
        return ventas_existentes;
    }

    @Override
    public void saveVenta(Venta venta) {
        //asigno 'borrado = false' a las ventas recién creadas
        if (venta.getBorrado() == null) {
            venta.setBorrado(Boolean.FALSE);
        }
        
        //calculo el monto total de la venta
        Double total = 0.0;
        
        for (Producto producto : venta.getLista_productos()) {
             Long id_prod = producto.getCodigo_producto();
             //Obtengo el producto cuya id recibo de postman y acumulo su monto.
             Producto prod = producto_serv.findProducto(id_prod);
             
             total += prod.getCosto();
        } 
        venta.setTotal(total);
        
        venta_repo.save(venta);
    }

    @Override
    public void deleteVenta(Long id) {
        //busco el objeto a borrar y cambio su atributo 'borrado'
        Venta venta = this.findVenta(id);
        venta.setBorrado(Boolean.TRUE);        
        
        this.saveVenta(venta);
    }

    @Override
    public Venta findVenta(Long id) {
        Venta venta = venta_repo.findById(id).orElse(null);
        return venta;
    }

    @Override
    public void editVenta(Long id_original, Long id_nueva, 
                        LocalDate fecha, Double total, 
                        List<Producto> lista_productos, Cliente cliente) {
        
        Venta venta = this.findVenta(id_original);
        
        venta.setCodigo_venta(id_nueva);
        venta.setFecha_venta(fecha);
        venta.setTotal(total);
        venta.setLista_productos(lista_productos);
        venta.setUn_cliente(cliente);
        
        this.saveVenta(venta);
    }

    @Override
    public void editVenta(Long id_venta, Venta venta_mod) {
        //venta original
        Venta venta_original = this.findVenta(id_venta);
        //venta con modificaciones
        venta_original = venta_mod;
        
        
        //guardo la venta modificada
        this.saveVenta(venta_original);
    }
    
    
    @Override
    public void editVenta(Venta venta) {
         
        this.saveVenta(venta);
    }

    @Override
    public List<Producto> listaProductos(Long id_venta) {
        Venta venta = this.findVenta(id_venta);
        return venta.getLista_productos();
    }

    @Override
    public VentaDiariaDTO ventaDiaria(LocalDate fecha) {
        List<Venta> lista_ventas = this.getVentas();
        VentaDiariaDTO registro_diario = new VentaDiariaDTO();
        Integer contador_ventas = 0;
        Double acum_ventas_diarias = 0.0;
        
        //calculo el total de ventas de la fecha y acumulo el monto total
        for (Venta venta : lista_ventas) {
            if (venta.getFecha_venta().compareTo(fecha) == 0) {
                acum_ventas_diarias += venta.getTotal();
                contador_ventas++;
            }
        }
        
        //guardo la info en el DTO y lo retorno        
        registro_diario.setCantidad_ventas_diarias(contador_ventas);
        registro_diario.setTotal_ventas_diarias(acum_ventas_diarias);
        
        return  registro_diario;
    }

    @Override
    public VentaClienteProductoDTO ventaMaxima() {
        List<Venta> listado_ventas = this.getVentas();
        VentaClienteProductoDTO venta_maxima_DTO = new VentaClienteProductoDTO();
        Long id_venta_max = 0L;
        int flag = 0;
        
        //calculo la venta máxima
        for (Venta venta : listado_ventas) {
            if (flag == 0) {
                id_venta_max = venta.getCodigo_venta();
            }else{
                if (venta.getTotal() > this.findVenta(id_venta_max).getTotal()) {
                    id_venta_max = venta.getCodigo_venta();
                }
            }
            
            flag = 1;
        }
        
        //seteo el objeto DTO
        venta_maxima_DTO.setCodigo_venta(id_venta_max);
        venta_maxima_DTO.setTotal(this.findVenta(id_venta_max).getTotal());
        venta_maxima_DTO.setCantidad_productos(this.listaProductos(id_venta_max).size());
        venta_maxima_DTO.setNombre_cliente(this.findVenta(id_venta_max).getUn_cliente().getNombre());
        venta_maxima_DTO.setApellido_cliente(this.findVenta(id_venta_max).getUn_cliente().getApellido());
        
        return venta_maxima_DTO;
    }
    
    

}
