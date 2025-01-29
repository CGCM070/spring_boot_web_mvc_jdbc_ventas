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

        pedidoDTOList.forEach(p -> {
            p.setTrimestre((int) totalTrimestre);
            p.setSemestre((int) totalSemestre);
            p.setYear((int) totalYear);
            p.setLustro((int) totalLustro);
        });

        return pedidoDTOList;

    }


}
