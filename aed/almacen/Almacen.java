package aed.almacen;

import java.util.ArrayList;
import java.util.List;

public class Almacen {
    private List<Producto> productos;
    private List<Compra> compras;
    private int idCompraCounter;

    public Almacen() {
        productos = new ArrayList<>();
        compras = new ArrayList<>();
        idCompraCounter = 1;
    }

    public Producto getProducto(String productoId) {
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            if (p.getProductoId().equals(productoId)) {
                return p;
            }
        }
        return null;
    }

    public Compra getCompra(int compraId) {
        for (int i = 0; i < compras.size(); i++) {
            Compra c = compras.get(i);
            if (c.getCompraId() == compraId) {
                return c;
            }
        }
        return null;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    private int busquedaBinariaEnProductos(String id) {
        int low = 0;
        int high = productos.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Producto midProducto = productos.get(mid);
            int cmp = midProducto.getProductoId().compareTo(id);

            if (cmp == 0) {
                return mid; // Encontrado
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -(low + 1); // No encontrado, posición insert
    }

    public void reabastecerProducto(String productoId, int cantidad) {
        int index = busquedaBinariaEnProductos(productoId);

        if (index >= 0) {
            Producto p = productos.get(index);
            p.setCantidadDisponible(p.getCantidadDisponible() + cantidad);
        } else {
            int insertAt = -(index + 1);
            Producto nuevoProducto = new Producto(productoId, cantidad);
            productos.add(insertAt, nuevoProducto);
        }
    }

    public Integer pedir(String clienteId, String productoId, int cantidad) {
        int index = busquedaBinariaEnProductos(productoId);

        if (index < 0) {
            return null;
        }

        Producto p = productos.get(index);

        if (p.getCantidadDisponible() < cantidad) {
            return null;
        }

        p.setCantidadDisponible(p.getCantidadDisponible() - cantidad);

        Compra compra = new Compra(idCompraCounter, clienteId, productoId, cantidad);
        compras.add(compra);
        idCompraCounter++;

        return compra.getCompraId();
    }
    
    public List<Compra> comprasCliente(String clienteId) {
        List<Compra> resultado = new ArrayList<>();
        for (int i = 0; i < compras.size(); i++) {
            Compra c = compras.get(i);
            if (c.getClienteId().equals(clienteId)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public List<Compra> comprasProducto(String productoId) {
        List<Compra> resultado = new ArrayList<>();
        for (int i = 0; i < compras.size(); i++) {
            Compra c = compras.get(i);
            if (c.getProductoId().equals(productoId)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

}