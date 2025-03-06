package sk.train;

import java.math.BigInteger;

/**
 * Klasse für verschiedene Varianten der Fakultätsfunktion
 *
 * es werden 3 Varianten angeboten
 *
 * @author uthor  Stephan Karrer
 * @version 1.0
 */
public class Fakultaet {
    private Fakultaet() {
       super();
    }

    /**
     * Fakultät anhand int
     * Berechnung anhand for-Schleife
     * @param n  muss positiv sein
     * @return Fakultät für kleine n
     * @see java.util.Date
     */
    public static int fak(int n) {
        if (n <= 0) {
            throw new ArithmeticException("n > 0 is required");
        }
        int erg = 1;
        for (int i = 2; i <= n; ++i) {
            erg = Math.multiplyExact(erg, i);
        }
        return erg;
    }

    public static int fakrek(int n) {
        if (n <= 0) {
            throw new ArithmeticException("n > 0 is required");
        }
        if (n == 1) return 1;
        else return Math.multiplyExact(n , fakrek(--n));
    }

    public static BigInteger fakBig(BigInteger n) {
        if (n.compareTo(BigInteger.ZERO) <= 0) {
            throw new ArithmeticException("n > 0 is required");
        }
        BigInteger erg = BigInteger.ONE;
        for (BigInteger i = new BigInteger("2"); i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
            erg = erg.multiply(i);
        }
        return (erg);
    }
}
