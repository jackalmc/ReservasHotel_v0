package org.iesalandalus.programacion.reservashotel.negocio;

import org.iesalandalus.programacion.reservashotel.dominio.Huesped;

public class Huespedes {
//Todo: Pasar los tests
    private int capacidad;
    private int tamano;
    private Huesped[] coleccionHuespedes;

    public Huespedes(int capacidad){
        if (capacidad < 1)
            throw new IllegalArgumentException("La capacidad no puede ser negativa");

        coleccionHuespedes = new Huesped[capacidad];
        this.capacidad = capacidad;
        this.tamano = 0;
    }

    public Huesped[] get(){
        return copiaProfundaHuespedes();
    }

    private Huesped[] copiaProfundaHuespedes(){
        if (coleccionHuespedes==null)
            throw new NullPointerException("coleccion no creada aún");

        Huesped[] copia = new Huesped[this.capacidad];
        for (int i=0; i < tamano; i++){
            copia[i]=new Huesped(coleccionHuespedes[i]);
        }
        return copia;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        return tamano;
    }

    public void insertar(Huesped huesped){
        if (huesped == null)
            throw new NullPointerException("huesped nulo insertar");
        if (buscarIndice(huesped)!= -1)
            throw new ArrayStoreException("El huesped ya existe");
        if (capacidadSuperada(tamano))
            throw new ArrayIndexOutOfBoundsException("está completo");

        coleccionHuespedes[tamano] = new Huesped(huesped);
        tamano++;
    }

    private int buscarIndice(Huesped huesped) {
        if (huesped == null)
            throw new NullPointerException("huesped nulo buscarindice");

        int indice = -1;

        for (int i = 0; i < tamano; i++) {
            if (coleccionHuespedes[i].equals(huesped))
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

    public Huesped buscar(Huesped huesped){
        if (huesped == null)
            throw new NullPointerException("huesped es nuuuuuulo buscar");

        if (buscarIndice(huesped) != -1)
            return coleccionHuespedes[buscarIndice(huesped)];
        else
            return null;

    }

    public void borrar(Huesped huesped){
        if (huesped == null)
            throw new NullPointerException("huezpez nulo borrar");
        if (buscarIndice(huesped) == -1)
            throw new IllegalArgumentException("El huesped no está guardado");

        int posicionBorrado = buscarIndice(huesped);

        coleccionHuespedes[posicionBorrado] = null;
        if (posicionBorrado != tamano-1){
            for (int i = posicionBorrado+1; i< tamano-1; i++)
                desplazarUnaPosicionHaciaIzquierda(i);
        }
        tamano--;


    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice){
        if (tamanoSuperado(indice))
            throw new IllegalArgumentException("El indice supera el tamaño");

        coleccionHuespedes[indice-1] = new Huesped(coleccionHuespedes[indice]);
        coleccionHuespedes[indice] = null;

    }

}
