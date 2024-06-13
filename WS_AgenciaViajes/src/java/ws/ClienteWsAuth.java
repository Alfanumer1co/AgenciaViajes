package ws;

import Dao.ClienteDao;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import model.ClienteVo;

@WebService(serviceName = "ClienteWsAuth")
public class ClienteWsAuth {

    @Resource
    private WebServiceContext wsc;
    private ClienteDao dao = new ClienteDao();
    // LISTAR
    @WebMethod(operationName = "listarClientes")
    public List<ClienteVo> listarClientes() {
        List<ClienteVo> lista = null;
        String usuario = "";
        String clave = "";

        MessageContext mc = wsc.getMessageContext();
        Map requeHeader = (Map) mc.get(MessageContext.HTTP_REQUEST_HEADERS);
        List user = (List) requeHeader.get("USERNAME");
        List password = (List) requeHeader.get("PASSWORD");

        if (user != null && password != null) {
            usuario = user.get(0).toString();
            clave = password.get(0).toString();
        }

        if (usuario.equals("admin") && clave.equals("12345678")) {
            lista = dao.listarClientes();
        }
        return lista;
    }

    // BUSCAR
    @WebMethod(operationName = "buscar_cliente_por_id")
    public ClienteVo buscarClientePorId(@WebParam(name = "idCliente") int idCliente) {
        ClienteVo cliente = new ClienteVo();
        String usuario = "";
        String clave = "";
        MessageContext mc = wsc.getMessageContext();
        Map requestHeaders = (Map) mc.get(MessageContext.HTTP_REQUEST_HEADERS);
        List user = (List) requestHeaders.get("USERNAME");
        List password = (List) requestHeaders.get("PASSWORD");

        if (user != null && password != null) {
            usuario = user.get(0).toString();
            clave = password.get(0).toString();
        }

        if (usuario.equals("admin") && clave.equals("12345678")) {
            cliente = dao.obtenerClientePorCodigo(idCliente);
        } else {
            cliente.setId_c(0);
            cliente.setNombres("Sin Acceso");
            cliente.setApellidos("Sin Acceso");
            cliente.setEdad(0);
            cliente.setDni(0);
            cliente.setCorreo("Sin Acceso");
            cliente.setCelular(0);
        }
        return cliente;
    }

    // ReGISTRO
    @WebMethod(operationName = "registrarCliente")
    public String registrarCliente(
            @WebParam(name = "nombres") String nombres,
            @WebParam(name = "apellidos") String apellidos,
            @WebParam(name = "edad") int edad,
            @WebParam(name = "dni") int dni,
            @WebParam(name = "correo") String correo,
            @WebParam(name = "celular") int celular) {

        String usuario = "";
        String clave = "";

        MessageContext mc = wsc.getMessageContext();
        java.util.Map requeHeader = (java.util.Map) mc.get(MessageContext.HTTP_REQUEST_HEADERS);
        java.util.List user = (java.util.List) requeHeader.get("USERNAME");
        java.util.List password = (java.util.List) requeHeader.get("PASSWORD");

        if (user != null && password != null) {
            usuario = user.get(0).toString();
            clave = password.get(0).toString();
        }

        if (usuario.equals("admin") && clave.equals("12345678")) {
            dao.registrarCliente(nombres, apellidos, edad, dni, correo, celular);
            return "Registro exitoso";
        } else {
            return "Acceso no autorizado";
        }
    }

    // MODIFICAR CLIENTE
    @WebMethod(operationName = "modificarCliente")
    public String modificarCliente(
            @WebParam(name = "idCliente") int idCliente,
            @WebParam(name = "nombres") String nombres,
            @WebParam(name = "apellidos") String apellidos,
            @WebParam(name = "edad") int edad,
            @WebParam(name = "dni") int dni,
            @WebParam(name = "correo") String correo,
            @WebParam(name = "celular") int celular) {

        String usuario = "";
        String clave = "";

        MessageContext mc = wsc.getMessageContext();
        java.util.Map requeHeader = (java.util.Map) mc.get(MessageContext.HTTP_REQUEST_HEADERS);
        java.util.List user = (java.util.List) requeHeader.get("USERNAME");
        java.util.List password = (java.util.List) requeHeader.get("PASSWORD");

        if (user != null && password != null) {
            usuario = user.get(0).toString();
            clave = password.get(0).toString();
        }

        if (usuario.equals("admin") && clave.equals("12345678")) {
            dao.modificarCliente(idCliente, nombres, apellidos, edad, dni, correo, celular);
            return "Modificación exitosa";
        } else {
            return "Acceso no autorizado";
        }
    }

    // ELIMINAR CLIENTE
    @WebMethod(operationName = "eliminarCliente")
    public String eliminarCliente(@WebParam(name = "idCliente") int idCliente) {

        String usuario = "";
        String clave = "";

        MessageContext mc = wsc.getMessageContext();
        java.util.Map requeHeader = (java.util.Map) mc.get(MessageContext.HTTP_REQUEST_HEADERS);
        java.util.List user = (java.util.List) requeHeader.get("USERNAME");
        java.util.List password = (java.util.List) requeHeader.get("PASSWORD");

        if (user != null && password != null) {
            usuario = user.get(0).toString();
            clave = password.get(0).toString();
        }

        if (usuario.equals("admin") && clave.equals("12345678")) {
            dao.eliminarCliente(idCliente);
            return "Eliminación exitosa";
        } else {
            return "Acceso no autorizado";
        }
    }
}
