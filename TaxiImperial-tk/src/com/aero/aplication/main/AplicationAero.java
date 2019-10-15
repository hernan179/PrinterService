/*
 * To change this template, choose Tools | Templates
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
import javax.swing.SwingWorker;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class AplicationAero extends javax.swing.JFrame {

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

    List<JsonDTO> lstSrvPrevies = new ArrayList<JsonDTO>();

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

    public AplicationAero(String host2, Integer port2, Sitios sitio) {
        this.host = host2;
        this.port = port2;
        this.sitio = sitio;

        if (!(listLugares != null && listLugares.size() > 0)) {
            System.out.println("h: " + host + " po:  " + port);
            jsonLugares = new WebserviceConnection(host, port).getStatusAccount(getJSONSitios(sitio.getIdSitio(), sitio.getNombre()));
            System.out.println("==================\nLISTANDO LUGARES" + jsonLugares);
            if (isOK(jsonLugares)) {
                RtaDTO obj = gson.fromJson(jsonLugares, RtaDTO.class);
                for (JsonDTO p : obj.getAlertas()) {
                    Lugares lugares = new Lugares();
                    lugares.setNombre(p.getNombreSitio());
                    lugares.setDireccion(p.getDireccion());
                    lugares.setValor(p.getValor());
                    listLugares.add(lugares);
                }
            }
        }

        cargarDatosTable();
        escuchador();
        initComponents();
        filtroEvento(DIRECCION);
        process = new ExecuteLoadSocket();
        process.execute();

    }

    public void cargarDatosTable() {

        model.addColumn("NOMBRE");
        model.addColumn("DIRECCION");
        model.addColumn("$ APROX");
        // model.addColumn("CIUDAD");

        for (Lugares l : listLugares) {
            model.addRow(new Object[]{l.getNombre(), l.getDireccion(), l.getValor(), l.getCiudad()});
            sortes.add(l);
        }
    }

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        DIRECCION = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane(table);
        jButton2 = new javax.swing.JButton();
        LUSUARIO = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jtfNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtfNit = new javax.swing.JTextField();
        btDir = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jtfPasajero = new javax.swing.JTextField();
        qsjPreliquidado = new javax.swing.JTextField();
        btConexion = new javax.swing.JButton();
        srvPreviewCount = new javax.swing.JTextField();
        observaciones = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        DIRECCION.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                DIRECCIONKeyTyped(evt);
            }
        });

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        LUSUARIO.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        LUSUARIO.setText("Bienvenido(a): ");

        jButton3.setText("Administrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jToggleButton1.setText("Sitios");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jtfNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNombreActionPerformed(evt);
            }
        });
        jtfNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfNombreKeyTyped(evt);
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

        btDir.setBackground(new java.awt.Color(51, 255, 51));
        btDir.setText("CONSULTAR");
        btDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDirActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 255, 0));
        jButton1.setText("IMP MANUAL");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Pasajeros");

        jtfPasajero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfPasajeroKeyTyped(evt);
            }
        });

        qsjPreliquidado.setEnabled(false);
        qsjPreliquidado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qsjPreliquidadoActionPerformed(evt);
            }
        });

        btConexion.setText("Conexion");
        btConexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConexionActionPerformed(evt);
            }
        });

        srvPreviewCount.setEditable(false);
        srvPreviewCount.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        srvPreviewCount.setForeground(new java.awt.Color(0, 102, 255));
        srvPreviewCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        srvPreviewCount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                srvPreviewCountMouseClicked(evt);
            }
        });
        srvPreviewCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                srvPreviewCountActionPerformed(evt);
            }
        });

        observaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                observacionesKeyTyped(evt);
            }
        });

        jLabel2.setText("Observaciones");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jtfPasajero, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DIRECCION, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtfNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfNit, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(qsjPreliquidado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(observaciones)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 629, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btDir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btConexion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(srvPreviewCount))
                .addGap(29, 29, 29))
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(LUSUARIO)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LUSUARIO)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfPasajero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DIRECCION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(observaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(qsjPreliquidado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btDir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(srvPreviewCount, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton1)
                        .addGap(4, 4, 4)
                        .addComponent(btConexion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void leerUsuario(CajerosAero u, String myClave, Sitios place, String host, Integer port, Properties properties) {
        this.myClave = myClave;
        this.user = u;
        this.sitio = place;
        this.host = host;
        this.port = port;
        this.usuarioInRole = u.getUsuario();
        this.idUsuario = u.getIdUsuario();
        this.properties = properties;

        LUSUARIO.setText(u.getNombre() + "<" + u.getUsuario() + ">" + " EN " + sitio.getNombre() + "<" + sitio.getDireccion() + ">");

    }

    public Ticket loadTikect(String direccion, String nombreCliente, String nitClinte, Long precio, String placa, Integer pasajero,
            String marca, String modelo, Long base, String obser) throws Exception {

        tiquete.setValor("" + precio);
        tiquete.setBase("" + base);
        tiquete.setNombre(nombreCliente);
        tiquete.setNitCliente(nitClinte);
        tiquete.setPasajero(pasajero.toString());

        tiquete.setDestino(direccion);
        tiquete.setUsuario(user);
        tiquete.setPlaca(placa);

        tiquete.setMarca(marca);
        tiquete.setModelo(modelo);
        tiquete.setControl(obser);

        Servicios servicio = new Servicios();
        servicio.setAddres(tiquete.getDestino() + "|" + nombreCliente + "|" + nitClinte + "|" + obser);
        TiqueteDialog ad = new TiqueteDialog(null, true);
        ad.leerCajero(user, tiquete, servicio, true, sitio, host, port, properties);
        ad.setVisible(true);

        DIRECCION.setText("");
        jtfNit.setText("");
        jtfNombre.setText("");
        jtfPasajero.setText("");
        return tiquete;
    }

    public byte[] testPrinter(Ticket tiquest, boolean isWS) throws Exception {
        try {
            String URL = tiquest.getId().toString();
            System.out.println("creando la factura......");
            File file = new File(getCurrentWorkingDirectory() + URL + "_factura.pdf");
            Properties pr = new Properties();
            pr.load(new FileReader(getCurrentWorkingDirectory() + "/factura.properties"));

            byte[] bytes = new CrearDocumento1().generarFactura(tiquest, sitio, pr, null, sitio);

            if (isWS == false) {

                OutputStream rutaArchivo = new FileOutputStream(file);
                rutaArchivo.write(bytes);
            } else {
                System.out.println("No Creando factura.....");
            }
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        boolean puedeSalir = new Administrar().mostrarCierre("si");
        if (puedeSalir) {
            listLugares = new ArrayList<Lugares>();
            new Principal().setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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

    void callRequest(String origen, String nombreCliente, String nitCliente, String placa,
            int pasajeros, String marca, String modelo, String urlLogo, String urlFirma, Integer qsj, String obser) {
        long base = 0;
        if (DIRECCION.getText().length() != 0 && jtfPasajero.getText().length() != 0) {
            JDialog.setDefaultLookAndFeelDecorated(true);

            Integer qsjPreli = getNumberInt(qsjPreliquidado.getText());

            String jsonGeo = null;
            long precio = 0;

            if (qsjPreli <= 0) {
                jsonGeo = new WebserviceConnection(host, port).getStatusAccountZonificar(getGeoRuteo(origen, sitio.getIdSitio()));
            } else {
                precio = qsjPreli;
                if (precio > 0) {
                } else {
                    jsonGeo = new WebserviceConnection(host, port).getStatusAccountZonificar(getGeoRuteo(origen, sitio.getIdSitio()));
                }
            }

            if (isOK(jsonGeo) && jsonGeo != null && !jsonGeo.equals("no")) {

                RtaDTO obj = gson.fromJson(jsonGeo, RtaDTO.class);
                for (JsonDTO p : obj.getAlertas()) {
                    precio = getNumberLong(p.getPrecio());
                    base = getNumberLong(p.getBase());
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
                        loadTikect(origen, nombreCliente, nitCliente, precio, placa, pasajeros, marca, modelo, base, obser);
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
                    String jsonGeo = new WebserviceConnection(host, port).getStatusAccount(probarConexion(idUsuario, usuarioInRole, sitio.getIdSitio()));
                    String rta = "No, hay comunicacion con el servidor";
                    if (isOK(jsonGeo)) {
                        RtaDTO obj = gson.fromJson(jsonGeo, RtaDTO.class);
                        for (JsonDTO p : obj.getAlertas()) {
                            if (p.getError().equals("ok")) {
                                rta = p.getError() + ",hay comunicacion con el servidor";
                            }
                        }
                        if (obj.getLstSrvPreview() != null && obj.getLstSrvPreview().size() > 0) {
                            srvPreviewCount.setText("" + obj.getLstSrvPreview().size());
                            srvPreviewCount.setBackground(Color.YELLOW);
                            lstSrvPrevies = obj.getLstSrvPreview();
                        } else {
                            srvPreviewCount.setText("");
                            srvPreviewCount.setBackground(Color.WHITE);
                            lstSrvPrevies = new ArrayList<JsonDTO>();
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
                    Thread.sleep(1000 * 6 * 10);
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

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// TODO add your handling code here:
    JDialog d = new JDialog(this, "Administracion de usuario", true);
    Administrar adm = new Administrar();
    adm.leerCajero(user, myClave, host, port, sitio);
    d.getContentPane();
    d.setSize(600, 500);
    d.add(adm);
    d.setLocationRelativeTo(null);
    d.setVisible(true);
}//GEN-LAST:event_jButton3ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:

        try {
            File file = new File(getCurrentWorkingDirectory() + "tarifas.pdf");
            //Process process = Runtime.getRuntime().exec(userDir+"tarifas.pdf");
            Desktop.getDesktop().open(file);
        } catch (Exception e) {
        }

    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jtfNitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfNitKeyTyped
        // TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar()) && !Character.isISOControl(evt.getKeyChar())) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
        
             boolean max = jtfNit.getText().length() > 15;
        if (max) {
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
                    String SUR = "";

                    String fullDireccion = DIRECCION.getText();
                    String obser = observaciones.getText();
                    System.out.println("fulDireccion: " + fullDireccion);

                    callRequest(fullDireccion, jtfNombre.getText(), jtfNit.getText(), null, new Integer(jtfPasajero.getText()), null, null, null, null, null, obser);
                } else {
                    DIRECCION.setBackground(Color.red);
                    jtfPasajero.setBackground(Color.red);
                    jtfNombre.setBackground(Color.red);
                    jtfNit.setBackground(Color.red);
                }

            }
        });


    }//GEN-LAST:event_jtfNitKeyTyped

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
            String obser = observaciones.getText();
            callRequest(_DIRECCION, null, nits[1], PLACA, new Integer(jtfPasajero.getText()), nits[2], nits[3], nits[4], nits[5], null, obser);
        }
        DIRECCION.setBackground(Color.white);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtfPasajeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfPasajeroKeyTyped
        // TODO add your handling code here:
        if (!Character.isDigit(evt.getKeyChar()) && !Character.isISOControl(evt.getKeyChar())) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jtfPasajeroKeyTyped

    private void DIRECCIONKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DIRECCIONKeyTyped
        // TODO add your handling code here:
        filtroEvento(DIRECCION);
        int condition = JComponent.WHEN_FOCUSED;
        InputMap iMap = DIRECCION.getInputMap(condition);
        ActionMap aMap = DIRECCION.getActionMap();
        qsjPreliquidado.setText("");
        
        boolean max = DIRECCION.getText().length() > 30;
        if (max) {
            evt.consume();
        }

        String enter = "enter";
        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), enter);
        aMap.put(enter, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String SUR = "";

                String fullDireccion = DIRECCION.getText();
                String obser = observaciones.getText();
                System.out.println("fulDireccion: " + fullDireccion);
                callRequest(fullDireccion, jtfNombre.getText(), jtfNit.getText(), null, new Integer(jtfPasajero.getText()), null, null, null, null, null, obser);

            }
        });
    }//GEN-LAST:event_DIRECCIONKeyTyped

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

    private void jtfNitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNitActionPerformed

    private void qsjPreliquidadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qsjPreliquidadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qsjPreliquidadoActionPerformed

    private void srvPreviewCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_srvPreviewCountActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_srvPreviewCountActionPerformed

    private void srvPreviewCountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_srvPreviewCountMouseClicked
        // TODO add your handling code here:
        JDialog d = new JDialog(this, "Admon servicios previos", true);
        AdmonSrvPreview adm = new AdmonSrvPreview();
        adm.recibirDatos(lstSrvPrevies);
        d.getContentPane();

        d.setSize(800, 500);
        d.add(adm);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }//GEN-LAST:event_srvPreviewCountMouseClicked

    private void jtfNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNombreActionPerformed

    private void observacionesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_observacionesKeyTyped
        // TODO add your handling code here:
        boolean max = observaciones.getText().length() > 30;
        if (max) {
            evt.consume();
        }
    }//GEN-LAST:event_observacionesKeyTyped

    private void jtfNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfNombreKeyTyped
        // TODO add your handling code here:
         boolean max = jtfNombre.getText().length() > 25;
        if (max) {
            evt.consume();
        }
    }//GEN-LAST:event_jtfNombreKeyTyped

    public void escuchador() {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // int tb1columns = table.getColumnCount();
                // int selectionrow = table.getSelectedRow();
                // int selectioncolumn = table.getSelectedColumn();

                int row = table.getSelectedRow();
                //  int col = table.getSelectedColumn();

                DIRECCION.setText("" + table.getValueAt(row, 1));
                qsjPreliquidado.setText("" + table.getValueAt(row, 2));

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DIRECCION;
    private javax.swing.JLabel LUSUARIO;
    private javax.swing.JButton btConexion;
    private javax.swing.JButton btDir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField jtfNit;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JTextField jtfPasajero;
    private javax.swing.JTextField observaciones;
    private javax.swing.JTextField qsjPreliquidado;
    private javax.swing.JTextField srvPreviewCount;
    // End of variables declaration//GEN-END:variables
}
