
package Dao;
import db.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.VentaVo;

public class VentaDao {
    
    // OBTENER LISTA
    public List<VentaVo> listarVentas() {
        Connection con;
        PreparedStatement pstm;
        ResultSet rs;
        List<VentaVo> lista = new ArrayList<>();

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_mostrar_venta()");
            rs = pstm.executeQuery();

            while (rs.next()) {
                lista.add(new VentaVo(
                        rs.getInt("id_ve"),
                        rs.getInt("id_res"),
                        rs.getString("metodoPag"),
                        rs.getDouble("pago"),
                        rs.getDate("fech_v")
                ));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return lista;
    }
    
    // BUSCAR POR ID
    public VentaVo obtenerVentaPorCodigo(int codigo) {
        Connection con;
        PreparedStatement pstm;
        ResultSet rs;
        VentaVo venta = null;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("{call sp_venta_by_codigo(?)}");
            pstm.setInt(1, codigo);
            rs = pstm.executeQuery();

            if (rs.next()) {
                venta = new VentaVo(
                        rs.getInt("id_ve"),
                        rs.getInt("id_res"),
                        rs.getString("metodoPag"),
                        rs.getDouble("pago"),
                        rs.getDate("fech_v")
                );
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return venta;
    }
    
    // REGISTRAR VENTA
    public void registrarVenta(int idRes, 
            String metodoPago, double pago, String fechaVenta) {
        Connection con;
        PreparedStatement pstm;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_registrar_venta(?, ?, ?, ?)");
            // Agregamos datos la extrano :c
            pstm.setInt(1, idRes);
            pstm.setString(2, metodoPago);
            pstm.setDouble(3, pago);
            pstm.setString(4, fechaVenta);

            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    // ACTUALIZAR VENTA
    public void actualizarVenta(int idVenta, int idRes, String metodoPago, double pago, String fechaVenta) {
        Connection con;
        PreparedStatement pstm;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_actualizar_venta(?, ?, ?, ?, ?)");
            // AgreGAAAAA
            pstm.setInt(1, idVenta);
            pstm.setInt(2, idRes);
            pstm.setString(3, metodoPago);
            pstm.setDouble(4, pago);
            pstm.setString(5, fechaVenta);

            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    // ELIMINAR POR ID
    public void eliminarVenta(int idVenta) {
        Connection con;
        PreparedStatement pstm;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_eliminar_venta(?)");
            // Codigo
            pstm.setInt(1, idVenta);

            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
