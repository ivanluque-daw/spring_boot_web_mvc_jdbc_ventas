package org.iesvdm.service;

import java.util.List;
import java.util.Optional;

import org.iesvdm.dao.ClienteDAO;
import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.dto.ComercialDTO;
import org.iesvdm.mapper.ComercialMapper;
import org.iesvdm.model.Cliente;
import org.iesvdm.model.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	@Autowired
	private ClienteDAO clienteDAO;
	@Autowired
	private ComercialDAO comercialDAO;
	@Autowired
	private PedidoDAO pedidoDAO;
	@Autowired
	private ComercialMapper comercialMapper;

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

	public List<ComercialDTO> findWithComerciales(long id) {
		List<Comercial> comerciales = comercialDAO.findWithPedidosByCliente(id);

		return comerciales.stream().map(comercial -> {
			long numeroPedidos = pedidoDAO.countByComercialAndCliente(comercial.getId(), id);
            return comercialMapper.comercialToComercialDTO(comercial, numeroPedidos);
		}).toList();
	}

	public void update(Cliente cliente) {
		clienteDAO.update(cliente);
	}

	public void delete(long id) {
		clienteDAO.delete(id);
	}
}
