package com.jcchuquimia.pedidoService.Services;

import com.jcchuquimia.ProductoService.Models.Producto;
import com.jcchuquimia.ProductoService.Repositories.ProductoRepository;
import com.jcchuquimia.pedidoService.Config.PedidoEventPublisher;
import com.jcchuquimia.pedidoService.Config.ProductoClient;
import com.jcchuquimia.pedidoService.Models.Pedido;
import com.jcchuquimia.pedidoService.Repositories.PedidoRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    //@Autowired
    //private RestTemplate restTemplate;
    @Autowired
    private final PedidoEventPublisher pedidoEventPublisher;
    private final ProductoClient productoClient;
    @CircuitBreaker(name = "productoServiceCB", fallbackMethod = "fallbackGetProducto")
    public Pedido registrarPedido(Pedido pedido) {
        // Llamada al microservicio de productos
        //String productoURL = "http://localhost:7070/api/v1/productos/"+pedido.getIdProducto();
        //Producto producto = restTemplate.getForObject(productoURL, Producto.class);
        Producto producto = productoClient.obtenerProductoPorId(pedido.getIdProducto());
        if(producto == null) throw new RuntimeException("No se encontró el producto (conexión REST a microservicio producto-service)");
        pedido.setTotal(producto.getPrecio()*pedido.getCantidad());

        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        pedidoEventPublisher.publishPedidoCreatedEvent(
                new PedidoEvent(pedidoGuardado.getId(),pedidoGuardado.getIdProducto(),pedidoGuardado.getCantidad())
        );

        return pedidoGuardado;
    }
    public List<Pedido> obtenerTodosLosPedidos(){
        return pedidoRepository.findAll();
    }

    public Pedido fallbackGetProducto(Pedido pedido, Throwable ex){
        System.err.println("FALLBACK originado por " + ex.getMessage());
        Pedido pedidoFallback = new Pedido();
        pedidoFallback.setIdProducto(pedido.getIdProducto());
        pedidoFallback.setCantidad(pedido.getCantidad());
        pedidoFallback.setTotal(-1.0);
        return pedidoRepository.save(pedidoFallback);
    }
}