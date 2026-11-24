package principal.pedidoService.Controllers;

import principal.pedidoService.Models.Pedido;
import principal.pedidoService.Services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido){
        return pedidoService.registrarPedido(pedido);
    }
    @GetMapping
    public List<Pedido> obtenerTodosLosPedidos(){
        return pedidoService.obtenerTodosLosPedidos();
    }
}
