package Controladores;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public final class CabecerasTablas {

    String articulos[] = {"CODIGO","CODBARRA","FAMILIA","MARCA", "PROVEEDOR", "DESCRIPCIÓN ARTÍCULO", "OBSERVACIÓN", "ACCION TERAPEUTICA", 
        "STOCK", "ST.MIN", "COSTO", "IVA", "COST.IVA", "P.PUBLICO","% DESC", "P.VENTA","% FLIA","% FIN","VENTA","ACT"};
    static String articulosCompra[] = {"CODIGO","CODINT","CODBARRA","DESCRIPCIÓN","P.COSTO"};
    String articulosAux_reparto[] = {"CODIGO","CODINT","CODBARRA","DESCRIPCIÓN","P.VENTAS"};
    static String articulosDependencia[] = {"CODIGO","DEPENDENCIA","CLASIFICACION","CODINT","CODBARRA", "DESCRIPCIÓN", "U.M.","COSTO","GAN.MAY.", "MAYORISTA","STOCK", "IMPU"};
    static String articulosMovil[] = {"CODIGO","DEPENDENCIA","CLASIFICACION","CODINT","CODBARRA", "DESCRIPCIÓN", "U.M.","COSTO","GAN.MAY.", "MAYORISTA","STOCK", "IMPU"};
    static String articulosMovil2[] = {"CODIGO","DEPENDENCIA","CLASIFICACION","CODINT","CODBARRA", "DESCRIPCIÓN", "U.M.","COSTO","GAN.MAY.", "PRECIO MAY.","STOCK", "IMPU"," ","GAN.MIN.","PRECIO MIN."};
    static String reparto[] = {"ID","CODINT","DESCRIPCION","PRECIO","RA","C","T", "CARGA TOTAL","MCARGA", "RECAMBIOS","MRECAMBIO","VENDIDOS","MVENDIDO","MCOSTO","DEVUELTOS","MDEVUELTO"};
    static String todosReparts[] = {"ID","MOV.","REFERENCIA","TOTAL","RECAMBIO","DEVUELTO","ENTREGAR","ENTREGADO","DIF."};
    static String reporteComision[] = {"ID.REPAR", "MOV.N°","FECHA","COMISIÓN"};
    String familia[] = {"ID", "FAMILIA","%","I.V.A."};
    String laboratorio[] = {"ID", "MARCAS"};
    String empresa[] = {"ID", "RAZÓN SOCIAL","R.U.C.","DIRECCIÓN","TELÉFONO","ESTADO","IDCIUDAD","CIUDAD"};
    String moviles[] = {"ID", "DESCRIPCIÓN DEL PUNTO DE CONTROL","CHAPA","MARCA","MODELO","CHASIS","AÑO FABR.","COLOR","CAP."};
    String vendedores[] = {"ID","ID","FUNCION","ID","PUNTO DE CONTROL", "NOMBRE Y APELLIDO", "C.I. NRO.", "FECHA INGRESO", "CELULAR", "SUELDO", "% COM", "OBSERVACIÓN"};
    private static final String clientes[] = {"ID", "NOMBRE Y APELLIDO | RAZÓN SOCIAL", "C.I. | R.U.C.", "DIRECCIÓN", "TELÉFONO", "CIUDAD"};
    String provedores[] = {"ID","RAZÓN SOCIAL","R.U.C.", "TELÉFONO","CONTACTO", "CELULAR","CIUDAD", "DIRECCIÓN","OBSERVACIÓN"};
    String tablaStock[] = {"Código", "Marca", "Descripción", "Stock"};
    String tablaAuxiliarArticulo[] = {"Codigo", "Rubro", "Descripcion", "Marca", "Precio"};
    String ciudad[] = {"ID", "CIUDAD"};
    String ciudadMovil[] = {"ID", "CIUDAD","DEPARTAMENTO","EST","IDDEP"};
    String sucursal[] = {"ID", "SUCURSAL","EMPRESA"};
    String zona[] = {"CODIGO", "ZONA"};
    String detalleGasto[] = {"ID", "DESCRIPCIÓN"};
    String motivo[] = {"ID", "MOTIVOS"};
    String clasificacion[] = {"ID", "CLASIFICACION","ESPECIFICACIÓN"};
    String um[] = {"ID", "UNIDAD DE MEDIDA","EQUIVALENCIA"};
    String timbrado[] = {"ID", "TIMBRADO","DESDE","HASTA","ESTADO"};
    String puntoEmision[] = {"ID","IDT", "TIMBRADO","EST.","P.E.","DIRECCION","INICIO","FIN","ACTUAL","N°.VTA.","TIPO","TIPO2","DIR. IP","ESTADO"};
    String iva[] = {"ID", "IMPUESTO","DESCRIPCIÓN DE IMPUESTO"};
    String salidas[] = {"CODART", "DESCRIPCIÓN", "CODMOTIVO", "MOTIVO SALIDA", "CANT", "COSTO", "MONTO"};
    String conSalidas[] = {"OP. N°","PROVEEDOR", "FECHA", "HORA", "COS.TOTAL","OBSERVACION", "INDICADOR"};
    String detalleSalida[] = {"CODART","MOTIVO SALIDA", "CANT", "OP. N°", "DESCRIPCIÓN", "COSTO","MONTO"};
    static String compras[] = {"COD", "CODINT", "DESCRIPCIÓN", "CANT","CANTf", "PRECIO ","PRECIOF","IVA", "EXENTA","EXENTAf","IVA 5%","IVA5f","IVA 10%","IVA10f","MONTO","MONTOf","GA"};
    static String facturas[] = {"COD","DEP","ID", "CODBARRA", "DESCRIPCIÓN", "CANT", "PRECIO", "EXENTA","IVA 5%","IVA 10%","MONTO","IDIVA","COSTO"};
    String categoria[] = {"CODIGO", "CATEGORIA"};
    String presupuestos[] = {"Cant.", "Cod.", "Descripción", "Precio", "Total"};
    String conFactura[] = {"OPERACIÓN", "RAZÓN SOCIAL", "FECHA","HORA", "COD CLI","MOV.CAJA","FACTURA N°", "CONDICION","PAGO", "TOTAL", "CODVENDE","ESTADO"};
    String detalleFactura[] = {"CANT","DEP","IDDEP", "ID","CÓD. BARRA","NOMBRE COMERCIAL", "PRECIO", "TOTAL"};
    String consPresupuesto[] = {"N°", "Fecha", "Razon Social", "Cód. Clie", "Desc", "Total"};
    String detallePresupuestoF[] = {"Cant.", "Código", "Descripción", "Precio", "Total"};
    String busEmpleado[] = {"Cód", "Empleado", "Observación"};
    String consNotaCredito[] = {"N°", "Fecha", "Razon Social", "Cod. Clie", "Total", "Desc", "Fac"};
    String detalleNotaCredito[] = {"Cant.", "Cód", "Descripción", "Precio", "Total"};
    String usuario[] = {"ID", "PERFIL ASIG.", "EMPLEADO", "USUARIO", "PASSWORD","INDICADOR","IP","CODPERFIL","CODVENDE"};
    String comisiones[] = {"FECHA", "FACT.", "CLIENTE", "TOTAL", "%COM.", "COMISION"};
    String consCompras[] = {"COMPRA.N°","MOV.N°","TIPO", "FECHA","HORA","R.U.C.", "PROVEEDOR","CONDICIÓN","FACTURA N°", "Cod. Prov", "TOTAL","ESTADO","OBSERV."};
    static String consVentasMoviles[] = {"VENTA.N°","EMISION","TIMBRADO","DESDE","HASTA","FACTURA NRO", "CONDICIÓN","FECHA"," HORA", "RUC","RAZÓN SOCIAL","TOTAL","VENDEDOR","ESTADO","E","5","10"};
    static String consDetalleVentasMoviles[] = {"CODINT","COD_BARRA","DESCRIPCIÓN","CANT", "PRECIO","SUB-TOTAL","IMP","UM"};
    String consRepartoAnterior[] = {"REPARTO.N°","MOVIM.N°","REFERENCIA", "FECHA","HORA", "KILOMETRAJE","EFECT. ENTREGADO"};
    static String consDetalleCompras[] = {"OPER. N°","ID","CÓD.INT", "DESCRIPCION","CANT", "COSTO","MONTO","VENTA","MONTO"};
    static String consDetalleTransferenciasRealizadas[] = {"OPER. N°","ID","CÓD.INT", "DESCRIPCION","CANT", "PRECIO","MONTO"};
    String consDetalleRepartoAnterior[] = {"OPER. N°","ID","CÓD.INT", "DESCRIPCION","CANT","VALOR"};
    static String Gastos[] = {"ID","MOV.N°","FECHA","MOTIVO","ORIGEN","REFERENCIA","MONTO","OBSERVACIÓN"};
    static String transferencia[] = {"ID","CODINT","DESCRIPCION","PRECIO","CANT","MONTO"};
    static String datosTransferencias[] = {"ID TRANSF.","MOV.N°","FECHA","HORA","O","TRANSFERENCIA ORIGEN","D","TRANSFERENCIA DESTINO", "TOTAL"};
    static String datosTransferencias2[] = {"ID TRANSF.","MOV.N°","FECHA","HORA","O","TRANSFERENCIA ORIGEN","D","TRANSFERENCIA DESTINO", "TOTAL","ID.O","ID.E"};
    static String DP[] = {"ID","FECHA","RAZÓN SOCIAL","RUC","OBSERVACIÓN","MONTO","ORIGEN"};
    static String CQ[] = {"ID","FECHA","TIPO","BANCO","CHEQUE N°","MONTO","RAZÓN SOCIAL","RUC","RECIBIDO EN"};
    static String datos[][] = {};
    static String datosVentasMoviles[][] = {};
    static String datosDetalleVentasMoviles[][] = {};
    static String datosDetalleTransf[][] = {};
    static String datosTT[][] = {};
    static String datosTT2[][] = {};
    static String datosTrans[][] = {};
    static String datosGastos[][] = {};
    static String datosCompras[][] = {};
    static String datosArtAuxCompra[][] = {};
    static String datosBuscarClientes[][] = {};
    static String datosVentas [][] = {};
    static String datosArtAux [][] = {};
    static String datosArtDependencia[][] = {};
    static String datosArtM [][] = {};
    static String datosTodosR[][]= {};
    static String datosconsDetalleCompras[][] = {};
    static String datosDP[][] = {};
    static String datosCQ[][] = {};
    private static DefaultTableModel modelo;
    private static DefaultTableModel modeloDependencia;
    private static DefaultTableModel modeloVentasMoviles;
    private static DefaultTableModel modeloDetalleVentasMoviles;
    private static DefaultTableModel modeloDetalleTransfe;
    private static DefaultTableModel modelotodosTransferencias;
    private static DefaultTableModel modelotodosTransferencias2;
    private static DefaultTableModel modeloTrans;
    private static DefaultTableModel modeloconsDetalleCompras;
    private static DefaultTableModel modeloArtAuxCompra;
    private static DefaultTableModel modeloArtM;
    private static DefaultTableModel modeloArtAux;
    private static DefaultTableModel modeloBuscarCliente;
    private static DefaultTableModel modeloReparto;
    private static DefaultTableModel modelotodosReparto;
    private static DefaultTableModel modeloVentas;
    private static DefaultTableModel modeloCompras;
    private static DefaultTableModel modeloGastos;
    private static DefaultTableModel modeloDP;
    private static DefaultTableModel modeloCQ;
    private static TableColumn colum = null;

    public static void limpiarTablasconsDetalleCompras(JTable tabla) {
        tabla.setModel(modeloconsDetalleCompras);
        int filas = tabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloconsDetalleCompras.removeRow(0);
        }
    }
    
    public static void limpiarTablas(JTable tabla) {
        tabla.setModel(modelo);
        int filas = tabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modelo.removeRow(0);
        }
    }
    
    public static void limpiarTablasDependencia(JTable tabla) {
        tabla.setModel(modeloDependencia);
        int filas = tabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloDependencia.removeRow(0);
        }
    }
    
    public static void limpiarTablasCheques(JTable tabla) {
        tabla.setModel(modeloCQ);
        int filas = tabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloCQ.removeRow(0);
        }
    }
    
    public static void limpiarTablasDeudasPendientes(JTable tabla) {
        tabla.setModel(modeloDP);
        int filas = tabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloDP.removeRow(0);
        }
    }
    
    public static void limpiarTablasVentasMoviles(JTable tabla) {
        tabla.setModel(modeloVentasMoviles);
        int filas = tabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloVentasMoviles.removeRow(0);
        }
    }
    
    public static void limpiarTablasDetalleVentasMoviles(JTable tabla) {
        tabla.setModel(modeloDetalleVentasMoviles);
        int filas = tabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloDetalleVentasMoviles.removeRow(0);
        }
    }
    
    public static void limpiarTablasDetalleTransferenciasRealizadas(JTable tabla) {
        tabla.setModel(modeloDetalleTransfe);
        int filas = tabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloDetalleTransfe.removeRow(0);
        }
    }
    
    public static void limpiarTablasTransferenciasRealizadas(JTable tabla) {
        tabla.setModel(modelotodosTransferencias);
        int filas = tabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modelotodosTransferencias.removeRow(0);
        }
    }
    
    public static void limpiarTablasTransferenciasRealizadas2(JTable tabla) {
        tabla.setModel(modelotodosTransferencias2);
        int filas = tabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modelotodosTransferencias2.removeRow(0);
        }
    }
    
    public static void limpiarTablasTransferencias(JTable tabla) {
        tabla.setModel(modeloTrans);
        int filas = tabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloTrans.removeRow(0);
        }
    }
    
    public static void limpiarTablasGastos(JTable tabla) {
        tabla.setModel(modeloGastos);
        int filas = tabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloGastos.removeRow(0);
        }
    }
    
    public static void limpiarTablasTodosRepartos(JTable tabla) {
        tabla.setModel(modelotodosReparto);
        int filas = tabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modelotodosReparto.removeRow(0);
        }
    }
    public static void limpiarTablasCompras(JTable tabla) {
        tabla.setModel(modeloCompras);
        int filas = tabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloCompras.removeRow(0);
        }
    }
    
    public static void limpiarTablasArtAuxCompra(JTable tabla) {
        tabla.setModel(modeloArtAuxCompra);
        int filas = tabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloArtAuxCompra.removeRow(0);
        }
    }
    
    public static void limpiarTablasArticuloM(JTable tabla) {
        tabla.setModel(modeloArtM);
        int filas = tabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloArtM.removeRow(0);
        }
    }
    public static void limpiarTablasArtAux(JTable tabla) {
        tabla.setModel(modeloArtAux);
        int filas = tabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloArtAux.removeRow(0);
        }
    }
    
    public static void limpiarTablasVentas(JTable tabla) {
        tabla.setModel(modeloVentas);
        int filas = tabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloVentas.removeRow(0);
        }
    }
    public static void limpiarTablasRepartos(JTable tabla) {
        tabla.setModel(modeloReparto);
        int filasR = tabla.getRowCount();
        for (int i = 0; i < filasR; i++) {
            modeloReparto.removeRow(0);
        }
    }
    
    public static void limpiarTablasBuscarClientes(JTable tabla) {
        tabla.setModel(modeloBuscarCliente);
        int filas = tabla.getRowCount();
        for (int i = 0; i < filas; i++) {
            modeloBuscarCliente.removeRow(0);
        }
    }
    
    public static void gestGastos(JTable tabla) {
        modeloGastos = new DefaultTableModel(datosGastos, Gastos);
        tabla.setModel(modeloGastos);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(75);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(180);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(100);
       /* colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);*/
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(130);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(200);

    }

     public void consCompras(JTable tabla) {
        modelo = new DefaultTableModel(datos, consCompras);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(75);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(85);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(80);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(60);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(80);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(210);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(110);
        colum = tabla.getColumnModel().getColumn(9);
        colum.setPreferredWidth(90);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(10);
        colum.setPreferredWidth(100);
        colum = tabla.getColumnModel().getColumn(11);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(12);
        colum.setPreferredWidth(170);
        /*colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);*/

    }
     
     public static void consVentasMoviles(JTable tabla) {
        modeloVentasMoviles = new DefaultTableModel(datosVentasMoviles, consVentasMoviles);
        tabla.setModel(modeloVentasMoviles);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(75);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(75);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(75);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(75);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(75);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(100);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(75);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(9);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(10);
        colum.setPreferredWidth(250);
        colum = tabla.getColumnModel().getColumn(11);
        colum.setPreferredWidth(75);
        colum = tabla.getColumnModel().getColumn(12);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(13);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(14);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(15);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(16);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);

    }
     
     public static void consDetalleVentasMoviles(JTable tabla) {
        modeloDetalleVentasMoviles = new DefaultTableModel(datosDetalleVentasMoviles, consDetalleVentasMoviles);
        tabla.setModel(modeloDetalleVentasMoviles);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(75);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(75);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(360);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(75);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(75);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(75);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(75);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);

    }
    
    public void consComprasReparto(JTable tabla) {
        modelo = new DefaultTableModel(datos, consCompras);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(75);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(60);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(80);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(220);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(95);
        colum = tabla.getColumnModel().getColumn(9);
        colum.setPreferredWidth(80);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(10);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(11);
        colum.setPreferredWidth(75);
        colum = tabla.getColumnModel().getColumn(12);
        colum.setPreferredWidth(120);
    }
    
    public void consRepartoAnterior(JTable tabla) {
        modelo = new DefaultTableModel(datos, consRepartoAnterior);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(100);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(85);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(175);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(85);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(65);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(115);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(115);
    }

    public static void consDetalleCompras(JTable tabla) {
        modeloconsDetalleCompras = new DefaultTableModel(datosconsDetalleCompras, consDetalleCompras);
        tabla.setModel(modeloconsDetalleCompras);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(120);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(505);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(95);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(95);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
    }
    
    public static void consDetalleTransferencias(JTable tabla) {
        modeloDetalleTransfe = new DefaultTableModel(datosDetalleTransf, consDetalleTransferenciasRealizadas);
        tabla.setModel(modeloDetalleTransfe);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(120);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(505);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(95);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(95);
    }
    
    public void consDetalleComprasReparto(JTable tabla) {
        modelo = new DefaultTableModel(datos, consDetalleCompras);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(120);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(450);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
    }
    
    public void consDetalleRepartoAnterior(JTable tabla) {
        modelo = new DefaultTableModel(datos, consDetalleRepartoAnterior);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(120);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(410);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(90);
    }

    public void usuario(JTable tabla) {
        modelo = new DefaultTableModel(datos, usuario);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(120);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(200);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(85);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
    }

    public void comision(JTable tabla) {
        modelo = new DefaultTableModel(datos, comisiones);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(250);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(80);
    }

    public void busEmpleado(JTable tabla) {
        modelo = new DefaultTableModel(datos, busEmpleado);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(200);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(200);
    }

    public void consNotaCredito(JTable tabla) {
        modelo = new DefaultTableModel(datos, consNotaCredito);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(200);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
    }

    public void detalleNotasCredito(JTable tabla) {
        modelo = new DefaultTableModel(datos, detalleNotaCredito);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(200);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(70);
    }

    public void consFacturasNotas(JTable tabla) {
        modelo = new DefaultTableModel(datos, conFactura);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(200);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
    }

    public void detallePresupuesto(JTable tabla) {
        modelo = new DefaultTableModel(datos, detallePresupuestoF);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(200);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(70);
    }

    public void consPresupuesto(JTable tabla) {
        modelo = new DefaultTableModel(datos, consPresupuesto);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(200);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
    }

    public void consFacturas(JTable tabla) {
        modelo = new DefaultTableModel(datos, conFactura);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(200);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(50);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(100);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(9);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(10);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(11);
        colum.setPreferredWidth(70);
        
        /*colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(200);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(100);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(9);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(10);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(11);
        colum.setPreferredWidth(70);*/
    }

    public void consFacturasA(JTable tabla) {
        modelo = new DefaultTableModel(datos, conFactura);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(200);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
    }

    public void detalleFactura(JTable tabla) {
        modelo = new DefaultTableModel(datos, detalleFactura);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(420);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(70);
    }

    public void detalleFacturaA(JTable tabla) {
        modelo = new DefaultTableModel(datos, detalleFactura);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(280);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(70);
    }

    public void presupuesto(JTable tabla) {
        modelo = new DefaultTableModel(datos, presupuestos);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(220);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(70);
    }

    public void factura(JTable tabla) {
        modelo = new DefaultTableModel(datos, facturas);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(220);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(80);
    }

    public static void buscarCliente(JTable tabla) {
        modeloBuscarCliente = new DefaultTableModel(datosBuscarClientes, clientes);
        tabla.setModel(modeloBuscarCliente);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(250);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(200);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(80);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(200);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
    }

    public void categoria(JTable tabla) {
        modelo = new DefaultTableModel(datos, categoria);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(150);
    }
    
    public static final void todosReparto(JTable tabla) {
        modelotodosReparto = new DefaultTableModel(datosTodosR, todosReparts){
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        tabla.setModel(modelotodosReparto);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(22);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(25);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(100);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(30);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(30);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(30);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(30);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(30);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(30);
    }
    
     public static final void todosTransferencias(JTable tabla) {
        modelotodosTransferencias = new DefaultTableModel(datosTT, datosTransferencias){
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        tabla.setModel(modelotodosTransferencias);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(30);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(30);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(20);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(10);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(5);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(170);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(5);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(170);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(30);
    }
    
    public static final void todosTransferencias2(JTable tabla) {
        modelotodosTransferencias2 = new DefaultTableModel(datosTT2, datosTransferencias2){
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false,false,false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        tabla.setModel(modelotodosTransferencias2);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(30);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(30);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(20);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(10);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(5);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(170);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(5);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(170);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(30);
        colum = tabla.getColumnModel().getColumn(9);
        colum.setPreferredWidth(30);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(10);
        colum.setPreferredWidth(30);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
    }
    
    public static final void reparto(JTable tabla) {
        modeloReparto = new DefaultTableModel(datos, reparto){
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        tabla.setModel(modeloReparto);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(60);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(370);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(50);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(37);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(37);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(37);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(80);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(9);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(10);
        colum.setPreferredWidth(80);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(11);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(12);
        colum.setPreferredWidth(50);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(13);
        colum.setPreferredWidth(50);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(14);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(15);
        colum.setPreferredWidth(80);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        
        /*tabla.setModel(modeloReparto);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(370);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(40);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(40);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(40);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(9);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(10);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(11);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(12);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(13);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(14);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(15);
        colum.setPreferredWidth(80);*/
    }
    
    public static void ReporteComision(JTable tabla) {
        modelo = new DefaultTableModel(datos, reporteComision){
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(25);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(25);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(40);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(40);
    }

    public void busProveedor(JTable tabla) {
        modelo = new DefaultTableModel(datos, provedores);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(200);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(90);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(90);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(50);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(200);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
    }
    public static void ventas(JTable tabla) {
        modeloVentas = new DefaultTableModel(datosVentas, facturas);
        tabla.setModel(modeloVentas);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(5);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(20);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(20);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(335);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(40);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(9);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(10);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(11);
        colum.setPreferredWidth(60);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(12);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        /*colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(20);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(20);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(20);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(335);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(40);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(9);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(10);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(11);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(12);
        colum.setPreferredWidth(70);*/
    }

    public static void compras(JTable tabla) {
        modeloCompras = new DefaultTableModel(datosCompras, compras);
        tabla.setModel(modeloCompras);
        
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(60);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(365);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(40);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(40);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(9);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(10);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(11);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(12);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(13);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(14);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(15);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(16);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        
       /* colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(355);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(40);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(40);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(9);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(10);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(11);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(12);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(13);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(14);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(15);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(16);
        colum.setPreferredWidth(70);*/
    }

    public void detalleSalidas(JTable tabla) {
        modelo = new DefaultTableModel(datos, detalleSalida);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(80);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(170);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(320);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(80);

    }

    public void consultaSalidas(JTable tabla) {
        modelo = new DefaultTableModel(datos, conSalidas);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(200);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(250);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(80);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
    }
    
    public static void Transferencia(JTable tabla) {
        
        modeloTrans = new DefaultTableModel(datosTrans, transferencia){
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        tabla.setModel(modeloTrans);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(40);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(350);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(60);
    }
    
    public static void tablaArticuloDependencia(JTable tabla) {
        modeloDependencia = new DefaultTableModel(datosArtDependencia, articulosDependencia);
        tabla.setModel(modeloDependencia);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(100);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(180);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(120);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(440);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(320);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(200);        
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(200);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(9);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(10);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(11);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
    }

    public static void tablaArticuloAuxiliar(JTable tabla) {
        modeloArtAux = new DefaultTableModel(datosArtAux, articulosMovil);
        tabla.setModel(modeloArtAux);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(100);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(180);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(120);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(380);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(320);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(200);        
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(200);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(9);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(10);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(11);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
    }
    public static void tablaArticuloAuxiliarCompra(JTable tabla) {
        modeloArtAuxCompra = new DefaultTableModel(datosArtAuxCompra, articulosCompra);
        tabla.setModel(modeloArtAuxCompra);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(120);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(430);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(80);
    }
    
    public void tablaArticuloAuxiliarReparto(JTable tabla) {
        modelo = new DefaultTableModel(datos, articulosAux_reparto);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(120);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(440);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(70);
    }

    public void salidas(JTable tabla) {
        modelo = new DefaultTableModel(datos, salidas);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(80);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(340);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(80);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(180);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(60);
    }
    
    public void motivo(JTable tabla) {
        modelo = new DefaultTableModel(datos, motivo);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(65);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(390);
    }
    
    public void clasificacion(JTable tabla) {
        modelo = new DefaultTableModel(datos, clasificacion);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(75);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(270);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(120);
    }
    
    public void UM(JTable tabla) {
        modelo = new DefaultTableModel(datos, um);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(75);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(300);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(110);
    }
    
    public void Timbrado(JTable tabla) {
        modelo = new DefaultTableModel(datos, timbrado);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(135);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(110);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(110);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(80);
    }
    
    public void PuntoEmision(JTable tabla) {
        modelo = new DefaultTableModel(datos, puntoEmision);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(50);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(50);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(35);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(35);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(80);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(9);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(10);
        colum.setPreferredWidth(40);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(11);
        colum.setPreferredWidth(40);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(12);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(13);
        colum.setPreferredWidth(55);
    }
    
    public void IVA(JTable tabla) {
        modelo = new DefaultTableModel(datos, iva);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(85);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(85);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(210);
    }

    public void ciudad(JTable tabla) {
        modelo = new DefaultTableModel(datos, ciudad);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(65);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(390);
    }
    public void ciudadMovil(JTable tabla) {
        modelo = new DefaultTableModel(datos, ciudadMovil);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(65);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(250);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(150);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(50);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(50);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
    }

    public void sucursal(JTable tabla) {
        modelo = new DefaultTableModel(datos, sucursal);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(185);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(220);
    }

    /*public void zona(JTable tabla) {
        modelo = new DefaultTableModel(datos, zona);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(150);
    }*/

    public void detalleGasto(JTable tabla) {
        modelo = new DefaultTableModel(datos, detalleGasto);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(380);
    }

    public void ajusteStock(JTable tabla) {
        modelo = new DefaultTableModel(datos, articulos);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(100);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(150);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(150);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(120);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(300);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(200);        
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(200);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(9);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(10);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(11);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(12);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(13);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(14);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(15);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(16);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(17);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(18);
        colum.setPreferredWidth(20);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(19);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
    }

    public void Articulos(JTable tabla) {
        modelo = new DefaultTableModel(datos, articulos);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(180);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(180);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(120);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(270);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(200);     
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(190);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(9);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(10);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(11);
        colum.setPreferredWidth(40);
        colum = tabla.getColumnModel().getColumn(12);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(13);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(14);
        colum.setPreferredWidth(60);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(15);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(16);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(17);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(18);
        colum.setPreferredWidth(20);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(19);
        colum.setPreferredWidth(30);
    }
    
    public static void ArticulosMovil(JTable tabla) {
        modeloArtM = new DefaultTableModel(datosArtM, articulosMovil2);
        tabla.setModel(modeloArtM);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(100);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(190);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(410);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(40);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(9);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(10);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(11);
        colum.setPreferredWidth(60);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(12);
        colum.setPreferredWidth(20);
        colum = tabla.getColumnModel().getColumn(13);
        colum.setPreferredWidth(60);
        colum = tabla.getColumnModel().getColumn(14);
        colum.setPreferredWidth(80);
    }

    public void familia(JTable tabla) {
        modelo = new DefaultTableModel(datos, familia);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(65);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(230);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(80);
    }

    public void laboratorio(JTable tabla) {
        modelo = new DefaultTableModel(datos, laboratorio);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(350);
    }

    public void empresa(JTable tabla) {
        modelo = new DefaultTableModel(datos, empresa);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(60);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(180);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(85);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(260);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(180);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(90);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(90);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(150);
    }
    
    public void moviles(JTable tabla) {
        modelo = new DefaultTableModel(datos, moviles);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(60);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(230);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(80);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(80);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(80);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(80);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
    }

    public void proveedor(JTable tabla) {
        modelo = new DefaultTableModel(datos, provedores);
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(230);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(190);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(150);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(230);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(270);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
    }
    
    public static void DeudaPendiete(JTable tabla) {
        modeloDP = new DefaultTableModel(datosDP, DP);
        tabla.setModel(modeloDP);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(280);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(120);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(280);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(120);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(160);
    }
    
    public static void Cheques(JTable tabla) {
        modeloCQ = new DefaultTableModel(datosCQ, CQ);
        tabla.setModel(modeloCQ);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(70);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(200);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(280);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(200);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(150);
    }

    public void vendedor(JTable tabla) {
        modelo = new DefaultTableModel(datos, vendedores);
        tabla.setModel(modelo);      
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(60);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(60);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(180);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(40);
        colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(180);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(270);
        colum = tabla.getColumnModel().getColumn(6);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(7);
        colum.setPreferredWidth(110);
        colum = tabla.getColumnModel().getColumn(8);
        colum.setPreferredWidth(90);
        colum = tabla.getColumnModel().getColumn(9);
        colum.setPreferredWidth(70);
        colum = tabla.getColumnModel().getColumn(10);
        colum.setPreferredWidth(50);
        colum = tabla.getColumnModel().getColumn(11);
        colum.setPreferredWidth(180);
    }

    public void cliente(JTable tabla) {
        modelo = new DefaultTableModel(datos, clientes){
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false, false
        };
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
        };
        tabla.setModel(modelo);
        colum = tabla.getColumnModel().getColumn(0);
        colum.setPreferredWidth(80);
        /*colum.setMaxWidth(0);
        colum.setMinWidth(0);
        colum.setPreferredWidth(0);*/
        colum = tabla.getColumnModel().getColumn(1);
        colum.setPreferredWidth(300);
        colum = tabla.getColumnModel().getColumn(2);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(3);
        colum.setPreferredWidth(200);
        colum = tabla.getColumnModel().getColumn(4);
        colum.setPreferredWidth(80);
        colum = tabla.getColumnModel().getColumn(5);
        colum.setPreferredWidth(200);
    }
}
