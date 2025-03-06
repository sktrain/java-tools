package sk.train;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

class SampleTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("einmalige Initialisierung");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("einmaliges Aufräumen");
    }

    @BeforeEach
    void setUp() {
        System.out.println("saubere Ausgangssituation");
    }

    @AfterEach
    void tearDown() {
        System.out.println("jedesmal Aufräumen");
    }

    @Test
    void demoTest1() {
        System.out.println("demoTest1");
    }

    @Test
    void demoTest2() {
        System.out.println("demoTest2");
        assertNotNull( null, "Fehler");
    }

    @Test
    void demoFail() {
        //fail("Not yet implemented");
    }
}