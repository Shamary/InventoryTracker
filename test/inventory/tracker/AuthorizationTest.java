/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.tracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author shamary williams
 */
public class AuthorizationTest {
    
    public AuthorizationTest() {
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
     * Test of init method, of class Authorization.
     */
    /*
    @Test
    public void testInit() {
        System.out.println("init");
        Authorization.init();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of authorize method, of class Authorization.
     */
    @Test
    public void testAuthorize() {
        System.out.println("authorize");
        String uid = "6";
        String password = "12345";
        boolean expResult = true;
        boolean result = Authorization.authorize(uid, password);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAuthorize2(){
        System.out.println("authorize");
        String uid = "6";
        String password = "abcde";
        boolean expResult = false;
        boolean result = Authorization.authorize(uid, password);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAuthorize3() {
        System.out.println("authorize");
        String uid = "dfg356";
        String password = "12345";
        boolean expResult = false;
        boolean result = Authorization.authorize(uid, password);
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void testAuthorize4() {
        System.out.println("authorize");
        String uid = "645dd";
        String password = "12d3r4";
        boolean expResult = false;
        boolean result = Authorization.authorize(uid, password);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAuthorize5() {
        System.out.println("authorize");
        String uid = "";
        String password = "";
        boolean expResult = false;
        boolean result = Authorization.authorize(uid, password);
        assertEquals(expResult, result);
    }
}
