package org.iesalandalus.programacion.reservashotel.dominio;

public enum TipoHabitacion {
    SUITE("Suite", 4), SIMPLE("Simple",1), DOBLE("Doble", 2), TRIPLE("Triple",3);

    private final String cadenaAMostrar;
    private final int numeroMaximoPersonas;

    TipoHabitacion(String cadenaAMostrar, int numeroMaximoPersonas){
        this.cadenaAMostrar = cadenaAMostrar;
        this.numeroMaximoPersonas = numeroMaximoPersonas;
    }

    public int getNumeroMaximoPersonas() {
        return numeroMaximoPersonas;
    }

    @Override
    public String toString() {
        return "TipoHabitacion{" +
                "cadenaAMostrar='" + cadenaAMostrar + '\'' +
                ", numeroMaximoPersonas=" + numeroMaximoPersonas +
                '}';
    }
}
