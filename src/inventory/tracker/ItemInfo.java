/*
 *Represents item information
 */
package inventory.tracker;

/**
 *
 * @author shamary williams
 */
public class ItemInfo 
{
    private String id,name;
    private int qty,howLong;
    
    public ItemInfo(String id,String name,int qty)
    {
        this.id=id;
        this.name=name;
        this.qty=qty;
    }
    
    public ItemInfo(String id,String name,int qty,int howLong)
    {
        this.id=id;
        this.name=name;
        this.qty=qty;
        this.howLong=howLong;
    }
    
    public String getID()
    {
        return id;
    }
    
    public void setName(String name)
    {
        this.name=name;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setQty(int qty)
    {
        this.qty=qty;
    }
    
    public int getQty()
    {
        return qty;
    }
    
    public int get_how_long()
    {
        return howLong;
    }
    
}
