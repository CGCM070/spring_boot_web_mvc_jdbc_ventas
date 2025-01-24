package org.iesvdm.service;

import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoDAO pedidoDAO;

    public List<PedidoDTO> commercialDetail (int id){
        return pedidoDAO.getPedidoDTOforComercialDetail(id);
    }
}
