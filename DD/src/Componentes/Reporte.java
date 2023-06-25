/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.mariadb.jdbc.MariaDbConnection;

/**
 *
 * @author Cris-Men
 */
public class Reporte{
    
    MariaDbConnection con = (MariaDbConnection) new ConexionBD().getConexion();
    
    public Reporte(){
        
        con = (MariaDbConnection) new ConexionBD().getConexion();
        if (con == null) {
            System.out.println("No hay Conexion con la Base de Datos");
        }
   
    }
    
    public void reporteUnParametroVertical(String ubicacion,String Nombrepar,Integer Valor_p1){
        try{
            String master=System.getProperty("user.dir")+ubicacion;
            System.out.println("master "+master);
            if (master==null) {
                System.out.println("NO encuentro el archivo del reporte maestro");
                //System.exit(2);
            }
            JasperReport masterReport =null;
            try{
                masterReport=(JasperReport) JRLoader.loadObjectFromFile(master);
            }catch(JRException e){
                System.out.println("Error cargando el reporte maestro: "+e.getMessage());
                //System.exit(3);
            }
            //Map parametro= new HashMap();
            Map<String, Object> parametro = new HashMap<>();
            parametro.put(Nombrepar, Valor_p1);
            JasperPrint jasperPrint=JasperFillManager.fillReport(masterReport, parametro, con);
            JasperViewer jviewer = new JasperViewer(jasperPrint,false);
            jviewer.setTitle("Reporte");
            jviewer.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Iconos/logo1.png")));
            jviewer.setSize(610, 645);
            jviewer.setResizable(false);
            jviewer.setZoomRatio((float) 0.7);
            jviewer.setLocationRelativeTo(null);
            jviewer.setVisible(true);

        }catch(JRException j){
        Mensajes.error("Error:"+j.getMessage());
        }
    }
    
    public void reporteDosParametroVertical(String ubicacion,String Nombre1,Integer Valor1, String Nombre2, String Valor2 ){
        try{
            String master=System.getProperty("user.dir")+ubicacion;
            System.out.println("master "+master);
            if (master==null) {
                System.out.println("NO encuentro el archivo del reporte maestro");
                //System.exit(2);
            }
            JasperReport masterReport =null;
            try{
                masterReport=(JasperReport) JRLoader.loadObjectFromFile(master);
            }catch(JRException e){
                System.out.println("Error cargando el reporte maestro: "+e.getMessage());
            }
            Map<String, Object> parametro = new HashMap<>();
            parametro.put(Nombre1, Valor1);
            parametro.put(Nombre2, Valor2);
            JasperPrint jasperPrint=JasperFillManager.fillReport(masterReport, parametro, con);
            JasperViewer jviewer = new JasperViewer(jasperPrint,false);
            jviewer.setTitle("Reporte");
            jviewer.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Iconos/logo1.png")));
            //jviewer.setSize(610, 645);
            jviewer.setResizable(false);
            //jviewer.setZoomRatio((float) 0.7);
            jviewer.setLocationRelativeTo(null);
            jviewer.setVisible(true);

        }catch(JRException j){
        Mensajes.error("Error:"+j.getMessage());
        }
    }
    
    public void reporteDosParametroHorizontal(String ubicacion,String fechaD,Date Valor_p1,String fechaH,Date Valor_p2){
        try{
            String master=System.getProperty("user.dir")+ubicacion;
            System.out.println("master "+master);
            if (master==null) {
                System.out.println("NO encuentro el archivo del reporte maestro");
                //System.exit(2);
            }
            JasperReport masterReport =null;
            try{
                masterReport=(JasperReport) JRLoader.loadObjectFromFile(master);
            }catch(JRException e){
                System.out.println("Error cargando el reporte maestro: "+e.getMessage());
                //System.exit(3);
            }
            //Map parametro= new HashMap();
            Map<String, Object> parametro = new HashMap<>();
            parametro.put(fechaD, Valor_p1);
            parametro.put(fechaH, Valor_p2);
            JasperPrint jasperPrint=JasperFillManager.fillReport(masterReport, parametro, con);
            JasperViewer jviewer = new JasperViewer(jasperPrint,false);
            jviewer.setTitle("Reporte");
            jviewer.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Iconos/logo1.png")));
            jviewer.setSize(880, 670);
            jviewer.setResizable(false);
            jviewer.setZoomRatio((float) 0.7);
            jviewer.setLocationRelativeTo(null);
            jviewer.setVisible(true);

        }catch(JRException j){
        Mensajes.error("Error:"+j.getMessage());
        }
    }
    
