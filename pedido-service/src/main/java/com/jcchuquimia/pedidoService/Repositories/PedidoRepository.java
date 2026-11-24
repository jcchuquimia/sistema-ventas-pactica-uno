package com.jcchuquimia.pedidoService.Repositories;

import com.jcchuquimia.pedidoService.Models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
