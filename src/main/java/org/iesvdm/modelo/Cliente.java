package org.iesvdm.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
	
	private long id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String ciudad;
	private int categoria;
	
}
