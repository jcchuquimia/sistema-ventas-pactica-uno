package principal.pedidoService.Repositories;

import principal.pedidoService.Models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByTotal(Double total);
}
