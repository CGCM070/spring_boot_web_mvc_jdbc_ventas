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

     //pq para algunos casos lo usare y otras no así solo las mapeo en las query que necesite
    @Builder.Default
    private String nombre_cliente = "";

    @Builder.Default
    private String nombre_comercial = "";
}
