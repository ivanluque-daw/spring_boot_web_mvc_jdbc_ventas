package org.iesvdm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comercial {
	private int id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private float comision;
}
