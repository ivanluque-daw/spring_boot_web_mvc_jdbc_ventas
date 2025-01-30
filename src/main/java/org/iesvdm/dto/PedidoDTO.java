package org.iesvdm.dto;

import org.iesvdm.model.Cliente;
import org.iesvdm.model.Comercial;

import java.sql.Date;

public class PedidoDTO {
    private int id;
    private double total;
    private Date fecha;

    private Comercial comercial;
    private Cliente cliente;
}
