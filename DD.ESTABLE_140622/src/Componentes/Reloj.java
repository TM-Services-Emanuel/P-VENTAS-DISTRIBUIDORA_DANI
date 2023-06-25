package Componentes;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

public class Reloj extends Thread{
	//JLabel lblReloj;
        JLabel lblFecha;
	
	// constructor
	public Reloj(/*JLabel lblReloj,*/ JLabel lblFecha){
		//this.lblReloj= lblReloj;
                this.lblFecha = lblFecha;
	}// fin del constructor
	
	// funcionamiento del reloj
        @Override
    	public void run() {
    		while(true)	{
    			Date hoy= new Date();
    	SimpleDateFormat sdf= new SimpleDateFormat("H:mm:ss");
        //SimpleDateFormat fec = new SimpleDateFormat(" EEEEEEEEE',' dd 'de' MMMMM 'de' yyyy");
        SimpleDateFormat fec = new SimpleDateFormat("dd 'de' MMMMM 'de' yyyy");
                        lblFecha.setText(fec.format(hoy)+" | "+sdf.format(hoy));
 	 	        //lblReloj.setText("Hora: "+sdf.format(hoy));

			// demora de 1 segundo            
	        try { sleep(1000); } catch (Exception ex) {}
    	}// fin del while
    }// fin del run


}