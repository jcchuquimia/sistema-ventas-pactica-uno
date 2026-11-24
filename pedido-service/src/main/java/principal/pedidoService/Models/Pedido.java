package principal.pedidoService.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orden")
public class Pedido {
    @Id
    @GeneratedValue
    private Long id;
    private Long idProducto;
    private Integer cantidad;
    private Double total;
}
