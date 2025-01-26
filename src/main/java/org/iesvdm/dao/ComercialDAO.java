package org.iesvdm.dao;

import java.util.List;
import java.util.Optional;

import org.iesvdm.dto.ComercialDTO;
import org.iesvdm.modelo.Comercial;

public interface ComercialDAO {
	
	 void create(Comercial comercial);
	
	 List<Comercial> getAll();

	 Optional<Comercial>  find(int id);

	 int getCantidadPedidos (int id_comercial);
	
	 void update(Comercial comercial);
	
	 void delete(long id);

	 ComercialDTO totalAndMediaPedidos (int id);
}
