package org.iesvdm.service;

import java.util.List;
import java.util.Optional;

import org.iesvdm.dao.ClienteDAO;
import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.dao.PedidoDAOImpl;
import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Comercial;
import org.iesvdm.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	PedidoDAOImpl pedidoDAO;

	@Autowired
	ComercialDAO comercialDAO;

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

	public void deleteCliente (int id) {

		List<Comercial>listComercial = comercialDAO.getAllComercialById(id);
		List<Pedido> listaPedido = pedidoDAO.getAllPedidoCliId(id);

		for (Comercial comercial :listComercial ){
			comercialDAO.delete(comercial.getId());
		}

		for (Pedido pedido :listaPedido ){
			pedidoDAO.delete(pedido.getId());
		}


		clienteDAO.delete(id);
	}

}
