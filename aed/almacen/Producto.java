package aed.almacen;

public class Producto {
    private String productoId;
    private int cantidadDisponible;

    // Constructor
    public Producto(String productoId, int cantidadDisponible) {
        this.productoId = productoId;
        this.cantidadDisponible = cantidadDisponible;
    }

    // Getters y setters
    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    // Para representar el producto como texto
    @Override
    public String toString() {
        return "Producto{" +
                "productoId='" + productoId + '\'' +
                ", cantidadDisponible=" + cantidadDisponible +
                '}';
    }
}