package org.iesvdm.mapper;

import org.iesvdm.dto.ComercialDTO;
import org.iesvdm.model.Comercial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComercialMapper {
    @Mapping(target = "numeroPedidos", source = "numeroPedidos")
    public ComercialDTO comercialToComercialDTO(Comercial comercial, long numeroPedidos);
    public Comercial comercialDTOToComercial(ComercialDTO comercialDTO);
}
