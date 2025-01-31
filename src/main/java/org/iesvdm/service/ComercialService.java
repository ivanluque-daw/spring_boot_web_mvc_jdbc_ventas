package org.iesvdm.service;

import java.util.List;
import java.util.Optional;

import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.model.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComercialService {
    @Autowired
    private ComercialDAO comercialDAO;

    public List<Comercial> listAll() {
        return comercialDAO.getAll();
    }

    public void create(Comercial comercial) {
        comercialDAO.create(comercial);
    }

    public Comercial find(long id) {
        Optional<Comercial> comercialOptional = comercialDAO.find(id);
        return comercialOptional.orElse(null);
    }

    public void update(Comercial comercial) {
        comercialDAO.update(comercial);
    }

    public void delete(long id) {
        comercialDAO.delete(id);
    }
}
