
package Dao;
import db.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.ViajeVo;

public class ViajeDao {
    
    // OBTENER LISTA
    public List<ViajeVo> listarViajes() {
        //variables necesarias para la conexión a la base de datos
        Connection con;
        PreparedStatement pstm;
        ResultSet rs;
        List<ViajeVo> lista = new ArrayList<>();

        try {
            //obtener una conexión a la base de datos.
            con = ConexionDB.ConexionMySql();
            //procedimiento almacenado 
            pstm = con.prepareCall("call sp_mostrar_viaje()");
            rs = pstm.executeQuery();

            //recorre cada fila del conjunto de resultados.
            while (rs.next()) {
                lista.add(new ViajeVo(
            //por cada fila en los resultados, crea un objeto
            //utilizara los datos obtenidos de la base de datos y lo agrega a la lista
                        rs.getInt("id_vi"),
                        rs.getInt("id_dest"),
                        rs.getDate("salida"),
                        rs.getDate("llegada")
                ));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //Finalmente, se retorna la lista de objetos
        return lista;
    }
    
    // OBTENER POR ID
    public ViajeVo obtenerViajePorCodigo(int codigo) {
        Connection con;
        PreparedStatement pstm;
        ResultSet rs;
        ViajeVo viaje = null;//para tenerlo ya definido

        try {
            //obtener una conexión a la base de datos.
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("{call sp_viaje_by_codigo(?)}");
            pstm.setInt(1, codigo);
            rs = pstm.executeQuery();

            if (rs.next()) {
                viaje = new ViajeVo(//Nuevo objeto con los datos obtenidos
                        rs.getInt("id_vi"),
                        rs.getInt("id_dest"),
                        rs.getDate("salida"),
                        rs.getDate("llegada")
                );
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return viaje;
    }
    
    // REGISTRAR VIAJE
    public void registrarViaje(int idDestino, String salida, String llegada) {
        Connection con;
        PreparedStatement pstm;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_registrar_viaje(?, ?, ?)");
            // Agregamos 
            //los valores de los parámetros en la sentencia preparada son 3
            pstm.setInt(1, idDestino);
            pstm.setString(2, salida);
            pstm.setString(3, llegada);

            //ejecuta una actualización en la base de datos, en este caso registra
            pstm.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    // ACTUALIZAR DATOOOOSSS
    public void actualizarViaje(int idViaje, int idDestino, String salida, String llegada) {
        Connection con;
        PreparedStatement pstm;
//vacicamente es lo mismo con la diferencia del SP
        try {
            //obtener una conexión a la base de datos.
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_actualizar_Viaje(?, ?, ?, ?)");
            // Agregamos 
            pstm.setInt(1, idViaje);
            pstm.setInt(2, idDestino);
            pstm.setString(3, salida);
            pstm.setString(4, llegada);

            pstm.executeUpdate();//Actualizamos la DB
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    // ELIMINAR POR ID
    public void eliminarViaje(int idViaje) {
        Connection con;
        PreparedStatement pstm;

        try {
            //obtener una conexión a la base de datos.
            con = ConexionDB.ConexionMySql();
            //Prepara la llamada al procedimiento almacenado
            pstm = con.prepareCall("call sp_eliminar_viaje(?)");
            // Agregamos
            pstm.setInt(1, idViaje);// pasamos el valor del ID que vamos a eliminar

            pstm.executeUpdate();//Actualizamos la DB
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
