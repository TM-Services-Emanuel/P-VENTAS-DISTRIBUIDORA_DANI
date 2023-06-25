package Componentes;

import java.io.IOException;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.Doc;
import javax.print.PrintException;
import javax.print.ServiceUI;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JOptionPane;


public class codTicket {
    //Atributos que almacenan los datos de la empresa y de la compra
    private String empresa;
    private String propietario;
    private String ruc;
    private String direccion;
    private String celular;
    private String caja;
    private String cliente;
    private String rucCliente;
    private String descripcion;
    private String detalle;
    private String total;
    private String recibo;
    private String cambio;
    private String vendedor;
    private String sucursal;
    private String fecha;

    /*Atributo que almacena la estructura del contenido del ticket
    *   Los campos que tengan la siguiente estructura {{nombreAtributo}}, ejemplo: {{telefono}}
    *   serán reemplazados por los datos correspondientes.
    */
    private String formatoTicket = 
    "**\n"+(char)27+(char)112+(char)0+(char)10+(char)100+"{{empresa}} \n"+
    " {{propietario}}\n"+
    "     R.U.C.: {{ruc}}\n"+
    "{{direccion}}\n"+
    "     Cel: {{telefono}}\n"+
    "\n"+
    "CAJA: {{caja}} \n"+
    "VENDIDO A: {{cliente}}\n"+
    "R.U.C.: {{rucCliente}}\n"+
    "\n"+
    "DESCRIP   CANT.  PRECIO  IMPORTE\n"+
    "================================\n"+
    "{{descripcion}}\n"+
    "{{detalle}}\n"+
    "================================\n"+
    "TOTAL:   $ {{total}}\n"+
    "RECIBIDO:$ {{recibo}}\n"+
    "CAMBIO:  $ {{cambio}}\n"+
    "\n\n"+
    "       GRACIAS POR SU PREFERENCIA\n\n"+
    "Atendido por:{{vendedor}}\n"+
    "Expedido en {{sucursal}} {{fecha}}\n"
    + "\n\n\n\n ";

    //Definimos los metodos SET de cada atributo para asignar los datos al TICKET.

    /**
     * @param empresa nombre del negocio
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * @param propietario dueño del negocio
     */
    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    /**
     * @param ruc registro del negocio
     */
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    /**
     * @param direccion del negocio
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @param celular del negocio
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @param caja el folio de la venta
     */
    public void setCaja(String caja) {
        this.caja = caja;
    }

    /**
     * @param cliente nombre del cliente
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    /**
     * @param rucCliente registro del cliente
     */
    public void setRucCliente(String rucCliente) {
        this.rucCliente = rucCliente;
    }

    /**
     * @param descripcion datos del producto vendido
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * @param detalle datos del producto vendido
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    /**
     * @param total total de la compra
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * @param recibo contidad con la que pago el cliente
     */
    public void setRecibo(String recibo) {
        this.recibo = recibo;
    }

    /**
     * @param cambio cantidad a devolver
     */
    public void setCambio(String cambio) {
        this.cambio = cambio;
    }

