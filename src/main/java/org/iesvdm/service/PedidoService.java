package org.iesvdm.service;


import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.dto.PedidoDTO;
import org.iesvdm.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoDAO pedidoDAO;

    public List<Pedido> listaPedidoByComID(int id) {
        return pedidoDAO.getAllByComId(id);
    }

}
