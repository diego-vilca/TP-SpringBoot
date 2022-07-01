
package com.example.TPIntegradorFinal.service;

import com.example.TPIntegradorFinal.model.Cliente;
import com.example.TPIntegradorFinal.repository.IClienteRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService{
    
    @Autowired
    private IClienteRepository cliente_repo;

    
    @Override
    public List<Cliente> getClientes() {
        
        List<Cliente> clientes_existentes = new ArrayList<Cliente>();
        
        for (Cliente cliente : cliente_repo.findAll()) {
            if (!cliente.getBorrado()) {
                //agrego a mi lista los clientes que no borré para mostrarlos
                clientes_existentes.add(cliente);
            }
        }
        
        return clientes_existentes;
    }

    @Override
    public void saveCliente(Cliente cliente) {
        //asigno 'borrado = false' a los clientes recién creados
        if (cliente.getBorrado() == null) {
            cliente.setBorrado(Boolean.FALSE);
        }
        cliente_repo.save(cliente);
    }

    @Override
    public String deleteCliente(Long id) {
        //busco el objeto a borrar
        Cliente cliente = this.findCliente(id);
        
        
        if (cliente.getUna_venta() == null) {
            //si cliente.getUna_venta() es null aun no esta asociado a ninguna venta y puedo borrarlo.
            
            //cambio su atributo 'borrado'
            cliente.setBorrado(Boolean.TRUE);
            //salvo los cambios
            this.saveCliente(cliente);
            
            return "Cliente borrado exitosamente.";
            
            
        }else{
            //Si el cliente no esta asociado a una venta activa, permito que sea borrado
            if (cliente.getUna_venta().getBorrado()) {
                //cambio su atributo 'borrado'
                cliente.setBorrado(Boolean.TRUE);
                //salvo los cambios
                this.saveCliente(cliente);

                    return "Cliente borrado exitosamente.";
            }else{
                return "No se pudo borrar, cliente asociado a una venta.";
            }
        }
                
    }

    @Override
    public Cliente findCliente(Long id) {
        Cliente cliente = cliente_repo.findById(id).orElse(null);
                
        //si el objeto fue previamente borrado no lo muestro
        if (Objects.equals(cliente.getBorrado(), Boolean.TRUE)) {
            return null;
        }
        
        return cliente;
    }
    
    @Override
    public void editCliente(Long id,
                            String nombre, String apellido, String dni) {
        
        Cliente cliente = this.findCliente(id);
        
        //seteo los datos del cliente
        if (nombre != null) {
            cliente.setNombre(nombre);
        }
        if(apellido != null){
            cliente.setApellido(apellido);
        }
        if(dni != null){
            cliente.setDni(dni);
        }
                               
        this.saveCliente(cliente);
        
    }
    
    @Override
    public void editCliente(Cliente cliente) {
        this.saveCliente(cliente);
    }
    
    
}
