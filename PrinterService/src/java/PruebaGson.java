/*    */ import com.google.gson.Gson;
/*    */ import com.rta.opain.delegate.dto.JsonDTO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PruebaGson
/*    */ {
/* 17 */   static String json = "[{\"tiquete\":{\"ip\":\"192.168.23.135\",\"usuarios\":\"admin\",\"objecto\":\"Contrato de prestacion de servicios publico de transporte especial del contratante (RES 0348/2015)\",\"fecha\":\"Sep 27, 2016 7:23:39 AM\",\"fechaFin\":\"Sep 29, 2016 7:23:25 AM\",\"valor\":\"55000\",\"origen\":\"VJS|Aeropuerto int Dorado Bogota\",\"destino\":\"  50 15| |12198468\",\"empresa\":\"VIAJES IMPERIAL S.A.S\",\"empresaVehiculo\":\"VIAJES IMPERIAL S.A.S\",\"nitPagador\":\"41482347\",\"distancia\":0,\"usuario\":{\"nombre\":\"Administrador\",\"clave\":\"admin\",\"usuario\":\"admin\",\"idUsuario\":\"0\",\"idSitio\":\"2\"},\"placa\":\"TDS069\",\"tipoPago\":\"005\",\"nitCliente\":\"12198468\",\"nitConductor\":\"79658726\",\"id\":0,\"conductor\":\"No DISP\",\"_2300\":\"0\",\"nombre\":\"hernan sanchez\",\"pasajero\":\"2\",\"marca\":\"HYUNDAI\",\"modelo\":\"2012\",\"logFirmaVehiculo\":\"\",\"autorizado\":\"\",\"nombreConductor1\":\"ANDRES\",\"apellidoConductor1\":\"GARCIA PARRA\",\"ccConductor1\":\"79658726\",\"licenciaConductor1\":\"79658726\",\"vigenciaConductor1\":\"2017-10-21\",\"nombreResponsable\":\"BLANCA CECILIA\",\"apellidoResponsable\":\"PARRA DAZA\",\"ccResponsable\":\"41482347\",\"direccionResponsable\":\"\",\"indProc\":\"0\",\"transaccion\":\"\",\"retiroParcial\":\"false\",\"clase\":\"MICROBUS\",\"numeroInterno\":\"2035\",\"numeroTarjetaOpe\":\"1010111\",\"keyCommand\":\"8a\",\"direccion\":\"  50 15| |12198468\",\"nit\":\"41482347\",\"sitio\":\"2\",\"grabador\":\"admin\",\"comprob\":\"VJS\",\"cierre\":\"0\",\"valorE\":\"55000\",\"valorD\":\"0\",\"valorC\":\"0\",\"idUsuario\":\"0\",\"personas\":[{\"nombreConductor1\":\"ANDRES\",\"apellidoConductor1\":\"GARCIA PARRA\",\"ccConductor1\":\"79658726\",\"licenciaConductor1\":\"79658726\",\"vigenciaConductor1\":\"2017-10-21\",\"empresaVehiculo\":\"VIAJES IMPERIAL S.A.S\"}]}}]";
/*    */   public static void main(String[] args) {
/* 19 */     System.out.println("rta:" + json);
/* 20 */     JsonDTO[] entra = (JsonDTO[])(new Gson()).fromJson(json, JsonDTO[].class);
/* 21 */     System.out.println("value: " + entra[0].getTiquete().getComprob());
/*    */   }
/*    */ }


/* Location:              C:\Users\hsancheza\Desktop\bk\PrinterService.jar!\PruebaGson.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */