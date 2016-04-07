/*
 * Represents employee information
 */
package inventory.tracker;

import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author shamary williams
 */
public class Employee 
{
    private String id,fname,lname;//employee id, first and last name
    
    public Employee(String id)
    {
        this.id=id;
    }
    
    public Employee(String id,String fname,String lname)
    {
        this.id=id;
        this.fname=fname;
        this.lname=lname;
    }
    
    public String getID()/// return employee id
    {
        return id;
    }
    
    public String getFirstName()
    {
        return fname;
    }
    
    public void setFirstName(String fname)
    {
        this.fname=fname;
    }
    
    public String getLastName()
    {
        return lname;
    }
    
    public void setLastName(String lname)
    {
        this.lname=lname;
    }
    
    protected String info()
    {
        return getFirstName()+" "+getLastName()+" ("+getID()+")";
    }
    
    public void RequestForItem(ItemInfo item,boolean approval) throws IOException
    {
        ////add request info
        (new ItemRequest(id,item.getID(),item.getName(),item.getQty(),approval,item.get_how_long())).request();
    }
    
    @Override
    public String toString()
    {
        return info();
    }
}
