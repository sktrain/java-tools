package sk.train;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import  static org.junit.jupiter.api.Assertions.*;

class ArithmeticTest {

    private Arithmetic ar = new Arithmetic();

    @BeforeEach
    void setUp() {
        ar = new Arithmetic();
    }

    @ParameterizedTest
    @CsvSource({"1,1,2", "5,5,10", "100,100,200"})
    void add(int in1, int in2, int erg) {
        //System.out.println(ar.add(1, 1));
        assertEquals(erg, ar.add(in1, in2), "fatal Error");

    }

    @Test
    void multiply() {
        System.out.println(ar.multiply(1, 1));
        assertAll(() -> { if (2 == ar.multiply(1,1)) throw new ArithmeticException();},
                () -> assertThrows(ArithmeticException.class,() -> ar.multiply(100_000, 100_000), "overflow nicht erkannt"),
                () -> System.out.println("Hallo"));
        //assertEquals(2, ar.multiply(1, 1), "fatal Error");
       // System.out.println("Test");
       // assertThrows(ArithmeticException.class,() -> ar.multiply(100_000, 100_000), "overflow nicht erkannt");
    }

    @Test
    void fakultaet() {
        System.out.println(ar.fakultaet(5));
        assertEquals(120, ar.fakultaet(5), "fatal Error");
    }

    @Test
    void TestDouble(){
        //assertEquals(1.0 , 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1, 0.0, "Delta ist nicht erfüllt");
    }
}