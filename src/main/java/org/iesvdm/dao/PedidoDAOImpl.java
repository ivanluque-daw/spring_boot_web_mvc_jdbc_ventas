package org.iesvdm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@Repository
public class PedidoDAOImpl implements PedidoDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Long> countByComercialAndCliente(long comercialId, long clienteId) {
        return jdbcTemplate.queryForObject(
                """
                SELECT 
                    COUNT(*) as total_pedidos,
                    SUM(CASE WHEN fecha >= DATE_SUB(CURDATE(), INTERVAL 3 MONTH) THEN 1 ELSE 0 END) AS pedidos_trimestre,
                    SUM(CASE WHEN fecha >= DATE_SUB(CURDATE(), INTERVAL 6 MONTH) THEN 1 ELSE 0 END) AS pedidos_semestre,
                    SUM(CASE WHEN fecha >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR) THEN 1 ELSE 0 END) AS pedidos_anio,
                    SUM(CASE WHEN fecha >= DATE_SUB(CURDATE(), INTERVAL 5 YEAR) THEN 1 ELSE 0 END) AS pedidos_lustro
                FROM pedido 
                WHERE id_comercial = ? AND id_cliente = ?
                """,
                (rs, rowNum) -> Map.of(
                        "total_pedidos", rs.getLong("total_pedidos"),
                        "pedidos_trimestre", rs.getLong("pedidos_trimestre"),
                        "pedidos_semestre", rs.getLong("pedidos_semestre"),
                        "pedidos_anio", rs.getLong("pedidos_anio"),
                        "pedidos_lustro", rs.getLong("pedidos_lustro")
                ),
                comercialId, clienteId
        );
    }
}
