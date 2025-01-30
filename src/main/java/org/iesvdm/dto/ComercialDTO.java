package org.iesvdm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComercialDTO {

    Integer comercial_id;

    @Builder.Default
    Integer  total_pedido =0;

    String nombre_comercial;

    @Builder.Default
    Double media_pedido =0.0;

    Date fecha_pedido;

}
