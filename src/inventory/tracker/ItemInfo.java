/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.tracker;

/**
 *
 * @author shamary williams
 */
public class ItemInfo 
{
    private String id,name;
    private int qty;
    
    public ItemInfo(String id,String name,int qty)
    {
        this.id=id;
        this.name=name;
        this.qty=qty;
    }
    
    public String getID()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getQty()
    {
        return qty;
    }
    
}
