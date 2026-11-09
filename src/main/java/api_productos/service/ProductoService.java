
package api_productos.service;

import api_productos.model.Producto;
import api_productos.exception.ProductoNoEncontradoException;
import api_productos.exception.ProductoInvalidoException;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductoService {

    // Colección en memoria para almacenar los productos
    private final Map<Long, Producto> productos = new LinkedHashMap<>();

    // Generador automático de identificadores
    private final AtomicLong contadorId = new AtomicLong(0);

    // Constructor con productos iniciales para realizar las pruebas
    public ProductoService() {
        registrarProducto(new Producto(null, "Laptop Asus", 950.00, 15));
        registrarProducto(new Producto(null, "Mouse Razer", 25.00, 17));
        registrarProducto(new Producto(null, "Teclado Mecanico", 45.00, 9));
    }

    // Consultar todos los productos
    public List<Producto> listarProductos() {
        return new ArrayList<>(productos.values());
    }

    // Buscar un producto por su identificador
    public Producto buscarProductoPorId(Long id) {

        Producto producto = productos.get(id);

        if (producto == null) {
            throw new ProductoNoEncontradoException(
                "No se encontró un producto con el ID: " + id
            );
        }

        return producto;
    }

    // Registrar un nuevo producto
    public Producto registrarProducto(Producto producto) {

        // Validar que el producto no sea nulo
        if (producto == null) {
            throw new ProductoInvalidoException(
                "Los datos del producto son obligatorios"
            );
        }

        // Validar el nombre
        if (producto.getNombre() == null ||
            producto.getNombre().isBlank()) {

            throw new ProductoInvalidoException(
                "El nombre del producto es obligatorio"
            );
        }

        // Validar que el precio sea mayor que cero
        if (producto.getPrecio() <= 0) {
            throw new ProductoInvalidoException(
                "El precio debe ser mayor que cero"
            );
        }

        // Validar que el stock no sea negativo
        if (producto.getStock() < 0) {
            throw new ProductoInvalidoException(
                "El stock no puede ser negativo"
            );
        }

        // Generar un identificador automáticamente
        Long nuevoId = contadorId.incrementAndGet();

        // Crear el producto con su nuevo identificador
        Producto nuevoProducto = new Producto(
            nuevoId,
            producto.getNombre(),
            producto.getPrecio(),
            producto.getStock()
        );

        // Almacenar el producto en memoria
        productos.put(nuevoId, nuevoProducto);

        return nuevoProducto;
    }

    // Eliminar un producto por su identificador
    public void eliminarProducto(Long id) {

        if (!productos.containsKey(id)) {
            throw new ProductoNoEncontradoException(
                "No se puede eliminar. No existe un producto con el ID: " + id
            );
        }

        productos.remove(id);
    }
    // Actualizar un producto por su identificador
public Producto actualizarProducto(Long id, Producto producto) {

    // Verificar que el producto exista
    buscarProductoPorId(id);

    // Validar que los datos no sean nulos
    if (producto == null) {
        throw new ProductoInvalidoException(
            "Los datos del producto son obligatorios"
        );
    }

    // Validar el nombre
    if (producto.getNombre() == null ||
        producto.getNombre().isBlank()) {

        throw new ProductoInvalidoException(
            "El nombre del producto es obligatorio"
        );
    }

    // Validar el precio
    if (producto.getPrecio() <= 0) {
        throw new ProductoInvalidoException(
            "El precio debe ser mayor que cero"
        );
    }

    // Validar el stock
    if (producto.getStock() < 0) {
        throw new ProductoInvalidoException(
            "El stock no puede ser negativo"
        );
    }

    // Crear el producto actualizado conservando su ID
    Producto productoActualizado = new Producto(
        id,
        producto.getNombre(),
        producto.getPrecio(),
        producto.getStock()
    );

    // Guardar los cambios
    productos.put(id, productoActualizado);

     return productoActualizado;
}

}