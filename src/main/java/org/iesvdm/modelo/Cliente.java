package org.iesvdm.modelo;

import jakarta.validation.constraints.*;
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
	private Integer categoria;

	@NotBlank(message = "{error.email}")
	@Email(message = "{error.email.regexp}")
	private String email;
	
}
