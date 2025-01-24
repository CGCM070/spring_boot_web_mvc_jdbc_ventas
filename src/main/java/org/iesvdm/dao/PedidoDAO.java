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

     // para eliminar en cascada cada pedido asociado a un cliente
     List<Pedido> getAllPedidoByCliId(int id_cliente);

     // para eliminar en cascada cada pedido asociado a un comercial
     List<Pedido> getAllByComId(int id_comercial);

     //mapea solo ciertos campos para la vista de clientes detalles (vista Cliente )
     List<PedidoDTO> getPedidoByClienteId (int id_cliente);

     // mapea solo ciertos campos para la vista de clientes detalles (vista Comercial )
     List<PedidoDTO> getPedidoDTOforComercialDetail (int id_comercial);

}
