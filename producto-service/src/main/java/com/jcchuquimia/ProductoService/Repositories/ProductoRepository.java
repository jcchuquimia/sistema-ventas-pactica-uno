package com.jcchuquimia.ProductoService.Repositories;

import com.jcchuquimia.ProductoService.Models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
