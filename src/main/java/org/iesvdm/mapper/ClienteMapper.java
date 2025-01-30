package org.iesvdm.mapper;

import org.iesvdm.dto.ClienteDTO;
import org.iesvdm.dto.ComercialDTO;
import org.iesvdm.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mapping(target = "comercialesPedidos", source = "comercialesPedidos")
    public ClienteDTO clienteToClienteDTO(Cliente cliente, List<ComercialDTO> comercialesPedidos);
    public Cliente clienteDTOToCliente(ClienteDTO clienteDTO);
}