    public void reporteTresParametroVertical(String ubicacion,String Nombre1,Integer Valor1, String Nombre2, int Valor2, String Nombre3, String Valor3 ){
        try{
            String master=System.getProperty("user.dir")+ubicacion;
            System.out.println("master "+master);
            if (master==null) {
                System.out.println("NO encuentro el archivo del reporte maestro");
                //System.exit(2);
            }
            JasperReport masterReport =null;
            try{
                masterReport=(JasperReport) JRLoader.loadObjectFromFile(master);
            }catch(JRException e){
                System.out.println("Error cargando el reporte maestro: "+e.getMessage());
                //System.exit(3);
            }
            //Map parametro= new HashMap();
            Map<String, Object> parametro = new HashMap<>();
            parametro.put(Nombre1, Valor1);
            parametro.put(Nombre2, Valor2);
            parametro.put(Nombre3, Valor3);
            JasperPrint jasperPrint=JasperFillManager.fillReport(masterReport, parametro, con);
            JasperViewer jviewer = new JasperViewer(jasperPrint,false);
            jviewer.setTitle("Reporte");
            jviewer.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Iconos/logo1.png")));
            //jviewer.setSize(610, 645);
            jviewer.setResizable(false);
            //jviewer.setZoomRatio((float) 0.7);
            jviewer.setLocationRelativeTo(null);
            jviewer.setVisible(true);

        }catch(JRException j){
        Mensajes.error("Error:"+j.getMessage());
        }
    }
    
    public void reporteCincoParametroVertical(String ubicacion,String Nombre1,Integer Valor1, String Nombre2, String Valor2, String Nombre3, String Valor3, String Nombre4, String Valor4, String Nombre5, int Valor5 ){
        try{
            String master=System.getProperty("user.dir")+ubicacion;
            System.out.println("master "+master);
            if (master==null) {
                System.out.println("NO encuentro el archivo del reporte maestro");
                //System.exit(2);
            }
            JasperReport masterReport =null;
            try{
                masterReport=(JasperReport) JRLoader.loadObjectFromFile(master);
            }catch(JRException e){
                System.out.println("Error cargando el reporte maestro: "+e.getMessage());
                //System.exit(3);
            }
            //Map parametro= new HashMap();
            Map<String, Object> parametro = new HashMap<>();
            parametro.put(Nombre1, Valor1);
            parametro.put(Nombre2, Valor2);
            parametro.put(Nombre3, Valor3);
            parametro.put(Nombre4, Valor4);
            parametro.put(Nombre5, Valor5);
            JasperPrint jasperPrint=JasperFillManager.fillReport(masterReport, parametro, con);
            JasperViewer jviewer = new JasperViewer(jasperPrint,false);
            jviewer.setTitle("Reporte");
            jviewer.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Iconos/logo1.png")));
            //jviewer.setSize(610, 645);
            jviewer.setResizable(false);
            //jviewer.setZoomRatio((float) 0.7);
            jviewer.setLocationRelativeTo(null);
            jviewer.setVisible(true);

        }catch(JRException j){
        Mensajes.error("Error:"+j.getMessage());
        }
    }
    
    
    public void StockCrGral(String ubicacion){
        try{
            String master=System.getProperty("user.dir")+ubicacion;
            System.out.println("master "+master);
            if (master==null) {
                //System.out.println("NO encuentro el archivo del reporte maestro");
                Mensajes.error("NO encuentro el archivo del reporte maestro");
                //System.exit(2);
            }
            JasperReport masterReport=null;
            try{
                masterReport=(JasperReport) JRLoader.loadObjectFromFile(master);
            }catch(JRException e){
                //System.out.println("Error cargando el reporte maestro: "+e.getMessage());
                Mensajes.error("Error cargando el reporte maestro: "+e.getMessage());
                //System.exit(3);
            }
            //Map parametro= new HashMap<>();
            HashMap<String, Object> parametro = new HashMap<>();
            JasperPrint jasperPrint=JasperFillManager.fillReport(masterReport, parametro,con);
            JasperViewer jviewer=new JasperViewer(jasperPrint,false);
            jviewer.setTitle("Reporte de Productos");
            jviewer.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Iconos/logo1.png")));
            jviewer.setSize(610, 645);
            jviewer.setResizable(false);
            jviewer.setZoomRatio((float) 0.7);
            jviewer.setLocationRelativeTo(null);
            jviewer.setVisible(true);
        }catch(Exception j){
            Mensajes.error("Reporte vacio.\nNo existen artículos con stock críticos");
        }
    }
    
