package org.iesvdm.dao;

import java.util.List;
import java.util.Optional;

import org.iesvdm.modelo.Cliente;
import org.iesvdm.modelo.Comercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@AllArgsConstructor
public class ComercialDAOImpl implements ComercialDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void create(Comercial cliente) {


	}

	@Override
	public List<Comercial> getAll() {
		
		List<Comercial> listComercial = jdbcTemplate.query(
                "SELECT * FROM comercial",
                (rs, rowNum) -> new Comercial(rs.getInt("id"), 
                							  rs.getString("nombre"), 
                							  rs.getString("apellido1"),
                							  rs.getString("apellido2"), 
                							  rs.getFloat("comisión"))
                						 	
        );
		
		log.info("Devueltos {} registros.", listComercial.size());
		
        return listComercial;
	}

	@Override
	public Optional<Comercial> find(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void update(Comercial cliente) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

}
