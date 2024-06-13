package ws;

import Dao.ViajeDao;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import model.ViajeVo;

@WebService(serviceName = "ViajeWsAuth")
public class ViajeWsAuth {

    @Resource
    private WebServiceContext wsc;
    private ViajeDao dao = new ViajeDao();

    //LisTar
    @WebMethod(operationName = "listarViajes")
    public List<ViajeVo> listarViajes() {
        List<ViajeVo> lista = null;
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
            lista = dao.listarViajes();
        }
        return lista;
    }

    //BUscar
    @WebMethod(operationName = "buscar_viaje_por_id")
    public ViajeVo buscarViajePorId(@WebParam(name = "idViaje") int idViaje) {
        ViajeVo viaje = new ViajeVo();
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
            viaje = dao.obtenerViajePorCodigo(idViaje);
        } else {
            viaje.setId_vi(0);
            viaje.setId_dest(0);
            viaje.setSalida(null);
            viaje.setLlegada(null);
        }
        return viaje;
    }

    // REGISTRAR VIAJE
    @WebMethod(operationName = "registrarViaje")
    public String registrarViaje(
            @WebParam(name = "idDestino") int idDestino,
            @WebParam(name = "salida") String salida,
            @WebParam(name = "llegada") String llegada) {

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
            
            if(validarFecha(salida) && validarFecha(llegada)){
                dao.registrarViaje(idDestino, salida, llegada);
                return "Registro exitoso";
            }
            else{
                return "Error en la Fecha";
            }
        } else {
            return "Acceso no autorizado";
        }
    }

    // MODIFICAR VIAJE
    @WebMethod(operationName = "modificarViaje")
    public String modificarViaje(
            @WebParam(name = "idViaje") int idViaje,
            @WebParam(name = "idDestino") int idDestino,
            @WebParam(name = "salida") String salida,
            @WebParam(name = "llegada") String llegada) {

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
            
            if(validarFecha(salida) && validarFecha(llegada)){
                dao.actualizarViaje(idViaje, idDestino, salida, llegada);
                return "Modificación exitosa";
            }else{
                return "Error en la Fecha";
            }
            
        } else {
            return "Acceso no autorizado";
        }
    }

    // ELIMINAR VIAJE
    @WebMethod(operationName = "eliminarViaje")
    public String eliminarViaje(@WebParam(name = "idViaje") int idViaje) {

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
            dao.eliminarViaje(idViaje);
            return "Eliminación exitosa";
        } else {
            return "Acceso no autorizado";
        }
    }

    // CALSE PARA VALIDAR LA FECHA
     public boolean validarFecha(String fecha) {
        try {
            LocalDate.parse(fecha);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
