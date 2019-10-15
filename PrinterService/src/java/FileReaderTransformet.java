/*    */ import java.io.BufferedReader;
/*    */ import java.io.BufferedWriter;
/*    */ import java.io.FileReader;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
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
/*    */ public class FileReaderTransformet
/*    */ {
/*    */   public void pdfToImg() {}
/*    */   
/*    */   public static void main(String[] args) throws Exception {
/*    */     try {
/* 25 */       FileReader fr = new FileReader("c:/bk/avacreditos.010114.txt");
/* 26 */       BufferedReader br = new BufferedReader(fr);
/*    */       
/* 28 */       String syourfilename = ""; String s;
/* 29 */       while ((s = br.readLine()) != null) {
/* 30 */         syourfilename = syourfilename + br.readLine() + "\n";
/*    */       }
/* 32 */       fr.close();
/*    */       
/* 34 */    BufferedWriter   writer = null;
/*    */       
/* 36 */       try {
    writer = new BufferedWriter(new FileWriter("c:/bk/_avacreditos.010114.txt"));
/* 37 */         writer.write(syourfilename);
}
/*    */       
/* 39 */       catch (IOException iOException)
/*    */       
/*    */       { try {
/* 42 */           if (writer != null) {
/* 43 */             writer.close();
/*    */           }
/* 45 */         } catch (Exception r) {
} } finally { try { if (writer != null) writer.close();  } catch (IOException iOException) {} }
/*    */ 
/*    */     
/*    */     }
/* 49 */     catch (Exception e) {
/* 50 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\hsancheza\Desktop\bk\PrinterService.jar!\FileReaderTransformet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */