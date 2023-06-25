package IU;

import Componentes.Mensajes;
import Componentes.RenderDecimal2;
import Componentes.RenderDecimal1;
import Componentes.RenderDecimalconPuntos;
import Componentes.RenderPagos;
import Componentes.Software;
import Componentes.cargarComboBox;
import Controladores.CabecerasTablas;
import Controladores.controlCompra;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class dlgConsultarPagosProveedor extends javax.swing.JDialog {

    CabecerasTablas cabe = new CabecerasTablas();

    public dlgConsultarPagosProveedor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        titulo();
        cabe.consPagosProveedor(tbPagosProveedor);
        cabe.consDetallePagosProveedor(tbDetallePagosProveedor);
        controlCompra.listarPagosCompras(tbPagosProveedor);
        cargarComboBox.cargar(cbProveedores, "SELECT pro_codigo, pro_razonsocial FROM proveedor WHERE pro_indicador='S'");
        Renders();
        txtCodProveedor.setVisible(false);

    }

    final void titulo() {
        if (Software.getSoftware().equals("null")) {
            this.setTitle("Gestión de pagos realizados");
        } else {
            this.setTitle(Software.getSoftware() + " - Gestión de pagos realizados");
        }
    }

    public static void Renders() {
        dlgConsultarPagosProveedor.tbPagosProveedor.getColumnModel().getColumn(6).setCellRenderer(new RenderDecimal2());
        //dlgConsultarPagosProveedor.tbPagosProveedor.getColumnModel().getColumn(13).setCellRenderer(new RenderPagos());
        //dlgConsultarPagosProveedor.tbPagosProveedor.getColumnModel().getColumn(14).setCellRenderer(new RenderDecimal2());
    }

    public static void RendersD() {
        dlgConsultarPagosProveedor.tbDetallePagosProveedor.getColumnModel().getColumn(6).setCellRenderer(new RenderDecimal1());
        dlgConsultarPagosProveedor.tbDetallePagosProveedor.getColumnModel().getColumn(7).setCellRenderer(new RenderDecimal2());
        dlgConsultarPagosProveedor.tbDetallePagosProveedor.getColumnModel().getColumn(9).setCellRenderer(new RenderDecimal1());
        //dlgConsultarPagosProveedor.tbDetallePagosProveedor.getColumnModel().getColumn(8).setCellRenderer(new RenderDecimalconPuntos());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Blanco = new org.edisoncor.gui.panel.PanelImage();
        Oscuro = new org.edisoncor.gui.panel.PanelImage();
        jPanel2 = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        txtCodProveedor = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPagosProveedor = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDetallePagosProveedor = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowInddex, int celIndex)
            {
                return false;
            }
        };
        cbProveedores = new RSMaterialComponent.RSComboBoxMaterial();
        btnFiltrar = new javax.swing.JButton();
        ckHabilitar = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        itemBuscarA = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        itemQuitar = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        itemSalir = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        itemBuscar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        Blanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/fondoBlanco.jpg"))); // NOI18N
        Blanco.setPreferredSize(new java.awt.Dimension(690, 418));

        Oscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/0-102-102.png"))); // NOI18N
        Oscuro.setPreferredSize(new java.awt.Dimension(690, 418));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        btnActualizar.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(0, 102, 102));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/refress_30.png"))); // NOI18N
        btnActualizar.setText("ACTUALIZAR PAGOS");
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizar);

        btnAnular.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnAnular.setForeground(new java.awt.Color(0, 102, 102));
        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/delete1.png"))); // NOI18N
        btnAnular.setText("ANULAR PAGO");
        btnAnular.setEnabled(false);
        btnAnular.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAnular.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });
        jPanel2.add(btnAnular);

        btnSalir.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(0, 102, 102));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back30.png"))); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir);

        txtCodProveedor.setEditable(false);
        txtCodProveedor.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 12)); // NOI18N
        txtCodProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout OscuroLayout = new javax.swing.GroupLayout(Oscuro);
        Oscuro.setLayout(OscuroLayout);
        OscuroLayout.setHorizontalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCodProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        OscuroLayout.setVerticalGroup(
            OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OscuroLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(OscuroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jScrollPane1.setOpaque(false);

        tbPagosProveedor.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        tbPagosProveedor.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPagosProveedor.setGridColor(new java.awt.Color(204, 204, 204));
        tbPagosProveedor.setRowHeight(20);
        tbPagosProveedor.setShowGrid(true);
        tbPagosProveedor.setShowVerticalLines(false);
        tbPagosProveedor.getTableHeader().setResizingAllowed(false);
        tbPagosProveedor.getTableHeader().setReorderingAllowed(false);
        tbPagosProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPagosProveedorMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbPagosProveedorMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbPagosProveedor);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);

        jScrollPane2.setOpaque(false);

        tbDetallePagosProveedor.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        tbDetallePagosProveedor.setModel(new javax.swing.table.DefaultTableModel(
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
        tbDetallePagosProveedor.setEnabled(false);
        tbDetallePagosProveedor.setGridColor(new java.awt.Color(204, 204, 204));
        tbDetallePagosProveedor.setRowHeight(20);
        tbDetallePagosProveedor.setShowGrid(true);
        tbDetallePagosProveedor.setShowVerticalLines(false);
        tbDetallePagosProveedor.getTableHeader().setResizingAllowed(false);
        tbDetallePagosProveedor.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbDetallePagosProveedor);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 947, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addContainerGap())
        );

        cbProveedores.setColorMaterial(new java.awt.Color(0, 102, 102));
        cbProveedores.setEnabled(false);
        cbProveedores.setFont(new java.awt.Font("Roboto", 1, 11)); // NOI18N
        cbProveedores.setOpaque(true);
        cbProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProveedoresActionPerformed(evt);
            }
        });

        btnFiltrar.setText("FITRAR FACTURAS");
        btnFiltrar.setEnabled(false);
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        ckHabilitar.setText("Habilitar Filtro por Proveedor");
        ckHabilitar.setOpaque(false);
        ckHabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckHabilitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BlancoLayout = new javax.swing.GroupLayout(Blanco);
        Blanco.setLayout(BlancoLayout);
        BlancoLayout.setHorizontalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Oscuro, javax.swing.GroupLayout.DEFAULT_SIZE, 991, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BlancoLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(ckHabilitar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFiltrar))
                    .addGroup(BlancoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BlancoLayout.setVerticalGroup(
            BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlancoLayout.createSequentialGroup()
                .addComponent(Oscuro, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(BlancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(ckHabilitar))
                    .addComponent(btnFiltrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jMenu2.setText("Opciones");
        jMenu2.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenu2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        itemBuscarA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        itemBuscarA.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        itemBuscarA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/refress_30.png"))); // NOI18N
        itemBuscarA.setText("ACTUALIZAR PAGOS");
        itemBuscarA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarAActionPerformed(evt);
            }
        });
        jMenu2.add(itemBuscarA);
        jMenu2.add(jSeparator4);

        itemQuitar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        itemQuitar.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        itemQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/16.png"))); // NOI18N
        itemQuitar.setText("ANULAR PAGOS");
        itemQuitar.setEnabled(false);
        itemQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemQuitarActionPerformed(evt);
            }
        });
        jMenu2.add(itemQuitar);
        jMenu2.add(jSeparator5);

        itemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        itemSalir.setFont(new java.awt.Font("Roboto", 1, 9)); // NOI18N
        itemSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/back15.png"))); // NOI18N
        itemSalir.setText("SALIR");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        jMenu2.add(itemSalir);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Búsqueda");
        jMenu1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jMenu1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        itemBuscar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        itemBuscar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        itemBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/search15.png"))); // NOI18N
        itemBuscar.setText("por N° de Factura Compra   ");
        itemBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBuscarActionPerformed(evt);
            }
        });
        jMenu1.add(itemBuscar);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Blanco, javax.swing.GroupLayout.DEFAULT_SIZE, 991, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Blanco, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbPagosProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPagosProveedorMouseClicked
        // TODO add your handling code here:
        /*try {
            CabecerasTablas.limpiarTablas(tbDetalleCompra);
            controlCompra.listarDetalleCompras(tbDetalleCompra);
            RendersD();
        } catch (Exception e) {
            Mensajes.error(e.toString());
        }*/
    }//GEN-LAST:event_tbPagosProveedorMouseClicked

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        int rpta = Mensajes.confirmar("¿Seguro que desea salir del formulario?");
        if (rpta == 0) {
            this.dispose();
        }

    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        // TODO add your handling code here:
        if (dlgConsultarPagosProveedor.tbPagosProveedor.getSelectedRow() < 0) {
            Mensajes.error("Seleccione una fila de la tabla");
        } else {
            int x = dlgConsultarPagosProveedor.tbPagosProveedor.getSelectedRow();
            String estado = dlgConsultarPagosProveedor.tbPagosProveedor.getValueAt(x, 7).toString();
            if (estado.equals("ANULADO")) {
                Mensajes.informacion("Este Pago ya fue anulado");
            } else {
                    try {
                        String msg;
                        int rpta = Mensajes.confirmar("Seguro que desea Anular este Pago?");
                        if (rpta == 0) {
                            msg = controlCompra.anularCompra();
                            if (msg == null) {
                                if (ckHabilitar.isSelected()) {
                                    if (txtCodProveedor.getText().trim().isEmpty()) {
                                        Mensajes.Sistema("No es posible cargar la tabla.\nSeleccione el proveedor para inicial el filtrado de las facturas.");
                                    } else {
                                        CabecerasTablas.limpiarTablas(tbPagosProveedor);
                                        CabecerasTablas.limpiarTablasconsDetalleCompras(tbDetallePagosProveedor);
                                        cabe.consCompras(tbPagosProveedor);
                                        CabecerasTablas.consDetalleCompras(tbDetallePagosProveedor);
                                        controlCompra.listarComprasFiltro(tbPagosProveedor, Integer.parseInt(txtCodProveedor.getText().trim()));
                                        //cargarComboBox.cargar(cbProveedores, "SELECT pro_codigo, pro_razonsocial FROM proveedor WHERE pro_indicador='S'");
                                        Renders();
                                    }

                                } else {
                                    CabecerasTablas.limpiarTablas(tbPagosProveedor);
                                    CabecerasTablas.limpiarTablasconsDetalleCompras(tbDetallePagosProveedor);
                                    cabe.consCompras(tbPagosProveedor);
                                    CabecerasTablas.consDetalleCompras(tbDetallePagosProveedor);
                                    controlCompra.listarCompras(tbPagosProveedor);
                                    //cargarComboBox.cargar(cbProveedores, "SELECT pro_codigo, pro_razonsocial FROM proveedor WHERE pro_indicador='S'");
                                    Renders();
                                }

                            }
                        }
                    } catch (Exception e) {
                        Mensajes.informacion("Seleccione la fila a eliminar:" + e.getMessage());
                    }
            }
        }
    }//GEN-LAST:event_btnAnularActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        CabecerasTablas.limpiarTablas(tbPagosProveedor);
        CabecerasTablas.limpiarTablas(tbDetallePagosProveedor);
        cabe.consPagosProveedor(tbPagosProveedor);
        cabe.consDetallePagosProveedor(tbDetallePagosProveedor);
        controlCompra.listarPagosCompras(tbPagosProveedor);
        cargarComboBox.cargar(cbProveedores, "SELECT pro_codigo, pro_razonsocial FROM proveedor WHERE pro_indicador='S'");
        Renders();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tbPagosProveedorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPagosProveedorMousePressed
        // TODO add your handling code here:
        try {
            CabecerasTablas.limpiarTablas(tbDetallePagosProveedor);
            controlCompra.listarDetallePagosCompras(tbDetallePagosProveedor);
            RendersD();
        } catch (Exception e) {
            Mensajes.error(e.toString());
        }
    }//GEN-LAST:event_tbPagosProveedorMousePressed

    private void itemBuscarAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarAActionPerformed
        // TODO add your handling code here:
        btnActualizarActionPerformed(null);
    }//GEN-LAST:event_itemBuscarAActionPerformed

    private void itemQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemQuitarActionPerformed
        // TODO add your handling code here:
        btnAnularActionPerformed(null);
    }//GEN-LAST:event_itemQuitarActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        // TODO add your handling code here:
        btnSalirActionPerformed(null);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void itemBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBuscarActionPerformed
        // TODO add your handling code here:
        try {
            String cod = (JOptionPane.showInputDialog("Ingrese N° de Factura Compra"));
            for (int i = 0; i < tbPagosProveedor.getRowCount(); i++) {
                if (tbPagosProveedor.getValueAt(i, 8).equals(cod)) {
                    tbPagosProveedor.changeSelection(i, 1, false, false);
                    tbPagosProveedorMousePressed(null);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Consulta cancelada" + e.getMessage());
        }
    }//GEN-LAST:event_itemBuscarActionPerformed

    private void cbProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProveedoresActionPerformed
        // TODO add your handling code here:
        try {
            String id = cargarComboBox.getCodidgo(cbProveedores);
            txtCodProveedor.setText(id);
        } catch (Exception e) {
            txtCodProveedor.setText("");
        }
    }//GEN-LAST:event_cbProveedoresActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // TODO add your handling code here:
        if (txtCodProveedor.getText().trim().isEmpty()) {
            Mensajes.Sistema("No es posible cargar la tabla.\nSeleccione el proveedor para inicial el filtrado de las facturas.");
        } else {
            CabecerasTablas.limpiarTablas(tbPagosProveedor);
            CabecerasTablas.limpiarTablas(tbDetallePagosProveedor);
            cabe.consPagosProveedor(tbPagosProveedor);
            cabe.consDetallePagosProveedor(tbDetallePagosProveedor);
            controlCompra.listarPaposComprasFiltro(tbPagosProveedor, Integer.parseInt(txtCodProveedor.getText().trim()));
            Renders();
        }

    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void ckHabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckHabilitarActionPerformed
        // TODO add your handling code here:
        if (ckHabilitar.isSelected()) {
            cbProveedores.setEnabled(true);
            btnFiltrar.setEnabled(true);
            btnActualizar.setEnabled(false);
        } else {
            btnActualizar.setEnabled(true);
            cbProveedores.setEnabled(false);
            btnFiltrar.setEnabled(false);
            btnActualizar.doClick();
        }
    }//GEN-LAST:event_ckHabilitarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarPagosProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarPagosProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarPagosProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dlgConsultarPagosProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dlgConsultarPagosProveedor dialog = new dlgConsultarPagosProveedor(new javax.swing.JFrame(), true);
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
    private org.edisoncor.gui.panel.PanelImage Blanco;
    private org.edisoncor.gui.panel.PanelImage Oscuro;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnSalir;
    private RSMaterialComponent.RSComboBoxMaterial cbProveedores;
    private javax.swing.JCheckBox ckHabilitar;
    private javax.swing.JMenuItem itemBuscar;
    private javax.swing.JMenuItem itemBuscarA;
    private javax.swing.JMenuItem itemQuitar;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    public static javax.swing.JTable tbDetallePagosProveedor;
    public static javax.swing.JTable tbPagosProveedor;
    public static javax.swing.JTextField txtCodProveedor;
    // End of variables declaration//GEN-END:variables
}
