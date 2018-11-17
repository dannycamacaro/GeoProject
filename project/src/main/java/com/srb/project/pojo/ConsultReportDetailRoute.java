package com.srb.project.pojo;

public class ConsultReportDetailRoute {

    private String nombreRuta;
    private String nombreDetalleRuta;
    private String coordenadaLatitud;
    private String coordenadaLongitud;

    public String getCoordenadaLatitud() {
        return coordenadaLatitud;
    }

    public void setCoordenadaLatitud(String coordenadaLatitud) {
        this.coordenadaLatitud = coordenadaLatitud;
    }

    public String getCoordenadaLongitud() {
        return coordenadaLongitud;
    }

    public void setCoordenadaLongitud(String coordenadaLongitud) {
        this.coordenadaLongitud = coordenadaLongitud;
    }


    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    public String getNombreDetalleRuta() {
        return nombreDetalleRuta;
    }

    public void setNombreDetalleRuta(String nombreDetalleRuta) {
        this.nombreDetalleRuta = nombreDetalleRuta;
    }
}
