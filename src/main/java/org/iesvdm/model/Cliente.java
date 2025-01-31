package org.iesvdm.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private long id;
    @NotBlank(message = "{error.form.NOT_BLANK}")
    @Size(max = 30, message = "{error.form.MAX_LENGTH}")
    private String nombre;
    @NotBlank(message = "{error.form.NOT_BLANK}")
    @Size(max = 30, message = "{error.form.MAX_LENGTH}")
    private String apellido1;
    private String apellido2;
    @NotBlank(message = "{error.form.NOT_BLANK}")
    @Size(max = 50, message = "{error.form.MAX_LENGTH}")
    private String ciudad;
    @NotNull(message = "{error.form.NOT_BLANK}")
    @Min(value = 100, message = "{error.form.MIN}")
    @Max(value = 1000, message = "{error.form.MAX}")
    private int categoria;
}
