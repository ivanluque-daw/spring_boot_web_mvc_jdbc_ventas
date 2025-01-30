package org.iesvdm.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "{msg.error.NOT_BLANK}")
    private String nombre;
    @NotBlank(message = "{msg.error.NOT_BLANK}")
    private String apellido1;
    private String apellido2;
    @NotBlank(message = "{msg.error.NOT_BLANK}")
    private String ciudad;
    @NotNull(message = "{msg.error.NOT_BLANK}")
    private int categoria;
}
