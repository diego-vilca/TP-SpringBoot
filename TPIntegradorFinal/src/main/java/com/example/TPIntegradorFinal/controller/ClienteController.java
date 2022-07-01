
package com.example.TPIntegradorFinal.controller;

import com.example.TPIntegradorFinal.model.Cliente;
import com.example.TPIntegradorFinal.service.IClienteService;
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
public class ClienteController {

    @Autowired
    private IClienteService cliente_serv;
    
    //1- crear un cliente;
    
    @PostMapping("/clientes/crear")
    public String crearCliente(@RequestBody Cliente cliente){
        cliente_serv.saveCliente(cliente);
        
        return "Cliente creado correctamente.";
    }
    
    //2- traer clientes
    
    @GetMapping("/clientes")
    public List<Cliente> traerClientes(){
        return cliente_serv.getClientes();
    }
    
    //3- buscar un producto
    
    @GetMapping("/clientes/{id_cliente}")
    public Cliente buscarCliente(@PathVariable Long id_cliente){
        return cliente_serv.findCliente(id_cliente);
    }
    
    //4- eliminar un producto
    
    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    public String eliminarCliente(@PathVariable Long id_cliente){
        return cliente_serv.deleteCliente(id_cliente);
        
    }
    
    //5- modificar un producto
    
    @PutMapping("/clientes/editar/{id_cliente}")
    public Cliente editarCliente(@PathVariable Long id_cliente,
            @RequestParam( required = false, name = "nombre") String nombre,
            @RequestParam( required = false, name = "apellido") String apellido,
            @RequestParam( required = false, name = "dni") String dni){
        
        cliente_serv.editCliente(id_cliente, nombre, apellido, dni);
        
        return cliente_serv.findCliente(id_cliente);
    }
       
   
}
