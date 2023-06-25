package IU;

import Componentes.ConexionBD;
import Componentes.Mensajes;
import Componentes.Software;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import org.mariadb.jdbc.MariaDbConnection;
import org.mariadb.jdbc.MariaDbStatement;

public final class frmCargaInicial extends javax.swing.JFrame {

    //public static ResultSet rs;
    public static MariaDbStatement st;
    public static MariaDbConnection con;
    public static MariaDbStatement stMovil;
    public static MariaDbConnection conMovil;

    public frmCargaInicial() {
        
        my_style();
        initComponents();
        //this.setSize(100,100);
        cargarIcono();
        Iniciar();
        Titulo();

    }
    public static void prepararBD() {
        try {
            con = (MariaDbConnection) new ConexionBD().getConexion();
            if (con == null) {
                System.out.println("No hay Conexion con la Base de Datos");
            } else {
                st = (MariaDbStatement) con.createStatement();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void prepararBDMovil() {
        try {
            conMovil = (MariaDbConnection) new ConexionBD().getConexionMovil();
            if (conMovil == null) {
                System.out.println("No hay Conexion con la Base de Datos Movil");
            } else {
                stMovil = (MariaDbStatement) conMovil.createStatement();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void Titulo() {
        
        try {
            prepararBD();
            String sql = "SELECT * FROM software WHERE indicador='S'";
            ResultSet rs = st.executeQuery(sql);
            rs.first();
            Software.setSoftware(rs.getString("nombre"));
            Software.setDescripcion(rs.getString("descripcion"));
            Software.setVersion(rs.getString("version"));
            Software.setDesarrollador(rs.getString("desarrollador"));
            Software.setProfesion(rs.getString("profesion"));
            Software.setTelefoo(rs.getString("tel_desarrollador"));
            Software.setCorreo(rs.getString("correo"));
            rs.close();
            st.close();
            con.close();
            
        } catch (SQLException ee) {
            Software.setSoftware("null");
            Software.setDescripcion("null");
            Software.setVersion("null");
            Software.setDesarrollador("null");
            Software.setProfesion("null");
            Software.setTelefoo("null");
            Software.setCorreo("null");
        }
    }

    private void my_style() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.getMessage();
        }

    }

    private void Iniciar() {
        CargandoDatos CargandoDatos = new CargandoDatos();
        CargandoDatos.start();
        CargandoDatos = null;
        Cargando Cargando = new Cargando();
        Cargando.start();
        Cargando = null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCarga = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCarga.setFont(new java.awt.Font("Roboto", 0, 10)); // NOI18N
        lblCarga.setForeground(new java.awt.Color(255, 255, 255));
        lblCarga.setText("Carga");
        lblCarga.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblCarga.setFocusable(false);
        lblCarga.setInheritsPopupMenu(false);
        lblCarga.setPreferredSize(new java.awt.Dimension(25, 14));
        getContentPane().add(lblCarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 400, 500, -1));

        jProgressBar1.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setForeground(new java.awt.Color(255, 0, 0));
        jProgressBar1.setBorderPainted(false);
        jProgressBar1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jProgressBar1.setEnabled(false);
        jProgressBar1.setOpaque(true);
        jProgressBar1.setPreferredSize(new java.awt.Dimension(146, 12));
        jProgressBar1.setString("");
        jProgressBar1.setStringPainted(true);
        getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 415, 692, 4));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/inicial.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void velocidadDeCarga() throws InterruptedException {
        for (int i = 0; i <= 120; i++) {
            Thread.sleep(30L);
            jProgressBar1.setValue(i);
            if (i == 00) {
                lblCarga.setText(" Cargando y Activando componentes necesarios...");
            }
            if (i == 10) {
                lblCarga.setText(" Comenzando inicio del Sistema...");
            }
            if (i == 20) {
                lblCarga.setText(" Iniciando conexion con la Base de Datos E-Farm...");
            }
            if (i == 25) {
                lblCarga.setText(" Conexion exitosa.");
            }
            if (i == 30) {
                lblCarga.setText(" Cargando Reportes e Interfaz de usuario...");
            }
            if (i == 40) {
                lblCarga.setText(" Verificando Perfiles de Acceso...");
            }
            if (i == 50) {
                lblCarga.setText(" Verificando Usuarios...");
            }
            if (i == 60) {
                lblCarga.setText(" Verificación completada con exito.");
            }
            if (i == 65) {
                lblCarga.setText(" Cargando Listas de funciones...");
            }
            if (i == 70) {
                lblCarga.setText(" Cargando Módulos de operación...");
            }
            if (i == 75) {
                lblCarga.setText(" Carga de listas y modulos Terminada.");
            }
            if (i == 80) {
                lblCarga.setText(" El sistema se ha ejecutado sin ningún inconveniente.");
            }
            if (i == 85) {
                lblCarga.setText(" Bienvenido al Sistema P-VENTAS + FACT-EXPRESS");
            }
        }
    }

    class Cargando extends Thread {

        public Cargando() {
            super();
        }

        @Override
        public void run() {
            setProgresoMax(100);
            try {
                velocidadDeCarga();
            } catch (InterruptedException ex) {
                Logger.getLogger(frmCargaInicial.class.getName()).log(Level.SEVERE, null, ex);
            }
            new frmAcceso().setVisible(true);
            dispose();
        }
    }

    class CargandoDatos extends Thread {

        public CargandoDatos() {
            super();
        }

        @Override
        public void run() {
            try {
                velocidadDeCarga();
            } catch (InterruptedException ex) {
                Logger.getLogger(frmCargaInicial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setProgresoMax(int maxProgress) {
        jProgressBar1.setMaximum(maxProgress);
    }

    public void setProgreso(int progress) {
        final int progreso = progress;
        jProgressBar1.setValue(progreso);
    }

    void cargarIcono() {
        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Iconos/logo1.png")));
            this.setLocationRelativeTo(null);
        } catch (Exception e) {
            Mensajes.error("No se pudo cargo icono");
        }
    }

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCargaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new frmCargaInicial().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel lblCarga;
    // End of variables declaration//GEN-END:variables
}