    public void StockCrL(String ubicacion,String Nombrepar,Integer Valor_p1){
        try{
            String master=System.getProperty("user.dir")+ubicacion;
            System.out.println("master "+master);
            if (master==null) {
                System.out.println("NO encuentro el archivo del reporte maestro");
                //System.exit(2);
            }
            JasperReport masterReport =null;
            try{
                masterReport=(JasperReport) JRLoader.loadObjectFromFile(master);
            }catch(JRException e){
                System.out.println("Error cargando el reporte maestro: "+e.getMessage());
                //System.exit(3);
            }
            //Map parametro= new HashMap();
            Map<String, Object> parametro = new HashMap<>();
            parametro.put(Nombrepar, Valor_p1);
            JasperPrint jasperPrint=JasperFillManager.fillReport(masterReport, parametro, con);
            JasperViewer jviewer = new JasperViewer(jasperPrint,false);
            jviewer.setTitle("Reporte de Articulos con Stock Crítico");
            jviewer.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Iconos/logo1.png")));
            jviewer.setSize(610, 645);
            jviewer.setResizable(false);
            jviewer.setZoomRatio((float) 0.7);
            jviewer.setLocationRelativeTo(null);
            jviewer.setVisible(true);

        }catch(JRException j){
        Mensajes.error("Error:"+j.getMessage());
        }
    }
     
    public void Historial_de_compras(String ubicacion,String Nombrepar,Integer Valor_p1){
        try{
            String master=System.getProperty("user.dir")+ubicacion;
            System.out.println("master "+master);
            if (master==null) {
                System.out.println("NO encuentro el archivo del reporte maestro");
                //System.exit(2);
            }
            JasperReport masterReport =null;
            try{
                masterReport=(JasperReport) JRLoader.loadObjectFromFile(master);
            }catch(JRException e){
                System.out.println("Error cargando el reporte maestro: "+e.getMessage());
                //System.exit(3);
            }
            //Map parametro= new HashMap();
            Map<String, Object> parametro = new HashMap<>();
            parametro.put(Nombrepar, Valor_p1);
            JasperPrint jasperPrint=JasperFillManager.fillReport(masterReport, parametro, con);
            JasperViewer jviewer = new JasperViewer(jasperPrint,false);
            jviewer.setTitle("Historial de Compras por Artículos");
            jviewer.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Iconos/logo1.png")));
            jviewer.setSize(930, 550);
            jviewer.setResizable(false);
            jviewer.setZoomRatio((float) 0.7);
            jviewer.setLocationRelativeTo(null);
            jviewer.setVisible(true);

        }catch(JRException j){
        Mensajes.error("Error:"+j.getMessage());
        }
    }
    /*public void BoletaCredito(String ubicacion,String Nombrepar,Integer Valor_p1){
        try{
            String master=System.getProperty("user.dir")+ubicacion;
            System.out.println("master "+master);
            if (master==null) {
                System.out.println("NO encuentro el archivo del reporte maestro");
                //System.exit(2);
            }
            JasperReport masterReport =null;
            try{
                masterReport=(JasperReport) JRLoader.loadObjectFromFile(master);
            }catch(JRException e){
                System.out.println("Error cargando el reporte maestro: "+e.getMessage());
                //System.exit(3);
            }
            //Map parametro= new HashMap();
            Map<String, Object> parametro = new HashMap<>();
            parametro.put(Nombrepar, Valor_p1);
            JasperPrint jasperPrint=JasperFillManager.fillReport(masterReport, parametro, con);
            JasperViewer jviewer = new JasperViewer(jasperPrint,false);
            jviewer.setTitle("Boleta Crédito");
            jviewer.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Iconos/logo1.png")));
            jviewer.setSize(610, 430);
            jviewer.setResizable(false);
            jviewer.setZoomRatio((float) 0.7);
            jviewer.setLocationRelativeTo(dlgVentas.dlgFinFacturaX);
            jviewer.setVisible(true);

        }catch(JRException j){
        Mensajes.error("Error:"+j.getMessage());
        }
    }   
    */
    public void BoletaCreditoRE(String ubicacion,String Nombrepar,Integer Valor_p1){
        try{
            String master=System.getProperty("user.dir")+ubicacion;
            System.out.println("master "+master);
            if (master==null) {
                System.out.println("NO encuentro el archivo del reporte maestro");
                //System.exit(2);
            }
            JasperReport masterReport =null;
            try{
                masterReport=(JasperReport) JRLoader.loadObjectFromFile(master);
            }catch(JRException e){
                System.out.println("Error cargando el reporte maestro: "+e.getMessage());
                //System.exit(3);
            }
            //Map parametro= new HashMap();
            Map<String, Object> parametro = new HashMap<>();
            parametro.put(Nombrepar, Valor_p1);
            JasperPrint jasperPrint=JasperFillManager.fillReport(masterReport, parametro, con);
            JasperViewer jviewer = new JasperViewer(jasperPrint,false);
            jviewer.setTitle("Boleta Crédito - Re-Impresión");
            jviewer.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Iconos/logo1.png")));
            jviewer.setSize(610, 430);
            jviewer.setResizable(false);
            jviewer.setZoomRatio((float) 0.7);
            jviewer.setLocationRelativeTo(null);
            jviewer.setVisible(true);

        }catch(JRException j){
        Mensajes.error("Error:"+j.getMessage());
        }
    }   
    
    public void cerrar(){
    try{
        con.close();
    }catch(SQLException ex){
        ex.getMessage();
    }
    }
    
}
