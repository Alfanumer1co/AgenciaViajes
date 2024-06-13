package ws;

import Dao.VentaDao;
import java.sql.Date;
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
import model.VentaVo;

@WebService(serviceName = "VentaWsAuth")
public class VentaWsAuth {

    @Resource
    private WebServiceContext wsc;
    private VentaDao dao = new VentaDao();

    //LISTAR
    @WebMethod(operationName = "listarVentas")
    public List<VentaVo> listarVentas() {
        List<VentaVo> lista = null;
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
            lista = dao.listarVentas();
        }
        return lista;
    }

    //BUSCAR
    @WebMethod(operationName = "buscar_venta_por_id")
    public VentaVo buscarVentaPorId(@WebParam(name = "idVenta") int idVenta) {
        VentaVo venta = new VentaVo();
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
            venta = dao.obtenerVentaPorCodigo(idVenta);
        } else {
            venta.setId_ve(0);
            venta.setId_res(0);
            venta.setMetodoPag("Sin Acceso");
            venta.setPago(0.00);
            venta.setFech_v(null);
        }
        return venta;
    }

    // REGISTRAR VENTA
    @WebMethod(operationName = "registrarVenta")
    public String registrarVenta(
            @WebParam(name = "idReserva") int idReserva,
            @WebParam(name = "metodoPago") String metodoPago,
            @WebParam(name = "pago") double pago,
            @WebParam(name = "fechaVenta") String fechaVenta) {

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
            if(validarFecha(fechaVenta)){
                dao.registrarVenta(idReserva, metodoPago, pago, fechaVenta);
                return "Registro exitoso";
            }else{
                return "Error en la Fecha";
            } 
        } else {
            return "Acceso no autorizado";
        }
    }

    // MODIFICAR VENTA
    @WebMethod(operationName = "modificarVenta")
    public String modificarVenta(
            @WebParam(name = "idVenta") int idVenta,
            @WebParam(name = "idReserva") int idReserva,
            @WebParam(name = "metodoPago") String metodoPago,
            @WebParam(name = "pago") double pago,
            @WebParam(name = "fechaVenta") String fechaVenta) {

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
            if(validarFecha(fechaVenta)){
                dao.actualizarVenta(idVenta, idReserva, metodoPago, pago, fechaVenta);
                return "Modificación exitosa";
            }else{
                return "Error en la Fecha";
            }
            
        } else {
            return "Acceso no autorizado";
        }
    }

    // ELIMINAR VENTA
    @WebMethod(operationName = "eliminarVenta")
    public String eliminarVenta(@WebParam(name = "idVenta") int idVenta) {

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
            dao.eliminarVenta(idVenta);
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
