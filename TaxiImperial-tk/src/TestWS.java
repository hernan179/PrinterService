
import com.aero.aplication.dto.JsonDTO;
import com.aero.aplication.dto.RtaDTO;
import com.aero.aplication.tools.Helper;

import static com.aero.aplication.tools.Helper.getGeoRuteo;
import com.aero.aplication.tools.SendMail;
import com.aero.rta.conn.WebserviceConnection;
import static com.aero.aplication.tools.Helper.isOK;
import static com.aero.aplication.tools.Helper.getNumberLong;
import com.google.gson.Gson;

public class TestWS {

    public static void main(String[] args) {
        String values = getGeoRuteo("CL 114 6 02","1");


        int ik = 0;
        int errores = 0;
        for (int i = 0; i < 50; i++) {
            String jsonGeo = new WebserviceConnection("185.80.129.150", 8280).getStatusAccount(values);
            //System.out.println("jsonGeo_"+jsonGeo);
            long precio = 0;
            if (isOK(jsonGeo)) {
                Gson gson = new Gson();
                RtaDTO obj = gson.fromJson(jsonGeo, RtaDTO.class);
                for (JsonDTO p : obj.getAlertas()) {
                    precio = getNumberLong(p.getPrecio());
                 System.out.println("Precio_"+precio);
                }
            }
            if (!(precio > 0)) {
                errores++;
            }
            ik++;
        }
        String resultado = "De " + ik + " solicitudes " + errores + " no fueron zonificadas";
        System.out.println(""+resultado);

       // new SendMail().sendOtpCode("hernan179@gmail.com", resultado);
    }
}
