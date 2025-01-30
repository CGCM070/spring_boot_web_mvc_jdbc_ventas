package org.iesvdm.dao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iesvdm.dto.ClienteDTO;
import org.iesvdm.dto.ComercialDTO;
import org.iesvdm.modelo.Comercial;
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
@AllArgsConstructor
public class ComercialDAOImpl implements ComercialDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Comercial comercial) {

        String sqlInsert = """
                INSERT INTO comercial (nombre, apellido1, apellido2, comisión)
                VALUES (?,?,?,?) """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[]{"id"});
            int idx = 1;
            ps.setString(idx++, comercial.getNombre());
            ps.setString(idx++, comercial.getApellido1());
            ps.setString(idx++, comercial.getApellido2());
            ps.setBigDecimal(idx++, comercial.getComision());
            return ps;
        }, keyHolder);

        comercial.setId(keyHolder.getKey().intValue());

        log.info("Insertados {} registros.", rows);
    }

    @Override
    public List<Comercial> getAll() {
        String query = "SELECT * FROM comercial";

        var listComercial = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Comercial.class));

        log.info("Devueltos {} registros.", listComercial.size());

        return listComercial;
    }

    @Override
    public Optional<Comercial> find(int id) {

        String query = "SELECT * FROM comercial WHERE id = ? ";

        return Optional.ofNullable(jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Comercial.class), id));
    }

    @Override
    public int getCantidadPedidos(int id_comercial) {
        String sql = "SELECT COUNT(*) from comercial " +
                     "JOIN ventas.pedido p ON comercial.id = p.id_comercial " +
                     " WHERE comercial.id = ?";


        Integer cantidad = jdbcTemplate.queryForObject(sql, Integer.class, id_comercial);
        return cantidad != null ? cantidad : 0;
    }

    @Override
    public void update(Comercial comercial) {

        int rows = jdbcTemplate.update("""
                        UPDATE comercial SET 
                        				nombre = ?, 
                        				apellido1 = ?, 
                        				apellido2 = ?,
                        				comisión = ? 
                        		WHERE id = ?
                        """
                , comercial.getNombre()
                , comercial.getApellido1()
                , comercial.getApellido2()
                , comercial.getComision()
                , comercial.getId());

        log.info("Update de Comercial con {} registros actualizados.", rows);
    }

    @Override
    public void delete(long id) {

        int rows = jdbcTemplate.update("DELETE FROM comercial WHERE id = ?", id);

        log.info("Delete de Comercial con {} registros eliminados.", rows);
    }

    @Override
    public ComercialDTO totalAndMediaPedidos(int id) {
        String query = """
        SELECT 
            COUNT(*) AS total_pedido, 
             ROUND (AVG(p.total), 2) AS media_pedido
        FROM 
            pedido p
        WHERE 
            p.id_comercial = ?
    """;

        //  queryForObject para devolver un único objeto
        return jdbcTemplate.queryForObject( query, new BeanPropertyRowMapper<>(ComercialDTO.class), id);
    }

    @Override
    public List<ClienteDTO> listaPorCuantia(int id) {

        String query = """
                SELECT c.nombre, ROUND(SUM(p.total), 2)  AS cuantia
                                   FROM pedido p
                                   JOIN cliente c ON c.id = p.id_cliente
                                   WHERE p.id_comercial = ?
                                   GROUP BY c.id, c.nombre
                                   ORDER BY cuantia DESC;
                """;
        return  jdbcTemplate.query(query, new BeanPropertyRowMapper<>(ClienteDTO.class), id);
    }


}
