package ws;

import Dao.DestinoDao;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import model.DestinoVo;

@WebService(serviceName = "DestinoWsAuth")
public class DestinoWsAuth {

    @Resource
    private WebServiceContext wsc;
    private DestinoDao dao = new DestinoDao();

    //LISTAR
    @WebMethod(operationName = "listarDestinos")
    public List<DestinoVo> listarDestinos() {
        List<DestinoVo> lista = null;
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
            lista = dao.listarDestinos();
        }
        return lista;
    }

    //BUSCAR
    @WebMethod(operationName = "buscar_destino_por_id")
    public DestinoVo buscarDestinoPorId(@WebParam(name = "idDestino") int idDestino) {
        DestinoVo destino = new DestinoVo();
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
            destino = dao.obtenerDestinoPorCodigo(idDestino);
        } else {
            destino.setId_d(0);
            destino.setNombre("Sin Acceso");
            destino.setDescrip("Sin Acceso");
            destino.setPrecio(0.00);
        }
        return destino;
    }

    //ReGISTRO
    @WebMethod(operationName = "registrarDestino")
    public String registrarDestino(
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "precio") double precio) {

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
            dao.registrarDestino(nombre, descripcion, precio);
            return "Registro exitoso";
        } else {
            return "Acceso no autorizado";
        }
    }

    // MODIFICAR DESTINO
    @WebMethod(operationName = "modificarDestino")
    public String modificarDestino(
            @WebParam(name = "idDestino") int idDestino,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "precio") double precio) {

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
            dao.modificarDestino(idDestino, nombre, descripcion, precio);
            return "Modificación exitosa";
        } else {
            return "Acceso no autorizado";
        }
    }

    // ELIMINAR DESTINO
    @WebMethod(operationName = "eliminarDestino")
    public String eliminarDestino(@WebParam(name = "idDestino") int idDestino) {

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
            dao.eliminarDestino(idDestino);
            return "Eliminación exitosa";
        } else {
            return "Acceso no autorizado";
        }
    }

}
