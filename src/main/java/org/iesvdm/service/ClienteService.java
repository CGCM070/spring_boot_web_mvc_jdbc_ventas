package org.iesvdm.service;

import java.util.List;
import java.util.Optional;

import org.iesvdm.dao.ClienteDAO;

import org.iesvdm.dao.PedidoDAOImpl;
import org.iesvdm.dto.ClienteDTO;
import org.iesvdm.dto.PedidoDTO;
import org.iesvdm.modelo.Cliente;

import org.iesvdm.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	PedidoDAOImpl pedidoDAO;


	public List<Cliente> listAll() {
		
		return clienteDAO.getAll();
		
	}
	public void  newCliente (Cliente cliente) {
		clienteDAO.create(cliente);
	}

	public Cliente findById(Integer id) {
		Optional<Cliente> optFab = clienteDAO.find(id);
		if (optFab.isPresent())
			return optFab.get();
		else
			return null;
	}
	
	public void replaceCliente (Cliente  cliente) {
		clienteDAO.update(cliente);
	}

	@Transactional
	public void deleteCliente (int id) {


		List<Pedido> listaPedido = pedidoDAO.getAllPedidoByCliId(id);
		for (Pedido pedido : listaPedido) {
			pedidoDAO.delete(pedido.getId());
		}

		clienteDAO.delete(id);
	}

	public  Optional<ClienteDTO> obtenerDatosCliente (int id) {

		//busco al cliente
		Optional<Cliente>clienteOptional = clienteDAO.find(id);

		//si existe cogemos los datos
		if (clienteOptional.isPresent()){
			Cliente cliente = clienteOptional.get();
			// busco sus pedidos
			List<PedidoDTO> pedidosDTO = pedidoDAO.getPedidoByClienteId(id);

			//construyo mi dto

			ClienteDTO clienteDetalle = ClienteDTO.builder()
					.id(cliente.getId())
					.nombre(cliente.getNombre())
					.apellido1(cliente.getApellido1())
					.ciudad(cliente.getCiudad())
					.pedidos(pedidosDTO)
					.build();


			return Optional.of(clienteDetalle);
		}

		// y si no existe vacio
		return Optional.empty();
	}





	public int cantidadPedidos (int id){
		return clienteDAO.getCantidadPedido(id) ;
	}

}
