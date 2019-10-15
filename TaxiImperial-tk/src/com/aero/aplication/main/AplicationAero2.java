/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aero.aplication.main;


import com.aero.aplication.docs.CrearDocumento1;
import com.aero.aplication.dto.CajerosAero;
import com.aero.aplication.dto.JsonDTO;
import com.aero.aplication.dto.Lugares;
import com.aero.aplication.dto.RtaDTO;
import com.aero.aplication.dto.Servicios;
import com.aero.aplication.dto.Sitios;
import com.aero.aplication.dto.Ticket;
import static com.aero.aplication.main.InitialContextUtil.getCurrentWorkingDirectory;
import com.aero.rta.conn.WebserviceConnection;
import static com.aero.aplication.tools.Helper.getJSONSitios;
import static com.aero.aplication.tools.Helper.getNumberInt;
import static com.aero.aplication.tools.Helper.getNumberLong;
import static com.aero.aplication.tools.Helper.probarConexion;

import static com.aero.aplication.tools.Helper.isOK;
import com.google.gson.Gson;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.apache.log4j.Logger;
import static com.aero.aplication.tools.Helper.getGeoRuteo;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


/**
 *
 * @author hsancheza
 */
public class AplicationAero2 extends javax.swing.JPanel {

       String PRINTER = "1";
    public ExecuteLoadSocket process;
    String usuarioInRole = "no";
    String idUsuario = "0";
    Boolean isWS;
    String myClave = "";
    private static final Logger log = Logger.getLogger(AplicationAero.class);
    static CajerosAero user = new CajerosAero();
    static Sitios sitio = new Sitios();
    Properties properties;

