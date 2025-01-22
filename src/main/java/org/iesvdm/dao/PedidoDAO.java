package org.iesvdm.dao;

import org.iesvdm.dto.PedidoDTO;
import org.iesvdm.modelo.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoDAO {

     void create(Pedido pedido);

     List<Pedido> getAll();

     Optional<Pedido> find(int id);

     void update(Pedido pedido);

     void delete(long id);

     List<Pedido> getAllPedidoByCliId(int id_cliente);

     List<Pedido> getAllByComId(int id_comercial);

     List<PedidoDTO> getPedidoByClienteId (int id_cliente);

}
