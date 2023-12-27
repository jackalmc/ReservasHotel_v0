package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservas {
    private int capacidad;
    private int tamano;
    private Reserva[] coleccionReservas;

    public Reservas(int capacidad){
        if (capacidad < 1)
            throw new IllegalArgumentException("La capacidad no puede ser negativa");

        coleccionReservas = new Reserva[capacidad];
        this.capacidad = capacidad;
        this.tamano = 0;
    }

    public Reserva[] get(){
        return copiaProfundaReservas();
    }

    private Reserva[] copiaProfundaReservas(){
        if (coleccionReservas==null)
            throw new NullPointerException("coleccion no creada aún");

        Reserva[] copia = new Reserva[this.capacidad];
        for (int i=0; i < tamano; i++){
            copia[i]=new Reserva(coleccionReservas[i]);
        }
        return copia;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        return tamano;
    }

    public void insertar(Reserva reserva){
        if (reserva == null)
            throw new NullPointerException("reserva nulo insertar");
        if (buscarIndice(reserva)!= -1)
            throw new ArrayStoreException("El reserva ya existe");
        if (capacidadSuperada(tamano)) //validado para negativo
            throw new ArrayIndexOutOfBoundsException("está completo");

        coleccionReservas[tamano] = new Reserva(reserva);
        tamano++;
    }

    private int buscarIndice(Reserva reserva) {
        if (reserva == null)
            throw new NullPointerException("reserva nulo buscarindice");

        int indice = -1;

        for (int i = 0; i < tamano; i++) {
            if (coleccionReservas[i].equals(reserva))
                indice = i;
        }

        return indice;
    }

    private boolean tamanoSuperado(int indice){
        if (indice < 0)
            throw new IllegalArgumentException("indice no puede ser negativo");
        return (indice>=tamano);
    }

    private boolean capacidadSuperada(int indice){
        if (indice < 0)
            throw new IllegalArgumentException("indice no puede ser negativo(capacidad)");

        return (indice==capacidad);
    }

    public Reserva buscar(Reserva reserva){
        if (reserva == null)
            throw new NullPointerException("reserva es nuuuuuulo buscar");

        if (buscarIndice(reserva) != -1)
            return coleccionReservas[buscarIndice(reserva)];
        else
            return null;

    }

    public void borrar(Reserva reserva){
        if (reserva == null)
            throw new NullPointerException("reserva nulo borrar");
        if (buscarIndice(reserva) == -1)
            throw new IllegalArgumentException("El reserva no está guardado");

        int posicionBorrado = buscarIndice(reserva);

        coleccionReservas[posicionBorrado] = null;
        if (posicionBorrado != tamano-1){
            for (int i = posicionBorrado+1; i< tamano-1; i++)
                desplazarUnaPosicionHaciaIzquierda(i);
        }
        tamano--;


    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        if (tamanoSuperado(indice)) //validado para negativo
            throw new IllegalArgumentException("El indice supera el tamaño");

        coleccionReservas[indice-1] = new Reserva(coleccionReservas[indice]);
        coleccionReservas[indice] = null;

    }

    public Reserva[] getReservas(Huesped huesped){
        if (coleccionReservas==null)
            throw new NullPointerException("coleccion no creada aún");
        if (huesped == null)
            throw new NullPointerException("huespednulo reserva");

        Reserva[] copiaEspecial = new Reserva[this.capacidad];

        for (int i=0; i < tamano; i++){
            if (coleccionReservas[i].getHuesped().equals(huesped))
                copiaEspecial[i]=new Reserva(coleccionReservas[i]);
        }
        return copiaEspecial;

    }
    public Reserva[] getReservas(TipoHabitacion tipoHabitacion){
        if (coleccionReservas==null)
            throw new NullPointerException("coleccion no creada aún");
        if (tipoHabitacion == null)
            throw new NullPointerException("tipohabitacion null");

        Reserva[] copiaEspecial = new Reserva[this.capacidad];

        for (int i=0; i < tamano; i++){
            if (coleccionReservas[i].getHabitacion().getTipoHabitacion().equals(tipoHabitacion))
                copiaEspecial[i]=new Reserva(coleccionReservas[i]);
        }
        return copiaEspecial;
    }
    public Reserva[] getReservasFuturas(Habitacion habitacion){
        if (coleccionReservas==null)
            throw new NullPointerException("coleccion no creada aún");
        if (habitacion==null)
            throw new NullPointerException("habitacion nula");

        Reserva[] copiaEspecial = new Reserva[this.capacidad];

        for (int i=0; i < tamano; i++){
            if (coleccionReservas[i].getFechaInicioReserva().isAfter(LocalDate.now()))
                copiaEspecial[i]=new Reserva(coleccionReservas[i]);
        }
        return copiaEspecial;
    }
}
