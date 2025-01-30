package org.iesvdm.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;


@Slf4j
@Repository
public class ClienteDAOImpl implements ClienteDAO {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Inserta en base de datos el nuevo Cliente, actualizando el id en el bean Cliente.
     */
    @Override
    public synchronized void create(Cliente cliente) {

        //Desde java15+ se tiene la triple quote """ para bloques de texto como cadenas.
        String sqlInsert = """
                INSERT INTO cliente (nombre, apellido1, apellido2, ciudad, categoría , email) 
                VALUES  (     ?,         ?,         ?,       ?,         ?,          ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        //Con recuperación de id generado
        int rows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[]{"id"});
            int idx = 1;
            ps.setString(idx++, cliente.getNombre());
            ps.setString(idx++, cliente.getApellido1());
            ps.setString(idx++, cliente.getApellido2());
            ps.setString(idx++, cliente.getCiudad());
            ps.setInt(idx++, cliente.getCategoria());
            ps.setString(idx++, cliente.getEmail());
            return ps;
        }, keyHolder);

        cliente.setId(keyHolder.getKey().intValue());

        log.info("Insertados {} registros.", rows);
    }

    /**
     * Devuelve lista con todos loa Clientes.
     */
    @Override
    public List<Cliente> getAll() {

        String query = "SELECT * FROM cliente";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Cliente.class));

//        List<Cliente> listFab = jdbcTemplate.query(
//                "SELECT * FROM cliente",
//                (rs, rowNum) -> new Cliente(rs.getInt("id"),
//                        rs.getString("nombre"),
//                        rs.getString("apellido1"),
//                        rs.getString("apellido2"),
//                        rs.getString("ciudad"),
//                        rs.getInt("categoría"),
//                        rs.getString("email")
//                )
//        );
//
//        log.info("Devueltos {} registros.", listFab.size());
//
//        return listFab;

    }

    /**
     * Devuelve Optional de Cliente con el ID dado.
     */
    @Override
    public Optional<Cliente> find(int id) {

        String query = "SELECT * FROM cliente WHERE id= ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Cliente.class), id));

    }

    /**
     * Actualiza Cliente con campos del bean Cliente según ID del mismo.
     */
    @Override
    public void update(Cliente cliente) {

        int rows = jdbcTemplate.update("""
                        UPDATE cliente SET 
                        				nombre = ?, 
                        				apellido1 = ?, 
                        				apellido2 = ?,
                        				ciudad = ?,
                        				categoría = ?,
                        				email = ?
                        		WHERE id = ?
                        """, cliente.getNombre()
                , cliente.getApellido1()
                , cliente.getApellido2()
                , cliente.getCiudad()
                , cliente.getCategoria()
                , cliente.getEmail()
                , cliente.getId());

        log.info("Update de Cliente con {} registros actualizados.", rows);

    }

    /**
     * Borra Cliente con ID proporcionado.
     */
    @Override
    public void delete(long id) {

        int rows = jdbcTemplate.update("DELETE FROM cliente WHERE id = ?", id);

        log.info("Delete de Cliente con {} registros eliminados.", rows);

    }

    @Override
    public int getCantidadPedido(int id) {

        String query = """
                SELECT COUNT(*) FROM pedido
                WHERE id_cliente = ?
                """;

        Integer cantidad = jdbcTemplate.queryForObject(query, Integer.class, id);
        return cantidad != null ? cantidad : 0;
    }


}
