
package model;

public class ClienteVo {
    
    private int id_c;
    private String nombres;
    private String apellidos;
    private int edad;
    private int dni;
    private String correo;
    private int celular;

    public ClienteVo() {
    }

    public ClienteVo(int id_c, String nombres, String apellidos, int edad, int dni, String correo, int celular) {
        this.id_c = id_c;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.dni = dni;
        this.correo = correo;
        this.celular = celular;
    }

    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }
    
    
    
}
