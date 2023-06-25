package IU;

import Componentes.Mensajes;
import Componentes.RenderDecimal2;
import Componentes.Software;
import Componentes.clsExportarExcel;
import Controladores.CabecerasTablas;
import Controladores.ControlDeudas;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public final class dlgDeudas extends javax.swing.JDialog {

    clsExportarExcel Export;
    public dlgDeudas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        titulo();
        CabecerasTablas.DeudaPendiete(tbDeudasPendientes);
        ControlDeudas.listarDeudasPendientes(tbDeudasPendientes);
        CabecerasTablas.DeudaPendiete(tbDeudasSaldadas);
        ControlDeudas.listarDeudasSaldadas(tbDeudasSaldadas);
        Renders();
        DecimalFormat formateador = new DecimalFormat("#,###");
        lbTotal.setText(formateador.format(CalcularTotalPendiente()));
        lbTotalSaldados.setText(formateador.format(CalcularTotalSaldados()));
        cargarIcono();
    }
    
    public static void Renders() {
        dlgDeudas.tbDeudasPendientes.getColumnModel().getColumn(5).setCellRenderer(new RenderDecimal2());
        dlgDeudas.tbDeudasSaldadas.getColumnModel().getColumn(5).setCellRenderer(new RenderDecimal2());   
    }
    
    final void titulo(){
        if(Software.getSoftware().equals("null")){
            this.setTitle("GESTIONAR DEUDAS");
        }else{
            this.setTitle(Software.getSoftware()+" - GESTIONAR DEUDAS");
        }
    }
    
    public static final int CalcularTotalPendiente(){
        int total=0;
        int filas=tbDeudasPendientes.getRowCount();
        for(int i=0; i<filas;i++){
            total=total+Integer.parseInt(tbDeudasPendientes.getValueAt(i, 5).toString().replace(".", "").replace(",", ""));
        }
        return total;
    }
    
    public static final int CalcularTotalSaldados(){
        int total=0;
        int filas=tbDeudasSaldadas.getRowCount();
        for(int i=0; i<filas;i++){
            total=total+Integer.parseInt(tbDeudasSaldadas.getValueAt(i, 5).toString().replace(".", "").replace(",", ""));
        }
        return total;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        itemPModificarP = new javax.swing.JMenuItem();
        itemPEliminarP = new javax.swing.JMenuItem();
        Blanco = new org.edisoncor.gui.panel.PanelImage();
        Oscuro = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        btnNuevoP = new javax.swing.JButton();
        btnModificarP = new javax.swing.JButton();
        btnEliminarP = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PanelPendientes = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtBuscarPendientes = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDeudasPendientes = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        lbTotal = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        PanelSaldados = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtBuscarSaldadas = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbTotalSaldados = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDeudasSaldadas = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemNuevoE = new javax.swing.JMenuItem();
        itemModificarE = new javax.swing.JMenuItem();
        itemEliminarE = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        itemExportar = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        itemSalir = new javax.swing.JMenuItem();

        jPopupMenu1.setLabel("Opciones");

        itemPModificarP.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        itemPModificarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/editUser15.png"))); // NOI18N
        itemPModificarP.setText("     Modificar");
        itemPModificarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPModificarPActionPerformed(evt);
            }
        });
        jPopupMenu1.add(itemPModificarP);

        itemPEliminarP.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        itemPEliminarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/deleteUser15 (2).png"))); // NOI18N
        itemPEliminarP.setText("     Eliminar");
        itemPEliminarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPEliminarPActionPerformed(evt);
            }
        });
        jPopupMenu1.add(itemPEliminarP);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));

        Oscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/0-102-102.png"))); // NOI18N
        Oscuro.setPreferredSize(new java.awt.Dimension(690, 418));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridLayout(1, 5));

        btnNuevoP.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnNuevoP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/newUsar40.png"))); // NOI18N
        btnNuevoP.setText("NUEVO");
        btnNuevoP.setToolTipText("Nuevo");
        btnNuevoP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevoP.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoPActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevoP);

        btnModificarP.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnModificarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/editUser40.png"))); // NOI18N
        btnModificarP.setText("MODIFICAR");
        btnModificarP.setToolTipText("Modificar");
        btnModificarP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificarP.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnModificarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarPActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificarP);

        btnEliminarP.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnEliminarP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/deleteUser40 (2).png"))); // NOI18N
        btnEliminarP.setText("ELIMINAR");
        btnEliminarP.setToolTipText("Eliminar");
        btnEliminarP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminarP.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminarP);

        btnSalir.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back40.png"))); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.setToolTipText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir);

        javax.swing.GroupLayout OscuroLayout = new javax.swing.GroupLayout(Oscuro);
        Oscuro.setLayout(OscuroLayout);
        OscuroLayout.setHorizontalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        OscuroLayout.setVerticalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setBackground(new java.awt.Color(17, 35, 46));
        jTabbedPane1.setForeground(new java.awt.Color(17, 35, 46));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N

        PanelPendientes.setOpaque(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        txtBuscarPendientes.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        txtBuscarPendientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarPendientesKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarPendientesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarPendientesKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(17, 35, 46));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/search15.png"))); // NOI18N
        jLabel1.setText("BUSCADOR");
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscarPendientes, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        jScrollPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jScrollPane1KeyPressed(evt);
            }
        });

        tbDeudasPendientes.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tbDeudasPendientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDeudasPendientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbDeudasPendientes.getTableHeader().setResizingAllowed(false);
        tbDeudasPendientes.getTableHeader().setReorderingAllowed(false);
        tbDeudasPendientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDeudasPendientesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbDeudasPendientesMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbDeudasPendientesMousePressed(evt);
            }
        });
        tbDeudasPendientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDeudasPendientesKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbDeudasPendientes);

        lbTotal.setBackground(new java.awt.Color(0, 102, 102));
        lbTotal.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(255, 255, 255));
        lbTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotal.setText("0");
        lbTotal.setOpaque(true);

        jLabel3.setBackground(new java.awt.Color(0, 102, 102));
        jLabel3.setFont(new java.awt.Font("Roboto", 1, 21)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("TOTAL PENDIENTES");
        jLabel3.setOpaque(true);

        javax.swing.GroupLayout PanelPendientesLayout = new javax.swing.GroupLayout(PanelPendientes);
        PanelPendientes.setLayout(PanelPendientesLayout);
        PanelPendientesLayout.setHorizontalGroup(
            PanelPendientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPendientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPendientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(PanelPendientesLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PanelPendientesLayout.setVerticalGroup(
            PanelPendientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPendientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPendientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        jTabbedPane1.addTab("DEUDAS PENDIENTES", new javax.swing.ImageIcon(getClass().getResource("/Iconos/documentos.png")), PanelPendientes); // NOI18N

        PanelSaldados.setOpaque(false);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        txtBuscarSaldadas.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        txtBuscarSaldadas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarSaldadasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarSaldadasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarSaldadasKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(17, 35, 46));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/search15.png"))); // NOI18N
        jLabel2.setText("BUSCADOR");
        jLabel2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscarSaldadas, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscarSaldadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        jLabel4.setBackground(new java.awt.Color(0, 102, 102));
        jLabel4.setFont(new java.awt.Font("Roboto", 1, 21)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("TOTAL SALDADAS");
        jLabel4.setOpaque(true);

        lbTotalSaldados.setBackground(new java.awt.Color(0, 102, 102));
        lbTotalSaldados.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        lbTotalSaldados.setForeground(new java.awt.Color(255, 255, 255));
        lbTotalSaldados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTotalSaldados.setText("0");
        lbTotalSaldados.setOpaque(true);

        jScrollPane2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jScrollPane2KeyPressed(evt);
            }
        });

        tbDeudasSaldadas.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tbDeudasSaldadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDeudasSaldadas.getTableHeader().setResizingAllowed(false);
        tbDeudasSaldadas.getTableHeader().setReorderingAllowed(false);
        tbDeudasSaldadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDeudasSaldadasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbDeudasSaldadasMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbDeudasSaldadasMousePressed(evt);
            }
        });
        tbDeudasSaldadas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDeudasSaldadasKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbDeudasSaldadas);

        javax.swing.GroupLayout PanelSaldadosLayout = new javax.swing.GroupLayout(PanelSaldados);
        PanelSaldados.setLayout(PanelSaldadosLayout);
        PanelSaldadosLayout.setHorizontalGroup(
            PanelSaldadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSaldadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSaldadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(PanelSaldadosLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lbTotalSaldados, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PanelSaldadosLayout.setVerticalGroup(
            PanelSaldadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSaldadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSaldadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTotalSaldados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DEUDAS SALDADAS", new javax.swing.ImageIcon(getClass().getResource("/Iconos/documentos.png")), PanelSaldados); // NOI18N

        javax.swing.GroupLayout BlancoLayout = new javax.swing.GroupLayout(Blanco);
        Blanco.setLayout(BlancoLayout);
        BlancoLayout.setHorizontalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Oscuro, javax.swing.GroupLayout.DEFAULT_SIZE, 1122, Short.MAX_VALUE)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BlancoLayout.setVerticalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addComponent(Oscuro, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jMenu1.setText("OPCIONES");
        jMenu1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenu1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        itemNuevoE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        itemNuevoE.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemNuevoE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/newUsar40 - copia.png"))); // NOI18N
        itemNuevoE.setText("NUEVO");
        itemNuevoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoEActionPerformed(evt);
            }
        });
        jMenu1.add(itemNuevoE);

        itemModificarE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        itemModificarE.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemModificarE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/editUser15.png"))); // NOI18N
        itemModificarE.setText("MODIFICAR");
        itemModificarE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemModificarEActionPerformed(evt);
            }
        });
        jMenu1.add(itemModificarE);

        itemEliminarE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        itemEliminarE.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemEliminarE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/deleteUser15 (2).png"))); // NOI18N
        itemEliminarE.setText("ELIMINAR");
        itemEliminarE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEliminarEActionPerformed(evt);
            }
        });
        jMenu1.add(itemEliminarE);
        jMenu1.add(jSeparator2);

        itemExportar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/excel15.png"))); // NOI18N
        itemExportar.setText("EXPORTAR A EXCEL");
        itemExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemExportarActionPerformed(evt);
            }
        });
        jMenu1.add(itemExportar);
        jMenu1.add(jSeparator3);

        itemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        itemSalir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back15.png"))); // NOI18N
        itemSalir.setText("SALIR");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        jMenu1.add(itemSalir);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
        this.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtBuscarPendientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPendientesKeyReleased
        // TODO add your handling code here:
        String cod = txtBuscarPendientes.getText();
        CabecerasTablas.limpiarTablasDeudasPendientes(tbDeudasPendientes);
        CabecerasTablas.DeudaPendiete(tbDeudasPendientes);
        ControlDeudas.filDeudas(tbDeudasPendientes, cod);
        Renders();
        DecimalFormat formateador = new DecimalFormat("#,###");
        lbTotal.setText(formateador.format(CalcularTotalPendiente()));

    }//GEN-LAST:event_txtBuscarPendientesKeyReleased

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        // TODO add your handling code here:
        btnSalirActionPerformed(null);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void btnNuevoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoPActionPerformed
        // TODO add your handling code here:
        dlgGestDeudas prov = new dlgGestDeudas(null, true);
        prov.setLocationRelativeTo(null);
        prov.setTitle("AGREGAR DEUDA");
        prov.setVisible(true);
    }//GEN-LAST:event_btnNuevoPActionPerformed

    private void itemNuevoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevoEActionPerformed
        // TODO add your handling code here:
        btnNuevoPActionPerformed(null);
    }//GEN-LAST:event_itemNuevoEActionPerformed

    private void tbDeudasPendientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDeudasPendientesMousePressed
        // TODO add your handling code here:
        if(SwingUtilities.isRightMouseButton(evt))
        {
            Point p = evt.getPoint();
            int number = tbDeudasPendientes.rowAtPoint(p);
            ListSelectionModel modelos = tbDeudasPendientes.getSelectionModel();
            modelos.setSelectionInterval(number, number);
        }
    }//GEN-LAST:event_tbDeudasPendientesMousePressed

    private void btnModificarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarPActionPerformed
        // TODO add your handling code here:
        if(PanelPendientes.isShowing()){
           if (tbDeudasPendientes.getSelectedRow() < 0) {
            Mensajes.informacion("Seleccione un fila de la tabla");
            System.out.println("Tabla Pendientes");
        } else {
            try {
            dlgGestDeudas prov = new dlgGestDeudas(null, true);
            prov.setLocationRelativeTo(null);
            ControlDeudas.aModificar();
            prov.setTitle("MODIFICAR DEUDA");
            dlgGestDeudas.btnModificar.setEnabled(true);
            dlgGestDeudas.itemModificar.setEnabled(true);
            dlgGestDeudas.btnGuardar.setEnabled(false);
            dlgGestDeudas.itemGuardar.setEnabled(false);
            dlgGestDeudas.btnNuevo.setEnabled(false);
            dlgGestDeudas.itemNuevo.setEnabled(false);
            dlgGestDeudas.btnCancelar.setEnabled(true);
            dlgGestDeudas.itemCancelar.setEnabled(true);
            prov.modCbOrigen();
            prov.modCliente();
            prov.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("No se pudo cargar información de la Deuda"+e.getMessage());
        }
        } 
        }else if(PanelSaldados.isShowing()){
            if (tbDeudasSaldadas.getSelectedRow() < 0) {
            Mensajes.informacion("Seleccione un fila de la tabla");
            System.out.println("Tabla Saldadas");
        } else {
            try {
            dlgGestDeudas prov = new dlgGestDeudas(null, true);
            prov.setLocationRelativeTo(null);
            ControlDeudas.aModificarSaldados();
            prov.setTitle("MODIFICAR DEUDA");
            dlgGestDeudas.btnModificar.setEnabled(true);
            dlgGestDeudas.itemModificar.setEnabled(true);
            dlgGestDeudas.btnGuardar.setEnabled(false);
            dlgGestDeudas.itemGuardar.setEnabled(false);
            dlgGestDeudas.btnNuevo.setEnabled(false);
            dlgGestDeudas.itemNuevo.setEnabled(false);
            dlgGestDeudas.btnCancelar.setEnabled(true);
            dlgGestDeudas.itemCancelar.setEnabled(true);
            prov.modCbOrigen();
            prov.modCliente();
            prov.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("No se pudo cargar información de la Deuda"+e.getMessage());
        }
        }
        }
        
        
    }//GEN-LAST:event_btnModificarPActionPerformed

    private void btnEliminarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPActionPerformed
        // TODO add your handling code here:
        if(PanelPendientes.isShowing()){
            if (tbDeudasPendientes.getSelectedRow() < 0) {
            Mensajes.informacion("Seleccione un fila de la tabla");
        } else {
            try {
                int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resp == JOptionPane.YES_OPTION) {
                    ControlDeudas.anularDeudaPendiente();
                    CabecerasTablas.DeudaPendiete(tbDeudasPendientes);
                    ControlDeudas.listarDeudasPendientes(tbDeudasPendientes);
                    CabecerasTablas.DeudaPendiete(tbDeudasSaldadas);
                    ControlDeudas.listarDeudasSaldadas(tbDeudasSaldadas);
                    Renders();

                }
            } catch (Exception e) {
                //Mensajes.informacion("Seleccione un fila de la tabla");
            }
        }
        }else if(PanelSaldados.isShowing()){
            if (tbDeudasSaldadas.getSelectedRow() < 0) {
            Mensajes.informacion("Seleccione un fila de la tabla");
        } else {
            try {
                int resp = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar el registro?", "Eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resp == JOptionPane.YES_OPTION) {
                    ControlDeudas.anularDeudaSaldada();
                    CabecerasTablas.DeudaPendiete(tbDeudasPendientes);
                    ControlDeudas.listarDeudasPendientes(tbDeudasPendientes);
                    CabecerasTablas.DeudaPendiete(tbDeudasSaldadas);
                    ControlDeudas.listarDeudasSaldadas(tbDeudasSaldadas);
                    Renders();

                }
            } catch (Exception e) {
                //Mensajes.informacion("Seleccione un fila de la tabla");
            }
        }
        }
        
    }//GEN-LAST:event_btnEliminarPActionPerformed

    private void itemModificarEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemModificarEActionPerformed
        // TODO add your handling code here:
        btnModificarPActionPerformed(null);
    }//GEN-LAST:event_itemModificarEActionPerformed

    private void itemEliminarEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEliminarEActionPerformed
        // TODO add your handling code here:
        btnEliminarPActionPerformed(null);
    }//GEN-LAST:event_itemEliminarEActionPerformed

    private void itemPModificarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPModificarPActionPerformed
        // TODO add your handling code here:
        btnModificarPActionPerformed(null);
    }//GEN-LAST:event_itemPModificarPActionPerformed

    private void itemPEliminarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPEliminarPActionPerformed
        // TODO add your handling code here:
        btnEliminarPActionPerformed(null);
    }//GEN-LAST:event_itemPEliminarPActionPerformed

    private void tbDeudasPendientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDeudasPendientesMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            try {
                dlgGestDeudas prov = new dlgGestDeudas(null, true);
                prov.setLocationRelativeTo(null);
                ControlDeudas.aModificar();
                prov.setTitle("MODIFICAR DEUDA");
                dlgGestDeudas.btnModificar.setEnabled(true);
                dlgGestDeudas.itemModificar.setEnabled(true);
                dlgGestDeudas.btnGuardar.setEnabled(false);
                dlgGestDeudas.itemGuardar.setEnabled(false);
                dlgGestDeudas.btnNuevo.setEnabled(false);
                dlgGestDeudas.itemNuevo.setEnabled(false);
                dlgGestDeudas.btnCancelar.setEnabled(true);
                dlgGestDeudas.itemCancelar.setEnabled(true);
                prov.modCbOrigen();
                prov.modCliente();
                prov.setVisible(true);
            } catch (Exception e) {
                Mensajes.informacion("No se pudo cargar información de la Deuda");
            }
        }
    }//GEN-LAST:event_tbDeudasPendientesMouseClicked

    private void itemExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExportarActionPerformed
        // TODO add your handling code here:
        /*try {
            //Agrego el jtable a un ArrayList
            //List<JTable> tb = new ArrayList<JTable>();
            List<JTable> tb = new ArrayList<>();
            tb.add(tablaProveedores);
            
            ExportatExcel excellExporter = new ExportatExcel(tb, new File("Registro de Proveedores.xls"));
            if(excellExporter.Export())
            {
                Mensajes.informacion("DATOS EXPORTADOS CON EXITO");
            }            
            llamaExcel();
        } catch (Exception e) {
            System.out.println("No se pudo");
        }*/
        try {
            Export = new clsExportarExcel();
            Export.exportarExcel(tbDeudasPendientes);
        } catch (IOException ex) {
            Logger.getLogger(dlgDeudas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_itemExportarActionPerformed

    private void txtBuscarPendientesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPendientesKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(Character.isLowerCase(c)){
            String cad=(""+c).toUpperCase();
            c=cad.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_txtBuscarPendientesKeyTyped

    private void txtBuscarPendientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarPendientesKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tbDeudasPendientes.requestFocus();
            int r=tbDeudasPendientes.getRowCount ();
            tbDeudasPendientes.changeSelection ( tbDeudasPendientes.getRowCount ()-r, 5, false, false );
        }
    }//GEN-LAST:event_txtBuscarPendientesKeyPressed

    private void jScrollPane1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1KeyPressed

    private void tbDeudasPendientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDeudasPendientesKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                dlgGestDeudas prov = new dlgGestDeudas(null, true);
                prov.setLocationRelativeTo(null);
                ControlDeudas.aModificar();
                prov.setTitle("MODIFICAR DEUDA");
                dlgGestDeudas.btnModificar.setEnabled(true);
                dlgGestDeudas.itemModificar.setEnabled(true);
                dlgGestDeudas.btnGuardar.setEnabled(false);
                dlgGestDeudas.itemGuardar.setEnabled(false);
                dlgGestDeudas.btnNuevo.setEnabled(false);
                dlgGestDeudas.itemNuevo.setEnabled(false);
                dlgGestDeudas.btnCancelar.setEnabled(true);
                dlgGestDeudas.itemCancelar.setEnabled(true);
                prov.modCbOrigen();
                prov.modCliente();
                prov.setVisible(true);
            } catch (Exception e) {
                Mensajes.informacion("No se pudo cargar información de la Deuda");
            }
        }
    }//GEN-LAST:event_tbDeudasPendientesKeyPressed

    private void tbDeudasPendientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDeudasPendientesMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDeudasPendientesMouseEntered

    private void txtBuscarSaldadasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarSaldadasKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tbDeudasSaldadas.requestFocus();
            int r=tbDeudasSaldadas.getRowCount ();
            tbDeudasSaldadas.changeSelection ( tbDeudasSaldadas.getRowCount ()-r, 5, false, false );
        }
    }//GEN-LAST:event_txtBuscarSaldadasKeyPressed

    private void txtBuscarSaldadasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarSaldadasKeyReleased
        // TODO add your handling code here:
        String cod = txtBuscarSaldadas.getText();
        CabecerasTablas.limpiarTablasDeudasPendientes(tbDeudasSaldadas);
        CabecerasTablas.DeudaPendiete(tbDeudasSaldadas);
        ControlDeudas.filDeudasSaldadas(tbDeudasSaldadas, cod);
        DecimalFormat formateador = new DecimalFormat("#,###");
        lbTotal.setText(formateador.format(CalcularTotalPendiente()));
    }//GEN-LAST:event_txtBuscarSaldadasKeyReleased

    private void txtBuscarSaldadasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarSaldadasKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(Character.isLowerCase(c)){
            String cad=(""+c).toUpperCase();
            c=cad.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_txtBuscarSaldadasKeyTyped

    private void tbDeudasSaldadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDeudasSaldadasMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            try {
                dlgGestDeudas prov = new dlgGestDeudas(null, true);
                prov.setLocationRelativeTo(null);
                ControlDeudas.aModificarSaldados();
                prov.setTitle("MODIFICAR DEUDA");
                dlgGestDeudas.btnModificar.setEnabled(true);
                dlgGestDeudas.itemModificar.setEnabled(true);
                dlgGestDeudas.btnGuardar.setEnabled(false);
                dlgGestDeudas.itemGuardar.setEnabled(false);
                dlgGestDeudas.btnNuevo.setEnabled(false);
                dlgGestDeudas.itemNuevo.setEnabled(false);
                dlgGestDeudas.btnCancelar.setEnabled(true);
                dlgGestDeudas.itemCancelar.setEnabled(true);
                prov.modCbOrigen();
                prov.modCliente();
                prov.setVisible(true);
            } catch (Exception e) {
                Mensajes.informacion("No se pudo cargar información de la Deuda");
            }
        }
    }//GEN-LAST:event_tbDeudasSaldadasMouseClicked

    private void tbDeudasSaldadasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDeudasSaldadasMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDeudasSaldadasMouseEntered

    private void tbDeudasSaldadasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDeudasSaldadasMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbDeudasSaldadasMousePressed

    private void tbDeudasSaldadasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDeudasSaldadasKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                dlgGestDeudas prov = new dlgGestDeudas(null, true);
                prov.setLocationRelativeTo(null);
                ControlDeudas.aModificarSaldados();
                prov.setTitle("MODIFICAR DEUDA");
                dlgGestDeudas.btnModificar.setEnabled(true);
                dlgGestDeudas.itemModificar.setEnabled(true);
                dlgGestDeudas.btnGuardar.setEnabled(false);
                dlgGestDeudas.itemGuardar.setEnabled(false);
                dlgGestDeudas.btnNuevo.setEnabled(false);
                dlgGestDeudas.itemNuevo.setEnabled(false);
                dlgGestDeudas.btnCancelar.setEnabled(true);
                dlgGestDeudas.itemCancelar.setEnabled(true);
                prov.modCbOrigen();
                prov.modCliente();
                prov.setVisible(true);
            } catch (Exception e) {
                Mensajes.informacion("No se pudo cargar información de la Deuda");
            }
        }
    }//GEN-LAST:event_tbDeudasSaldadasKeyPressed

    private void jScrollPane2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2KeyPressed

    void cargarIcono() {
        try {
            java.awt.Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Iconos/logo1.png"));
            setIconImage(icon);
        } catch (Exception e) {
            Mensajes.error("No se pudo cargo icono");
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgDeudas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> {
            dlgDeudas dialog = new dlgDeudas(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.panel.PanelImage Blanco;
    private org.edisoncor.gui.panel.PanelImage Oscuro;
    private javax.swing.JPanel PanelPendientes;
    private javax.swing.JPanel PanelSaldados;
    private javax.swing.JButton btnEliminarP;
    private javax.swing.JButton btnModificarP;
    private javax.swing.JButton btnNuevoP;
    private javax.swing.JButton btnSalir;
    private javax.swing.JMenuItem itemEliminarE;
    private javax.swing.JMenuItem itemExportar;
    private javax.swing.JMenuItem itemModificarE;
    private javax.swing.JMenuItem itemNuevoE;
    private javax.swing.JMenuItem itemPEliminarP;
    private javax.swing.JMenuItem itemPModificarP;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JLabel lbTotal;
    public static javax.swing.JLabel lbTotalSaldados;
    public static javax.swing.JTable tbDeudasPendientes;
    public static javax.swing.JTable tbDeudasSaldadas;
    private javax.swing.JTextField txtBuscarPendientes;
    private javax.swing.JTextField txtBuscarSaldadas;
    // End of variables declaration//GEN-END:variables
}
