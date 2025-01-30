package org.iesvdm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class PedidoDAOImpl implements PedidoDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long countByComercialAndCliente(long comercialId, long clienteId) {
        return 2;
    }
}
