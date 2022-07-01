
package com.example.TPIntegradorFinal.repository;

import com.example.TPIntegradorFinal.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Long>{

}
