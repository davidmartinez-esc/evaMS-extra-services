package evaMS.descuentos_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

@Service
public class DescuentoIngresoLunesJueves {
    public Integer getDescuentoPorHoraYdiaIngreso(LocalTime horaIngreso, Date fechaIngreso){
        Integer descuento=0;
        int diaDeIngreso;

        Calendar calendario= new GregorianCalendar();
        calendario.setTime(fechaIngreso);

        int dia=calendario.get(Calendar.DAY_OF_WEEK);
        System.out.println(dia);

        LocalTime limiteInferior = LocalTime.of(9, 0);
        LocalTime limiteSuperior = LocalTime.of(12,0);

        if (horaIngreso.isAfter(limiteInferior) && horaIngreso.isBefore(limiteSuperior)){
            if (dia==2 || dia==5){
                descuento=10;
                return descuento;
            }
        }
        return descuento;
    }


}
