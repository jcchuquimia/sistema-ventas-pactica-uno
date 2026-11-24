package com.jcchuquimia.pedidoService.Services;

import com.jcchuquimia.pedidoService.Config.ProductoClient;
import com.jcchuquimia.pedidoService.DTOs.Producto;
import com.jcchuquimia.pedidoService.Models.Pedido;
import com.jcchuquimia.pedidoService.Repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoReocveryService {
    private static final Double TOTAL_PENDIENTE = -1.0;

    private final PedidoRepository pedidoRepository;
    private final ProductoClient productoClient;

    public void recoverPedidosPendientes() {
        System.out.println("Recuperando pedidos pendientes...");
        List<Pedido> pedidosPendientes = pedidoRepository.findByTotal(TOTAL_PENDIENTE);

        for (Pedido pedido : pedidosPendientes) {
            try {
                Producto producto = productoClient.obtenerProductoPorId(pedido.getIdProducto());
                pedido.setTotal(producto.getPrecio() * pedido.getCantidad());
                pedidoRepository.save(pedido);
            } catch (RuntimeException ignored) {
                pedidoRepository.delete(pedido);
            }
        }
    }

    private Object obtenerProductoDePedido(Pedido pedido) {
        Object productoId = invocarGetter(pedido, "getProductoId", "getProductId", "getIdProducto");

        return invocarMetodo(
                productoClient,
                new String[]{"obtenerProducto", "obtenerProductoPorId", "getProducto", "getProductoById", "findProductoById"},
                productoId
        );
    }

    private double obtenerValorNumerico(Object objeto, String... nombresMetodos) {
        Object valor = invocarGetter(objeto, nombresMetodos);

        if (valor instanceof Number numero) {
            return numero.doubleValue();
        }

        throw new IllegalStateException("El valor obtenido no es numérico");
    }

    private Object invocarGetter(Object objeto, String... nombresMetodos) {
        for (String nombreMetodo : nombresMetodos) {
            try {
                Method metodo = objeto.getClass().getMethod(nombreMetodo);
                return metodo.invoke(objeto);
            } catch (ReflectiveOperationException ignored) {
            }
        }

        throw new IllegalStateException("No se pudo obtener el valor solicitado");
    }

    private Object invocarMetodo(Object objeto, String[] nombresMetodos, Object argumento) {
        for (String nombreMetodo : nombresMetodos) {
            for (Method metodo : objeto.getClass().getMethods()) {
                if (metodo.getName().equals(nombreMetodo) && metodo.getParameterCount() == 1) {
                    try {
                        return metodo.invoke(objeto, argumento);
                    } catch (ReflectiveOperationException ignored) {
                    }
                }
            }
        }

        throw new IllegalStateException("No se pudo consultar ProductoClient");
    }

}
