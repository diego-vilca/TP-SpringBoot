
package com.example.TPIntegradorFinal.service;

import com.example.TPIntegradorFinal.model.Cliente;
import java.util.List;


public interface IClienteService {
    
    public List<Cliente> getClientes();
    
    public void saveCliente(Cliente cliente);
    
    public String deleteCliente(Long id);
    
    public Cliente findCliente(Long id);
    
    public void editCliente(Long id,
                            String nombre, String apellido, String dni);
    
    public void editCliente(Cliente cliente);
}
