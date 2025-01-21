package org.iesvdm.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {
    private long id;
    private String nombre;
    private String apellido1;
    private String ciudad;
    private List<PedidoDTO> pedidos;
}
