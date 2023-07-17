package org.chakuy.hbelga.model;

public class HbelgaModel {



    String area,descripcion,estado,fecha,nombre;

    public HbelgaModel (){ }


    public HbelgaModel(String area, String descripcion, String estado, String fecha, String nombre) {
        this.area = area;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha = fecha;
        this.nombre = nombre;

    }


    public String getarea() {
        return area;
    }

    public void setarea(String area) {
        this.area = area;
    }

    public String getdescripcion() {
        return descripcion;
    }

    public void setdescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getestado() {
        return estado;
    }

    public void setestado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getfecha() {
        return fecha;
    }

    public void setfecha(String fecha) {
        this.fecha = fecha;
    }


}



