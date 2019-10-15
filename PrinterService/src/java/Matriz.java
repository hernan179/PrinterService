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
/*    */ public class Matriz
/*    */ {
/*    */   public static void main(String[] args) {
/* 14 */     int[][] matriz = new int[4][4];
/* 15 */     int J = 0;
/* 16 */     for (int i = 0; i < matriz.length; i++) {
/* 17 */       for (int j = 0; j < matriz.length; j++) {
/* 18 */         if (J == 0) {
/* 19 */           J = 2;
/* 20 */           matriz[i][j] = J;
/*    */         } else {
/* 22 */           matriz[i][j] = J * 2;
/* 23 */           J++;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 28 */     for (int i = 0; i < matriz.length; i++) {
/* 29 */       for (int j = 0; j < matriz.length; j++) {
/* 30 */         if (j == matriz.length - 1) {
/* 31 */           System.out.println(matriz[i][j]);
/*    */         } else {
/* 33 */           System.out.print(matriz[i][j] + ", ");
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\hsancheza\Desktop\bk\PrinterService.jar!\Matriz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */