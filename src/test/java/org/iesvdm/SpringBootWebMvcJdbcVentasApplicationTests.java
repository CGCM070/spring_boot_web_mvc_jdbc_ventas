package org.iesvdm;

import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.modelo.Comercial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootWebMvcJdbcVentasApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	ComercialDAO comercialDAO ;

	Comercial comercial = Comercial.builder()
			.nombre("comercial")
			.apellido1("apellido1")
			.apellido2("apellido2")
			.comision(1231123)
			.build();

	@Test
	void insertComercial (){
		comercialDAO.create(comercial);
	}
}
