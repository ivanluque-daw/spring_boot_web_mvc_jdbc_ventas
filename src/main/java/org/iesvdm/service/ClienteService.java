package org.iesvdm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.iesvdm.dao.ClienteDAO;
import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.dto.ClienteDTO;
import org.iesvdm.dto.ComercialDTO;
import org.iesvdm.mapper.ClienteMapper;
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
    private ClienteMapper clienteMapper;
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

    public ClienteDTO findWithComerciales(Cliente cliente) {
        List<Comercial> comerciales = comercialDAO.findWithPedidosByCliente(cliente.getId());
        List<ComercialDTO> comercialDTOs = comerciales.stream().map(comercial -> {
            Map<String, Long> numeroPedidos = pedidoDAO.countByComercialAndCliente(comercial.getId(), cliente.getId());
            return comercialMapper.comercialToComercialDTO(comercial, numeroPedidos.get("total_pedidos"), numeroPedidos.get("pedidos_trimestre"), numeroPedidos.get("pedidos_semestre"), numeroPedidos.get("pedidos_anio"), numeroPedidos.get("pedidos_lustro"));
        }).toList();

        return clienteMapper.clienteToClienteDTO(cliente, comercialDTOs);
    }

    public void update(Cliente cliente) {
        clienteDAO.update(cliente);
    }

    public void delete(long id) {
        clienteDAO.delete(id);
    }
}
