package com.bbraun.pedidos.cotizacion.repository;

import com.bbraun.pedidos.cotizacion.models.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento,Integer> {


    public Departamento findByNombreDepartamento(String nombre_departamento);

    public Departamento findByIdDepartamento(Integer id_departamento);

}
