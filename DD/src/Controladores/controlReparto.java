package Controladores;

import Componentes.Fecha;
import Componentes.Login;
import Componentes.Mensajes;
import Componentes.Redondeo;
import Datos.ArregloReparto;
import Datos.GestionarArticulosMovil;
import Datos.GestionarCompra;
import Datos.GestionarReparto;
import IU.dlgBuscarArticuloReparto;
import IU.dlgBuscarArticuloTransferencia;
import IU.dlgConsultarCompras1;
import IU.dlgConsultarRepartoAnterior;
import IU.dlgGestRepartos;
import IU.dlgGestTransferencias;
import IU.dlgRepartos;
import IU.dlgReporteComisiones;
import Modelo.ArticuloMovil;
import Modelo.DetalleReparto;
import Modelo.reparto;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controlReparto {

    static ArticuloMovil art;
    static DetalleReparto dc;
    public static ArregloReparto array = new ArregloReparto();
    public static int precio_venta;
    public static int precio_costo;
    public static int ganancia;
    static String UsuarioL = "";

    public static void selectArticulo() {
        int x = dlgBuscarArticuloReparto.tbDetalle.getSelectedRow();
        String cod = dlgBuscarArticuloReparto.tbDetalle.getValueAt(x, 0).toString();
        art = GestionarArticulosMovil.busArticulo(cod);
        dlgGestRepartos.txtCodArticulo.setText(String.valueOf(art.getIdproducto()));
        dlgGestRepartos.txtProducto.setText(art.getDescripcion());
        String PC = String.valueOf(art.getPrecio_venta());
    }
    
    public static void selectArticuloTransf() {
        int x = dlgBuscarArticuloTransferencia.tbDetalle.getSelectedRow();
        String cod = dlgBuscarArticuloTransferencia.tbDetalle.getValueAt(x, 0).toString();
        art = GestionarArticulosMovil.busArticulo(cod);
        dlgGestTransferencias.txtCodArticulo.setText(String.valueOf(art.getIdproducto()));
        dlgGestTransferencias.txtArt.setText(art.getDescripcion());
        dlgGestTransferencias.txtCant.setText("1");
    }

    public static void aModifcar() {
        try {
            //int cod = Integer.parseInt(dlgGestRepartos.txtIdReparto.getText().trim());
            int x = dlgRepartos.tbDetalle.getSelectedRow();
            int cod = Integer.parseInt(dlgRepartos.tbDetalle.getValueAt(x, 0).toString());
            
            reparto re = GestionarReparto.busReparto(cod);
            if (re.getCerrado().equals("N")){
                    dlgGestRepartos.txtEstado.setText("REPARTO ABIERTO");
                    dlgGestRepartos.cbCerrar.setSelected(false);
                    dlgGestRepartos.cbCerrar.setEnabled(true);

                    dlgGestRepartos.chAgregar.setEnabled(true);
                    dlgGestRepartos.txtContenedorI.setEnabled(true);
                    dlgGestRepartos.txtContenedorF.setEnabled(true);
                    dlgGestRepartos.txtContenedorV.setEnabled(true);
                    dlgGestRepartos.txtPresentado.setEnabled(true);
                    dlgGestRepartos.txtKmAnterior.setEnabled(true);
                    dlgGestRepartos.txtKmActual.setEnabled(true);
                    //
                    dlgGestRepartos.btnCompraA.setEnabled(true);
                    dlgGestRepartos.btnCargarTransferencias.setEnabled(true);
                    dlgGestRepartos.btnNuevo.setEnabled(false);
                    dlgGestRepartos.itemNuevo.setEnabled(false);
                    dlgGestRepartos.btnGuardar.setEnabled(false);
                    dlgGestRepartos.itemGuardar.setEnabled(false);
                    dlgGestRepartos.btnModificar.setEnabled(true);
                    dlgGestRepartos.itemModificar.setEnabled(true);
                    dlgGestRepartos.btnCancelar.setEnabled(true);
                    dlgGestRepartos.itemCancelar.setEnabled(true);
                    dlgGestRepartos.btnSalir.setEnabled(false);
                    dlgGestRepartos.itemSalir.setEnabled(false);
                    dlgGestRepartos.itemHojaR.setEnabled(true);
                    dlgGestRepartos.itemHojaR1.setEnabled(true);
                    dlgGestRepartos.itemHojaApunte.setEnabled(true);
                    dlgGestRepartos.itemHojaApunte1.setEnabled(true);
                    dlgGestRepartos.itemBuscarArticulo.setEnabled(false);
                    dlgGestRepartos.itemQuitar.setEnabled(false);
                    dlgGestRepartos.tbDetalleReparto.setEnabled(false);
                    dlgGestRepartos.btnRC.setEnabled(true);
                    dlgGestRepartos.btnRR.setEnabled(true);
                    dlgGestRepartos.txtIdReparto.setEnabled(false);
                    dlgGestRepartos.txtIdReparto.setText(String.valueOf(re.getId_reparto()));
                    dlgGestRepartos.txtCaja.setText(String.valueOf(re.getId_caja()));
                    dlgGestRepartos.txtIdResponsable.setText(String.valueOf(re.getId_responsable()));
                    dlgGestRepartos.modificarcboResponsable();
                    dlgGestRepartos.txtIdMovil.setText(String.valueOf(re.getIdMovil()));
                    dlgGestRepartos.ObtenerGastosA();
                    dlgGestRepartos.lbInfoMovil.setText(" Referencia: " + String.valueOf(re.getPreferencia()));
                    dlgGestRepartos.lbComR.setText(String.valueOf(re.getComision_resp()));
                    dlgGestRepartos.lbComR1.setText(String.valueOf(re.getComision_resp())+"%");
                    dlgGestRepartos.lbInfoChofer.setText(" Chofer: " + String.valueOf(re.getChofer()));
                    dlgGestRepartos.txtIdChofer.setText(String.valueOf(re.getIdchofer()));
                    dlgGestRepartos.lbComC.setText(String.valueOf(re.getComision_chof()));
                    dlgGestRepartos.lbComC1.setText(String.valueOf(re.getComision_chof())+"%");                    
                    dlgGestRepartos.txtFechaIF.setText(String.valueOf(re.getFecha()));
                    dlgGestRepartos.txtFechaI.setText(Fecha.formatoFechaF(re.getFecha()));
                    dlgGestRepartos.txtHoraI.setText(Fecha.FormatoHoraSinSS(re.getHora()));
                    
                    dlgGestRepartos.txtCAnterior.setText(String.valueOf(re.getRecolectorA()));
                    dlgGestRepartos.txtCdif.setText(String.valueOf(re.getRecolectorDA()));
                    dlgGestRepartos.txtCdifACA.setText(String.valueOf(re.getRecolectorDAC()));
                    dlgGestRepartos.txtContenedorI.setText(String.valueOf(re.getRecolectorI()));
                    dlgGestRepartos.txtContenedorT.setText(String.valueOf(re.getRecolectorT()));
                    dlgGestRepartos.txtContenedorF.setText(String.valueOf(re.getRecolectorF()));
                    dlgGestRepartos.txtContenedorV.setText(String.valueOf(re.getRecolectorV()));
                    dlgGestRepartos.txtContenedorD.setText(String.valueOf(re.getRecolectorDF()));
                    dlgGestRepartos.txtContenedorDA.setText(String.valueOf(re.getRecolectorDFAC()));
                    dlgGestRepartos.calcularCajas();
                    dlgGestRepartos.txtValorRA.setText(String.valueOf(re.getValorRA()));
                    dlgGestRepartos.txtValorCompra.setText(String.valueOf(re.getValorC()));
                    dlgGestRepartos.txtValorTransferencia.setText(String.valueOf(re.getValorT()));
                    
                    dlgGestRepartos.txtKmAnterior.setText(String.valueOf(re.getkm_anterior()));
                    dlgGestRepartos.txtKmActual.setText(String.valueOf(re.getkm_actual()));
                    dlgGestRepartos.txtKmRecorrido.setText(String.valueOf(re.getkm_recorrido()));
                    dlgGestRepartos.calcularKilometraje();
                    DecimalFormat df = new DecimalFormat("#,###");
                    dlgGestRepartos.txtTotalL.setText(String.valueOf(re.getValorTotal()));
                    dlgGestRepartos.txtTotal.setText(df.format(Integer.valueOf(dlgGestRepartos.txtTotalL.getText().trim().replace(".", "").replace(",", ""))));
                    dlgGestRepartos.txtRecambioL.setText(String.valueOf(re.getValorRecambio()));
                    dlgGestRepartos.txtRecambio.setText(df.format(Integer.valueOf(dlgGestRepartos.txtRecambioL.getText().trim().replace(".", "").replace(",", ""))));
                    dlgGestRepartos.txtDevueltosL.setText(String.valueOf(re.getValorDevuelto()));
                    dlgGestRepartos.txtDevueltos.setText(df.format(Integer.valueOf(dlgGestRepartos.txtDevueltosL.getText().trim().replace(".", "").replace(",", ""))));
                    dlgGestRepartos.txtTotalVentasL.setText(String.valueOf(re.getEfectivoEntregar()));
                    dlgGestRepartos.txtTotalVentas.setText(df.format(Integer.valueOf(dlgGestRepartos.txtTotalVentasL.getText().trim().replace(".", "").replace(",", ""))));
                    dlgGestRepartos.txtPresentadoL.setText(String.valueOf(re.getEfectivoEntregado()));
                    dlgGestRepartos.txtPresentado.setText(df.format(Integer.valueOf(dlgGestRepartos.txtPresentadoL.getText().trim().replace(".", "").replace(",", ""))));
                    dlgGestRepartos.txtDiferenciaL.setText(String.valueOf(re.getDiferencia()));
                    dlgGestRepartos.txtDiferencia.setText(df.format(Integer.valueOf(dlgGestRepartos.txtDiferenciaL.getText().trim().replace(".", "").replace(",", ""))));
                } else {
                    dlgGestRepartos.txtEstado.setText("REPARTO CERRADO");
                    dlgGestRepartos.cbCerrar.setSelected(true);
                    dlgGestRepartos.cbCerrar.setEnabled(false);
                    
                    dlgGestRepartos.btnNuevo.setEnabled(false);
                    dlgGestRepartos.itemNuevo.setEnabled(false);
                    dlgGestRepartos.btnGuardar.setEnabled(false);
                    dlgGestRepartos.itemGuardar.setEnabled(false);
                    dlgGestRepartos.btnModificar.setEnabled(false);
                    dlgGestRepartos.itemModificar.setEnabled(false);
                    dlgGestRepartos.btnCancelar.setEnabled(false);
                    dlgGestRepartos.itemCancelar.setEnabled(false);
                    dlgGestRepartos.btnSalir.setEnabled(true);
                    dlgGestRepartos.itemSalir.setEnabled(true);
                    
                    dlgGestRepartos.itemHojaR.setEnabled(true);
                    dlgGestRepartos.itemHojaR1.setEnabled(true);
                    dlgGestRepartos.itemHojaApunte.setEnabled(false);
                    dlgGestRepartos.itemHojaApunte1.setEnabled(false);
                    dlgGestRepartos.btnRC.setEnabled(true);
                    dlgGestRepartos.btnRR.setEnabled(true);
                    dlgGestRepartos.txtIdReparto.setText(String.valueOf(re.getId_reparto()));
                    dlgGestRepartos.txtCaja.setText(String.valueOf(re.getId_caja()));
                    dlgGestRepartos.txtIdResponsable.setText(String.valueOf(re.getId_responsable()));
                    dlgGestRepartos.modificarcboResponsable();
                    dlgGestRepartos.txtIdMovil.setText(String.valueOf(re.getIdMovil()));
                    dlgGestRepartos.ObtenerGastosA();
                    dlgGestRepartos.lbInfoMovil.setText(" Referencia: " + String.valueOf(re.getPreferencia()));
                    dlgGestRepartos.lbComR.setText(String.valueOf(re.getComision_resp()));
                    dlgGestRepartos.lbComR1.setText(String.valueOf(re.getComision_resp())+"%");
                    dlgGestRepartos.lbInfoChofer.setText(" Chofer: " + String.valueOf(re.getChofer()));
                    dlgGestRepartos.txtIdChofer.setText(String.valueOf(re.getIdchofer()));
                    dlgGestRepartos.lbComC.setText(String.valueOf(re.getComision_chof()));
                    dlgGestRepartos.lbComC1.setText(String.valueOf(re.getComision_chof())+"%");
                    dlgGestRepartos.txtFechaIF.setText(String.valueOf(re.getFecha()));
                    dlgGestRepartos.txtFechaI.setText(Fecha.formatoFechaF(re.getFecha()));
                    dlgGestRepartos.txtHoraI.setText(Fecha.FormatoHoraSinSS(re.getHora()));
                    
                    dlgGestRepartos.txtCAnterior.setText(String.valueOf(re.getRecolectorA()));
                    dlgGestRepartos.txtCdif.setText(String.valueOf(re.getRecolectorDA()));
                    dlgGestRepartos.txtCdifACA.setText(String.valueOf(re.getRecolectorDAC()));
                    dlgGestRepartos.txtContenedorI.setText(String.valueOf(re.getRecolectorI()));
                    dlgGestRepartos.txtContenedorT.setText(String.valueOf(re.getRecolectorT()));
                    dlgGestRepartos.txtContenedorF.setText(String.valueOf(re.getRecolectorF()));
                    dlgGestRepartos.txtContenedorV.setText(String.valueOf(re.getRecolectorV()));
                    dlgGestRepartos.txtContenedorD.setText(String.valueOf(re.getRecolectorDF()));
                    dlgGestRepartos.txtContenedorDA.setText(String.valueOf(re.getRecolectorDFAC()));
                    dlgGestRepartos.calcularCajas();
                    dlgGestRepartos.txtKmAnterior.setText(String.valueOf(re.getkm_anterior()));
                    dlgGestRepartos.txtKmActual.setText(String.valueOf(re.getkm_actual()));
                    dlgGestRepartos.txtKmRecorrido.setText(String.valueOf(re.getkm_recorrido()));
                    dlgGestRepartos.calcularKilometraje();
                    DecimalFormat df = new DecimalFormat("#,###");
                    dlgGestRepartos.txtTotalL.setText(String.valueOf(re.getValorTotal()));
                    dlgGestRepartos.txtTotal.setText(df.format(Integer.valueOf(dlgGestRepartos.txtTotalL.getText().trim().replace(".", "").replace(",", ""))));
                    dlgGestRepartos.txtRecambioL.setText(String.valueOf(re.getValorRecambio()));
                    dlgGestRepartos.txtRecambio.setText(df.format(Integer.valueOf(dlgGestRepartos.txtRecambioL.getText().trim().replace(".", "").replace(",", ""))));
                    dlgGestRepartos.txtDevueltosL.setText(String.valueOf(re.getValorDevuelto()));
                    dlgGestRepartos.txtDevueltos.setText(df.format(Integer.valueOf(dlgGestRepartos.txtDevueltosL.getText().trim().replace(".", "").replace(",", ""))));
                    dlgGestRepartos.txtTotalVentasL.setText(String.valueOf(re.getEfectivoEntregar()));
                    dlgGestRepartos.txtTotalVentas.setText(df.format(Integer.valueOf(dlgGestRepartos.txtTotalVentasL.getText().trim().replace(".", "").replace(",", ""))));
                    dlgGestRepartos.txtPresentadoL.setText(String.valueOf(re.getEfectivoEntregado()));
                    dlgGestRepartos.txtPresentado.setText(df.format(Integer.valueOf(dlgGestRepartos.txtPresentadoL.getText().trim().replace(".", "").replace(",", ""))));
                    dlgGestRepartos.txtDiferenciaL.setText(String.valueOf(re.getDiferencia()));
                    dlgGestRepartos.txtDiferencia.setText(df.format(Integer.valueOf(dlgGestRepartos.txtDiferenciaL.getText().trim().replace(".", "").replace(",", ""))));
                }
        } catch (NumberFormatException ee) {
            System.out.println("Error aModificar: " + ee.getMessage());
        }
    }

    public static int getRecambio() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgGestRepartos.tbDetalleReparto.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgGestRepartos.tbDetalleReparto.getModel().getValueAt(i, 10)));
        }
        return (total);
    }

    public static int getDevueltos() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgGestRepartos.tbDetalleReparto.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgGestRepartos.tbDetalleReparto.getModel().getValueAt(i, 15)));
        }
        return (total);
    }

    public static int getTotalVentas() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgGestRepartos.tbDetalleReparto.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgGestRepartos.tbDetalleReparto.getModel().getValueAt(i, 12)));
        }
        return (total);
    }

    public static int getTotal() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgGestRepartos.tbDetalleReparto.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgGestRepartos.tbDetalleReparto.getModel().getValueAt(i, 8)));
        }
        return (total);
    }
    
    public static int getTotalComision() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgReporteComisiones.tbDetalleComisiones.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgReporteComisiones.tbDetalleComisiones.getModel().getValueAt(i, 3)).replace(".", "").replace(",", ""));
        }
        return (total);
    }
    
    public static int getCT() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgRepartos.tbDetalle.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgRepartos.tbDetalle.getModel().getValueAt(i, 3)).replace(".", "").replace(",", ""));
        }
        return (total);
    }
    public static int getR() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgRepartos.tbDetalle.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgRepartos.tbDetalle.getModel().getValueAt(i, 4)).replace(".", "").replace(",", ""));
        }
        return (total);
    }
    public static int getD() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgRepartos.tbDetalle.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgRepartos.tbDetalle.getModel().getValueAt(i, 5)).replace(".", "").replace(",", ""));
        }
        return (total);
    }
    
   public static int getEER() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgRepartos.tbDetalle.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgRepartos.tbDetalle.getModel().getValueAt(i, 6)).replace(".", "").replace(",", ""));
        }
        return (total);
    }
   public static int getEED() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgRepartos.tbDetalle.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgRepartos.tbDetalle.getModel().getValueAt(i, 7)).replace(".", "").replace(",", ""));
        }
        return (total);
    }
   public static int getDIF() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgRepartos.tbDetalle.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            if(Integer.parseInt(String.valueOf(dlgRepartos.tbDetalle.getModel().getValueAt(i, 8)).replace(".", "").replace(",", "")) < 0){
                total += Integer.valueOf(String.valueOf(dlgRepartos.tbDetalle.getModel().getValueAt(i, 8)).replace(".", "").replace(",", ""));
            }
               // total += Integer.valueOf(String.valueOf(dlgRepartos.tbDetalle.getModel().getValueAt(i, 8)).replace(".", "").replace(",", ""));
            }
        return (total);
    }

    public static void insertar(String cod, String codI, String desc, String precio, double cargaT, int montoCargaT,
            double recambio, int montoRecambio, double vendido, int montoVendido, int costoVendido, double devuelto, int montoDevuelto, JTable tabla) {

        String fila[] = new String[17];
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        fila[0] = cod;
        fila[1] = codI;
        fila[2] = desc;
        fila[3] = precio;
        fila[4] = "0";
        fila[5] = "0";
        fila[6] = "0";
        fila[7] = String.valueOf(cargaT);
        fila[8] = String.valueOf(montoCargaT);
        fila[9] = String.valueOf(recambio);
        fila[10] = String.valueOf(montoRecambio);
        fila[11] = String.valueOf(vendido);
        fila[12] = String.valueOf(montoVendido);
        fila[13] = String.valueOf(costoVendido);
        fila[14] = String.valueOf(devuelto);
        fila[15] = String.valueOf(montoDevuelto);
        fila[16] = "N";
        tb.addRow(fila);
    }
    
    public static void insertarM(String cod, String codI, String desc, String precio, double RA, double C, double T, double cargaT, int montoCargaT,
            double recambio, int montoRecambio, double vendido, int montoVendido, int costoVendido, double devuelto, int montoDevuelto, JTable tabla) {

        String fila[] = new String[17];
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        fila[0] = cod;
        fila[1] = codI;
        fila[2] = desc;
        fila[3] = precio;
        fila[4] = String.valueOf(RA);
        fila[5] = String.valueOf(C);
        fila[6] = String.valueOf(T);
        fila[7] = String.valueOf(cargaT);
        fila[8] = String.valueOf(montoCargaT);
        fila[9] = String.valueOf(recambio);
        fila[10] = String.valueOf(montoRecambio);
        fila[11] = String.valueOf(vendido);
        fila[12] = String.valueOf(montoVendido);
        fila[13] = String.valueOf(costoVendido);
        fila[14] = String.valueOf(devuelto);
        fila[15] = String.valueOf(montoDevuelto);
        fila[16] = "V";
        tb.addRow(fila);
    }
    public static void insertarT(String cod, String codI, String desc, String precio, double cargaT, int montoCargaT,
            double recambio, int montoRecambio, double vendido, int montoVendido, int costoVendido, double devuelto, int montoDevuelto, JTable tabla) {

        String fila[] = new String[17];
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        fila[0] = cod;
        fila[1] = codI;
        fila[2] = desc;
        fila[3] = precio;
        fila[4] = "0";
        fila[5] = "0";
        fila[6] = String.valueOf(cargaT);
        fila[7] = String.valueOf(cargaT);
        fila[8] = String.valueOf(montoCargaT);
        fila[9] = String.valueOf(recambio);
        fila[10] = String.valueOf(montoRecambio);
        fila[11] = String.valueOf(vendido);
        fila[12] = String.valueOf(montoVendido);
        fila[13] = String.valueOf(costoVendido);
        fila[14] = String.valueOf(devuelto);
        fila[15] = String.valueOf(montoDevuelto);
        fila[16] = "N";
        tb.addRow(fila);
    }
    public static void insertarRA(String cod, String codI, String desc, String precio, double cargaT, int montoCargaT,
            double recambio, int montoRecambio, double vendido, int montoVendido, int costoVendido, double devuelto, int montoDevuelto, JTable tabla) {

        String fila[] = new String[16];
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        fila[0] = cod;
        fila[1] = codI;
        fila[2] = desc;
        fila[3] = precio;
        fila[4] = String.valueOf(cargaT);
        fila[5] = "0";
        fila[6] = "0";
        fila[7] = String.valueOf(cargaT);
        fila[8] = String.valueOf(montoCargaT);
        fila[9] = String.valueOf(recambio);
        fila[10] = String.valueOf(montoRecambio);
        fila[11] = String.valueOf(vendido);
        fila[12] = String.valueOf(montoVendido);
        fila[13] = String.valueOf(costoVendido);
        fila[14] = String.valueOf(devuelto);
        fila[15] = String.valueOf(montoDevuelto);
        tb.addRow(fila);
    }
    public static void insertarC(String cod, String codI, String desc, String precio, double cargaT, int montoCargaT,
            double recambio, int montoRecambio, double vendido, int montoVendido, int costoVendido, double devuelto, int montoDevuelto, JTable tabla) {

        String fila[] = new String[17];
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        fila[0] = cod;
        fila[1] = codI;
        fila[2] = desc;
        fila[3] = precio;
        fila[4] = "0";
        fila[5] = String.valueOf(cargaT);
        fila[6] = "0";
        fila[7] = String.valueOf(cargaT);
        fila[8] = String.valueOf(montoCargaT);
        fila[9] = String.valueOf(recambio);
        fila[10] = String.valueOf(montoRecambio);
        fila[11] = String.valueOf(vendido);
        fila[12] = String.valueOf(montoVendido);
        fila[13] = String.valueOf(costoVendido);
        fila[14] = String.valueOf(devuelto);
        fila[15] = String.valueOf(montoDevuelto);
        fila[16] = "N";
        tb.addRow(fila);
    }

    public static void addTabla(JTable tabla) {
        try {
            int codA = art.getIdproducto();
            String codI = art.getCodinterno();
            String desc = art.getDescripcion();
            double cant = Double.parseDouble(dlgGestRepartos.txtCantidad.getText());
            double valorI = 0;
            precio_venta = art.getPrecio_venta();
            precio_costo = art.getPrecio_costo();
            //int monto_carga_total = (int) (cant * precio_venta);
            DetalleReparto dco = new DetalleReparto(codA);
            if (array.busca(dco.getIdproducto()) != -1) {
                int Nfila = array.busca(dco.getIdproducto());
                double cantTabla = Double.parseDouble(dlgGestRepartos.tbDetalleReparto.getValueAt(Nfila, 7).toString());
                double cantR = Double.parseDouble(dlgGestRepartos.tbDetalleReparto.getValueAt(Nfila, 9).toString());
                double cantD = Double.parseDouble(dlgGestRepartos.tbDetalleReparto.getValueAt(Nfila, 14).toString());
                addmismoItemReparto(Nfila, cantTabla, cant, precio_venta, precio_costo, cantR, cantD);
            } else {
                array.agregar(dco);
                insertar(String.valueOf(codA), codI, desc, String.valueOf(precio_venta), cant, (int) (precio_venta * cant), (valorI),
                        (int) (valorI * precio_venta), cant, (int) (precio_venta * cant), (int) (precio_costo * cant),
                        (valorI), (int) (valorI * precio_venta), tabla);
            }
            DecimalFormat df = new DecimalFormat("#,###");
            String total = String.valueOf(getTotal());
            dlgGestRepartos.txtTotalL.setText(total);
            dlgGestRepartos.txtTotal.setText(df.format(Integer.parseInt(total.replace(".", "").replace(",", ""))));
            String recambio = String.valueOf(getRecambio());
            dlgGestRepartos.txtRecambioL.setText(recambio);
            dlgGestRepartos.txtRecambio.setText(df.format(Integer.parseInt(recambio.trim().replace(".", "").replace(",", ""))));
            String devueltos = String.valueOf(getDevueltos());
            dlgGestRepartos.txtDevueltosL.setText(devueltos);
            dlgGestRepartos.txtDevueltos.setText(df.format(Integer.parseInt(devueltos.replace(".", "").replace(",", ""))));
            String Ventas = String.valueOf(getTotalVentas());
            dlgGestRepartos.txtTotalVentasL.setText(Ventas);
            dlgGestRepartos.txtTotalVentas.setText(df.format(Integer.parseInt(Ventas.replace(".", "").replace(",", ""))));
        } catch (Exception e) {
            Mensajes.error("ERROR AL CARGAR DETALLE: " + e.getMessage().toLowerCase());
        }
    }

    public static void addTablaRecabios(JTable tabla) {
        try {
            int codA = art.getIdproducto();
            String codI = art.getCodinterno();
            String desc = art.getDescripcion();
            double cant = Double.parseDouble(dlgGestRepartos.txtCantidad.getText());
            precio_venta = art.getPrecio_venta();
            precio_costo = art.getPrecio_costo();
            DetalleReparto dco = new DetalleReparto(codA);
            if (array.busca(dco.getIdproducto()) != -1) {
                int Nfila = array.busca(dco.getIdproducto());
                double cantCarga = Double.parseDouble(dlgGestRepartos.tbDetalleReparto.getValueAt(Nfila, 7).toString());
                double cantDevuelto = Double.parseDouble(dlgGestRepartos.tbDetalleReparto.getValueAt(Nfila, 14).toString());
                addRecambiosReparto(Nfila, cant, precio_venta, precio_costo, cantDevuelto, cantCarga);
            } else {
                Mensajes.error("OBSERVACIÓN:\nEl PRODUCTO que intenta ingresar como RECAMBIO:<Vencidos/Dañados> no cuenta con registro en este Reparto."
                        + "\nPor favor, verifique que los datos a insertar sean correctos.");
            }
            
            DecimalFormat df = new DecimalFormat("#,###");
            String total = String.valueOf(getTotal());
            dlgGestRepartos.txtTotalL.setText(total);
            dlgGestRepartos.txtTotal.setText(df.format(Integer.parseInt(total.replace(".", "").replace(",", ""))));
            String recambio = String.valueOf(getRecambio());
            dlgGestRepartos.txtRecambioL.setText(recambio);
            dlgGestRepartos.txtRecambio.setText(df.format(Integer.parseInt(recambio.trim().replace(".", "").replace(",", ""))));
            String devueltos = String.valueOf(getDevueltos());
            dlgGestRepartos.txtDevueltosL.setText(devueltos);
            dlgGestRepartos.txtDevueltos.setText(df.format(Integer.parseInt(devueltos.replace(".", "").replace(",", ""))));
            String Ventas = String.valueOf(getTotalVentas());
            dlgGestRepartos.txtTotalVentasL.setText(Ventas);
            dlgGestRepartos.txtTotalVentas.setText(df.format(Integer.parseInt(Ventas.replace(".", "").replace(",", ""))));
        } catch (Exception e) {
            Mensajes.error("ERROR AL CARGAR DETALLE: " + e.getMessage().toLowerCase());
        }
    }

    public static void addTablaDevueltos(JTable tabla) {
        try {
            int codA = art.getIdproducto();
            double cant = Double.parseDouble(dlgGestRepartos.txtCantidad.getText());
            precio_venta = art.getPrecio_venta();
            precio_costo = art.getPrecio_costo();
            DetalleReparto dco = new DetalleReparto(codA);
            if (array.busca(dco.getIdproducto()) != -1) {
                int Nfila = array.busca(dco.getIdproducto());
                double cantCarga = Double.parseDouble(dlgGestRepartos.tbDetalleReparto.getValueAt(Nfila, 7).toString());
                double cantRecambios = Double.parseDouble(dlgGestRepartos.tbDetalleReparto.getValueAt(Nfila, 9).toString());
                addDevueltosReparto(Nfila, cant, precio_venta, precio_costo, cantRecambios, cantCarga);
            } else {
                Mensajes.error("OBSERVACIÓN:\nEl PRODUCTO que intenta ingresar como DEVUELTO:<Stock sobrante> no cuenta con registro en este Reparto."
                        + "\nPor favor, verifique que los datos a insertar sean correctos.");
                
            }
                
            DecimalFormat df = new DecimalFormat("#,###");
            String total = String.valueOf(getTotal());
            dlgGestRepartos.txtTotalL.setText(total);
            dlgGestRepartos.txtTotal.setText(df.format(Integer.parseInt(total.replace(".", "").replace(",", ""))));
            String recambio = String.valueOf(getRecambio());
            dlgGestRepartos.txtRecambioL.setText(recambio);
            dlgGestRepartos.txtRecambio.setText(df.format(Integer.parseInt(recambio.trim().replace(".", "").replace(",", ""))));
            String devueltos = String.valueOf(getDevueltos());
            dlgGestRepartos.txtDevueltosL.setText(devueltos);
            dlgGestRepartos.txtDevueltos.setText(df.format(Integer.parseInt(devueltos.replace(".", "").replace(",", ""))));
            String Ventas = String.valueOf(getTotalVentas());
            dlgGestRepartos.txtTotalVentasL.setText(Ventas);
            dlgGestRepartos.txtTotalVentas.setText(df.format(Integer.parseInt(Ventas.replace(".", "").replace(",", ""))));
        } catch (Exception e) {
            Mensajes.error("ERROR AL CARGAR DETALLE: " + e.getMessage().toLowerCase());
        }
    }

    public static void addTabladesdeCompra(int codA, String codI, String desc, double cant, JTable tabla) {
        try {
            art = GestionarArticulosMovil.busArticulo(String.valueOf(codA));
            double valorI = 0;
            precio_venta = art.getPrecio_venta();
            precio_costo = art.getPrecio_costo();
            DetalleReparto dco = new DetalleReparto(codA);
            if (array.busca(dco.getIdproducto()) != -1) {
                int Nfila = array.busca(dco.getIdproducto());
                double cantTabla = Double.parseDouble(dlgGestRepartos.tbDetalleReparto.getValueAt(Nfila, 7).toString());
                double cantC = Double.parseDouble(dlgGestRepartos.tbDetalleReparto.getValueAt(Nfila, 5).toString());
                double cantR = Double.parseDouble(dlgGestRepartos.tbDetalleReparto.getValueAt(Nfila, 9).toString());
                double cantD = Double.parseDouble(dlgGestRepartos.tbDetalleReparto.getValueAt(Nfila, 14).toString());
                addmismoItemRepartoC(Nfila, cantTabla, cant, precio_venta, precio_costo,cantC, cantR, cantD);
            } else {
                array.agregar(dco);
                insertarC(String.valueOf(codA), codI, desc, String.valueOf(precio_venta), cant, (int) Redondeo.redondearD(precio_venta * cant), (valorI),
                        (int) Redondeo.redondearD(valorI * precio_venta), cant, (int) Redondeo.redondearD(precio_venta * cant), (int) Redondeo.redondearD(precio_costo * cant),
                        (valorI), (int) Redondeo.redondearD(valorI * precio_venta), tabla);
            }
            DecimalFormat df = new DecimalFormat("#,###");
            String total = String.valueOf(getTotal());
            dlgGestRepartos.txtTotalL.setText(total);
            dlgGestRepartos.txtTotal.setText(df.format(Integer.parseInt(total.replace(".", "").replace(",", ""))));
            String recambio = String.valueOf(getRecambio());
            dlgGestRepartos.txtRecambioL.setText(recambio);
            dlgGestRepartos.txtRecambio.setText(df.format(Integer.parseInt(recambio.trim().replace(".", "").replace(",", ""))));
            String devueltos = String.valueOf(getDevueltos());
            dlgGestRepartos.txtDevueltosL.setText(devueltos);
            dlgGestRepartos.txtDevueltos.setText(df.format(Integer.parseInt(devueltos.replace(".", "").replace(",", ""))));
            String Ventas = String.valueOf(getTotalVentas());
            dlgGestRepartos.txtTotalVentasL.setText(Ventas);
            dlgGestRepartos.txtTotalVentas.setText(df.format(Integer.parseInt(Ventas.replace(".", "").replace(",", ""))));
        } catch (Exception e) {
            Mensajes.error("ERROR AL CARGAR DETALLE: " + e.getMessage().toLowerCase());
        }
    }
    
    public static void addTabladesdeRepartoA(int codA, String codI, String desc, double cant, JTable tabla) {
        try {
            art = GestionarArticulosMovil.busArticulo(String.valueOf(codA));
            double valorI = 0;
            precio_venta = art.getPrecio_venta();
            precio_costo = art.getPrecio_costo();
            DetalleReparto dco = new DetalleReparto(codA);
            if (array.busca(dco.getIdproducto()) != -1) {
                int Nfila = array.busca(dco.getIdproducto());
                double cantTabla = Double.parseDouble(dlgGestRepartos.tbDetalleReparto.getValueAt(Nfila, 7).toString());
                double cantRA = Double.parseDouble(dlgGestRepartos.tbDetalleReparto.getValueAt(Nfila, 4).toString());
                addmismoItemRepartoRA(Nfila, cantTabla, cant, precio_venta, precio_costo, cantRA);
            } else {
                array.agregar(dco);
                insertarRA(String.valueOf(codA), codI, desc, String.valueOf(precio_venta), cant, (int) Redondeo.redondearD(precio_venta * cant), (valorI),
                        (int) Redondeo.redondearD(valorI * precio_venta), cant, (int) Redondeo.redondearD(precio_venta * cant), (int) Redondeo.redondearD(precio_costo * cant),
                        (valorI), (int) Redondeo.redondearD(valorI * precio_venta), tabla);
            }
            DecimalFormat df = new DecimalFormat("#,###");
            String total = String.valueOf(getTotal());
            dlgGestRepartos.txtTotalL.setText(total);
            dlgGestRepartos.txtTotal.setText(df.format(Integer.parseInt(total.replace(".", "").replace(",", ""))));
            String recambio = String.valueOf(getRecambio());
            dlgGestRepartos.txtRecambioL.setText(recambio);
            dlgGestRepartos.txtRecambio.setText(df.format(Integer.parseInt(recambio.trim().replace(".", "").replace(",", ""))));
            String devueltos = String.valueOf(getDevueltos());
            dlgGestRepartos.txtDevueltosL.setText(devueltos);
            dlgGestRepartos.txtDevueltos.setText(df.format(Integer.parseInt(devueltos.replace(".", "").replace(",", ""))));
            String Ventas = String.valueOf(getTotalVentas());
            dlgGestRepartos.txtTotalVentasL.setText(Ventas);
            dlgGestRepartos.txtTotalVentas.setText(df.format(Integer.parseInt(Ventas.replace(".", "").replace(",", ""))));
        } catch (Exception e) {
            Mensajes.error("ERROR AL CARGAR DETALLE: " + e.getMessage().toLowerCase());
        }
    }
    
    public static void addTabladesdeTransferencia(int codA, String codI, String desc, double cant, JTable tabla) {
        try {
            art = GestionarArticulosMovil.busArticulo(String.valueOf(codA));
            double valorI = 0;
            precio_venta = art.getPrecio_venta();
            precio_costo = art.getPrecio_costo();
            DetalleReparto dco = new DetalleReparto(codA);
            if (array.busca(dco.getIdproducto()) != -1) {
                int Nfila = array.busca(dco.getIdproducto());
                double cantTabla = Double.parseDouble(dlgGestRepartos.tbDetalleReparto.getValueAt(Nfila, 7).toString());
                double cantT = Double.parseDouble(dlgGestRepartos.tbDetalleReparto.getValueAt(Nfila, 6).toString());
                double cantR = Double.parseDouble(dlgGestRepartos.tbDetalleReparto.getValueAt(Nfila, 9).toString());
                double cantD = Double.parseDouble(dlgGestRepartos.tbDetalleReparto.getValueAt(Nfila, 14).toString());
                
                addmismoItemRepartoT(Nfila, cantTabla, cant, precio_venta, precio_costo,cantT, cantR, cantD);
            } else {
                array.agregar(dco);
                insertarT(String.valueOf(codA), codI, desc, String.valueOf(precio_venta), cant, (int) Redondeo.redondearD(precio_venta * cant), (valorI),
                        (int) Redondeo.redondearD(valorI * precio_venta), cant, (int) Redondeo.redondearD(precio_venta * cant), (int) Redondeo.redondearD(precio_costo * cant),
                        (valorI), (int) Redondeo.redondearD(valorI * precio_venta), tabla);
            }
            DecimalFormat df = new DecimalFormat("#,###");
            String total = String.valueOf(getTotal());
            dlgGestRepartos.txtTotalL.setText(total);
            dlgGestRepartos.txtTotal.setText(df.format(Integer.parseInt(total.replace(".", "").replace(",", ""))));
            String recambio = String.valueOf(getRecambio());
            dlgGestRepartos.txtRecambioL.setText(recambio);
            dlgGestRepartos.txtRecambio.setText(df.format(Integer.parseInt(recambio.trim().replace(".", "").replace(",", ""))));
            String devueltos = String.valueOf(getDevueltos());
            dlgGestRepartos.txtDevueltosL.setText(devueltos);
            dlgGestRepartos.txtDevueltos.setText(df.format(Integer.parseInt(devueltos.replace(".", "").replace(",", ""))));
            String Ventas = String.valueOf(getTotalVentas());
            dlgGestRepartos.txtTotalVentasL.setText(Ventas);
            dlgGestRepartos.txtTotalVentas.setText(df.format(Integer.parseInt(Ventas.replace(".", "").replace(",", ""))));
        } catch (Exception e) {
            Mensajes.error("ERROR AL CARGAR DETALLE: " + e.getMessage().toLowerCase());
        }
    }

    public static void consLinea() {
        int fila = dlgGestRepartos.tbDetalleReparto.getSelectedRow();
        int cod = Integer.parseInt(dlgGestRepartos.tbDetalleReparto.getValueAt(fila, 0).toString());
        int p = array.busca(cod);
        if (p == -1) {
            Mensajes.informacion("Producto no existe");
        } else {
            dc = array.getFila(p);
            int codAr = dc.getIdproducto();
            //int cant = dc.getCant();
            //int prec = dc.getPrecio();
            //int monto = dc.getMonto();
        }
    }

    public static void delRenglon(JTable tabla) {
        consLinea();
        int fila = dlgGestRepartos.tbDetalleReparto.getSelectedRow();
        int cod = Integer.parseInt(dlgGestRepartos.tbDetalleReparto.getValueAt(fila, 0).toString());
        String codint = (dlgGestRepartos.tbDetalleReparto.getValueAt(fila, 1).toString());
        String desc = (dlgGestRepartos.tbDetalleReparto.getValueAt(fila, 2).toString());
        int p = array.busca(cod);
        if (p != -1) {
            int res = Mensajes.confirmar("¿Desea remover del reparto el siguiente producto?"
                    + "\nCOD INTERNO: "+codint+"\n"
                            + "DESCRIPCIÓN: "+desc);
            if (res == 0) {
                array.eliminar(p);
                DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
                tb.removeRow(fila);
                DecimalFormat df = new DecimalFormat("#,###");
                String total = String.valueOf(getTotal());
                dlgGestRepartos.txtTotalL.setText(total);
                dlgGestRepartos.txtTotal.setText(df.format(Integer.parseInt(total.replace(".", "").replace(",", ""))));
                String recambio = String.valueOf(getRecambio());
                dlgGestRepartos.txtRecambioL.setText(recambio);
                dlgGestRepartos.txtRecambio.setText(df.format(Integer.parseInt(recambio.trim().replace(".", "").replace(",", ""))));
                String devueltos = String.valueOf(getDevueltos());
                dlgGestRepartos.txtDevueltosL.setText(devueltos);
                dlgGestRepartos.txtDevueltos.setText(df.format(Integer.parseInt(devueltos.replace(".", "").replace(",", ""))));
                String Ventas = String.valueOf(getTotalVentas());
                dlgGestRepartos.txtTotalVentasL.setText(Ventas);
                dlgGestRepartos.txtTotalVentas.setText(df.format(Integer.parseInt(Ventas.replace(".", "").replace(",", ""))));
            }
        }
    }

    public static void canCelar() {
        array.vaciar();
    }

    public static void listarCompras(JTable tabla) {
        List lista;
        lista = GestionarCompra.listarCompras();
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            fila[1].toString();
            fila[2].toString();
            fila[3].toString();
            fila[4].toString();
            fila[5].toString();
            fila[6].toString();
            fila[7].toString();
            fila[8].toString();
            fila[9].toString();
            //fila[10].toString();
            if (fila[10].toString().equals("S")) {
                fila[10] = "ACTIVO";
            } else {
                fila[10] = "ANULADO";
            }
            tb.addRow(fila);
        }
    }
    
    public static void listarComisiones(JTable tabla, int idvendedor, String fechaD, String fechaH) {
        List lista;
        lista = GestionarReparto.listarComisiones(idvendedor, fechaD, fechaH);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            fila[1].toString();
            fila[2].toString();
            fila[3].toString();
            tb.addRow(fila);
        }
    }

    public static void listarDetalleReparto_A_Modificar(JTable tabla) {
        
        if (!dlgGestRepartos.txtIdReparto.getText().trim().isEmpty()) {
            try {
                int cod = Integer.parseInt(dlgGestRepartos.txtIdReparto.getText().trim());
                List lista;
                lista = GestionarReparto.listarDetalleReparto(cod);
                for (int i = 1; i < lista.size(); i++) {
                    DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
                    Object[] fila = (Object[]) lista.get(i);
                    DetalleReparto dco = new DetalleReparto(Integer.parseInt(fila[1].toString()));
                    array.agregar(dco);
                    insertarM(fila[1].toString(), fila[2].toString(), fila[3].toString(), (fila[4].toString()), Double.parseDouble(fila[5].toString()), Double.parseDouble(fila[6].toString()), Double.parseDouble(fila[7].toString()), Double.parseDouble(fila[8].toString()), Integer.parseInt(fila[9].toString()), Double.parseDouble(fila[10].toString()),
                            Integer.parseInt(fila[11].toString()), Double.parseDouble(fila[12].toString()), Integer.parseInt(fila[13].toString()), Integer.parseInt(fila[14].toString()),
                            Double.parseDouble(fila[15].toString()), Integer.parseInt(fila[16].toString()), tabla);
                }
                dlgGestRepartos.cant();
            } catch (Exception e) {
                Mensajes.error("ERROR LISTANDO PRODUCTOS DEL REPARTO A MODIFICAR");
            }
        }
    }

    public static String anularReparto(int cod) {
        String msg;
        String usuario = UsuarioL = Login.getUsuarioLogueado();
        msg = GestionarReparto.delReparto(cod,usuario);
        if (msg == null) {
            Mensajes.informacion("El Reparto fue anulado satisfactoriamente");
            //dlgGestRepartos.Cancelar();
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }
    public static String anularComision(int cod) {
        String msg;
        String usuario = UsuarioL = Login.getUsuarioLogueado();
        msg = GestionarReparto.delComision(cod,usuario);
        if (msg == null) {
            Mensajes.informacion("Las Comisiones relacionadas al reparto fueron anuladas satisfactoriamente");
        } else {
            Mensajes.error(msg);
        }
        return msg;
    }
    
    public static void addmismoItemReparto(int fila, double cantTabla, double cantAdd, int precio_venta, int precio_costo, double cantR, double cantD) {
        try {
            double cantFinal = (cantTabla + cantAdd);
            double cantVendido = cantFinal -(cantR + cantD);
            int montoVenta = (int) (precio_venta * cantVendido);
            int montoCosto = (int) (precio_costo * cantVendido);
            int montoCT = (int) (precio_venta * cantFinal);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(cantFinal), fila, 7);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(montoCT), fila, 8);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(cantVendido), fila, 11);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(montoVenta), fila, 12);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(montoCosto), fila, 13);
        } catch (Exception e) {
            Mensajes.error("Error_ADD_MIR: "+e.getMessage());
        }
    }
    
    public static void addmismoItemRepartoRA(int fila, double cantTabla, double cantAdd, int precio_venta, int precio_costo, double cantRA) {
        try {
            double cantFinal = (cantTabla + cantAdd);
            double cantF_RA = (cantRA + cantAdd);
            int montoVenta = (int) Redondeo.redondearD(precio_venta * cantFinal);
            int montoCosto = (int) Redondeo.redondearD(precio_costo * cantFinal);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(cantF_RA), fila, 4);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(cantFinal), fila, 7);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(montoVenta), fila, 8);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(cantFinal), fila, 11);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(montoVenta), fila, 12);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(montoCosto), fila, 13);
        } catch (Exception e) {
            Mensajes.error("Error_ADD_MIR: "+e.getMessage());
        }
    }
    
    public static void addmismoItemRepartoC(int fila, double cantTabla, double cantAdd, int precio_venta, int precio_costo, double cantC, double cantR, double cantD) {
        try {
            double cantFinal = (cantTabla + cantAdd);
            double cantF_C = (cantC + cantAdd);
            double cantVendido = cantFinal-(cantR + cantD);
            //int montoVenta = (int) Redondeo.redondearD(precio_venta * cantFinal);
            //int montoCosto = (int) Redondeo.redondearD(precio_costo * cantFinal);
            int montoCT = (int) Redondeo.redondearD(precio_venta * cantFinal);
            int montoVenta = (int) Redondeo.redondearD(precio_venta * cantVendido);
            int montoCosto = (int) Redondeo.redondearD(precio_costo * cantVendido);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(cantF_C), fila, 5);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(cantFinal), fila, 7);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(montoCT), fila, 8);
            //dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(cantFinal), fila, 11);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(cantVendido), fila, 11);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(montoVenta), fila, 12);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(montoCosto), fila, 13);
        } catch (Exception e) {
            Mensajes.error("Error_ADD_MIR: "+e.getMessage());
        }
    }
    
    public static void addmismoItemRepartoT(int fila, double cantTabla, double cantAdd, int precio_venta, int precio_costo, double cantT, double cantR, double cantD) {
        try {
            double cantFinal = (cantTabla + cantAdd);
            double cantF_T = (cantT + cantAdd);
            double cantVendido = cantFinal -(cantR + cantD);
            int montoVenta = (int) Redondeo.redondearD(precio_venta * cantVendido);
            int montoCosto = (int) Redondeo.redondearD(precio_costo * cantVendido);
            int montoCT = (int) Redondeo.redondearD(precio_venta * cantFinal);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(cantF_T), fila, 6);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(cantFinal), fila, 7);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(montoCT), fila, 8);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(cantVendido), fila, 11);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(montoVenta), fila, 12);
            dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(montoCosto), fila, 13);
        } catch (Exception e) {
            Mensajes.error("Error_ADD_MIR: "+e.getMessage());
        }
    }

    public static void addRecambiosReparto(int fila, double cantAdd, int precio_venta, int precio_costo, double cantDevuelto, double cantCarga) {
        try {
            double cantFinalRecambios = (cantAdd);
            double cantControl = (cantFinalRecambios + cantDevuelto);

            if (cantCarga >= cantControl) {
                int montoRecambio = (int) (precio_venta * cantFinalRecambios);
                double cantVenta = (cantCarga - cantControl);
                int montoVenta = (int) (cantVenta * precio_venta);
                int montoCosto = (int) (cantVenta * precio_costo);
                dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(cantFinalRecambios), fila, 9);
                dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(montoRecambio), fila, 10);
                dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(cantVenta), fila, 11);
                dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(montoVenta), fila, 12);
                dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(montoCosto), fila, 13);
                
                dlgGestRepartos.tbDetalleReparto.changeSelection ( fila, 0, false, false );
                
            } else {
                Mensajes.error("OBSERVACIÓN:"
                        + "\nLa suma de las cantidades entre RECAMBIO + DEVUELTO superan a la CARGA TOTAL del Reparto."
                        + "\nPor favor, verifique los números ingresados entre RECAMBIO y DEVUELTO para corregir este inconveniente.");
                dlgGestRepartos.tbDetalleReparto.changeSelection ( fila, 0, false, false );
            }

        } catch (Exception e) {
            Mensajes.error("Error_ADD_RR: "+e.getMessage());
        }
    }

    public static void addDevueltosReparto(int fila, double cantAdd, int precio_venta, int precio_costo, double cantRecambio, double cantCarga) {
        try {
            double cantFinalDevuelto = (cantAdd);
            double cantControl = (cantFinalDevuelto + cantRecambio);
            if (cantCarga >= cantControl) {
                double cantVendido = cantCarga - cantControl;
                int montoDevuelto = (int) (precio_venta * cantFinalDevuelto);
                int montoVenta = (int) (precio_venta * cantVendido);
                int montoCosto = (int) (precio_costo * cantVendido);
                dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(cantFinalDevuelto), fila, 14);
                dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(montoDevuelto), fila, 15);
                dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(cantVendido), fila, 11);
                dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(montoVenta), fila, 12);
                dlgGestRepartos.tbDetalleReparto.setValueAt(String.valueOf(montoCosto), fila, 13); 
                
                dlgGestRepartos.tbDetalleReparto.changeSelection ( fila, 0, false, false );
                
            } else {
                Mensajes.error("OBSERVACIÓN:"
                        + "\nLa suma de las cantidades entre RECAMBIO + DEVUELTO superan a la CARGA TOTAL del Reparto."
                        + "\nPor favor, verifique los números ingresados entre RECAMBIO y DEVUELTO para corregir este inconveniente.");
                dlgGestRepartos.tbDetalleReparto.changeSelection ( fila, 0, false, false );
            }
        } catch (Exception e) {
            Mensajes.error("Error_ADD_DR: "+e.getMessage());
        }
    }
    
    public static void listarRepartoAnterior(JTable tabla, int idresponsable) {        
        List lista;
        lista = GestionarReparto.listarRepartoAnterior(idresponsable);
          for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            fila[1].toString();
            fila[2].toString();
            fila[3].toString();
            fila[4].toString();
            fila[5].toString();
            fila[6].toString();
            fila[7].toString();
            fila[8].toString();
            fila[9].toString();
            tb.addRow(fila);
        }           
        
    }
    
    public static void listarDetalleRepartoAnterior(JTable tabla) {
        int x = dlgConsultarRepartoAnterior.tbReparto.getSelectedRow();
        int cod = Integer.parseInt(dlgConsultarRepartoAnterior.tbReparto.getValueAt(x, 0).toString());
        List lista;
        lista = GestionarReparto.listarDetalleRepartoAnterior(cod);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            fila[0].toString();
            fila[1].toString();
            fila[2].toString();
            fila[3].toString();
            fila[4].toString();
            fila[5].toString();
            tb.addRow(fila);
        }
    }
    
    public static int getTotalReparto() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgConsultarRepartoAnterior.tbDetalleRepartoAnterior.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgConsultarRepartoAnterior.tbDetalleRepartoAnterior.getValueAt(i, 5)).replace(".", "").replace(",", ""));
        }
        return (total);
    }
    
    public static int getTotalCompra() {
        int total = 0;
        DefaultTableModel tb = (DefaultTableModel) dlgConsultarCompras1.tbDetalleCompra.getModel();
        int fila = tb.getRowCount();
        for (int i = 0; i < fila; i++) {
            total += Integer.valueOf(String.valueOf(dlgConsultarCompras1.tbDetalleCompra.getValueAt(i, 8)).replace(".00", ""));
        }
        return (total);
    }
    
    
    public static void listRepartos(JTable tabla, String cod, String fecha) {
        List lista;
        lista = GestionarReparto.listarReparto(cod , fecha);
        for (int i = 1; i < lista.size(); i++) {
            DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
            Object[] fila = (Object[]) lista.get(i);
            tb.addRow(fila);
        }
    }
}
