package com.rta.opain.delegate;

import com.rta.opain.delegate.tools.LogTest;
import com.rta.opain.delegate.tools.SendMail;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import static com.rta.opain.delegate.tools.Utilidades.getNumberInt;
import com.rta.opain.domain.CajerosAero;

//@Singleton
//@Lock(LockType.READ)
public class ScheduleEJB {
//20 * * * * ---> 20 minutes after every hour. (00:20, 01:20, etc.)
//5 22 * * * ---> Every day at 10:05 p.m.
//0  8 1 * * ---> First day of every month at 8:00 a.m.
//0 8 4 7 * ---> The fourth of July at 8:00 a.m.
//15 12 * * 5 ---> Every Friday at 12:15 p.m.

    @EJB
    PrinterServicesDelegateRemote service;

    @Schedules({
        @Schedule(dayOfMonth = "1", hour = "1", minute = "1", second = "1")
    })
    public void guardarStock() {
        LogTest.rw("Tarea programa cada 5 minutos todos los dias " + new SimpleDateFormat("dd MMMM yyyy HH:mm:ss").format(new Date()));

        try {
            LogTest.rw("ejecutar tarea");
            List<String> values = service.realizarCierreAutomatico();
                        
            if (values != null) {
                for (String s : values) {
                    service.cerrarServicios(new CajerosAero(getNumberInt(s)), true, null);
                }
                new SendMail().sendMesageCierreFinDeMes("Cirre enviar con exito");
            } else {
                new SendMail().sendMesageCierreFinDeMes("No hay cierres pendietnes");
            }
            
        } catch (Exception e) {
            LogTest.rw("Error al envia rel cierre");
            new SendMail().sendMesageCierreFinDeMes("Error al enviar el cierre");
            e.printStackTrace();
        }
    }
}
