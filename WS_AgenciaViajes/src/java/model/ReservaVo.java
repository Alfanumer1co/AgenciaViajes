package model;

import java.util.Date;

public class ReservaVo {
    
    private int id_r;
    private int id_cli;
    private int id_via;
    private int asientos;
    private Date fech_pa;
    private double precio_r;
    private boolean est_r;

    public ReservaVo() {
    }

    public ReservaVo(int id_r, int id_cli, int id_via, int asientos, Date fech_pa, double precio_r, boolean est_r) {
        this.id_r = id_r;
        this.id_cli = id_cli;
        this.id_via = id_via;
        this.asientos = asientos;
        this.fech_pa = fech_pa;
        this.precio_r = precio_r;
        this.est_r = est_r;
    }

    public int getId_r() {
        return id_r;
    }

    public void setId_r(int id_r) {
        this.id_r = id_r;
    }

    public int getId_cli() {
        return id_cli;
    }

    public void setId_cli(int id_cli) {
        this.id_cli = id_cli;
    }

    public int getId_via() {
        return id_via;
    }

    public void setId_via(int id_via) {
        this.id_via = id_via;
    }

    public int getAsientos() {
        return asientos;
    }

    public void setAsientos(int asientos) {
        this.asientos = asientos;
    }

    public Date getFech_pa() {
        return fech_pa;
    }

    public void setFech_pa(Date fech_pa) {
        this.fech_pa = fech_pa;
    }

    public double getPrecio_r() {
        return precio_r;
    }

    public void setPrecio_r(double precio_r) {
        this.precio_r = precio_r;
    }

    public boolean isEst_r() {
        return est_r;
    }

    public void setEst_r(boolean est_r) {
        this.est_r = est_r;
    }
    
    
    
}
