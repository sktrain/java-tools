package sk.train;

public class Arithmetic {

    //Konstruktor und Instanzmethoden nur zu Demozwecken

    public int add(int a, int b){
        return Math.addExact(a, b);
    }

    public int multiply(int a, int b){
        return Math.multiplyExact(a, b);
    }

    public int fakultaet(int n){
        if (n <= 0) {
            throw new ArithmeticException("Fakulaet nicht definiert für n<=0");
        }
        else {
           int result = 1;
           for (int i = 2; i <= n; ++i){
               result = result * i;
           }
           return result;
        }
    }

}
