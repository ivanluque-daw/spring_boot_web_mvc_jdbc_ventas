package org.iesvdm.dao;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.iesvdm.model.Cliente;
import org.iesvdm.model.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ComercialDAOImpl implements ComercialDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Comercial cliente) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO comercial (nombre, apellido1, apellido2, comisión) VALUES  (?, ?, ?, ?)", new String[]{"id"});
            int idx = 1;
            ps.setString(idx++, cliente.getNombre());
            ps.setString(idx++, cliente.getApellido1());
            ps.setString(idx++, cliente.getApellido2());
            ps.setFloat(idx++, cliente.getComision());
            return ps;
        }, keyHolder);

        cliente.setId(keyHolder.getKey().intValue());
    }

    @Override
    public List<Comercial> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM comercial",
                (rs, rowNum) -> new Comercial(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getFloat("comisión")
                )
        );
    }

    @Override
    public Optional<Comercial> find(long id) {
        Comercial comercial = jdbcTemplate.queryForObject("SELECT * FROM comercial WHERE id = ?"
                , (rs, rowNum) -> new Comercial(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getFloat("comisión"))
                , id
        );

        return Optional.ofNullable(comercial);
    }

    @Override
    public List<Comercial> findWithPedidosByCliente(long clienteId) {
        List<Comercial> comerciales = jdbcTemplate.query(
                "SELECT c.id, c.nombre, c.apellido1, c.apellido2, c.comisión FROM comercial as c JOIN pedido as p ON c.id = p.id_comercial WHERE p.id_cliente = ? GROUP BY c.id",
                (rs, rowNum) -> new Comercial(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getFloat("comisión")),
                clienteId
        );

        return comerciales;
    }

    @Override
    public void update(Comercial comercial) {
        jdbcTemplate.update("UPDATE comercial SET nombre = ?, apellido1 = ?, apellido2 = ?, comisión = ? WHERE id = ?"
                , comercial.getNombre()
                , comercial.getApellido1()
                , comercial.getApellido2()
                , comercial.getComision()
                , comercial.getId()
        );
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM comercial WHERE id = ?", id);
    }
}
