/*    */ import com.google.gson.Gson;
/*    */ import com.rta.opain.delegate.dto.Ticket;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
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
/*    */ public class Lectura
/*    */ {
/*    */   public static void fixMAc(String mac8) {
/* 19 */     String mac = "90:9:17:60:67:7D";
/* 20 */     String[] partMac = mac.split("\\:");
/* 21 */     String nvMac = "";
/* 22 */     for (int i = 0; i < partMac.length; i++) {
/* 23 */       if (partMac[i].matches("[0-9]*")) {
/* 24 */         int iPart = (new Integer(partMac[i])).intValue();
/* 25 */         if (iPart <= 9) {
/* 26 */           partMac[i] = "0" + iPart;
/*    */         }
/*    */       } 
/* 29 */       if (i < partMac.length - 1) {
/* 30 */         nvMac = nvMac + partMac[i] + ":";
/*    */       } else {
/* 32 */         nvMac = nvMac + partMac[i];
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 37 */     System.out.println("mac   _" + mac);
/* 38 */     System.out.println("nv_mac_" + nvMac);
/*    */   }
/*    */ 
/*    */   
/* 42 */   public static void main(String[] args) { fixMAc(null); }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main2(String[] args) {
/* 47 */     String pago = "src/java/archivo.json";
/*    */     
/* 49 */     String JSON = loaderFile(pago);
/*    */     
/* 51 */     String dos = JSON.substring(1, JSON.length() - 1);
/* 52 */     System.out.println(" dos: " + dos);
/* 53 */     Gson gson = new Gson();
/* 54 */     Ticket tk = (Ticket)gson.fromJson(dos, Ticket.class);
/* 55 */     System.out.println("file: " + tk.getPlaca());
/*    */   }
/*    */   
/*    */   public static String loaderFile(String file) {
/*    */     String result;
/*    */     try {
/* 61 */       File f = new File(file);
/* 62 */       byte[] buffer = new byte[(int)f.length()];
/* 63 */       DataInputStream in = new DataInputStream(new FileInputStream(f));
/* 64 */       in.readFully(buffer);
/* 65 */       result = new String(buffer);
/* 66 */     } catch (Exception e) {
/* 67 */       throw new RuntimeException("IO problem in fileToString", e);
/*    */     } finally {}
/*    */     
/* 70 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\hsancheza\Desktop\bk\PrinterService.jar!\Lectura.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */