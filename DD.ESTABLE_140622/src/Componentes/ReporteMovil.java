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
public class ReporteMovil {
    
    MariaDbConnection con = (MariaDbConnection) new ConexionBD().getConexionMovil();
    
    public ReporteMovil(){
        con = (MariaDbConnection) new ConexionBD().getConexionMovil();
        if (con == null) {
            System.out.println("No hay Conexion con la Base de Datos");
        }
    }
    
    
    public void ListaProductosMovilesV(String ubicacion){
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
            //jviewer.setSize(610, 645);
            jviewer.setResizable(false);
            //jviewer.setZoomRatio((float) 0.7);
            jviewer.setLocationRelativeTo(null);
            jviewer.setVisible(true);
        }catch(Exception j){
            Mensajes.error("Error de Reporte:\n"+j.getMessage());
        }
    }
    public void ListaProductosMovilesH(String ubicacion){
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
            //jviewer.setSize(610, 645);
            jviewer.setResizable(false);
            jviewer.setZoomRatio((float) 0.7);
            jviewer.setLocationRelativeTo(null);
            jviewer.setVisible(true);
        }catch(Exception j){
            Mensajes.error("Error de Reporte:\n"+j.getMessage());
        }
    }
    
    public void ListaClientesMoviles(String ubicacion){
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
            jviewer.setTitle("Reporte de Clientes");
            jviewer.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Iconos/logo1.png")));
            //jviewer.setSize(610, 645);
            jviewer.setResizable(false);
           // jviewer.setZoomRatio((float) 0.7);
            jviewer.setLocationRelativeTo(null);
            jviewer.setVisible(true);
        }catch(Exception j){
            Mensajes.error("Error de Reporte:\n"+j.getMessage());
        }
    }
    
    public void ReportesAnulados(String ubicacion){
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
            jviewer.setTitle("Repartos anulados");
            jviewer.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Iconos/logo1.png")));
            //jviewer.setSize(610, 645);
            jviewer.setResizable(false);
            jviewer.setZoomRatio((float) 0.7);
            jviewer.setLocationRelativeTo(null);
            jviewer.setVisible(true);
        }catch(Exception j){
            Mensajes.error("Error de Reporte:\n"+j.getMessage());
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
