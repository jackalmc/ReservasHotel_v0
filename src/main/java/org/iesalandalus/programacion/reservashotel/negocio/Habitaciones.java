package org.iesalandalus.programacion.reservashotel.negocio;


import org.iesalandalus.programacion.reservashotel.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.dominio.TipoHabitacion;

public class Habitaciones {

    private int capacidad;
    private int tamano;
    private Habitacion[] coleccionHabitaciones;

    public Habitaciones(int capacidad){
        if (capacidad < 1)
            throw new IllegalArgumentException("La capacidad no puede ser negativa");

        coleccionHabitaciones = new Habitacion[capacidad];
        this.capacidad = capacidad;
        this.tamano = 0;
    }

    public Habitacion[] get(){
        return copiaProfundaHabitaciones();
    }
    public Habitacion[] get(TipoHabitacion tipoHabitacion){
        if (coleccionHabitaciones==null)
            throw new NullPointerException("coleccion no creada aún");

        Habitacion[] copiaEspecial = new Habitacion[this.capacidad];

        for (int i=0; i < tamano; i++){
            if (coleccionHabitaciones[i].getTipoHabitacion() == tipoHabitacion)
                copiaEspecial[i]=new Habitacion(coleccionHabitaciones[i]);
        }
        return copiaEspecial;
    }

    private Habitacion[] copiaProfundaHabitaciones(){
        if (coleccionHabitaciones==null)
            throw new NullPointerException("coleccion no creada aún");

        Habitacion[] copia = new Habitacion[this.capacidad];

        for (int i=0; i < tamano; i++){
            copia[i]=new Habitacion(coleccionHabitaciones[i]);
        }
        return copia;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        return tamano;
    }

    public void insertar(Habitacion habitacion){
        if (habitacion == null)
            throw new NullPointerException("huesped nulo insertar");
        if (buscarIndice(habitacion)!= -1)
            throw new ArrayStoreException("El huesped ya existe");
        if (capacidadSuperada(tamano))
            throw new ArrayIndexOutOfBoundsException("está completo");

        coleccionHabitaciones[tamano] = new Habitacion(habitacion);
        tamano++;
    }

    private int buscarIndice(Habitacion habitacion) {
        if (habitacion == null)
            throw new NullPointerException("Habitacion nulo buscarindice");

        int indice = -1;

        for (int i = 0; i < tamano; i++) {
            if (coleccionHabitaciones[i].equals(habitacion))
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

    public Habitacion buscar(Habitacion habitacion){
        if (habitacion == null)
            throw new NullPointerException("Habitacion es nuuuuuulo buscar");

        if (buscarIndice(habitacion) != -1)
            return coleccionHabitaciones[buscarIndice(habitacion)];
        else
            return null;

    }

    public void borrar(Habitacion habitacion){
        if (habitacion == null)
            throw new NullPointerException("Habitacion nulo borrar");
        if (buscarIndice(habitacion) == -1)
            throw new IllegalArgumentException("El Habitacion no está guardado");

        int posicionBorrado = buscarIndice(habitacion);

        coleccionHabitaciones[posicionBorrado] = null;
        if (posicionBorrado != tamano-1){
            for (int i = posicionBorrado+1; i< tamano-1; i++)
                desplazarUnaPosicionHaciaIzquierda(i);
        }
        tamano--;


    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        if (tamanoSuperado(indice))
            throw new IllegalArgumentException("El indice supera el tamaño");

        coleccionHabitaciones[indice-1] = new Habitacion(coleccionHabitaciones[indice]);
        coleccionHabitaciones[indice] = null;

    }

}
