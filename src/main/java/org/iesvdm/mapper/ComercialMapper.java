package org.iesvdm.mapper;

import org.iesvdm.dto.ComercialDTO;
import org.iesvdm.model.Comercial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComercialMapper {
    @Mapping(target = "numeroPedidos", source = "numeroPedidos")
    @Mapping(target = "pedidosTrimestre", source = "pedidosTrimestre")
    @Mapping(target = "pedidosSemestre", source = "pedidosSemestre")
    @Mapping(target = "pedidosAnio", source = "pedidosAnio")
    @Mapping(target = "pedidosLustro", source = "pedidosLustro")
    public ComercialDTO comercialToComercialDTO(Comercial comercial, long numeroPedidos, long pedidosTrimestre, long pedidosSemestre, long pedidosAnio, long pedidosLustro);
    public Comercial comercialDTOToComercial(ComercialDTO comercialDTO);
}
