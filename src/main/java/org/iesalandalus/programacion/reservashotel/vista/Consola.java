package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Consola {

    private Consola(){}

    public static void mostrarMenu(){
        System.out.println(Opcion.INSERTAR_HUESPED);
        System.out.println(Opcion.BUSCAR_HUESPED);
        System.out.println(Opcion.BORRAR_HUESPED);
        System.out.println(Opcion.MOSTRAR_HUESPEDES);
        System.out.println(Opcion.INSERTAR_HABITACION);
        System.out.println(Opcion.BUSCAR_HABITACION);
        System.out.println(Opcion.BORRAR_HABITACION);
        System.out.println(Opcion.MOSTRAR_HABITACIONES);
        System.out.println(Opcion.INSERTAR_RESERVA);
        System.out.println(Opcion.ANULAR_RESERVA);
        System.out.println(Opcion.MOSTRAR_RESERVAS);
        System.out.println(Opcion.CONSULTAR_DISPONIBILIDAD);
        System.out.println(Opcion.SALIR);
        //System.out.println("---------------");
        System.out.println(" ");
        //System.out.print("Elegir la opción deseada: ");
    }

    public static Opcion elegirOpcion(){
        System.out.println("---------------");
        System.out.println(" ");
        int opcion;
        do{
            System.out.print("Elegir la opción deseada: ");
            opcion = Entrada.entero();
        }while(opcion < 0 || opcion > 12);

        return switch (opcion) {
            case 1 -> Opcion.INSERTAR_HUESPED;
            case 2 -> Opcion.BUSCAR_HUESPED;
            case 3 -> Opcion.BORRAR_HUESPED;
            case 4 -> Opcion.MOSTRAR_HUESPEDES;
            case 5 -> Opcion.INSERTAR_HABITACION;
            case 6 -> Opcion.BUSCAR_HABITACION;
            case 7 -> Opcion.BORRAR_HABITACION;
            case 8 -> Opcion.MOSTRAR_HABITACIONES;
            case 9 -> Opcion.INSERTAR_RESERVA;
            case 10 -> Opcion.ANULAR_RESERVA;
            case 11 -> Opcion.MOSTRAR_RESERVAS;
            case 12 -> Opcion.CONSULTAR_DISPONIBILIDAD;
            case 0 -> Opcion.SALIR;
            default -> throw new IllegalStateException("Valor fuera de rango (0-12)");
        };
    }

    public static Huesped leerHuesped(){

        String nombre, dni, correo, telefono;
        LocalDate fechaNacimiento;


        System.out.println("Introduzca el nombre del huésped: ");
        nombre=Entrada.cadena();
        System.out.println("Introduzca DNI del huésped: ");
        dni=Entrada.cadena();
        System.out.println("Introduzca Correo del huésped: ");
        correo=Entrada.cadena();
        System.out.println("Introduzca teléfono del huésped: ");
        telefono=Entrada.cadena();
        System.out.println("Introduzca fecha de nacimiento del huésped (dd/MM/yyyy): ");
        fechaNacimiento= leerFecha(Entrada.cadena());


        return new Huesped(nombre, dni, correo, telefono, fechaNacimiento);
    }

    public static Huesped getHuespedPorDni(){
        System.out.print("Introduzca DNI: ");
        String dni = Entrada.cadena();
        return new Huesped("Soy Un Dummy", dni, "ningun_sitio@ahi.ong","950000000", LocalDate.of(1990, 9, 9));
    }

    public static LocalDate leerFecha(String mensaje){

        Pattern p = Pattern.compile("[0-3][0-9]/[0-1][0-9]/[1-2][0-9]{3}");
        Matcher m;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(Huesped.FORMATO_FECHA); //GUARDADO PARA OTRAS FECHAS
        m = p.matcher(mensaje);

        while(!m.matches()){

            System.out.println("Introduzca una fecha válida (dd/MM/yyyy): ");
            mensaje = Entrada.cadena();
            m = p.matcher(mensaje);
        }

        return LocalDate.parse(mensaje, formatoFecha);
    }

    public static Habitacion leerHabitacion(){
        int puerta, planta;
        double precio;
        TipoHabitacion tipoHabitacion = null;

        System.out.print("Introduzca la planta de la habitación: "); //intento
        try{
            planta = Entrada.entero();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("Prueba de nuevo: ");
            planta = Entrada.entero();
        }
        System.out.println("Introduzca la puerta de la habitación: ");
        puerta=Entrada.entero();
        System.out.println("Introduzca precio de la habitación (40.0-150.0): ");
        precio=Entrada.realDoble();
        tipoHabitacion = leerTipoHabitacion();

        return new Habitacion(planta, puerta, precio, tipoHabitacion);
    }

    public static Habitacion leerHabitacionPorIdentificador(){
        int puerta, planta;

        System.out.print("Introduzca la planta de la habitación: "); //intento
        try{
            planta = Entrada.entero();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("Prueba de nuevo: ");
            planta = Entrada.entero();

        }
        System.out.println("Introduzca la puerta de la habitación: ");
        puerta=Entrada.entero();

        return new Habitacion(planta, puerta, 100);
    }

    public static TipoHabitacion leerTipoHabitacion(){
        TipoHabitacion tipoHabitacion = null;
        int opcion;

        System.out.println("Elige tipo de habitación: ");
        System.out.println("1- "+TipoHabitacion.SIMPLE);
        System.out.println("2- "+TipoHabitacion.DOBLE);
        System.out.println("3- "+TipoHabitacion.TRIPLE);
        System.out.println("4- "+TipoHabitacion.SUITE);
        do{
            opcion = Entrada.entero();
        }while (opcion<1 || opcion > 4);

        switch (opcion){
            case 1 -> tipoHabitacion=TipoHabitacion.SIMPLE;
            case 2 -> tipoHabitacion=TipoHabitacion.DOBLE;
            case 3 -> tipoHabitacion=TipoHabitacion.TRIPLE;
            case 4 -> tipoHabitacion=TipoHabitacion.SUITE;
        }
        return tipoHabitacion;
    }

    public static Regimen leerRegimen(){
        Regimen regimen = null;
        int opcion;

        System.out.println("Elige tipo de régimen: ");
        System.out.println("1- "+Regimen.SOLO_ALOJAMIENTO);
        System.out.println("2- "+Regimen.ALOJAMIENTO_DESAYUNO);
        System.out.println("3- "+Regimen.MEDIA_PENSION);
        System.out.println("4- "+Regimen.PENSION_COMPLETA);
        do{
            opcion = Entrada.entero();
        }while (opcion<1 || opcion > 4);

        switch (opcion){
            case 1 -> regimen=Regimen.SOLO_ALOJAMIENTO;
            case 2 -> regimen=Regimen.ALOJAMIENTO_DESAYUNO;
            case 3 -> regimen=Regimen.MEDIA_PENSION;
            case 4 -> regimen=Regimen.PENSION_COMPLETA;
        }
        return regimen;
    }

    public static Reserva leerReserva(){
        Huesped huesped;
        Habitacion habitacion;
        Regimen regimen;
        LocalDate fechaInicio, fechaFin;
        int numeroPersonas;

        huesped=leerHuesped();
        habitacion=leerHabitacion();
        regimen=leerRegimen();
        System.out.println("-|Fecha de entrada|-");
        fechaInicio=leerFecha(Entrada.cadena());
        System.out.println("-|Fecha de salida|-");
        fechaFin=leerFecha(Entrada.cadena());
        System.out.println("Introduce cuantas personas: ");
        numeroPersonas=Entrada.entero();

        return new Reserva(huesped, habitacion, regimen, fechaInicio, fechaFin, numeroPersonas);
    }



}
