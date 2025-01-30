package org.iesvdm.dao;

import java.util.List;
import java.util.Optional;

import org.iesvdm.model.Cliente;

public interface ClienteDAO {
	public void create(Cliente cliente);
	
	public List<Cliente> getAll();
	public Optional<Cliente> find(long id);
	
	public void update(Cliente cliente);
	
	public void delete(long id);
}