    static List<Lugares> listLugares = new ArrayList<Lugares>();
    Set<Lugares> sortes = new HashSet<Lugares>();
    UtilContest tools = new UtilContest();
    static String host;
    static Integer port;
    Gson gson = new Gson();
    static String jsonLugares;
    final DefaultTableModel model = new DefaultTableModel();
    final JTable table = new JTable(model) {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    TableRowSorter sorter = new TableRowSorter<DefaultTableModel>(model);
    static Ticket tiquete = new Ticket();
    

    public AplicationAero2() {
        initComponents();
    }
  public Ticket loadTikect(String direccion, String nombreCliente, String nitClinte, Long precio, String placa, Integer pasajero, String marca, String modelo) throws Exception {

        tiquete.setValor("" + precio);
        tiquete.setNombre(nombreCliente);
        tiquete.setNitCliente(nitClinte);
        tiquete.setPasajero(pasajero.toString());

        tiquete.setDestino(direccion);
        tiquete.setUsuario(user);
        tiquete.setPlaca(placa);

        tiquete.setMarca(marca);
        tiquete.setModelo(modelo);

        Servicios servicio = new Servicios();
        servicio.setAddres(tiquete.getDestino() + "|" + nombreCliente + "|" + nitClinte);
        TiqueteDialog ad = new TiqueteDialog(null, true);
        ad.leerCajero(user, tiquete, servicio, true, sitio, host, port, properties);
        ad.setVisible(true);

        DIRECCION.setText("");
        jtfNit.setText("");
        jtfNombre.setText("");
        jtfPasajero.setText("");
        return tiquete;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        LUSUARIO = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtfPasajero = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        DIRECCION = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtfNit = new javax.swing.JTextField();
        qsjPreliquidado = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btDir = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        btConexion = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane(table);

        jLabel1.setText("Pasajeros");

        jButton1.setBackground(new java.awt.Color(51, 255, 0));
        jButton1.setText("IMP MANUAL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        LUSUARIO.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LUSUARIO.setText("Bienvenido(a): ");

        jLabel3.setText("Pasajeros");

        jtfPasajero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfPasajeroActionPerformed(evt);
            }
        });
        jtfPasajero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfPasajeroKeyTyped(evt);
            }
        });

        jLabel2.setText("DIRECCION:");

        DIRECCION.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                DIRECCIONKeyTyped(evt);
            }
        });

        jLabel4.setText("Nombre");

        jLabel5.setText("Nit:");

        jtfNit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNitActionPerformed(evt);
            }
        });
        jtfNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfNitKeyTyped(evt);
            }
        });

        qsjPreliquidado.setEnabled(false);

        jButton3.setText("Administrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(51, 255, 0));
        jButton2.setText("IMP MANUAL");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btDir.setBackground(new java.awt.Color(51, 255, 51));
        btDir.setText("CONSULTAR");
        btDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDirActionPerformed(evt);
            }
        });

        jToggleButton1.setText("Sitios");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        btConexion.setText("Conexion");
        btConexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConexionActionPerformed(evt);
            }
        });

        jButton4.setText("Salir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btConexion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btDir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btDir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btConexion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addComponent(jButton4))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfPasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DIRECCION))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfNit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(qsjPreliquidado, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfPasajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(DIRECCION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(qsjPreliquidado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(LUSUARIO)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(LUSUARIO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtfPasajeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfPasajeroKeyTyped
        // TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar()) && !Character.isISOControl(evt.getKeyChar())) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jtfPasajeroKeyTyped
 private void filtroEvento(JTextField busqueda) {
        table.setRowSorter(sorter);

        RowFilter<DefaultTableModel, Object> filtro = null;
        try {
            filtro = RowFilter.regexFilter(busqueda.getText().toUpperCase(), 0);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(filtro);
    }
    
    private void DIRECCIONKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DIRECCIONKeyTyped
        // TODO add your handling code here:
        filtroEvento(DIRECCION);
        int condition = JComponent.WHEN_FOCUSED;
        InputMap iMap = DIRECCION.getInputMap(condition);
        ActionMap aMap = DIRECCION.getActionMap();
        qsjPreliquidado.setText("");

        String enter = "enter";
        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter);
        aMap.put(enter, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                callRequest(DIRECCION.getText(), jtfNombre.getText(), jtfNit.getText(), null, new Integer(jtfPasajero.getText()), null, null, null, null, null);

            }
        });
    }//GEN-LAST:event_DIRECCIONKeyTyped

     void callRequest(String origen, String nombreCliente, String nitCliente, String placa, int pasajeros, String marca, String modelo, String urlLogo, String urlFirma, Integer qsj) {
        if (DIRECCION.getText().length() != 0 && jtfPasajero.getText().length() != 0) {
            JDialog.setDefaultLookAndFeelDecorated(true);

            Integer qsjPreli = getNumberInt(qsjPreliquidado.getText());

            String jsonGeo = null;
            long precio = 0;

            if (qsjPreli <= 0) {
                    jsonGeo = new WebserviceConnection(host, port).getStatusAccountZonificar(getGeoRuteo(DIRECCION.getText(), sitio.getIdSitio()));
            } else {
                precio = qsjPreli;
                if (precio > 0) {
                } else {
                    jsonGeo = new WebserviceConnection(host, port).getStatusAccountZonificar(getGeoRuteo(DIRECCION.getText(), sitio.getIdSitio()));
                }
            }

            if (isOK(jsonGeo) && jsonGeo != null && !jsonGeo.equals("no")) {

                RtaDTO obj = gson.fromJson(jsonGeo, RtaDTO.class);
                for (JsonDTO p : obj.getAlertas()) {
                    precio = getNumberLong(p.getPrecio());
                }
            } else {

            }

            if (precio <= 0) {
                if (!sitio.getIdSitio().equals("8")) {
                    precio = precioCarrera();
                } else {
                    precio = 1;
                }

            }

            if (precio > 0) {
                String ALERT = "Son: $" + precio + " pesos, Desea imprimir tiquete? ";
                int response = JOptionPane.showConfirmDialog(null, ALERT + "\n\n\n\n\n\n", "Confirmar registro de tiquete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.NO_OPTION) {
                    DIRECCION.setText("");
                } else if (response == JOptionPane.YES_OPTION) {
                    try {
                        log.info(" =================START OPEN WINDOWS TIQUETES ===================");
                        loadTikect(origen, nombreCliente, nitCliente, precio, placa, pasajeros, marca, modelo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (response == JOptionPane.CLOSED_OPTION) {
                    System.out.println("JOptionPane closed");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hya conexion con el servidor, intentelo nuevamente ");
                DIRECCION.setBackground(Color.yellow);
            }
        }
    }

    public int precioCarrera() {
        int intoPreciono = 0;
        String precion = "";

        int i = 1;
        do {
            System.out.println("validar " + i);

            if (intoPreciono == 0) {
                precion = JOptionPane.showInputDialog(this, "Ingresar precio", "Debe ingresar costo de la carrera", JOptionPane.INFORMATION_MESSAGE);
                if (precion != null && !precion.isEmpty() && precion.matches("[0-9]*")) {
                    intoPreciono = new Integer(precion);
                }
            }
            i++;
        } while (i <= 3);
        return intoPreciono;
    }

    class ExecuteLoadSocket extends SwingWorker {

        boolean isContinue = true;

        @Override
        protected Object doInBackground() {

            while (isContinue) {
                try {
                    Thread.sleep(1000 * 6 * 10);

                    String jsonGeo = new WebserviceConnection(host, port).getStatusAccount(probarConexion(idUsuario, usuarioInRole, sitio.getIdSitio()));
                    String rta = "No, hay comunicacion con el servidor";
                    if (isOK(jsonGeo)) {
                        RtaDTO obj = gson.fromJson(jsonGeo, RtaDTO.class);
                        for (JsonDTO p : obj.getAlertas()) {
                            if (p.getError().equals("ok")) {
                                rta = p.getError() + ",hay comunicacion con el servidor";
                            }
                        }

                        btConexion.setForeground(Color.BLACK);
                        btConexion.setBackground(Color.GREEN);

                        LineBorder line = new LineBorder(Color.BLACK);
                        EmptyBorder margin = new EmptyBorder(5, 15, 5, 15);
                        CompoundBorder compound = new CompoundBorder(line, margin);
                        btConexion.setBorder(compound);
                    } else {
                        btConexion.setForeground(Color.BLACK);
                        btConexion.setBackground(Color.RED);

                        LineBorder line = new LineBorder(Color.BLACK);
                        EmptyBorder margin = new EmptyBorder(5, 15, 5, 15);
                        CompoundBorder compound = new CompoundBorder(line, margin);
                        btConexion.setBorder(compound);
                    }
                    System.out.println("conexion:   " + rta);
                } catch (Exception e) {
                    btConexion.setForeground(Color.BLACK);
                    btConexion.setBackground(Color.RED);

                    LineBorder line = new LineBorder(Color.BLACK);
                    EmptyBorder margin = new EmptyBorder(5, 15, 5, 15);
                    CompoundBorder compound = new CompoundBorder(line, margin);
                    btConexion.setBorder(compound);
                }

            }

            return 0;
        }
    }

    
    private void jtfNitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNitActionPerformed

    private void jtfNitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfNitKeyTyped
        // TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar()) && !Character.isISOControl(evt.getKeyChar())) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }

        int condition = JComponent.WHEN_FOCUSED;
        InputMap iMap = jtfNit.getInputMap(condition);
        ActionMap aMap = jtfNit.getActionMap();

        String enter = "enter";
        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter);
        aMap.put(enter, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (isCORRECT()) {
                    callRequest(DIRECCION.getText(), jtfNombre.getText(), jtfNit.getText(), null, new Integer(jtfPasajero.getText()), null, null, null, null, null);
                } else {
                    DIRECCION.setBackground(Color.red);
                    jtfPasajero.setBackground(Color.red);
                    jtfNombre.setBackground(Color.red);
                    jtfNit.setBackground(Color.red);
                }

            }
        });

    }//GEN-LAST:event_jtfNitKeyTyped
  boolean isCORRECT() {
        if (DIRECCION.getText().length() != 0
                && jtfPasajero.getText().length() != 0
                && jtfNombre.getText() != null && jtfNombre.getText().length() != 0
                && jtfNit.getText() != null && jtfNit.getText().length() != 0) {
            return true;
        } else {
            return false;
        }
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        /*JDialog d = new JDialog(this, "Administracion de usuario", true);
        Administrar adm = new Administrar();
        adm.leerCajero(user, myClave, host, port, sitio);
        d.getContentPane();
        d.setSize(600, 500);
        d.add(adm);
        d.setLocationRelativeTo(null);
        d.setVisible(true);*/
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String _DIRECCION = "";
        if (_DIRECCION == "") {
            while (!(_DIRECCION != "")) {
                _DIRECCION = JOptionPane.showInputDialog(this, "Ingrese direccion destino", "DIRECCION DESTINO", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        String PLACA = "";
        String nits[] = null;
        if (_DIRECCION != null && !_DIRECCION.equals("")) {

            if (nits == null) {
                while ((nits == null)) {
                    PLACA = JOptionPane.showInputDialog(this, "Ingrese placa", "IMPRIMIR SERVICIO PLACA", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        //System.out.println("look up... " + PLACA);
                        //DatosRadiolocalizador OBJ = obtenerMovilConPlaca(PLACA);
                        //if (OBJ != null && OBJ.getDocPropietario() != null && !OBJ.getDocPropietario().isEmpty()) {
                            //    nits = OBJ.getDocPropietario();
                            //    System.out.println("Nist: " + nits);
                            //}else{
                            //    PLACA="";
                            //}

                        nits = new LoadPlaca().cargarNits(PLACA);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        DIRECCION.setText(_DIRECCION);
        if (_DIRECCION != null && !_DIRECCION.equals("") && jtfPasajero != null && !jtfPasajero.equals("")) {
            callRequest(_DIRECCION, null, nits[1], PLACA, new Integer(jtfPasajero.getText()), nits[2], nits[3], nits[4], nits[5], null);
        }
        DIRECCION.setBackground(Color.white);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String _DIRECCION = "";
        if (_DIRECCION == "") {
            while (!(_DIRECCION != "")) {
                _DIRECCION = JOptionPane.showInputDialog(this, "Ingrese direccion destino", "DIRECCION DESTINO", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        String PLACA = "";
        String nits[] = null;
        if (_DIRECCION != null && !_DIRECCION.equals("")) {

            if (nits == null) {
                while ((nits == null)) {
                    PLACA = JOptionPane.showInputDialog(this, "Ingrese placa", "IMPRIMIR SERVICIO PLACA", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        //System.out.println("look up... " + PLACA);
                        //DatosRadiolocalizador OBJ = obtenerMovilConPlaca(PLACA);
                        //if (OBJ != null && OBJ.getDocPropietario() != null && !OBJ.getDocPropietario().isEmpty()) {
                            //    nits = OBJ.getDocPropietario();
                            //    System.out.println("Nist: " + nits);
                            //}else{
                            //    PLACA="";
                            //}

                        nits = new LoadPlaca().cargarNits(PLACA);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        DIRECCION.setText(_DIRECCION);
        if (_DIRECCION != null && !_DIRECCION.equals("") && jtfPasajero != null && !jtfPasajero.equals("")) {
            callRequest(_DIRECCION, null, nits[1], PLACA, new Integer(jtfPasajero.getText()), nits[2], nits[3], nits[4], nits[5], null);
        }
        DIRECCION.setBackground(Color.white);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDirActionPerformed
        // TODO add your handling code here:

        if (DIRECCION.getText() != null && !DIRECCION.getText().isEmpty() && DIRECCION.getText().length() > 0) {
            String jsonGeo = new WebserviceConnection(host, port).getStatusAccountZonificar(getGeoRuteo(DIRECCION.getText(), sitio.getIdSitio()));
            long precio = 0;
            if (isOK(jsonGeo)) {
                RtaDTO obj = gson.fromJson(jsonGeo, RtaDTO.class);
                for (JsonDTO p : obj.getAlertas()) {
                    precio = getNumberLong(p.getPrecio());
                }
            }

            JOptionPane.showMessageDialog(null, precio);
        } else {
            DIRECCION.setBackground(Color.red);
        }
    }//GEN-LAST:event_btDirActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:

        try {
            File file = new File(getCurrentWorkingDirectory() + "tarifas.pdf");
            //Process process = Runtime.getRuntime().exec(userDir+"tarifas.pdf");
            Desktop.getDesktop().open(file);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void btConexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConexionActionPerformed
        // TODO add your handling code here:
        String jsonGeo = new WebserviceConnection(host, port).getStatusAccount(probarConexion(idUsuario, usuarioInRole, sitio.getIdSitio()));
        String rta = "No, hay comunicacion con el servidor";
        if (isOK(jsonGeo)) {
            RtaDTO obj = gson.fromJson(jsonGeo, RtaDTO.class);
            for (JsonDTO p : obj.getAlertas()) {
                if (p.getError().equals("ok")) {
                    rta = p.getError() + ",hay comunicacion con el servidor";
                }
            }
            btConexion.setForeground(Color.BLACK);
            btConexion.setBackground(Color.GREEN);

            LineBorder line = new LineBorder(Color.BLACK);
            EmptyBorder margin = new EmptyBorder(5, 15, 5, 15);
            CompoundBorder compound = new CompoundBorder(line, margin);
            btConexion.setBorder(compound);
        } else {
            btConexion.setForeground(Color.BLACK);
            btConexion.setBackground(Color.RED);

            LineBorder line = new LineBorder(Color.BLACK);
            EmptyBorder margin = new EmptyBorder(5, 15, 5, 15);
            CompoundBorder compound = new CompoundBorder(line, margin);
            btConexion.setBorder(compound);
        }
    }//GEN-LAST:event_btConexionActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        boolean puedeSalir = new Administrar().mostrarCierre("si");
        if (puedeSalir) {
            listLugares= new ArrayList<Lugares>();
            new Principal().setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jtfPasajeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfPasajeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfPasajeroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DIRECCION;
    private javax.swing.JLabel LUSUARIO;
    private javax.swing.JButton btConexion;
    private javax.swing.JButton btDir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField jtfNit;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JTextField jtfPasajero;
    private javax.swing.JTextField qsjPreliquidado;
    // End of variables declaration//GEN-END:variables
}
