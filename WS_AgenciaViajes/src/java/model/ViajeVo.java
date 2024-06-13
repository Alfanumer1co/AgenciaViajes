
package model;

import java.util.Date;

public class ViajeVo {
    
    private int id_vi;
    private int id_dest;
    private Date salida;
    private Date llegada;

    public ViajeVo() {
    }

    public ViajeVo(int id_vi, int id_dest, Date salida, Date llegada) {
        this.id_vi = id_vi;
        this.id_dest = id_dest;
        this.salida = salida;
        this.llegada = llegada;
    }

    public int getId_vi() {
        return id_vi;
    }

    public void setId_vi(int id_vi) {
        this.id_vi = id_vi;
    }

    public int getId_dest() {
        return id_dest;
    }

    public void setId_dest(int id_dest) {
        this.id_dest = id_dest;
    }

    public Date getSalida() {
        return salida;
    }

    public void setSalida(Date salida) {
        this.salida = salida;
    }

    public Date getLlegada() {
        return llegada;
    }

    public void setLlegada(Date llegada) {
        this.llegada = llegada;
    }
    
    
}
