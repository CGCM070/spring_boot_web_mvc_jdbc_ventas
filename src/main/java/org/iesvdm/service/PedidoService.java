package org.iesvdm.service;

import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.dto.ComercialDTO;
import org.iesvdm.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoDAO pedidoDAO;

    public List<PedidoDTO> commercialDetail (int id){
        return pedidoDAO.getPedidoDTOforComercialDetail(id);
    }


    public List<ComercialDTO> getComercialesYConteoDePedidos (int id){
        return pedidoDAO.getComercialesYConteoDePedidos(id);
    }

    public List<PedidoDTO> resumenComercialPedidoFechas(int id_cliente) {
        List<PedidoDTO> pedidoDTOList = pedidoDAO.pedidoPorFechasComerciales(id_cliente);

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        //cantidad de pedidos en cada periodo
        long totalTrimestre = pedidoDTOList.stream()
                .filter(pedido -> pedido.getFecha().toLocalDate().isAfter(currentDate.minusMonths(3)))
                .count();

        long totalSemestre = pedidoDTOList.stream()
                .filter(pedido -> pedido.getFecha().toLocalDate().isAfter(currentDate.minusMonths(6)))
                .count();

        long totalYear = pedidoDTOList.stream()
                .filter(pedido -> pedido.getFecha().toLocalDate().getYear() == currentYear)
                .count();

        long totalLustro = pedidoDTOList.stream()
                .filter(pedido -> pedido.getFecha().toLocalDate().isAfter(currentDate.minusYears(5)))
                .count();

        // Asignar estos valores a cada pedido
        return pedidoDTOList.stream()
                .map(pedidoDTO -> PedidoDTO.builder()

                        .id(pedidoDTO.getId())
                        .fecha(pedidoDTO.getFecha())
                        .total(pedidoDTO.getTotal())
                        .id_cliente(pedidoDTO.getId_cliente())
                        .id_comercial(pedidoDTO.getId_comercial())
                        .nombre_cliente(pedidoDTO.getNombre_cliente())
                        .nombre_comercial(pedidoDTO.getNombre_comercial())
                        .trimestre( (int) totalTrimestre)
                        .semestre((int) totalSemestre)
                        .year((int) totalYear)
                        .lustro((int) totalLustro)
                        .build())
                .toList();
    }


}