    /**
     * @param vendedor datos del vendedor
     */
    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    /**
     * @param sucursal nombre de la sucursal principal
     */
    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    /**
     * @param fecha fecha de impresion
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    /*
    *PARA ESTE EJEMPLO USAMOS UNA IMPRESORA TERMICA CON EL NOMBRE DE  SUBARASI
    *EL CUAL LE ASIGNAMOS DESDE LA VENTANA DE IMPRESORA Y DISPOSITIVOS (WINDOWS)
    *Configuración del documento de impresión y reemplazo de los campos
    *con el valor de las propiedades
    */
    public void print(boolean flagServicio) throws IOException {
        //Datos de impresion.
        //Datos de la Empresa y/o negocio
        this.formatoTicket=formatoTicket.replace("{{empresa}}", this.empresa);
        this.formatoTicket=formatoTicket.replace("{{propietario}}", this.propietario);
        this.formatoTicket=formatoTicket.replace("{{ruc}}", this.ruc);
        this.formatoTicket=formatoTicket.replace("{{direccion}}", this.direccion);
        this.formatoTicket=formatoTicket.replace("{{telefono}}", this.celular);
        //Datos de la venta
        this.formatoTicket=formatoTicket.replace("{{caja}}", this.caja);
        this.formatoTicket=formatoTicket.replace("{{cliente}}", this.cliente);
        this.formatoTicket=formatoTicket.replace("{{rfcCliente}}", this.rucCliente);
        this.formatoTicket=formatoTicket.replace("{{descripcion}}", this.descripcion);
        this.formatoTicket=formatoTicket.replace("{{detalle}}", this.detalle);
        this.formatoTicket=formatoTicket.replace("{{total}}", this.total);
        this.formatoTicket=formatoTicket.replace("{{recibo}}", this.recibo);
        this.formatoTicket=formatoTicket.replace("{{cambio}}", this.cambio);
        //Datos del vendedor, lugar y fecha
        this.formatoTicket=formatoTicket.replace("{{vendedor}}", this.vendedor);
        this.formatoTicket=formatoTicket.replace("{{sucursal}}", this.sucursal);
        this.formatoTicket=formatoTicket.replace("{{fecha}}", this.fecha);
        
        //Especificamos el tipo de dato a imprimir
        //Tipo: bytes -- Subtipo: autodetectado
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        //Creamos un arreglo de tipo byte y le agregamos el string convertido (cuerpo del ticket)
        //a bytes tal como lo maneja la impresora.
        byte[] bytes= this.formatoTicket.getBytes();
        //Creamos un documento a imprimir pasandole el arreglo de byte
        Doc doc = new SimpleDoc(bytes,flavor,null);

        //Creamos un trabajo de impresión
        DocPrintJob job =null;
        //Creamos una bandera para determinar si se encontro la impresora
        //que especificamos en este caso "subarasi" O si usamos la impresora predeterminada del S.O.
        boolean flagJob=false;

        //Opcion 1 ->nos da el array de los servicios de impresion
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        //Opcion 2-> servicios de impresion por default
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        //Opcion 3-> mostramos dialogo para seleccionar impresoras que soporten arreglos de bits
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        
        /*flagServicio
        *   
        *true --si queremos imprimir en una impresora especifica  en este caso "subarasi"
        *       o si deseamos imprimir en la impresora establecida como predeterminada en el Sistema Operativo
        *false --si queremos visualizar el cuadro de dialogo de impresion y elegir una impresora
        *        que soporte arreglo de bits
        */

        if(flagServicio==true){
            if(services.length>0){//usamos la opcion 1 y comprobamos si hay impresoras disponibles
                //Recorremos el arreglo de impresoras
                for (PrintService service1 : services) {
                    //Aqui definimos el nombre de la impresora (para este ejemplo: subarasi)
                    // y comparamos si esta dentro del arreglo de impresoras
                    if (service1.getName().equals("subarasi")) {
                        job = service1.createPrintJob(); // creamos el trabajo de impresion
                        flagJob=true; //si la impresora existe ponemos en TRUE la bandera
                    }
                }
            }
            //En caso de que la opcion 1 no encuentre la impresora que buscamos
            // el flagJob es false y job es null, entonces empleamos la opcion 2
            if(job==null && flagJob==false){
                //creamos el trabajo de impresion con el servicio de impresion por default
                //(la impresora establecida como predeterminada en el sistema operativo)
                job = defaultService.createPrintJob();
            }
            flagJob=false;
        }else{
            //si flagServicio es false, usamos la opcion 3 para crear el trabajo de impresion
            //seleccionando la impresora desde el cuadro de dialogo de impresion
            PrintService service = ServiceUI.printDialog(null, 700, 200, printService, defaultService, flavor, pras);
            job = service.createPrintJob();
        }       
        
        //por ultimo Imprimimos dentro de un try obligatoriamente para el contro de excepciones
        try{
            job.print(doc, null);
        }catch(PrintException ex){
            JOptionPane.showMessageDialog(null, "IMPRIMIR TICKET (Compruebe impresion) "+ex.getMessage());
        }
    }
}
