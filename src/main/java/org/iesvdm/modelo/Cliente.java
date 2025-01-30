package org.iesvdm.modelo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
	
	private long id;

	@NotBlank(message = "{error.nombre}")
	@Size(min=4, message = "{error.longitud.min}")
	@Size(max=10, message = "{error.longitud.max}")
	private String nombre;

	@NotBlank(message = "{error.nombre}")
	@Size(min=4, message = "{error.longitud.min}")
	@Size(max=10, message = "{error.longitud.max}")
	private String apellido1;

	@NotBlank(message = "{error.nombre}")
	@Size(min=4, message = "{error.longitud.min}")
	@Size(max=10, message = "{error.longitud.max}")
	private String apellido2;

	@NotBlank(message = "{error.nombre}")
	@Size(min=4, message = "{error.longitud.min}")
	@Size(max=10, message = "{error.longitud.max}")
	private String ciudad;

	@Min(value = 1000, message = "{error.tamano.min}")
	@Max(value = 10000, message = "{error.tamano.max}")
	private int categoria;
	
}
