package org.iesvdm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComercialDTO {

    int comercial_id;

    @Builder.Default
    Integer  total_pedido =0;

    String nombre_comercial;

    @Builder.Default
    Double media_pedido =0.0;
}
