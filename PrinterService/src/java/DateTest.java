
/*    */ import java.text.DateFormat;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
/*    */ import java.util.GregorianCalendar;
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
/*    */ public class DateTest
/*    */ {
/*    */   public static boolean conRecargo(Date horaActual, Date date1, Date date2) {
/* 20 */     Calendar cal1 = new GregorianCalendar();
/* 21 */     cal1.setTime(date1);
/*    */     
/* 23 */     Calendar cal2 = new GregorianCalendar();
/* 24 */     cal2.setTime(date2);
/*    */ 
/*    */     
/* 27 */     if (cal1.after(date1) && cal2.before(date2)) {
/* 28 */       return true;
/*    */     }
/* 30 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] args) throws Exception {
/* 36 */     String fecha = (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(new Date());
/*    */ 
/*    */     
/* 39 */     System.out.println("fecha:" + fecha);
/*    */     
/* 41 */     System.out.println("fecha:" + (new SimpleDateFormat("yyyy/MM/dd")).format(new Date()));
/*    */   }
/*    */   
/*    */   public static Date ddMMyyyyHHmmss(String str_date) {
/* 45 */     Date date = null;
/*    */     try {
/* 47 */       DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
/* 48 */       date = formatter.parse(str_date);
/* 49 */       System.out.println("fecha: " + date);
/* 50 */     } catch (Exception e) {
/* 51 */       e.printStackTrace();
/*    */     } 
/*    */ 
/*    */     
/* 55 */     return date;
/*    */   }
/*    */   
/*    */   public static boolean reinicarValores() {
/* 59 */   SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
/* 60 */     String fecha = sdf.format(new Date());
/* 61 */     String[] par = fecha.split("\\ ");
/* 62 */     String[] par2 = par[1].split("\\:");
/* 63 */     Integer hora = new Integer(par2[0]);
/* 64 */     Integer minuto = new Integer(par2[1]);
/* 65 */     System.out.println("fech..a:  " + par[1] + " dividido: " + par2[0] + " " + par2[1]);
/* 66 */     if (hora.intValue() == 11 && minuto.intValue() > 50) {
/* 67 */       return true;
/*    */     }
/* 69 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\hsancheza\Desktop\bk\PrinterService.jar!\DateTest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */