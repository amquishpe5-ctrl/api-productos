
package api_productos.exception;

public class ProductoInvalidoException extends RuntimeException {

    public ProductoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
