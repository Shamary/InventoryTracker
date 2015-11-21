/*
 * Class runs unit tests on inventory components
 */
package inventory.tracker;

import java.util.ArrayList;
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
public class InventoryTest {
    
    private static final String p_id="T0",pname="test0";//,
                          //l_id="8A2B55",lname="L-Arm";
    
    private static ArrayList<String> ids=new ArrayList<>();////items id
    
    
    public InventoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Inventory.init("6","12345");
        
        Inventory.UpdateInvent(new ItemInfo(p_id,pname,80));///add item in inventory
        
        ids.add(p_id);
    }
    
    @AfterClass
    public static void tearDownClass() {////cleanup added data
        
        for(String id: ids)
        {
            Inventory.delete(id);
        }
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of UpdateInvent method, of class Inventory.
     */
    @Test
    public void testUpdateInvent() {
        System.out.println("UpdateInvent");
        ItemInfo item = new ItemInfo(p_id,pname,30);
        boolean expResult = false;
        boolean result = Inventory.UpdateInvent(item);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testUpdateInvent2() {
        System.out.println("UpdateInvent");
        ItemInfo item = new ItemInfo(p_id,"random",26);
        boolean expResult = false;
        boolean result = Inventory.UpdateInvent(item);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testUpdateInvent3() {
        System.out.println("UpdateInvent");
        ItemInfo item = new ItemInfo("5TGH",pname,260);
        boolean expResult = false;
        boolean result = Inventory.UpdateInvent(item);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testUpdateInvent4() {
        
        String new_id=p_id+"01";
        String new_name=pname+"01";
        
        System.out.println("UpdateInvent");
        ItemInfo item = new ItemInfo(new_id,new_name,77);
        boolean expResult = true;
        boolean result = Inventory.UpdateInvent(item);
        assertEquals(expResult, result);
        
        ids.add(new_id);
    }
    
    
    /**
     * Test of update method, of class Inventory.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        String id = p_id;
        int amt = 300;
        boolean expResult = true;
        boolean result = Inventory.update(id, amt);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testUpdate2() {
        System.out.println("update");
        String id = p_id;
        int amt = 95;
        boolean expResult = true;
        boolean result = Inventory.update(id, amt);
        assertEquals(expResult, result);
    }
    /*
    @Test
    public void testUpdate3() {
        System.out.println("update");
        String id = p_id+"03";
        int amt = 65;
        boolean expResult = true;
        boolean result = Inventory.update(id, amt);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of searchItem method, of class Inventory.
     */
    @Test
    public void testSearchItem() {
        System.out.println("searchItem");
        String id = "5564";
        String expResult = "not found\tnot found";
        String result = Inventory.searchItem(id);
        assertEquals(expResult, result);
    }
    
    public void testSearchItem2() {
        System.out.println("searchItem");
        String id = p_id;
        String expResult = "test";
        String result = Inventory.searchItem(id);
        assertEquals(expResult.contains("test"), result.contains("test"));
    }

    /**
     * Test of showAll method, of class Inventory.
     */
    @Test
    public void testShowAll() {
        System.out.println("showAll");
        String expResult = "test";
        String result = Inventory.showAll();
        assertEquals(expResult.contains("test"), result.contains("test"));
    }
    
}
