package org.iesvdm.dto;

import lombok.Data;

@Data
public class ComercialDTO {
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private float comision;

    private long numeroPedidos;
}

