import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    /* Do not change this to be private. For silly testing reasons it is public. */
    public Calculator tester;

    /**
     * setUp() performs setup work for your Calculator.  In short, we 
     * initialize the appropriate Calculator for you to work with.
     * By default, we have set up the Staff Calculator for you to test your 
     * tests.  To use your unit tests for your own implementation, comment 
     * out the StaffCalculator() line and uncomment the Calculator() line.
     **/
    @Before
    public void setUp() {
        //tester = new StaffCalculator(); // Comment me out to test your Calculator
        tester = new Calculator();   // Un-comment me to test your Calculator
    }

    // TASK 1: WRITE JUNIT TESTS
    @Test
    public void testStaffCalculatorAdd1(){
        int a = tester.add(100,101);
        assertEquals(201,a);
    }

    @Test
    public void testStaffCalculatorAdd2(){
        int a = tester.add(100,-100);
        assertEquals(0,a);
    }

    @Test
    public void testStaffCalculatorAdd3(){
        int a = tester.add(2,2);
        assertEquals(4,a);
    }

    @Test
    public void testStaffCalculatorMultiply1(){
        int a = tester.multiply(6,7);
        assertEquals(42,a);
    }

    @Test
    public void testStaffCalculatorMultiply2(){
        int a = tester.multiply(-1,-5);
        assertEquals(5,a);
    }

    @Test
    public void testStaffCalculatorMultiply3(){
        int a = tester.multiply(-2,5);
        assertEquals(-10,a);
    }



    /* Run the unit tests in this file. */
    public static void main(String... args) {
        jh61b.junit.textui.runClasses(CalculatorTest.class);
    }       
}