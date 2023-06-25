package Datos;

import Modelo.DetallePago;
import java.util.ArrayList;

public class ArregloPago {
    
    public ArrayList <DetallePago> dc; //Arreglo de objetos para la linea de saldas de compra
    
    //Constructor
    public ArregloPago()
    {
        dc = new ArrayList();
    }
    
    //Agregamos una fila al detalle
    public void agregar(DetallePago nuevo)
    {
        dc.add(nuevo);
    }
    
    //Obtenemos una fila del detalle
    public DetallePago getFila(int i)
    {
        return dc.get(i);
    }
    
    //Remplazamos la informacion de la linea
    public void update(int i, DetallePago act)
    {
        dc.set(i, act);
    }
    
    //Obtenemos el numero de filas registradas
    public int numFilas()
    {
        return dc.size();
    }
    
    //Elimina una fila del detalle
    public void eliminar(int i)
    {
        dc.remove(i);
    }        
    
    //Elimina toda la fila del detalle
    public void vaciar()
    {
        for(int i=0;i<numFilas();i++)
        {
            //dc.remove(0);
            dc.clear();
        }
    }
    
    public int busca(int codigo)
    {
        for(int i=0;i<numFilas();i++)
        {
            if(codigo == getFila(i).getCodCompra())
                return i; //Retorna indice
        }
        return -1; //No se encontro objeto
    }
    
}
