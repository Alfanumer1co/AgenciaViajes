
package Dao;
import db.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ClienteVo;

public class ClienteDao {
    
    // OBTENER LISTA
    public List<ClienteVo> listarClientes() {
        //variables necesarias para la conexión a la base de datos
        Connection con;
        PreparedStatement pstm;
        ResultSet rs;
        List<ClienteVo> lista = new ArrayList<>();
        try {
            //obtener una conexión a la base de datos.
            con = ConexionDB.ConexionMySql();
            //procedimiento almacenado 
            pstm = con.prepareCall("call sp_mostrar_cliente()");
            rs = pstm.executeQuery();

            //recorre cada fila del conjunto de resultados.
            while (rs.next()) {
                lista.add(new ClienteVo(
            //por cada fila en los resultados, crea un objeto
            //utilizara los datos obtenidos de la base de datos y lo agrega a la lista
                        rs.getInt("id_c"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getInt("edad"),
                        rs.getInt("dni"),
                        rs.getString("correo"),
                        rs.getInt("celular")
                ));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //se retorna la lista de objetos
        return lista;
    }
    
    // OBTENER POR ID
    public ClienteVo obtenerClientePorCodigo(int codigo) {
        Connection con;
        PreparedStatement pstm;
        ResultSet rs;
        ClienteVo cliente = null;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("{call sp_cliente_by_codigo(?)}");
            pstm.setInt(1, codigo);
            rs = pstm.executeQuery();

            //Verifica si hay una fila en el resultado
            if (rs.next()) {
                //creamos un nuevo objeto
                cliente = new ClienteVo(
                        rs.getInt("id_c"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getInt("edad"),
                        rs.getInt("dni"),
                        rs.getString("correo"),
                        rs.getInt("celular")
                );
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        //se retorna el objeto con los datos del ID solicitado
        return cliente;
    }
    
    // REGISTRAR CLIENTE
    public void registrarCliente(String nombres, String apellidos, int edad, int dni, String correo, int celular) {
        Connection con;
        PreparedStatement pstm;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_registrar_cliente(?, ?, ?, ?, ?, ?)");
            // Agregamos datos
            // los valores de los parámetros en la sentencia preparada son 6
            pstm.setString(1, nombres);
            pstm.setString(2, apellidos);
            pstm.setInt(3, edad);
            pstm.setInt(4, dni);
            pstm.setString(5, correo);
            pstm.setInt(6, celular);

            //ejecuta una actualización en la base de datos, en este caso registra
            pstm.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    // MODIFICAR CLIENTE
    public void modificarCliente(int idCliente, String nombres, String apellidos, int edad, int dni, String correo, int celular) {
        Connection con;
        PreparedStatement pstm;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_modificar_cliente(?, ?, ?, ?, ?, ?, ?)");
            // Agregamos 
            pstm.setInt(1, idCliente);
            pstm.setString(2, nombres);
            pstm.setString(3, apellidos);
            pstm.setInt(4, edad);
            pstm.setInt(5, dni);
            pstm.setString(6, correo);
            pstm.setInt(7, celular);

            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    // ELIMINAR POR ID
    public void eliminarCliente(int idCliente) {
        Connection con;
        PreparedStatement pstm;

        try {
            con = ConexionDB.ConexionMySql();
            pstm = con.prepareCall("call sp_eliminar_cliente(?)");
            // Codigo id
            pstm.setInt(1, idCliente);

            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
}
