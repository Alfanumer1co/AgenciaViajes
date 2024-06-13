package model;

public class DestinoVo {
    
    private int id_d;
    private String nombre;
    private String descrip;
    private double precio;

    public DestinoVo() {
    }

    public DestinoVo(int id_d, String nombre, String descrip, double precio) {
        this.id_d = id_d;
        this.nombre = nombre;
        this.descrip = descrip;
        this.precio = precio;
    }

    public int getId_d() {
        return id_d;
    }

    public void setId_d(int id_d) {
        this.id_d = id_d;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
    
}
