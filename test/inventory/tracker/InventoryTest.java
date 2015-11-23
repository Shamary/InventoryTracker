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
    
    private static ArrayList<String> ids=new ArrayList<>();////item ids
    
    
    public InventoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Inventory.init("USER123","12345");
        
        Inventory.UpdateInvent(new ItemInfo(p_id,pname,80));///add item in inventory
        
        ids.add(p_id);
        
        System.out.println("item added");
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
    public void testUpdateInvent() {///add item with same ID and name to that in database
        System.out.println("UpdateInvent");
        ItemInfo item = new ItemInfo(p_id,pname,30);
        boolean expResult = false;
        boolean result = Inventory.UpdateInvent(item);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testUpdateInvent2() {////add same ID diff name
        System.out.println("UpdateInvent");
        ItemInfo item = new ItemInfo(p_id,"random",26);
        boolean expResult = false;
        boolean result = Inventory.UpdateInvent(item);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testUpdateInvent3() {///diff ID same name
        System.out.println("UpdateInvent");
        ItemInfo item = new ItemInfo("5TGH",pname,260);
        boolean expResult = false;
        boolean result = Inventory.UpdateInvent(item);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testUpdateInvent4() {/////add a new item diff id and name
        
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
   
    @Test
    public void testSearchItem() {////search random ID
        System.out.println("searchItem");
        String id = "5564";
        String expResult = "not found\tnot found";
        String result = Inventory.searchItem(id);
        assertEquals(expResult, result);
    }
    
    public void testSearchItem2() {///search valid ID
        System.out.println("searchItem");
        String id = p_id;
        String expResult = "\t"+pname+"\t";///if result contains this string then the result is valid
        String result = Inventory.searchItem(id);
        assertEquals(expResult.contains("\t"+pname+"\t"), result.contains("\t"+pname+"\t"));
    }

    /**
     * Test of showAll method, of class Inventory.
     */
    @Test
    public void testShowAll() {
        System.out.println("showAll");
        String expResult = "\t"+pname+"\t";
        String result = Inventory.showAll();
        assertEquals(expResult.contains("\t"+pname+"\t"), result.contains("\t"+pname+"\t"));
    }
    
}
