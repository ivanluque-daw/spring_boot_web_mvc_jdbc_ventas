package org.iesvdm.dao;

public interface PedidoDAO {
    public long countByComercialAndCliente(long comercialId, long clienteId);
}
