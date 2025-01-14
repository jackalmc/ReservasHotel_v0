package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.iesalandalus.programacion.reservashotel.MainApp.huespedes;

public class Consola {

    private Consola(){}

    public static void mostrarMenu(){

        for (Opcion opcion : Opcion.values())
            System.out.println(opcion);

        System.out.println(" ");

    }

    public static Opcion elegirOpcion(){
        System.out.println("---------------");
        System.out.println(" ");
        int numeroOpcion;

        do{
            System.out.print("Elegir la opci�n deseada: ");
            numeroOpcion = Entrada.entero();
        }while(numeroOpcion < 0 || numeroOpcion > Opcion.values().length);

        return Opcion.values()[numeroOpcion];
    }

    public static Huesped leerHuesped(){

        String nombre, dni, correo, telefono;
        LocalDate fechaNacimiento;


        System.out.println("Introduzca el nombre del hu�sped: ");
        nombre=Entrada.cadena();
        System.out.println("Introduzca DNI del hu�sped: ");
        dni=Entrada.cadena();
        System.out.println("Introduzca Correo del hu�sped: ");
        correo=Entrada.cadena();
        System.out.println("Introduzca tel�fono del hu�sped: ");
        telefono=Entrada.cadena();
        System.out.println("Introduzca fecha de nacimiento del hu�sped (dd/MM/yyyy): ");
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

            System.out.println("Introduzca una fecha v�lida (dd/MM/yyyy): ");
            mensaje = Entrada.cadena();
            m = p.matcher(mensaje);
        }

        return LocalDate.parse(mensaje, formatoFecha);
    }

    public static Habitacion leerHabitacion(){
        int puerta, planta;
        double precio;
        TipoHabitacion tipoHabitacion = null;

        System.out.print("Introduzca la planta de la habitaci�n: "); //intento
        try{
            planta = Entrada.entero();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("Prueba de nuevo: ");
            planta = Entrada.entero();
        }
        System.out.println("Introduzca la puerta de la habitaci�n: ");
        puerta=Entrada.entero();
        System.out.println("Introduzca precio de la habitaci�n (40.0-150.0): ");
        precio=Entrada.realDoble();
        tipoHabitacion = leerTipoHabitacion();

        return new Habitacion(planta, puerta, precio, tipoHabitacion);
    }

    public static Habitacion leerHabitacionPorIdentificador(){
        int puerta, planta;

        System.out.print("Introduzca la planta de la habitaci�n: "); //intento
        try{
            planta = Entrada.entero();
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("Prueba de nuevo: ");
            planta = Entrada.entero();

        }
        System.out.println("Introduzca la puerta de la habitaci�n: ");
        puerta=Entrada.entero();

        return new Habitacion(planta, puerta, 100);
    }

    public static TipoHabitacion leerTipoHabitacion(){
        int opcion;

        System.out.println("-----");
        System.out.println("Elige tipo de habitaci�n: ");

        for (TipoHabitacion tipoHabitacion1: TipoHabitacion.values())
            System.out.println(tipoHabitacion1);
        System.out.println("-----");

        do{
            opcion = Entrada.entero();
        }while (opcion<0 || opcion > TipoHabitacion.values().length);


        return TipoHabitacion.values()[opcion];
    }

    public static Regimen leerRegimen(){
        int opcion;

        System.out.println("-----");
        System.out.println("Elige tipo de r�gimen: ");
        for (Regimen regimen : Regimen.values())
            System.out.println(regimen);
        System.out.println("-----");

        do{
            opcion = Entrada.entero();
        }while (opcion<0 || opcion > Regimen.values().length);

        return Regimen.values()[opcion];
    }

    public static Reserva leerReserva(){

        Huesped huesped;
        Habitacion habitacion;
        Regimen regimen;
        LocalDate fechaInicio, fechaFin;
        int numeroPersonas;

        huesped = Consola.getHuespedPorDni();
        huesped = huespedes.buscar(huesped);

        habitacion=leerHabitacion();
        regimen=leerRegimen();
        System.out.println("-|Fecha de entrada (dd/MM/yyyy) |-");
        fechaInicio=leerFecha(Entrada.cadena());
        System.out.println("-|Fecha de salida (dd/MM/yyyy) |-");
        fechaFin=leerFecha(Entrada.cadena());
        System.out.println("Introduce cuantas personas: ");
        numeroPersonas=Entrada.entero();

        return new Reserva(huesped, habitacion, regimen, fechaInicio, fechaFin, numeroPersonas);
    }



}
