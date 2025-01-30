package org.iesvdm.modelo;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comercial {

	private int id;


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


	@DecimalMin(value = "0.276", message = "La comisión debe ser al menos 0.276")
	@DecimalMax(value = "0.946", message = "La comisión no puede ser mayor que 0.946")
	private BigDecimal comision;
	
}
