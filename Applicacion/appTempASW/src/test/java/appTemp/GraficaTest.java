package appTemp;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraficaTest {
    
    public GraficaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addData method, of class Grafica.
     */
    @Test
    public void testAddData() {/*
        System.out.println("addData");
        Medicion medicion = null;
        Grafica instance = null;
        instance.addData(medicion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of getInstance method, of class Grafica.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Grafica result = Grafica.getInstance();
        assertNotNull( result);
        
    }
    
}
