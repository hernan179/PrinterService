/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
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
/*    */ 
/*    */ public class Principal
/*    */ {
/*    */   public static void main(String[] args) {
/* 20 */     Principal objetoPrincipal = new Principal();
/*    */     
/* 22 */     String fecha1 = "19/09/2016 03:05:00 PM";
/* 23 */     String fecha2 = "20/09/2016 01:05:00 AM";
/*    */     
/* 25 */     Date fechaActual = new Date();
/* 26 */     SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
/* 27 */     String fechaSistema = formateador.format(fechaActual);
/*    */     
/* 29 */     boolean resultadoMenor = objetoPrincipal.compararFechasConDate(fecha1, fecha2, new Date());
/* 30 */     System.out.println("Fecha1" + resultadoMenor + "\n");
/*    */   }
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
/*    */   private boolean compararFechasConDate(String fecha1, String fecha2, Date fActual) {
/* 44 */     boolean resultado = false;
/*    */     try {
/* 46 */       SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
/* 47 */       Date fechaDate1 = formateador.parse(fecha1);
/* 48 */       Date fechaDate2 = formateador.parse(fecha2);
/*    */       
/* 50 */       if (fechaDate1.before(fActual) && fActual.before(fechaDate2)) {
/* 51 */         resultado = true;
/*    */       } else {
/* 53 */         resultado = false;
/*    */       } 
/* 55 */     } catch (ParseException e) {
/* 56 */       System.out.println("Se Produjo un Error!!!  " + e.getMessage());
/*    */     } 
/* 58 */     return resultado;
/*    */   }
/*    */ }


/* Location:              C:\Users\hsancheza\Desktop\bk\PrinterService.jar!\Principal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */