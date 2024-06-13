
package Dao;
import db.ConexionDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ReservaVo;

public class ReservaDao {
    
     // lISTA DE RESERVAS
    public List<ReservaVo> listarReservas() {
        Connection con;
        PreparedStatement pstm;
        ResultSet rs;
        List<ReservaVo> lista = new ArrayList<>();

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_mostrar_reserva()");
            rs = pstm.executeQuery();

            while (rs.next()) {
                lista.add(new ReservaVo(
                        rs.getInt("id_r"),
                        rs.getInt("id_cli"),
                        rs.getInt("id_via"),
                        rs.getInt("asientos"),
                        rs.getDate("fech_pa"),
                        rs.getDouble("precio_r"),
                        rs.getBoolean("est_r")
                ));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return lista;
    }
    
    // BUSCAR ID
    public ReservaVo obtenerReservaPorCodigo(int codigo) {
        Connection con;
        PreparedStatement pstm;
        ResultSet rs;
        ReservaVo reserva = null;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("{call sp_reserva_by_codigo(?)}");
            pstm.setInt(1, codigo);
            rs = pstm.executeQuery();

            if (rs.next()) {
                reserva = new ReservaVo(
                        rs.getInt("id_r"),
                        rs.getInt("id_cli"),
                        rs.getInt("id_via"),
                        rs.getInt("asientos"),
                        rs.getDate("fech_pa"),
                        rs.getDouble("precio_r"),
                        rs.getBoolean("est_r")
                );
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return reserva;
    }
    
    // OBTENER RESERVAS ACTIVAS
    public List<ReservaVo> listarReservasActivas() {
        Connection con;
        PreparedStatement pstm;
        ResultSet rs;
        List<ReservaVo> lista = new ArrayList<>();

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_reservas_activas()");
            rs = pstm.executeQuery();

            while (rs.next()) {
                lista.add(new ReservaVo(
                        rs.getInt("id_r"),
                        rs.getInt("id_cli"),
                        rs.getInt("id_via"),
                        rs.getInt("asientos"),
                        rs.getDate("fech_pa"),
                        rs.getDouble("precio_r"),
                        rs.getBoolean("est_r")
                ));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return lista;
    }
    
    // OBTENER RESERVAS INACTIVAS
    public List<ReservaVo> listarReservasInactivas() {
        Connection con;
        PreparedStatement pstm;
        ResultSet rs;
        List<ReservaVo> lista = new ArrayList<>();

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_reservas_inactivas()");
            rs = pstm.executeQuery();

            while (rs.next()) {
                lista.add(new ReservaVo(
                        rs.getInt("id_r"),
                        rs.getInt("id_cli"),
                        rs.getInt("id_via"),
                        rs.getInt("asientos"),
                        rs.getDate("fech_pa"),
                        rs.getDouble("precio_r"),
                        rs.getBoolean("est_r")
                ));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return lista;
    }
    
    // REGISTRAR RESERVA
    public void registrarReserva(int idCli, int idVia, int asientos, 
                     String fechaPago, double precio, boolean estado) {
        Connection con;
        PreparedStatement pstm;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_registrar_reserva(?, ?, ?, ?, ?, ?)");
            
            // AGREGAMOS
            pstm.setInt(1, idCli);
            pstm.setInt(2, idVia);
            pstm.setInt(3, asientos);
            pstm.setString(4, fechaPago);
            pstm.setDouble(5, precio);
            pstm.setBoolean(6, estado);

            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    // ACTUALIZAR RESERVA :P
    public void actualizarReserva(int idReserva, int idCli, int idVia, int asientos, 
                     String fechaPago, double precio, boolean estado) {
        Connection con;
        PreparedStatement pstm;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_actualizar_reserva(?, ?, ?, ?, ?, ?, ?)");
            // AGREGAMOS :'D
            pstm.setInt(1, idReserva);
            pstm.setInt(2, idCli);
            pstm.setInt(3, idVia);
            pstm.setInt(4, asientos);
            pstm.setString(5, fechaPago);
            pstm.setDouble(6, precio);
            pstm.setBoolean(7, estado);

            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    // ELIMINAR POR ID
    public void eliminarReserva(int idReserva) {
        Connection con;
        PreparedStatement pstm;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_eliminar_reserva(?)");
            // AGreasfasfb
            pstm.setInt(1, idReserva);

            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
