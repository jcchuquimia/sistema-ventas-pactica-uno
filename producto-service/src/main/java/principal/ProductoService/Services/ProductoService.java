package principal.ProductoService.Services;

import principal.ProductoService.Models.Producto;
import principal.ProductoService.Repositories.ProductoRepository;
import principal.ProductoService.Config.ProductoEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductoEventPublisher productoEventPublisher;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Producto findById(Long id){
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
    }
    public Producto GuardarProducto(Producto producto){
        Producto productoGuardado = productoRepository.save(producto);
        productoEventPublisher.publicarProductoCreado(productoGuardado);
        return productoGuardado;
    }
}
