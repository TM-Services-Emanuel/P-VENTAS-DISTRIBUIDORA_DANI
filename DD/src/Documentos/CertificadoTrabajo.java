
package Documentos;

import Componentes.Mensajes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CertificadoTrabajo {
    
    private final XWPFDocument document=new XWPFDocument();
    
    public void crear(String Empleado, String ci, String funcion, String antiguedad, String sueldo, String letra, String dia, String mes, String ano){
        javax.swing.filechooser.FileNameExtensionFilter filtroWord=new FileNameExtensionFilter("Microsoft Word", "docx");
        final JFileChooser miArchivo=new JFileChooser();
        miArchivo.setFileFilter(filtroWord);
        int aceptar=miArchivo.showSaveDialog(null);
        miArchivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(aceptar==JFileChooser.APPROVE_OPTION){
            File fileWord=miArchivo.getSelectedFile();
            String nombre=fileWord.getName();
            if(nombre.indexOf('.')==-1){
                nombre+=".docx";
                fileWord=new File(fileWord.getParentFile(), nombre);
            }
            try {
                /*========= INSERTAR UNA IMAGEN ======= */
                XWPFParagraph paragraphImagen=document.createParagraph();
                paragraphImagen.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun Imagen = paragraphImagen.createRun();
                Imagen.setSubscript(VerticalAlign.BASELINE);
                //InputStream pic = new FileInputStream("D:\\Chasqui\\chasqui.jpg");
                InputStream pic = new FileInputStream("C:\\P-VENTAS\\Logo_DD.PNG");
                //Imagen.addPicture(pic, document.PICTURE_TYPE_JPEG, "1", Units.toEMU(180), Units.toEMU(35));
                Imagen.addPicture(pic, document.PICTURE_TYPE_PNG, "0", Units.toEMU(240), Units.toEMU(65));
                
                FileOutputStream output=new FileOutputStream(fileWord);
                //FileOutputStream output=new FileOutputStream("documento de prueba.docx");

                /*========= TITULO ======= */
                XWPFParagraph paragraphTitulo=document.createParagraph();
                XWPFRun runTitulo=paragraphTitulo.createRun();
                paragraphTitulo.setAlignment(ParagraphAlignment.CENTER);
                runTitulo.setBold(true);
                runTitulo.setFontSize(16);
                runTitulo.setFontFamily ("Times New Roman");
                //runTitulo.setUnderline(UnderlinePatterns.WORDS);
                //runTitulo.setColor("2f66f2");

                /*========= LETRA NORMAL - JUSTIFICADO ======= */
                XWPFParagraph paragraphTexto=document.createParagraph();
                paragraphTexto.setAlignment(ParagraphAlignment.LOW_KASHIDA);
                XWPFRun runTexto=paragraphTexto.createRun();
                runTexto.setFontSize(12);
                runTexto.setFontFamily("Times New Roman");
                
                /*========= LETRA NORMAL - JUSTIFICADO ======= */
                XWPFParagraph paragraphTexto2=document.createParagraph();
                paragraphTexto2.setAlignment(ParagraphAlignment.LOW_KASHIDA);
                XWPFRun runTexto2=paragraphTexto2.createRun();
                runTexto2.setFontSize(12);
                runTexto2.setFontFamily("Times New Roman");
                
                /*========= LETRA NORMAL - Derecha ======= */
                XWPFParagraph paragraphTexto5=document.createParagraph();
                paragraphTexto5.setAlignment(ParagraphAlignment.RIGHT);
                XWPFRun runTexto5=paragraphTexto5.createRun();
                runTexto5.setFontSize(12);
                runTexto5.setFontFamily("Times New Roman");
                
                /*========= NEGRITA ======= */
                XWPFParagraph paragraphTextoNegrita=document.createParagraph();
                paragraphTextoNegrita.setAlignment(ParagraphAlignment.LOW_KASHIDA);
                XWPFRun runTextoNegrita=paragraphTextoNegrita.createRun();
                runTextoNegrita.setBold(true);
                runTextoNegrita.setFontSize(12);
                runTextoNegrita.setFontFamily("Times New Roman");
                
                
                /*========= LETRA NORMAL - JUSTIFICADO ======= */
                XWPFParagraph paragraphTexto3=document.createParagraph();
                paragraphTexto3.setAlignment(ParagraphAlignment.LOW_KASHIDA);
                XWPFRun runTexto3=paragraphTexto3.createRun();
                runTexto3.setFontSize(12);
                runTexto3.setFontFamily("Times New Roman");
                
                /*========= LETRA NORMAL - IZQUIERDA ======= */
                XWPFParagraph paragraphTexto4=document.createParagraph();
                paragraphTexto4.setAlignment(ParagraphAlignment.LOW_KASHIDA);
                XWPFRun runTexto4=paragraphTexto4.createRun();
                runTexto4.setFontSize(12);
                runTexto4.setFontFamily("Times New Roman");
                
                
                runTitulo.addCarriageReturn();
                runTitulo.setText("CERTIFICADO DE TRABAJO");
                
                /*runTexto.setText("Por la presente, dejo Constancia de que "+vendedor+" ");
                runTexto.setText("con Cedula de Identidad Número "+CI+" ");
                runTexto.setText("se encuentra trabajando dentro de la Empresa en el cargo de VENDEDOR con una antiguedad de "+antiguedad+" ");
                runTexto.setText("percibiendo una remuneración mensual de "+sueldo+(LETRA)".");
                runTexto.setText("Se expide este presente certificado a pedido de la persona interesada para los fines que hubiere lugar en la ciudad de CORONEL OVIEDO a los ");
                runTexto.setText(dia+"dias/s del mes de "+mes+" del "+anho);*/
                
                runTexto.setText("Por la presente, dejo Constancia de que "+Empleado+" ");
                //runTexto.setText("EMANUEL MIGUEL SOSA VERA ");
                runTexto.setText("con Cedula de Identidad Número "+ci+" ");
                //runTexto.setText("4744832 ");
                runTexto.setText("se encuentra trabajando dentro de la Empresa en el cargo de "+funcion+" con una antigüedad de "+antiguedad+" ");
                runTexto.setText("percibiendo una remuneración mensual de Gs.");
                runTexto.setText(sueldo+" ("+letra+").");
                runTexto.addCarriageReturn();
                runTexto2.setText("Se expide este presente certificado a pedido de la persona interesada para los fines que hubiere lugar en la ciudad de CORONEL OVIEDO a los ");
                runTexto2.setText(dia+" día/s del mes de "+mes+" del "+ano+".");
                runTexto2.addCarriageReturn();
                runTexto2.addBreak();
                runTexto2.addBreak();
                runTexto2.addBreak();
                runTexto2.addBreak();
                runTexto4.setText("                                                                           ..........................................................");
                runTexto4.addBreak();
                runTexto4.setText("                                                                                         La Administración");
 
                /*
                for(int i=0;i<10;i++){
                    XWPFParagraph paragraphLista=document.createParagraph();
                    XWPFRun runLista=paragraphLista.createRun();
                    runLista.setText("Item "+i);
                    paragraphLista.setNumID(BigInteger.ONE);
                }
                */

                document.write(output);
                output.close();
            }
            catch (IOException | InvalidFormatException e) {
                Mensajes.Alerta(e.getMessage());
            }
            
            finally{
                try {
                    if(System.getProperty("os.name").equals("Linux")){
                        Runtime.getRuntime().exec("libreoffice"+fileWord.getAbsolutePath());
                    }
                    else{
                        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+fileWord.getAbsolutePath());
                    }
                }
                catch (IOException ex) {
                    Logger.getLogger(CertificadoTrabajo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
