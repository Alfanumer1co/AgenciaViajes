package ws;

import Dao.ReservaDao;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import model.ReservaVo;

@WebService(serviceName = "ReservaWsAuth")
public class ReservaWsAuth {

    @Resource
    private WebServiceContext wsc;
    private ReservaDao dao = new ReservaDao();

    // LISTRASDASD
    @WebMethod(operationName = "listarReservas")
    public List<ReservaVo> listarReservas() {
        List<ReservaVo> lista = null;
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
            lista = dao.listarReservas();
        }
        return lista;
    }

    //BUSCAR
    @WebMethod(operationName = "buscar_reserva_por_id")
    public ReservaVo buscarReservaPorId(@WebParam(name = "idReserva") int idReserva) {
        ReservaVo reserva = new ReservaVo();
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
            reserva = dao.obtenerReservaPorCodigo(idReserva);
        } else {
            reserva.setId_r(0);
            reserva.setId_cli(0);
            reserva.setId_via(0);
            reserva.setAsientos(0);
            reserva.setFech_pa(null);
            reserva.setPrecio_r(0.00);
            reserva.setEst_r(false);
        }
        return reserva;
    }

    // RESERVAS ACTIVAS
    @WebMethod(operationName = "listarReservasActivas")
    public List<ReservaVo> listarReservasActivas() {

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
            return dao.listarReservasActivas();
        } else {
            return new ArrayList<>();
        }
    }

    // RESERVAS INACTIVAS
    @WebMethod(operationName = "listarReservasInactivas")
    public List<ReservaVo> listarReservasInactivas() {

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
            return dao.listarReservasInactivas();
        } else {
            return new ArrayList<>();
        }
    }

    //REGISTRO
    @WebMethod(operationName = "registrarReserva")
    public String registrarReserva(
            @WebParam(name = "idCliente") int idCliente,
            @WebParam(name = "idViaje") int idViaje,
            @WebParam(name = "asientos") int asientos,
            @WebParam(name = "fechaPago") String fechaPago,
            @WebParam(name = "precio") double precio,
            @WebParam(name = "estado") boolean estado) {

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
            if(validarFecha(fechaPago)){
                dao.registrarReserva(idCliente, idViaje, asientos, fechaPago, precio, estado);
                return "Registro exitoso";
            }else{
                return "Error en la Fecha";
            }
        } else {
            return "Acceso no autorizado";
        }
    }

    // MODIFICAR RESERVA
    @WebMethod(operationName = "modificarReserva")
    public String modificarReserva(
            @WebParam(name = "idReserva") int idReserva,
            @WebParam(name = "idCliente") int idCliente,
            @WebParam(name = "idViaje") int idViaje,
            @WebParam(name = "asientos") int asientos,
            @WebParam(name = "fechaPago") String fechaPago,
            @WebParam(name = "precio") double precio,
            @WebParam(name = "estado") boolean estado) {

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
            if(validarFecha(fechaPago)){
                dao.actualizarReserva(idReserva, idCliente, idViaje, asientos, fechaPago, precio, estado);
                return "Modificación exitosa";
            }else{
                return "Error en la Fecha";
            }
        } else {
            return "Acceso no autorizado";
        }
    }

    // ELIMINAR RESERVA
    @WebMethod(operationName = "eliminarReserva")
    public String eliminarReserva(@WebParam(name = "idReserva") int idReserva) {

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
            dao.eliminarReserva(idReserva);
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
