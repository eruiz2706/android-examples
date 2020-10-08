package eduardo.calculadora;

/**
 * Created by Eduardo on 06/03/2017.
 */

public class Aritmetica {

    public double suma(double numero1,double numero2){
        double n1   =numero1;
        double n2   =numero2;

        return n1+n2;
    }
    public double suma(String numero1,String numero2){
        double n1   =Double.parseDouble(numero1);
        double n2   =Double.parseDouble(numero2);

        return n1+n2;
    }

    public double resta(double numero1,double numero2){
        double n1   =numero1;
        double n2   =numero2;

        return n1-n2;
    }

    public double resta(String numero1,String numero2){
        double n1   =Double.parseDouble(numero1);
        double n2   =Double.parseDouble(numero2);

        return n1-n2;
    }

    public double multiplicacion(double numero1,double numero2){
        double n1   =numero1;
        double n2   =numero2;

        return n1*n2;
    }

    public double multiplicacion(String numero1,String numero2){
        double n1   =Double.parseDouble(numero1);
        double n2   =Double.parseDouble(numero2);

        return n1*n2;
    }

    public double division(double numero1,double numero2){
        double n1   =numero1;
        double n2   =numero2;

        return n1*n2;
    }

    public double division(String numero1,String numero2){
        double n1   =Double.parseDouble(numero1);
        double n2   =Double.parseDouble(numero2);

        return n1/n2;
    }

    public double invertir(double numero1){
        double n1   =numero1;

        return n1*-1;
    }

    public double invertir(String numero1){
        double n1   =Double.parseDouble(numero1);

        return n1*-1;
    }

}
