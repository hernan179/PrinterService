/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rta.opain.delegate.tools;

import com.rta.opain.delegate.dto.JsonDTO;
import com.rta.opain.delegate.dto.Ticket;
import com.rta.opain.domain.Servicios;
import java.util.Date;
import static com.rta.opain.delegate.tools.DateHelper.toDateYYYYMMDDHHMM;
import static com.rta.opain.delegate.tools.DateHelper.toDateYYYYMMDDHHMM;

import static com.rta.opain.delegate.tools.StringTools.getNumberInt;
import com.rta.opain.domain.Estados;

/**
 *
 * @author Hernan Sanchez
 */
public class InitHelper {

    public String getCurrentWorkingDirectory() {
        String osname = System.getProperty("os.name");
        String userDir = System.getProperty("user.dir");

        if (osname.toLowerCase().contains("windows")) {
            return userDir + "\\";
        } else {
            return userDir + "/";
        }
    }

    public JsonDTO reimpresion(Servicios retA)
            throws Exception {
        JsonDTO dto = new JsonDTO();
        try {
            String[] dir_nom_nit = retA.getAddres().split("\\|");
            dto.setDireccion(retA.getAddres());
            if (dir_nom_nit.length > 1) {
                dto.setNombre(dir_nom_nit[1]);
            }
            if (dir_nom_nit.length > 2) {
                dto.setNit(dir_nom_nit[2]);
            }
            dto.setId(retA.getNumero().toString());
            dto.setValor(retA.getValor().toString());
            dto.setPlaca(retA.getAutoPlaca());
            dto.setFecha(DateHelper.toDateYYYYMMDDHHMM(retA.getFechaGrab()));

            dto.setUsuario(retA.getUsuarios().getUsuario());
            dto.setNombreCajero(retA.getUsuarios().getNombre());
            dto.setEstado(retA.getEstado().getDescripcion());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    public static void main(String[] args) {
        String value = "av americas 50 15";

        String dir_nom_nit[] = value.split("\\|");
        System.out.println("dir_nom_nit[0]) " + dir_nom_nit[0]);
        if (dir_nom_nit.length > 1) {
            System.out.println("dir_nom_nit[0]) " + dir_nom_nit[1]);
        }
        if (dir_nom_nit.length > 2) {
            System.out.println("dir_nom_nit[0]) " + dir_nom_nit[2]);
        }
    }
}
