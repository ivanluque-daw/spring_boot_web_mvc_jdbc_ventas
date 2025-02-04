package org.iesvdm.dao;

import java.util.List;
import java.util.Optional;

import org.iesvdm.model.Comercial;

public interface ComercialDAO {
	public void create(Comercial cliente);
	
	public List<Comercial> getAll();
	public Optional<Comercial> find(long id);
	public List<Comercial> findWithPedidosByCliente(long clienteId);
	
	public void update(Comercial cliente);
	
	public void delete(long id);
}
