package Componentes;

public class Redondeo {
    
    public static double redondearD(double numero)
    {
        return Math.rint(numero*100)/100;
    }
    public static int redondearI(int numero)
    {
        return (int) (Math.ceil(numero*100)/100);
    }
    
}
