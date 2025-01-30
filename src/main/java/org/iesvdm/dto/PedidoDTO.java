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

    private Integer id;
    private Double total;
    private Date fecha;
    private Integer id_cliente;
    private Integer id_comercial;

     //pq para algunos casos lo usare y en otras no, así solo las mapeo en las query las que necesite
    @Builder.Default
    private String nombre_cliente = "";

    @Builder.Default
    private String nombre_comercial = "";


    // para el tema de las fechas
    Integer trimestre;
    Integer semestre;
    Integer year;
    Integer lustro;

}
