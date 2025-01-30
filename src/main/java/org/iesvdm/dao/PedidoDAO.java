package org.iesvdm.dao;

import java.util.Map;

public interface PedidoDAO {
    public Map<String, Long> countByComercialAndCliente(long comercialId, long clienteId);
}
