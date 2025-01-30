package org.iesvdm;

import org.iesvdm.dao.ComercialDAO;
import org.iesvdm.dao.PedidoDAO;
import org.iesvdm.dto.PedidoDTO;
import org.iesvdm.modelo.Comercial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class SpringBootWebMvcJdbcVentasApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    ComercialDAO comercialDAO;

    @Autowired
    PedidoDAO pedidoDAO;

    Comercial comercial = Comercial.builder()
            .nombre("comercial")
            .apellido1("apellido1")
            .apellido2("apellido2")
//            .comision(1231123)
            .build();

    @Test
    void insertComercial() {
        comercialDAO.create(comercial);
    }

    @Test
    public void testFechas() {
        List<PedidoDTO> pedidoDTOList = pedidoDAO.pedidoPorFechasComerciales(1);
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        // Convertir las fechas de los pedidos
        var trimestre = pedidoDTOList.stream()
                .filter(pedidoDTO -> {
                    LocalDate fechaPedido = pedidoDTO.getFecha().toLocalDate();
                    return fechaPedido.isAfter(currentDate.minusMonths(3));
                })
                .count();

        var semestre = pedidoDTOList.stream()
                .filter(pedidoDTO -> {
                    LocalDate fechaPedido = pedidoDTO.getFecha().toLocalDate();
                    return fechaPedido.isAfter(currentDate.minusMonths(6));
                })
                .count();

        var year = pedidoDTOList.stream()
                .filter(pedidoDTO -> {
                    LocalDate fechaPedido = pedidoDTO.getFecha().toLocalDate();
                    return fechaPedido.getYear() == currentYear;
                })
                .count();

        var lustro = pedidoDTOList.stream()
                .filter(pedidoDTO -> {
                    LocalDate fechaPedido = pedidoDTO.getFecha().toLocalDate();
                    return fechaPedido.isAfter(currentDate.minusYears(5));
                })
                .count();


        System.out.println("Pedidos por fechas");
        pedidoDTOList.forEach(System.out::println);

        System.out.println("trimestre: " + trimestre);
        System.out.println("semestre: " + semestre);
        System.out.println("year: " + year);
        System.out.println("lustro: " + lustro);
        System.out.println("currentYear: " + currentYear);


    }


}
