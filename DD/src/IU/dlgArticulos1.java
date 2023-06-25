package IU;

import Componentes.Mensajes;
import Componentes.MiRender;
import Componentes.Reporte;
import Componentes.clsExportarExcel;
import Controladores.CabecerasTablas;
import Controladores.controlArticulo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class dlgArticulos1 extends javax.swing.JDialog {

    CabecerasTablas cabe = new CabecerasTablas();
    clsExportarExcel Export;
    public Reporte jasper;
    
    public dlgArticulos1(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jasper= new Reporte();
        cabe.Articulos(tbProductos);
        controlArticulo.listArticulo(tbProductos, "cod");
        //Render();
        cant();
        cbkBuscarActionPerformed(null);
    }
    
    public static void Render(){
        dlgArticulos1.tbProductos.setDefaultRenderer(Object.class, new MiRender());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        mbtnMmodificar = new javax.swing.JMenuItem();
        mbtnEliminar = new javax.swing.JMenuItem();
        grupoBotones = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        etiCant = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProductos = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        btnSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbkBuscar = new javax.swing.JCheckBox();
        rCodigo = new javax.swing.JRadioButton();
        rDescripcion = new javax.swing.JRadioButton();
        rPrincipio = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemNuevoE = new javax.swing.JMenuItem();
        itemModificarE = new javax.swing.JMenuItem();
        itemEliminarE = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        itemCodigo = new javax.swing.JMenuItem();
        itemNombre = new javax.swing.JMenuItem();
        itemPrincipio = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        itemOrdenC = new javax.swing.JMenuItem();
        itemOrdenN = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        itemExportar = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        itemSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itemNuevoE1 = new javax.swing.JMenuItem();

        mbtnMmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/modificarproducto - copia.png"))); // NOI18N
        mbtnMmodificar.setText("     Modificar");
        mbtnMmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mbtnMmodificarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mbtnMmodificar);

        mbtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/deleteproducto - copia.png"))); // NOI18N
        mbtnEliminar.setText("     Eliminar");
        mbtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mbtnEliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mbtnEliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Gestor de Artículos");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        etiCant.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        etiCant.setText("CANT");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.GridLayout(1, 6));

        btnNuevo.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/addproducto.png"))); // NOI18N
        btnNuevo.setText("Nuevo-F1");
        btnNuevo.setToolTipText("Registrar Nuevo Artículo");
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo);

        btnModificar.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/modificarproducto.png"))); // NOI18N
        btnModificar.setText("Modif-F5");
        btnModificar.setToolTipText("Modificar Artículo");
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar);

        btnEliminar.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 9)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/deleteproducto.png"))); // NOI18N
        btnEliminar.setText("Elim-Supr");
        btnEliminar.setToolTipText("Eliminar Artículo");
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar);

        tbProductos.setBackground(new java.awt.Color(255, 255, 204));
        tbProductos.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 10)); // NOI18N
        tbProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tbProductos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tbProductos.setComponentPopupMenu(jPopupMenu1);
        tbProductos.getTableHeader().setResizingAllowed(false);
        tbProductos.getTableHeader().setReorderingAllowed(false);
        tbProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProductosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbProductosMousePressed(evt);
            }
        });
        tbProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbProductosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbProductos);

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back30.png"))); // NOI18N
        btnSalir.setToolTipText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setPreferredSize(new java.awt.Dimension(53, 47));
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/search15.png"))); // NOI18N
        jLabel3.setText(" Buscar por");
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        cbkBuscar.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        cbkBuscar.setSelected(true);
        cbkBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        cbkBuscar.setOpaque(false);
        cbkBuscar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        cbkBuscar.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        cbkBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbkBuscarActionPerformed(evt);
            }
        });

        grupoBotones.add(rCodigo);
        rCodigo.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        rCodigo.setSelected(true);
        rCodigo.setText("Código de Barra - Alt+C");
        rCodigo.setEnabled(false);
        rCodigo.setOpaque(false);
        rCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rCodigoActionPerformed(evt);
            }
        });

        grupoBotones.add(rDescripcion);
        rDescripcion.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        rDescripcion.setText("Nombre Comercial - Atl+N");
        rDescripcion.setEnabled(false);
        rDescripcion.setOpaque(false);
        rDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rDescripcionActionPerformed(evt);
            }
        });

        grupoBotones.add(rPrincipio);
        rPrincipio.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        rPrincipio.setText("Principio Activo - Atl+P");
        rPrincipio.setEnabled(false);
        rPrincipio.setOpaque(false);
        rPrincipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rPrincipioActionPerformed(evt);
            }
        });

        txtBuscar.setBackground(new java.awt.Color(255, 255, 204));
        txtBuscar.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 11)); // NOI18N
        txtBuscar.setEnabled(false);
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscar)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(cbkBuscar)
                        .addGap(18, 18, 18)
                        .addComponent(rCodigo)
                        .addGap(18, 18, 18)
                        .addComponent(rDescripcion)
                        .addGap(18, 18, 18)
                        .addComponent(rPrincipio)
                        .addGap(0, 620, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbkBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rDescripcion)
                        .addComponent(rPrincipio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Teclas Arriba/Abajo para Navegar | Enter para Seleccionar el Artículo");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel2.setOpaque(true);

        jMenu1.setText("Opciones");
        jMenu1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenu1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        itemNuevoE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        itemNuevoE.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemNuevoE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/addproducto - copia.png"))); // NOI18N
        itemNuevoE.setText("Nuevo");
        itemNuevoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoEActionPerformed(evt);
            }
        });
        jMenu1.add(itemNuevoE);

        itemModificarE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        itemModificarE.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemModificarE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/modificarproducto - copia.png"))); // NOI18N
        itemModificarE.setText("Modificar");
        itemModificarE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemModificarEActionPerformed(evt);
            }
        });
        jMenu1.add(itemModificarE);

        itemEliminarE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        itemEliminarE.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemEliminarE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/deleteproducto - copia.png"))); // NOI18N
        itemEliminarE.setText("Eliminar");
        itemEliminarE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEliminarEActionPerformed(evt);
            }
        });
        jMenu1.add(itemEliminarE);
        jMenu1.add(jSeparator1);

        itemCodigo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK));
        itemCodigo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemCodigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/search20.png"))); // NOI18N
        itemCodigo.setText("Buscar por Codigo de Barra");
        itemCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCodigoActionPerformed(evt);
            }
        });
        jMenu1.add(itemCodigo);

        itemNombre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_DOWN_MASK));
        itemNombre.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/search20.png"))); // NOI18N
        itemNombre.setText("Buscar por Nombre Comercial");
        itemNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNombreActionPerformed(evt);
            }
        });
        jMenu1.add(itemNombre);

        itemPrincipio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_DOWN_MASK));
        itemPrincipio.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemPrincipio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/search20.png"))); // NOI18N
        itemPrincipio.setText("Buscar por Principio Activo");
        itemPrincipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPrincipioActionPerformed(evt);
            }
        });
        jMenu1.add(itemPrincipio);
        jMenu1.add(jSeparator4);

        itemOrdenC.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemOrdenC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/orderC15.png"))); // NOI18N
        itemOrdenC.setText("Ordenar por Código");
        itemOrdenC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemOrdenCActionPerformed(evt);
            }
        });
        jMenu1.add(itemOrdenC);

        itemOrdenN.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemOrdenN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/orderN15.png"))); // NOI18N
        itemOrdenN.setText("Ordenar por Nombre Comercial");
        itemOrdenN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemOrdenNActionPerformed(evt);
            }
        });
        jMenu1.add(itemOrdenN);
        jMenu1.add(jSeparator2);

        itemExportar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/excel15.png"))); // NOI18N
        itemExportar.setText("Exportar datos a EXCEL");
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
        itemSalir.setText("Salir");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        jMenu1.add(itemSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Reporte");
        jMenu2.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenu2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        itemNuevoE1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemNuevoE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/reports.png"))); // NOI18N
        itemNuevoE1.setText("Reporte de Artículos con Stock Crítico");
        itemNuevoE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNuevoE1ActionPerformed(evt);
            }
        });
        jMenu2.add(itemNuevoE1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(etiCant, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiCant, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbProductosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductosMousePressed
        // TODO add your handling code here:
        if (SwingUtilities.isRightMouseButton(evt)) {
            Point p = evt.getPoint();
            int number = tbProductos.rowAtPoint(p);
            ListSelectionModel modelos = tbProductos.getSelectionModel();
            modelos.setSelectionInterval(number, number);
        }
    }//GEN-LAST:event_tbProductosMousePressed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        nuevoArticulo();
    }//GEN-LAST:event_btnNuevoActionPerformed
    void nuevoArticulo() {
        dlgGestArticulos gestArticulos = new dlgGestArticulos(null, true);
        gestArticulos.setLocationRelativeTo(null);
        gestArticulos.Nuevo();
        gestArticulos.setVisible(true);
        
        
    }
    private void mbtnMmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mbtnMmodificarActionPerformed
        // TODO add your handling code here:
        dlgGestArticulos a = new dlgGestArticulos(null, true);
        a.setLocationRelativeTo(null);
        controlArticulo.aModifcar();
        dlgGestArticulos.btnNuevo.setEnabled(false);
        dlgGestArticulos.itemNuevo.setEnabled(false);
        dlgGestArticulos.btnModificar.setEnabled(true);
        dlgGestArticulos.itemModificar.setEnabled(true);
        dlgGestArticulos.btnGuardar.setEnabled(false);
        dlgGestArticulos.itemGuardar.setEnabled(false);
        dlgGestArticulos.btnCancelar.setEnabled(true);
        dlgGestArticulos.itemCancelar.setEnabled(true);
        dlgGestArticulos.txtCodBarra.requestFocus();
        //dlgGestAriculos1.txtStock.setEnabled(false);
        a.modcbLaboratorio();
        a.modcbProveedor();
        a.modcbFamilia();
        dlgGestArticulos.CalculoIVAC();
        a.setVisible(true);
    }//GEN-LAST:event_mbtnMmodificarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        modArticulo();
    }//GEN-LAST:event_btnModificarActionPerformed
    void modArticulo() {
        int x = tbProductos.getSelectedRow();
        if (x < 0) {
            Mensajes.informacion("Seleccione una fila de la tabla");
        } else {
            try {
                dlgGestArticulos a = new dlgGestArticulos(null, true);
                a.setLocationRelativeTo(null);
                controlArticulo.aModifcar();
                dlgGestArticulos.btnNuevo.setEnabled(false);
                dlgGestArticulos.itemNuevo.setEnabled(false);
                dlgGestArticulos.btnModificar.setEnabled(true);
                dlgGestArticulos.itemModificar.setEnabled(true);
                dlgGestArticulos.btnGuardar.setEnabled(false);
                dlgGestArticulos.itemGuardar.setEnabled(false);
                dlgGestArticulos.btnCancelar.setEnabled(true);
                dlgGestArticulos.itemCancelar.setEnabled(true);
                dlgGestArticulos.txtCodBarra.requestFocus();
                //dlgGestAriculos1.txtStock.setEnabled(false);
                a.modcbLaboratorio();
                a.modcbProveedor();
                a.modcbFamilia();
                dlgGestArticulos.CalculoIVAC();
                a.setVisible(true);
            } catch (Exception e) {
                //Mensajes.error("Seleccione una fila de la tabla");     
            }
            tbProductos.clearSelection();
        }

    }
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        delArticulo();
    }//GEN-LAST:event_btnEliminarActionPerformed
    void delArticulo() {
        try {
            int x = tbProductos.getSelectedRow();
            String desc = tbProductos.getValueAt(x, 5).toString();
            int rpta = Mensajes.confirmar("Desea realmente eliminar " + desc + " de la lista");
            if (rpta == 0) {
                controlArticulo.delArticulo();
                CabecerasTablas.limpiarTablas(tbProductos);
                controlArticulo.listArticulo(tbProductos, "cod");
                txtBuscar.setText("");
                txtBuscar.requestFocus();
                cant();
            }
        } catch (Exception e) {
            Mensajes.error("Seleccione una fila de la tabla");
        }
    }
    private void mbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mbtnEliminarActionPerformed
        // TODO add your handling code here:
        try {
            int x = tbProductos.getSelectedRow();
            String desc = tbProductos.getValueAt(x, 5).toString();
            int rpta = Mensajes.confirmar("Desea realmente eliminar " + desc + " de la lista");
            if (rpta == 0) {
                controlArticulo.delArticulo();
                CabecerasTablas.limpiarTablas(tbProductos);
                controlArticulo.listArticulo(tbProductos, "cod");
                txtBuscar.setText("");
                txtBuscar.requestFocus();
                cant();
            }
        } catch (Exception e) {
            Mensajes.informacion("Seleccione una fila de la tabla");
        }
    }//GEN-LAST:event_mbtnEliminarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
        try {
            String cod = txtBuscar.getText();
            
            cabe.Articulos(tbProductos);
            controlArticulo.listArticulo(tbProductos, "cod");
            CabecerasTablas.limpiarTablas(tbProductos);
            controlArticulo.filtrarGral(tbProductos, cod);
            
            
            
            
            /*if (rCodigo.isSelected()) {
                cabe.Articulos(tbProductos);
                controlArticulo.listArticulo(tbProductos, "cod");
                CabecerasTablas.limpiarTablas(tbProductos);
                controlArticulo.filtrarCodBarra(tbProductos, cod);
            } else if (rDescripcion.isSelected()) {
                cabe.Articulos(tbProductos);
                controlArticulo.listArticulo(tbProductos, "cod");
                CabecerasTablas.limpiarTablas(tbProductos);
                controlArticulo.filtrarDescripcion(tbProductos, cod);
            } else if (rPrincipio.isSelected()) {
                cabe.Articulos(tbProductos);
                controlArticulo.listArticulo(tbProductos, "cod");
                CabecerasTablas.limpiarTablas(tbProductos);
                controlArticulo.filrarPrincipio(tbProductos, cod);
            }*/
        } catch (Exception e) {
            System.out.println("Mensaje de Error: " + e.getMessage());
        }
        cant();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void rCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rCodigoActionPerformed
        // TODO add your handling code here:
        seleccionarRadio();
    }//GEN-LAST:event_rCodigoActionPerformed

    private void rDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rDescripcionActionPerformed
        // TODO add your handling code here:
        seleccionarRadio();
    }//GEN-LAST:event_rDescripcionActionPerformed

    private void rPrincipioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rPrincipioActionPerformed
        // TODO add your handling code here:
        seleccionarRadio();
    }//GEN-LAST:event_rPrincipioActionPerformed

    private void tbProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductosMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            try {
                dlgGestArticulos a = new dlgGestArticulos(null, true);
                a.setLocationRelativeTo(null);
                controlArticulo.aModifcar();
                //a.setTitle("Gestionar Productos");
                dlgGestArticulos.btnNuevo.setEnabled(false);
                dlgGestArticulos.itemNuevo.setEnabled(false);
                dlgGestArticulos.btnModificar.setEnabled(true);
                dlgGestArticulos.itemModificar.setEnabled(true);
                dlgGestArticulos.btnGuardar.setEnabled(false);
                dlgGestArticulos.itemGuardar.setEnabled(false);
                dlgGestArticulos.btnCancelar.setEnabled(true);
                dlgGestArticulos.itemCancelar.setEnabled(true);
                dlgGestArticulos.txtCodBarra.requestFocus();
                //dlgGestAriculos1.txtStock.setEnabled(false);
                a.modcbLaboratorio();
                a.modcbProveedor();
                a.modcbFamilia();
                dlgGestArticulos.CalculoIVAC();
                a.setVisible(true);
            } catch (Exception e) {
                Mensajes.error("No se pudo cagar informacion del Producto");

            }
        }
    }//GEN-LAST:event_tbProductosMouseClicked

    private void cbkBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbkBuscarActionPerformed
        // TODO add your handling code here:
        habilitarbusqueda();
    }//GEN-LAST:event_cbkBuscarActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void itemNuevoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevoEActionPerformed
        // TODO add your handling code here:
        btnNuevoActionPerformed(null);
    }//GEN-LAST:event_itemNuevoEActionPerformed

    private void itemModificarEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemModificarEActionPerformed
        // TODO add your handling code here:
        btnModificarActionPerformed(null);
    }//GEN-LAST:event_itemModificarEActionPerformed

    private void itemEliminarEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEliminarEActionPerformed
        // TODO add your handling code here:
        btnEliminarActionPerformed(null);
    }//GEN-LAST:event_itemEliminarEActionPerformed

    private void itemOrdenCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemOrdenCActionPerformed
        // TODO add your handling code here:
        CabecerasTablas.limpiarTablas(tbProductos);
        controlArticulo.listArticulo(tbProductos, "cod");
    }//GEN-LAST:event_itemOrdenCActionPerformed

    private void itemOrdenNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemOrdenNActionPerformed
        // TODO add your handling code here:
        CabecerasTablas.limpiarTablas(tbProductos);
        controlArticulo.listArticulo(tbProductos, "descripcion");
    }//GEN-LAST:event_itemOrdenNActionPerformed

    private void itemExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemExportarActionPerformed

        try {
            Export = new clsExportarExcel();
            Export.exportarExcel(tbProductos);
        } catch (IOException ex) {
            Logger.getLogger(dlgClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_itemExportarActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        // TODO add your handling code here:
        btnSalirActionPerformed(null);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void tbProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProductosKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                dlgGestArticulos a = new dlgGestArticulos(null, true);
                a.setLocationRelativeTo(null);
                controlArticulo.aModifcar();
                dlgGestArticulos.btnNuevo.setEnabled(false);
                dlgGestArticulos.itemNuevo.setEnabled(false);
                dlgGestArticulos.btnModificar.setEnabled(true);
                dlgGestArticulos.itemModificar.setEnabled(true);
                dlgGestArticulos.btnGuardar.setEnabled(false);
                dlgGestArticulos.itemGuardar.setEnabled(false);
                dlgGestArticulos.btnCancelar.setEnabled(true);
                dlgGestArticulos.itemCancelar.setEnabled(true);
                dlgGestArticulos.txtCodBarra.requestFocus();
                //dlgGestAriculos1.txtStock.setEnabled(false);
                a.modcbLaboratorio();
                a.modcbProveedor();
                a.modcbFamilia();
                dlgGestArticulos.CalculoIVAC();
                a.setVisible(true);
            } catch (Exception e) {
                //Mensajes.error("No se pudo cagar informacion del Producto" + e.getMessage());
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txtBuscar.requestFocus();
            txtBuscar.selectAll();
        }
    }//GEN-LAST:event_tbProductosKeyPressed

    private void itemCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCodigoActionPerformed
        // TODO add your handling code here:
        rCodigo.setSelected(true);
    }//GEN-LAST:event_itemCodigoActionPerformed

    private void itemNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNombreActionPerformed
        // TODO add your handling code here:
        rDescripcion.setSelected(true);
    }//GEN-LAST:event_itemNombreActionPerformed

    private void itemPrincipioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPrincipioActionPerformed
        // TODO add your handling code here:
        rPrincipio.setSelected(true);
    }//GEN-LAST:event_itemPrincipioActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus

    }//GEN-LAST:event_formWindowLostFocus

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowStateChanged

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tbProductos.getRowCount() <= 0) {
                txtBuscar.requestFocus();
                txtBuscar.selectAll();
            } else {
                tbProductos.requestFocus();
                tbProductos.getSelectionModel().setSelectionInterval(0, 0);
            }
        }
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void itemNuevoE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNuevoE1ActionPerformed
        // TODO add your handling code here:
        try {
            dlgReporteStockCritico rsc = new dlgReporteStockCritico(null, false);
            rsc.setLocationRelativeTo(null);
            rsc.setVisible(true);
        } catch (Exception e) {
            Mensajes.informacion("No hay conexión con el servidor");
        }

        //
    }//GEN-LAST:event_itemNuevoE1ActionPerformed

    void seleccionarRadio() {
        txtBuscar.setEditable(true);
        cabe.Articulos(tbProductos);
        controlArticulo.listArticulo(tbProductos, "v_articulo.cod");
        txtBuscar.setText("");
        txtBuscar.requestFocus();
    }

    void habilitarbusqueda() {
        if (cbkBuscar.isSelected()) {
            txtBuscar.setEnabled(true);
            rCodigo.setEnabled(true);
            rDescripcion.setEnabled(true);
            rPrincipio.setEnabled(true);
            txtBuscar.requestFocus();
        } else {
            txtBuscar.setEnabled(false);
            rCodigo.setEnabled(false);
            rDescripcion.setEnabled(false);
            rPrincipio.setEnabled(false);
            txtBuscar.setText("");
            CabecerasTablas.limpiarTablas(tbProductos);
            controlArticulo.listArticulo(tbProductos, "Cod");
        }
    }
    private void cant() {
        int total = tbProductos.getRowCount();
        etiCant.setText("Registros acumulados: " + String.valueOf(total));
    }

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(dlgArticulos1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgArticulos1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgArticulos1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgArticulos1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                dlgArticulos1 dialog = new dlgArticulos1(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbkBuscar;
    public static javax.swing.JLabel etiCant;
    private javax.swing.ButtonGroup grupoBotones;
    private javax.swing.JMenuItem itemCodigo;
    private javax.swing.JMenuItem itemEliminarE;
    private javax.swing.JMenuItem itemExportar;
    private javax.swing.JMenuItem itemModificarE;
    private javax.swing.JMenuItem itemNombre;
    private javax.swing.JMenuItem itemNuevoE;
    public javax.swing.JMenuItem itemNuevoE1;
    private javax.swing.JMenuItem itemOrdenC;
    private javax.swing.JMenuItem itemOrdenN;
    private javax.swing.JMenuItem itemPrincipio;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JMenuItem mbtnEliminar;
    private javax.swing.JMenuItem mbtnMmodificar;
    private javax.swing.JRadioButton rCodigo;
    private javax.swing.JRadioButton rDescripcion;
    private javax.swing.JRadioButton rPrincipio;
    public static javax.swing.JTable tbProductos;
    public static javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
