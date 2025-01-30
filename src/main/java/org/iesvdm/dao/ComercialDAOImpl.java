package org.iesvdm.dao;

import java.util.List;
import java.util.Optional;

import org.iesvdm.model.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ComercialDAOImpl implements ComercialDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Comercial cliente) {
    }

    @Override
    public List<Comercial> getAll() {
        List<Comercial> listComercial = jdbcTemplate.query(
                "SELECT * FROM comercial",
                (rs, rowNum) -> new Comercial(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido1"),
                        rs.getString("apellido2"),
                        rs.getFloat("comisión"))

        );

        log.info("Devueltos {} registros.", listComercial.size());
        return listComercial;
    }

    @Override
    public Optional<Comercial> find(int id) {
        return Optional.empty();
    }

    @Override
    public void update(Comercial cliente) {
    }

    @Override
    public void delete(long id) {
    }

}
