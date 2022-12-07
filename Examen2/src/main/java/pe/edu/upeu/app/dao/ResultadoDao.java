/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import pe.edu.upeu.app.connx.ConnS;
import pe.edu.upeu.app.modelo.ResultadoTO;
import pe.edu.upeu.app.util.ErrorLogger;

public class ResultadoDao implements ResultadoDaoI {
    Statement stmt = null;
    Vector columnNames;
    Vector visitdata;
    Connection connection = ConnS.getInstance().getConnection();
    static PreparedStatement ps;
    static ErrorLogger log = new ErrorLogger(ResultadoDao.class.getName());
    ResultSet rs = null;

    public ResultadoDao() {
        columnNames = new Vector();
        visitdata = new Vector();
    }

    @Override
    public int create(ResultadoTO d) {
        int rsId = 0;
        String[] returns = {"id_resultado"};
        String sql = "INSERT INTO resultados(nombre_partida,nombre_jugador1, nombre_jugador2,ganador, punto, estado) "
                + "VALUES(?,?,?,?,?,?)";
        int i = 0;
        try {
            ps = connection.prepareStatement(sql, returns);
            ps.setString(++i, d.getNombrePartida());
            ps.setString(++i, d.getNombreJugador1());
            ps.setString(++i, d.getNombreJugador2());
            ps.setString(++i, d.getGanador());
            ps.setInt(++i, d.getPunto());
            ps.setString(++i, d.getEstado());
            rsId = ps.executeUpdate();// 0 no o 1 si commit
            try ( ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    rsId = rs.getInt(1);
                }
                rs.close();
            }
        } catch (SQLException ex) {
            //System.err.println("create:" + ex.toString());
            log.log(Level.SEVERE, "create", ex);
        }
        return rsId;
    }

    @Override
    public int update(ResultadoTO d) {
        System.out.println("actualizar d.getNombre_partida: " + d.getNombrePartida());
        int comit = 0;
        String sql = "UPDATE resultados SET "

                + "ganador=?, "
                + "punto=?, "
                + "estado=? "
                + "WHERE id_resultado=?";
        int i = 0;
        try {
            ps = connection.prepareStatement(sql);

            ps.setString(++i, d.getGanador());
            ps.setInt(++i, d.getPunto());
            ps.setString(++i, d.getEstado());
            ps.setInt(++i, d.getIdResultado());
            comit = ps.executeUpdate();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "update", ex);
        }
        return comit;
    }

    @Override
    public List<ResultadoTO> listCmb(String filter) {
        List<ResultadoTO> ls = new ArrayList();
        ls.add(new ResultadoTO());
        ls.addAll(listarResultado());
        return ls;
    }

    @Override
    public List<ResultadoTO> listarResultado() {
        List<ResultadoTO> listarresultado = new ArrayList<>();
        String sql = "SELECT * FROM resultados";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ResultadoTO cli = new ResultadoTO();
                cli.setNombrePartida(rs.getString("nombre_partida"));
                cli.setNombreJugador1(rs.getString("nombre_jugador1"));
                cli.setNombreJugador2(rs.getString("nombre_jugador2"));
                cli.setGanador(rs.getString("ganador"));
                cli.setPunto(rs.getInt("punto"));
                cli.setEstado(rs.getString("estado"));

                cli.setIdResultado(rs.getInt("id_resultado"));
                listarresultado.add(cli);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listarresultado;
    }

    @Override
    public ResultadoTO buscarResultado(int id) {
        ResultadoTO resultado = new ResultadoTO();
        String sql = "SELECT * FROM resultados WHERE id_resultado = ?";
        System.out.println("ID: "+ id);
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("nombre_partida: "+ rs.getString("nombre_partida"));
                resultado.setIdResultado(rs.getInt("id_resultado"));
                resultado.setNombrePartida(rs.getString("nombre_partida"));
                resultado.setNombreJugador1(rs.getString("nombre_jugador1"));
                resultado.setNombreJugador2(rs.getString("nombre_jugador2"));
                resultado.setGanador(rs.getString("ganador"));
                resultado.setPunto(rs.getInt("punto"));
                resultado.setEstado(rs.getString("estado"));     
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return resultado;}
}
