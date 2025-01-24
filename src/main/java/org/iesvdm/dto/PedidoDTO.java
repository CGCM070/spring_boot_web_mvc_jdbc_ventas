package org.iesvdm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoDTO {

    private int id;
    private double total;
    private Date fecha;
    private int id_cliente;
     private int id_comercial;

    @Builder.Default
    private String nombre_cliente = "";

    @Builder.Default
    private String nombre_comercial = "";
}
