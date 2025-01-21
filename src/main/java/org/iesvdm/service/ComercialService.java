package org.iesvdm.service;


import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComercialService {

    @Autowired
    ComercialDAO comercialDAO;

    @Autowired
    PedidoDAO pedidoDAO;


    public List<Comercial> listAll() {
        return comercialDAO.getAll();
    }


    public void newComercial(Comercial comercial) {
        comercialDAO.create(comercial);
    }

    public Comercial findById(Integer id) {
        Optional<Comercial> optCom = comercialDAO.find(id);
        if (optCom.isPresent())
            return optCom.get();
        else
            return null;
    }

    public void replaceComercial(Comercial comercial) {
        comercialDAO.update(comercial);
    }

    public void deleteComercial(int id) {
        //ver las agergaciones en las tablas
        List<Pedido> listaPedidos = pedidoDAO.getAllByComId(id);
        for (Pedido pedido :listaPedidos ){
            pedidoDAO.delete(pedido.getId());
        }

        comercialDAO.delete(id);
    }
}
