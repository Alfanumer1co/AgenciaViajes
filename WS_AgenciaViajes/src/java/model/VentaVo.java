
package model;

import java.util.Date;

public class VentaVo {
    
    private int id_ve;
    private int id_res;
    private String metodoPag;
    private double pago;
    private Date fech_v;

    public VentaVo() {
    }

    public VentaVo(int id_ve, int id_res, String metodoPag, double pago, Date fech_v) {
        this.id_ve = id_ve;
        this.id_res = id_res;
        this.metodoPag = metodoPag;
        this.pago = pago;
        this.fech_v = fech_v;
    }

    public int getId_ve() {
        return id_ve;
    }

    public void setId_ve(int id_ve) {
        this.id_ve = id_ve;
    }

    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public String getMetodoPag() {
        return metodoPag;
    }

    public void setMetodoPag(String metodoPag) {
        this.metodoPag = metodoPag;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public Date getFech_v() {
        return fech_v;
    }

    public void setFech_v(Date fech_v) {
        this.fech_v = fech_v;
    }
    
    
    
}
