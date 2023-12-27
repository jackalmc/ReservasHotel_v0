package org.iesalandalus.programacion.reservashotel.vista;

public enum Opcion {

    SALIR("0 .- Salir"),
    INSERTAR_HUESPED("1 .- Insertar Hu�sped"),BUSCAR_HUESPED("2 .- Buscar Hu�sped"),BORRAR_HUESPED("3 .- Borrar Hu�sped"), MOSTRAR_HUESPEDES("4 .- Mostrar Hu�sped"),
    INSERTAR_HABITACION("5 .- Insertar Habitaci�n"),BUSCAR_HABITACION("6 .- Buscar Habitaci�n"), BORRAR_HABITACION("7 .- Borrar Habitaci�n"), MOSTRAR_HABITACIONES("8 .- Mostrar Habitaciones"),
    INSERTAR_RESERVA("9 .- Insertar Reserva"),ANULAR_RESERVA("10 .- Anular Reserva"),MOSTRAR_RESERVAS("11 .- Mostrar Reserva"),
    CONSULTAR_DISPONIBILIDAD("12 .- Consultar Disponibilidad");

    private String mensajeAMostrar;

    Opcion(String mensajeAMostrar){
        this.mensajeAMostrar=mensajeAMostrar;
    }

    @Override
    public String toString() {
        return mensajeAMostrar;
    }
}
