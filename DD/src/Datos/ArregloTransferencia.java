package Datos;

import Modelo.DetalleTransferencia;
import java.util.ArrayList;

public class ArregloTransferencia {
    
    private final ArrayList<DetalleTransferencia> df; //Arreglo de objetos para las lineas de la factura
    
    //Constructor
    public ArregloTransferencia()
    {
        df = new ArrayList(); //Creamos el objeto
    }
    //Agregamos una nueva fila al detalle
    public void agregar(DetalleTransferencia nuevo)
    {
        df.add(nuevo);
    }
    
    //Obtenemo una fila del detalle
    public DetalleTransferencia getFila(int i)
    {
        return df.get(i);
    }
    
    //Remplazamos la informacion de la linea
    public void update(int i, DetalleTransferencia act)
    {
        df.set(i, act);
    }
    
    //Elimina todas la lineas del detalle
    public void vaciar()
    {
        for(int i=0;i<numFilas();i++)
        {
            //df.remove(0);
            df.clear();
        }
    }
    
    //Elimina una linea del detalle
    public void eliminar(int i)
    {
        df.remove(i);
    }
    
    //Obtiene el numero de filas registradas
    public int numFilas()
    {
        return df.size();
    }
    
    //Buscar y validar que el objeto no se repita
    public int busca(int codigo)
    {
        for(int i=0;i<numFilas();i++)
        {
            if(codigo == getFila(i).getCodArticulo())
                return i; //Retorna indice
        }
        return -1; //No se encontro objeto
    }
    
}
