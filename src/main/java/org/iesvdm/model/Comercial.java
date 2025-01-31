package org.iesvdm.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
	@NotBlank(message = "{error.form.NOT_BLANK}")
	@Size(max = 30, message = "{error.form.MAX_LENGTH}")
	private String nombre;
	@NotBlank(message = "{error.form.NOT_BLANK}")
	@Size(max = 30, message = "{error.form.MAX_LENGTH}")
	private String apellido1;
	private String apellido2;
	private float comision;
}
