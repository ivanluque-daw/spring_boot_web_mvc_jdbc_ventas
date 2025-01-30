package org.iesvdm.service;

import java.util.List;
import java.util.Optional;

import org.iesvdm.dao.ClienteDAO;
import org.iesvdm.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	@Autowired
	private ClienteDAO clienteDAO;

	public List<Cliente> listAll() {
		return clienteDAO.getAll();
	}

	public void create(Cliente cliente) {
		clienteDAO.create(cliente);
	}

	public Cliente find(long id) {
		Optional<Cliente> clienteOptional = clienteDAO.find(id);
        return clienteOptional.orElse(null);
    }

	public void update(Cliente cliente) {
		clienteDAO.update(cliente);
	}

	public void delete(long id) {
		clienteDAO.delete(id);
	}
}
