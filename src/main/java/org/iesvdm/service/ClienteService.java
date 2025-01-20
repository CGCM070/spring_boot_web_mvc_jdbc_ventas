package org.iesvdm.service;

import java.util.List;
import java.util.Optional;

import org.iesvdm.dao.ClienteDAO;
import org.iesvdm.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	private ClienteDAO clienteDAO;

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
		//ver las agergaciones en las tablas
		clienteDAO.delete(id);
	}

}
