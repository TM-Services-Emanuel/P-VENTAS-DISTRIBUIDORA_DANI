package Datos;

import Componentes.ConexionBD;
import Componentes.Mensajes;
import Componentes.Operacion;
import Componentes.SeleccionarImagen;
import Componentes.generarCodigos;
import IU.dlgVentas;
import Modelo.Vendedor;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GestionarVendedor {

    public static String getCodigo() {
        String cod = generarCodigos.getCodigo("SELECT MAX(ven_codigo) FROM vendedor");
        return cod;
    }

    public static String addVendedor(Vendedor v, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("INSERT INTO vendedor VALUES (");
        sql.append(getCodigo());
        sql.append(",");
        sql.append(v.getIdfuncion());
        sql.append(",");
        sql.append(v.getIdmovil());
        sql.append(",'");
        sql.append(v.getNombreV());
        sql.append("','");
        sql.append(v.getDireccion());
        sql.append("','");
        sql.append(v.getTelefono());
        sql.append("','");
        sql.append(v.getCelular());
        sql.append("','");
        sql.append(v.getSueldo());
        sql.append("',");
        sql.append(v.getComision());
        sql.append(",'");
        sql.append(v.getObs());
        sql.append("','S','");
        sql.append(usuario);
        sql.append("')");
//        String sql = "INSERT INTO vendedor VALUES ("
//                + getCodigo() + ",'"
//                + v.getNombreV() + "','" 
//                + v.getDireccion() + "','" 
//                + v.getTelefono() + "','" 
//                + v.getCelular() + "',"
//                + v.getSueldo() + "," 
//                + v.getCodProv() + "," 
//                + v.getCodZona() + ","
//                + v.getComision() + ",'" 
//                + v.getEmail() + "','" 
//                + v.getObs() + "','S')";
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static void addImagen(String cod) {
        try {

            String sql = "INSERT INTO imagenVendedor (img_vendedor, img_imagen) VALUES (?, ?)";

            PreparedStatement ps = new ConexionBD().getConexion().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(cod));
            ps.setBinaryStream(2, SeleccionarImagen.fis, SeleccionarImagen.longitudBytes);
            ps.execute();
            ps.close();
            System.out.println("Imagen Guardada");
        } catch (SQLException e) {
            Mensajes.error("No se pudo guardarImagen " + e.toString());
        }
    }

    public static void actImagen(String cod) {
        try {
            String sql = "UPDATE imagenVendedor SET img_imagen=? WHERE img_vendedor=?";
            PreparedStatement ps = new ConexionBD().getConexion().prepareStatement(sql);
            ps.setInt(2, Integer.parseInt(cod));
            ps.setBinaryStream(1, SeleccionarImagen.fis, SeleccionarImagen.longitudBytes);
            ps.executeUpdate();
            ps.close();
            System.out.println("Imagen actualizada");
        } catch (SQLException e) {
            Mensajes.error("No se pudo actualizar imagen " + e.getMessage());
        }
    }

    public static void busImagen(String cod, JLabel lblImagen) {
        String sql = new StringBuffer("SELECT img_imagen FROM imagenVendedor WHERE img_vendedor = ")
                .append(cod).toString();
//        String sql="SELECT img_imagen FROM \"imagenArticulo\" WHERE img_articulo = "+cod;
        ImageIcon foto;
        InputStream is;
        try {
            ResultSet rs;
            PreparedStatement sentencia = new ConexionBD().getConexion().prepareStatement(sql);
            rs = sentencia.executeQuery();
            while (rs.next()) {
                is = rs.getBinaryStream(1);

                BufferedImage bi = ImageIO.read(is);
                foto = new ImageIcon(bi);

                Image img = foto.getImage();
                Image newimg = img.getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), java.awt.Image.SCALE_SMOOTH);

                ImageIcon newicon = new ImageIcon(newimg);

                lblImagen.setIcon(newicon);
            }
        } catch (Exception ex) {
            System.out.println("No se pudo cargar imagen " + ex.toString());
        }
    }

    public static String actVendedor(Vendedor v, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE vendedor SET idfuncion=");
        sql.append(v.getIdfuncion());
        sql.append(",idmovil=");
        sql.append(v.getIdmovil());
        sql.append(",ven_nombre='");
        sql.append(v.getNombreV());
        sql.append("',ven_direccion='");
        sql.append(v.getDireccion());
        sql.append("',ven_fecha_ingreso='");
        sql.append(v.getTelefono());
        sql.append("',ven_celular='");
        sql.append(v.getCelular());
        sql.append("',ven_sueldo='");
        sql.append(v.getSueldo());
        sql.append("',ven_comision=");
        sql.append(v.getComision());
        sql.append(",ven_observacion='");
        sql.append(v.getObs());
        sql.append("',usu='");
        sql.append(usuario);
        sql.append("' WHERE ven_codigo = ");
        sql.append(v.getCodVe());
        msg = Operacion.exeOperacion(sql.toString());
//        String sql = "UPDATE vendedor SET ven_nombre='" + v.getNombreV()
//                + "',ven_direccion='" + v.getDireccion()
//                + "',ven_telefono='" + v.getTelefono()
//                + "',ven_celular='" + v.getCelular()
//                + "',ven_sueldo=" + v.getSueldo()
//                + ",ven_provincia=" + v.getCodProv()
//                + ",ven_zona=" + v.getCodZona()
//                + ",ven_comision=" + v.getComision()
//                + ",ven_email = '" + v.getEmail()
//                + "',ven_observacion='" + v.getObs()
//                + "' WHERE ven_codigo = " + v.getCodVe() + "";
//        msg = Operacion.exeOperacion(sql);
        return msg;
    }

    public static Vendedor busVendedor(String cod) {
        Vendedor v = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM vendedor WHERE ven_codigo=");
        sql.append(cod);
        sql.append("");
//        String sql = "SELECT * FROM vendedor WHERE ven_codigo = " + cod + "";
        Object[] filaObt = Operacion.getFila(sql.toString());
        if (filaObt != null) {
            v = new Vendedor();
            v.setCodVe(Integer.parseInt(filaObt[0].toString()));
            v.setIdfuncion(Integer.parseInt(filaObt[1].toString()));
            v.setIdmovil(Integer.parseInt(filaObt[2].toString()));
            v.setNombreV(filaObt[3].toString());
            v.setDireccion(filaObt[4].toString());
            v.setTelefono(filaObt[5].toString());
            v.setCelular(filaObt[6].toString());
            v.setSueldo((filaObt[7].toString()));
            v.setComision(Double.parseDouble(filaObt[8].toString()));
            v.setObs(filaObt[9].toString());
            } else {
            System.out.println("No encontrado");
        }
        return v;
    }
    public static Vendedor busVendedor2(String cod) {
        Vendedor v = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM vendedor WHERE ven_codigo =");
        sql.append(cod);
        sql.append("");
//        String sql = "SELECT * FROM vendedor WHERE ven_codigo = " + cod + "";
        Object[] filaObt = Operacion.getFila(sql.toString());
        if (filaObt != null) {
            v = new Vendedor();
            v.setCodVe(Integer.parseInt(filaObt[0].toString()));
            System.out.println(Integer.parseInt(filaObt[0].toString()));
            v.setNombreV(filaObt[3].toString());
            System.out.println((filaObt[3].toString()));
        } else {
            System.out.println("No encontrado");
        }
        return v;
    }
    public static Vendedor busVendedorFactura(String cod) {
        Vendedor v = null;
        StringBuilder sql = new StringBuilder("SELECT * FROM v_usuario WHERE CodVend =");
        sql.append(cod);
        sql.append(" And CodPerfil=2");
//        String sql = "SELECT * FROM vendedor WHERE ven_codigo = " + cod + "";
        Object[] filaObt = Operacion.getFila(sql.toString());
        if (filaObt != null) {
            v = new Vendedor();
            v.setNombreV(filaObt[2].toString());
            
        } else {
            System.out.println("No encontrado");
            Mensajes.error("CODIGO EQUIVOCADO O NO POSEE PERFIL PARA VENTA");
            dlgVentas.btnConfirmar.setEnabled(false);
            dlgVentas.txtCodVendedor.requestFocus();
            dlgVentas.txtCodVendedor.selectAll();
        }
        return v;
    }

    public static String delVendedor(String cod, String usuario) {
        String msg;
        StringBuilder sql = new StringBuilder("UPDATE vendedor SET ven_indicador='N', usu='");
        sql.append(usuario);
        sql.append("' WHERE ven_codigo =");
        sql.append(cod);
        sql.append("");
//        String sql = "UPDATE vendedor SET ven_indicador='N' WHERE ven_codigo = " + cod + "";
        msg = Operacion.exeOperacion(sql.toString());
        return msg;
    }

    public static List listVendedor(String cad) {
        StringBuilder sql = new StringBuilder("SELECT * FROM v_vendedores WHERE ven_indicador='S' ORDER BY ");
        sql.append(cad);
        sql.append("");
//        String sql = "SELECT * FROM vendedor WHERE ven_indicador='S' ORDER BY " + cad + "";
        return Operacion.getTabla(sql.toString());
    }

    public static List filNombre(String cad) {
        StringBuilder sql = new StringBuilder("SELECT * FROM v_vendedores WHERE ven_indicador='S' AND ven_nombre LIKE '%");
        sql.append(cad);
        sql.append("%'");
//        String sql = "SELECT * FROM vendedor WHERE ven_indicador='S' AND ven_nombre ILIKE '" + cad + "%'";
        return Operacion.getTabla(sql.toString());
    }

    public static List filDireccion(String cad) {
        StringBuilder sql = new StringBuilder("SELECT * FROM vendedor WHERE ven_indicador='S' AND ven_direccion LIKE '");
        sql.append(cad);
        sql.append("%'");
//        String sql = "SELECT * FROM vendedor WHERE ven_indicador='S' AND ven_direccion ILIKE '" + cad + "%'";
        return Operacion.getTabla(sql.toString());
    }

    public static List filTelefono(String cad) {
        StringBuilder sql = new StringBuilder("SELECT * FROM vendedor WHERE ven_indicador='S' AND ven_telefono LIKE '");
        sql.append(cad);
        sql.append("%'");
//        String sql = "SELECT * FROM vendedor WHERE ven_indicador='S' AND ven_telefono ILIKE '" + cad + "%'";
        return Operacion.getTabla(sql.toString());
    }
}
