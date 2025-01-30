package org.iesvdm.dao;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.iesvdm.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ClienteDAOImpl implements ClienteDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public synchronized void create(Cliente cliente) {
        String sqlInsert = """
                INSERT INTO cliente (nombre, apellido1, apellido2, ciudad, categoría) 
                VALUES  (     ?,         ?,         ?,       ?,         ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[]{"id"});
            int idx = 1;
            ps.setString(idx++, cliente.getNombre());
            ps.setString(idx++, cliente.getApellido1());
            ps.setString(idx++, cliente.getApellido2());
            ps.setString(idx++, cliente.getCiudad());
            ps.setInt(idx, cliente.getCategoria());
            return ps;
        }, keyHolder);
        log.info("Insertados {} registros.", rows);

        cliente.setId(keyHolder.getKey().intValue());
    }

    @Override
    public List<Cliente> getAll() {
        List<Cliente> clientes = jdbcTemplate.query(
                "SELECT * FROM cliente",
                (rs, rowNum) -> new Cliente(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("ciudad"),
                        rs.getInt("categoría")
                )
        );
        log.info("Clients: {}", clientes.size());

        return clientes;
    }

    @Override
    public Optional<Cliente> find(long id) {
        Cliente cliente = jdbcTemplate.queryForObject("SELECT * FROM cliente WHERE id = ?"
                , (rs, rowNum) -> new Cliente(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getString("ciudad"),
                        rs.getInt("categoría"))
                , id
        );

        return Optional.ofNullable(cliente);
    }

    @Override
    public void update(Cliente cliente) {
        int rows = jdbcTemplate.update("UPDATE cliente SET nombre = ?, apellido1 = ?, apellido2 = ?, ciudad = ?, categoría = ? WHERE id = ?"
                , cliente.getNombre()
                , cliente.getApellido1()
                , cliente.getApellido2()
                , cliente.getCiudad()
                , cliente.getCategoria()
                , cliente.getId()
        );
        log.info("Updated clients: {}", rows);
    }

    @Override
    public void delete(long id) {
        int rows = jdbcTemplate.update("DELETE FROM cliente WHERE id = ?", id);
        log.info("Deleted clients: {}", rows);
    }
}
