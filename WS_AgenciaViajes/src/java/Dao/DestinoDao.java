
package Dao;
import db.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.DestinoVo;

public class DestinoDao {
    
    // OBTENER LISTA
    public List<DestinoVo> listarDestinos() {
        Connection con;
        PreparedStatement pstm;
        ResultSet rs;
        List<DestinoVo> lista = new ArrayList<>();

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_mostrar_destino()");
            rs = pstm.executeQuery();

            while (rs.next()) {
                lista.add(new DestinoVo(
                        rs.getInt("id_d"),
                        rs.getString("nombre"),
                        rs.getString("descrip"),
                        rs.getDouble("precio")
                ));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return lista;
    }
    
    // OBTENER POR ID....NO COMPILA ASI COMO MI RELACION CON ELLA :C
    // nota del Editor: Ya funciona el codigo
    public DestinoVo obtenerDestinoPorCodigo(int codigo) {
        Connection con;
        PreparedStatement pstm;
        ResultSet rs;
        DestinoVo destino = null;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("{call sp_destino_by_codigo(?)}");
            pstm.setInt(1, codigo);
            rs = pstm.executeQuery();

            if (rs.next()) {
                destino = new DestinoVo(
                        rs.getInt("id_d"),
                        rs.getString("nombre"),
                        rs.getString("descrip"),
                        rs.getDouble("precio")
                );
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return destino;
    }
    
    // REGISTRAR DESTINO
    public void registrarDestino(String nombre, String descrip, double precio) {
        Connection con;
        PreparedStatement pstm;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_registrar_destino(?, ?, ?)");
            // Agregamos 
            pstm.setString(1, nombre);
            pstm.setString(2, descrip);
            pstm.setDouble(3, precio);

            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    // MODIFICARRRR
    public void modificarDestino(int idDestino, String nombre, String descrip, double precio) {
        Connection con;
        PreparedStatement pstm;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_modificar_destino(?, ?, ?, ?)");
            // Agregamos
            pstm.setInt(1, idDestino);
            pstm.setString(2, nombre);
            pstm.setString(3, descrip);
            pstm.setDouble(4, precio);

            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    // ELIMINAR POR ID
    public void eliminarDestino(int idDestino) {
        Connection con;
        PreparedStatement pstm;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_eliminar_Destino(?)");
            // Agregamos el id
            pstm.setInt(1, idDestino);

            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    
    
}
