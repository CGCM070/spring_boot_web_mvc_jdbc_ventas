package org.iesvdm.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.dto.PedidoDTO;
import org.iesvdm.modelo.Pedido;
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
public class PedidoDAOImpl implements PedidoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void create(Pedido pedido) {

        String sqlInsert = """
                INSERT INTO pedido (total, fecha, id_cliente, id_comercial)
                VALUES (?,?,?,?) """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[]{"id"});
            int idx = 1;
            ps.setDouble(idx++, pedido.getTotal());
            ps.setDate(idx++, pedido.getFecha());
            ps.setInt(idx++, pedido.getId_cliente());
            ps.setInt(idx++, pedido.getId_comercial());
            return ps;
        }, keyHolder);

        pedido.setId(keyHolder.getKey().intValue());

        log.info("Insertados {} registros.", rows);

    }

    @Override
    public List<Pedido> getAll() {

        List<Pedido> listPedido = jdbcTemplate.query(
                "SELECT * FROM pedido",
                (rs, rowNum) -> new Pedido(
                        rs.getInt("id"),
                        rs.getDouble("total"),
                        rs.getDate("fecha"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_comercial"))

        );

        log.info("Devueltos {} registros.", listPedido.size());

        return listPedido;


    }

    @Override
    public Optional<Pedido> find(int id) {

        Pedido pedido = jdbcTemplate
                .queryForObject("SELECT * FROM pedido WHERE id = ?"
                        , (rs, rowNum) ->

                                new Pedido(rs.getInt("id"),
                                        rs.getDouble("total"),
                                        rs.getDate("fecha"),
                                        rs.getInt("id_cliente"),
                                        rs.getInt("id_comercial"))
                        , id
                );

        if (pedido != null) {
            return Optional.of(pedido);
        } else {
            log.info("Pedido no encontrado.");
            return Optional.empty();
        }


    }

    @Override
    public void update(Pedido pedido) {

        int rows = jdbcTemplate.update("""
                        UPDATE pedido SET 
                        				total = ?, 
                        				fecha = ?, 
                        				id_cliente = ?,
                        				id_comercial = ?,  
                        		WHERE id = ?
                        """, pedido.getTotal()
                , pedido.getFecha()
                , pedido.getId_cliente()
                , pedido.getId_comercial()
                , pedido.getId());

        log.info("Update de Pedido con {} registros actualizados.", rows);

    }

    @Override
    public void delete(long id) {

        int rows = jdbcTemplate.update("DELETE FROM pedido WHERE id = ?", id);

        log.info("Delete de Pedido con {} registros eliminados.", rows);
    }


    // Obtengo todos los pedidos de clientes byClienteId
    @Override
    public List<Pedido> getAllPedidoByCliId(int id_cliente) {

        List<Pedido> listPedido = jdbcTemplate.query("SELECT * FROM pedido WHERE id_cliente = ? ",
                (rs, rowNum) ->
                        new Pedido(
                                rs.getInt("id"),
                                rs.getDouble("total"),
                                rs.getDate("fecha"),
                                rs.getInt("id_cliente"),
                                rs.getInt("id_comercial")
                        ), id_cliente
        );

        return listPedido;

    }

    // Obtengo todos los pedidos del comercial ByComercialId
    @Override
    public List<Pedido> getAllByComId(int id_comercial) {

        List<Pedido> listaPedidoCom = jdbcTemplate.query("SELECT * FROM pedido WHERE id_comercial = ? ",
                (rs, rowNum) ->
                        new Pedido(
                                rs.getInt("id"),
                                rs.getDouble("total"),
                                rs.getDate("fecha"),
                                rs.getInt("id_cliente"),
                                rs.getInt("id_comercial")
                        ), id_comercial
        );
        return listaPedidoCom;
    }


    //PARA MI DTO , SOLO CIERTOS VALORES
    @Override
    public List<PedidoDTO> getPedidoByClienteId(int id_cliente) {

        List<PedidoDTO> listPedidoDTO = jdbcTemplate.query(
                "SELECT * FROM pedido WHERE id_cliente = ?",
                (rs, rowNum) -> PedidoDTO.builder()
                        .id(rs.getInt("id"))
                        .total(rs.getDouble("total"))
                        .fecha(rs.getDate("fecha"))
                        .id_cliente(rs.getInt("id_cliente"))
                        .id_comercial(rs.getInt("id_comercial"))
                        .build(), // nombre_comercial predeterminado
                id_cliente
        );
        log.info("Devueltos {} registros para el cliente con id {}.", listPedidoDTO.size(), id_cliente);

        return listPedidoDTO;

    }

    // Datos para la vista de comercial(detalles comercial)
    @Override
    public List<PedidoDTO> getPedidoDTOforComercialDetail(int id_comercial) {
        String query = """
                SELECT
                    p.id,
                    p.total,
                    p.fecha,
                    p.id_cliente,
                    p.id_comercial,
                    cli.nombre AS nombre_cliente,
                    com.nombre AS nombre_comercial
                FROM pedido p
                         JOIN cliente cli ON p.id_cliente = cli.id
                         JOIN comercial com ON com.id = p.id_comercial
                WHERE p.id_comercial = ?
                """;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(PedidoDTO.class), id_comercial);

    }
}
