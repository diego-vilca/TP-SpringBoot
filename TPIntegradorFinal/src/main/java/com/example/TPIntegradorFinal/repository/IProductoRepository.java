
package com.example.TPIntegradorFinal.repository;

import com.example.TPIntegradorFinal.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long>{

}
