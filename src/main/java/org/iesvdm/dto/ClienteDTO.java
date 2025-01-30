package org.iesvdm.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClienteDTO {
    private long id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String ciudad;
    private int categoria;

    private List<ComercialDTO> comercialesPedidos;
}
