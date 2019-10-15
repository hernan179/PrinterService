/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.rta.easypass;

import com.aero.rta.conn.WebserviceConnection;
import com.aero.rta.dto.JsonDTO;
import com.aero.rta.dto.RtaDTO;
import com.google.gson.Gson;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import com.aero.easypass.tools.Utilitario;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import static com.aero.rta.dto.TestBarcode.addEmage;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import static com.aero.easypass.tools.Utilitario.crearReporte;
import java.awt.Cursor;
import java.awt.image.BufferedImage;
import java.net.Inet4Address;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.RootPaneContainer;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author hsancheza
 */
public class Administrar extends javax.swing.JFrame {

    boolean start = true;
    Set<String> sortes = new HashSet<String>();
    DefaultTableModel model = new DefaultTableModel();
    String host;
    Integer port;
    private DataInputStream entradaDatos;
    private DataOutputStream salidaDatos;
    boolean isOpen = false;
    ServerSocket serverSocket = null;
    Socket socket;
    static Gson gson = new Gson();
    Thread t = null;
    public ExecuteLoadSocket process;
    static Document document = new Document(PageSize.LETTER);
    static ByteArrayOutputStream baos = new ByteArrayOutputStream();
    String actividad = "";

    public Administrar() {
    }

    public Administrar(String host, Integer port) {
        this.host = host;
        this.port = port;
        model.setRowCount(0);
        model.addColumn("ID");
        model.addColumn("FECHA");
        model.addColumn("PLACA");
        model.addColumn("CONTRATO");
        model.addColumn("DIAS");
        RootPaneContainer root = (RootPaneContainer) this.getRootPane().getTopLevelAncestor();
        root.getGlassPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        root.getGlassPane().setVisible(true);
        System.out.println("Waitting....."+new Date());
        cargarDatosTable();
        System.out.println("endding....."+new Date());  
        root.getGlassPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        initComponents();

        btOff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                jtaConsola.setText("");
            }
        });

        btOn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                process = new ExecuteLoadSocket();
                process.execute();
                btOn.setEnabled(false);

            }
        });

    }

    public void cargarDatosTable() {
        //model = new DefaultTableModel();

        // model.setRowCount(0);
        model.setNumRows(0);

        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();

        String json = "["
                + "    {"
                + "        \"empresaId\":\"12\",\n"
                + "	\"contrato\":\"" + 1 + "\",\n"
                + "	\"fecha\":\"" + Utilitario.getColCurrentTime() + "\",\n"
                + "	\"keyCommand\":\"2\"\n"
                + "    }"
                + "]";

        String jsonIn = new WebserviceConnection(host, port).getStatusAccount(json);

        System.out.println("entrada from server :   " + jsonIn);
        if (jsonIn != null && !jsonIn.isEmpty() && jsonIn != "") {
            Gson gson = new Gson();
            RtaDTO obj = gson.fromJson(jsonIn, RtaDTO.class);
            int i = 1;
            if (obj.getAlertas() != null && !obj.getAlertas().isEmpty()) {
                for (JsonDTO j : obj.getAlertas()) {
                    try {
                        model.addRow(new Object[]{i, j.getFecha(), j.getPlaca(), j.getContrato(), j.getDias()});
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        } else {
            System.out.println("cargando datos desde el WS 3");
        }
        // model.fireTableDataChanged();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btOn = new javax.swing.JToggleButton();
        btOff = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaConsola = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/aero/rta/img/logoCompany.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setText("ACTIVIDAD EN LINEA");

        jTable1.setModel(model);
        jScrollPane2.setViewportView(jTable1);

        btOn.setText("Recibir peticiones");
        btOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOnActionPerformed(evt);
            }
        });

        btOff.setText("Limpiar");
        btOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOffActionPerformed(evt);
            }
        });

        jtaConsola.setColumns(20);
        jtaConsola.setRows(5);
        jScrollPane1.setViewportView(jtaConsola);

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Reorte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Barcode");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btOn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btOff)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)))))
                .addGap(168, 168, 168))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btOn)
                            .addComponent(btOff)
                            .addComponent(jButton2)
                            .addComponent(jButton1)
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOffActionPerformed
        // TODO add your handling code here:
        jtaConsola.setText("");
        actividad = "";
    }//GEN-LAST:event_btOffActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new Utilitario().position(new Principal(host, port));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        try {
            String json = "["
                    + "    {"
                    + "        \"empresaId\":\"12\",\n"
                    + "	\"fecha\":\"" + Utilitario.getColCurrentTime() + "\",\n"
                    + "	\"keyCommand\":\"2\"\n"
                    + "    }"
                    + "]";
            String jsonIn = new WebserviceConnection(host, port).getStatusAccount(json);

            RtaDTO obj = gson.fromJson(jsonIn, RtaDTO.class);
            String data = "";
            if (jsonIn != null && !jsonIn.isEmpty() && jsonIn != "") {
                Gson gson = new Gson();
                int i = 1;
                if (obj.getAlertas() != null && !obj.getAlertas().isEmpty()) {

                    data += "CONTRATO,DETALLE,FECHA,DIAS,PLACA\n";
                    for (JsonDTO j : obj.getAlertas()) {
                        data += j.getContrato() + "," + j.getDetalle() + "," + j.getFecha() + "," + j.getDias() + "," + j.getPlaca() + "\n";
                    }
                }
            }
            Desktop.getDesktop().open(crearReporte(data, "estadoCuenta.xls"));
            // generateCode(obj);
            //generateCode2(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:
            String json = "["
                    + "    {"
                    + "        \"empresaId\":\"12\",\n"
                    + "	\"fecha\":\"" + Utilitario.getColCurrentTime() + "\",\n"
                    + "	\"keyCommand\":\"2\"\n"
                    + "    }"
                    + "]";
            String jsonIn = new WebserviceConnection(host, port).getStatusAccount(json);

            RtaDTO obj = gson.fromJson(jsonIn, RtaDTO.class);
            generateCode2(obj);
        } catch (Exception ex) {
            Logger.getLogger(Administrar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void btOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btOnActionPerformed
    public void generateCode2(RtaDTO obj) throws Exception {
        int i = 0;
        for (JsonDTO ob : obj.getAlertas()) {
            //if (i < 5) {
            BufferedImage buffer = addEmage(ob.getContrato() + ob.getPlaca());
            try {

                String file = new File(".").getCanonicalPath() + "/barcode/" + ob.getPlaca() + ".png";
                File outputfile = new File(file);
                ImageIO.write(buffer, "png", outputfile); // Write the Buffered Image into an output file
            } catch (Exception e) {
                e.printStackTrace();
            }

//            }
            i++;
        }
    }

    public void generateCode(RtaDTO obj) {
        try {

            PdfPTable itextTable = new PdfPTable(20);
            itextTable.setWidthPercentage(98);

            PdfPCell cell00 = new PdfPCell();
            cell00.setBorder(0);
            cell00.setColspan(20);
            cell00.addElement(new Paragraph(" "));
            itextTable.addCell(cell00);

            PdfWriter writer = PdfWriter.getInstance(document, baos);
            document.setMargins(-1, -1, 3, 1);
            document.open();

            int i = 1;

            for (JsonDTO ob : obj.getAlertas()) {
                int column = 0;
                //if (i < 20) {
                System.out.println("datos:  contrato:" + ob.getContrato() + "  placa:" + ob.getPlaca());
                BufferedImage buffer = addEmage(ob.getContrato() + ob.getPlaca());
                Image img = Image.getInstance(buffer, null);
                PdfPCell cell = new PdfPCell();
                PdfPCell cell2 = new PdfPCell();

                if (i % 3 == 0) {
                    cell.setColspan(6);
                    cell.setPadding(1);
                    cell.setBorder(0);
                    cell.setPaddingTop(1);
                    cell.setPaddingBottom(1);
                    cell.addElement(img);
                    itextTable.addCell(cell);
                } else {
                    cell.setColspan(6);
                    cell.setPadding(1);
                    cell.setBorder(0);
                    cell.setPaddingTop(1);
                    cell.setPaddingBottom(1);
                    cell.addElement(img);
                    itextTable.addCell(cell);

                    cell2.setPadding(1);
                    cell2.setBorder(0);
                    cell2.setPaddingTop(1);
                    cell2.setPaddingBottom(1);
                    itextTable.addCell(cell2);

                }
                if (i % 3 == 0) {
                    PdfPCell cell000 = new PdfPCell();
                    cell000.setColspan(20);
                    cell000.setPadding(1);
                    cell000.setBorder(0);
                    cell000.setPaddingTop(1);
                    cell000.setPaddingBottom(1);

                    //cell000.addElement(new Paragraph());
                    // itextTable.addCell(cell000);
                }

                i++;

            }
            document.add(itextTable);
            document.close();
            String file = "C:/vehiculosBarCode.pdf";
            OutputStream RutaArchivo = new FileOutputStream(file);
            baos.writeTo(RutaArchivo);

//            if (Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
//                Desktop.getDesktop().open(new File(file));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {

        for (int i = 1; i < 50; i++) {
            if (i % 3 == 0) {
                System.out.println("i % 3 == 0: " + i);
            } else {
                System.out.print("i solo" + i);
            }
        }

        String json = "";
        json += "[{\"contrato\":\"1\",\"fecha\":\"July 09 2014 15:055:25\",\"placa\":\"TUO565  \",\"detalle\":\"P\",\"keyCommand\":\"3\",\"dias\":\"124\",\"error\":\"ok\"}]";

        System.out.println("json:   " + json);
        JsonDTO[] entra = gson.fromJson(json.trim(), JsonDTO[].class);

        JsonDTO dto = entra[0];
//                           
        String actividad = "";
        if (entra[0].getKeyCommand()
                .equals("3")) {
            actividad = "Salida de parqueadero principal av 26";

        } else {
            actividad = "Salida de parqueadero auxiliar av 26";
        }

        System.out.println(
                "atcv:   " + actividad);

        actividad += "\n" + dto.getPlaca();
        actividad += " " + dto.getContrato();
        actividad += " " + dto.getDias();
        actividad += " " + dto.getFecha();
    }

    class ExecuteLoadSocket extends SwingWorker {

        @Override
        protected Object doInBackground() {

            try {
                String jsonIn;
                String capitalizedSentence;
                serverSocket = new ServerSocket(1234);

                do {
                    System.out.println(String.format("SERVER INICIADO %s:%d", Inet4Address.getLocalHost().getAddress(), serverSocket.getLocalPort()));
                    actividad = jtaConsola.getText() != null && jtaConsola.getText().length() > 0 ? jtaConsola.getText() + "\n" : "";
                    socket = serverSocket.accept();
                    BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
                    jsonIn = inFromClient.readLine();
                    Gson gson = new Gson();
                    JsonDTO[] entra = gson.fromJson(jsonIn.trim(), JsonDTO[].class);
                    System.out.println("entrada form cliente:  " + jsonIn);
                    capitalizedSentence = jsonIn.toUpperCase() + '\n';
                    outToClient.writeBytes(capitalizedSentence);

                    JsonDTO dto = entra[0];

                    if (entra[0].getKeyCommand().equals("3")) {
                        actividad += "VEHICULO";
                        cargarDatosTable();
                    } else {
                        actividad += "Salida de parqueadero auxiliar av 26";
                    }
                    actividad += " " + dto.getPlaca();
                    actividad += " CONTRATO[" + dto.getContrato() + "]";

                    if (new Integer(dto.getDias()) > 60) {
                        String motivo = " BLOQUEADO POR MORA!!! ";
                        actividad += motivo;

                    } else {
                        actividad += " DIAS MORA[" + dto.getDias() + "]";
                    }

                    actividad += " HORA[" + dto.getFecha() + "]";
                    actividad += " SALIENDO DEL PARQUEADERO PRINCIPAL";
                    System.out.println("nv mensaje:   " + actividad);
                    jtaConsola.setText(actividad);

                } while (start);
                System.out.println("SERVER STOPING");
                System.out.println("Daemon?: " + Thread.currentThread().isDaemon());
                // server.close();

                // return 0;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(String.format("SERVER STOP %s:%d", serverSocket.getInetAddress(), serverSocket.getLocalPort()));
                //btOn.setEnabled(true);
                //btOff.setEnabled(false);

            }

            return 0;
        }

        @Override
        protected void done() {
            try {
                if (process.isCancelled() && !start) {
                    serverSocket.close();
                    System.out.println("done() esta en el hilo " + Thread.currentThread().getName());
                    System.out.println(String.format("KILLER PROCCES %s:%d", serverSocket.getInetAddress(), serverSocket.getLocalPort()));
//
//                Runtime rt = Runtime.getRuntime();
//                if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) {
//                    rt.exec("taskkill " + Thread.currentThread().getName());
//                } else {
//                    rt.exec("kill -9 " + Thread.currentThread().getName());
//                }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btOff;
    private javax.swing.JToggleButton btOn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jtaConsola;
    // End of variables declaration//GEN-END:variables
}
