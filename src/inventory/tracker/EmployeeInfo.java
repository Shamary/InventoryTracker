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
public class EmployeeInfo 
{
    private String id,fname,lname;//employee id, first and last name
    
    public EmployeeInfo(String id)
    {
        this.id=id;
    }
    
    public EmployeeInfo(String id,String fname,String lname)
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
    
    private String info()
    {
        return getFirstName()+" "+getLastName()+" ("+getID()+")";
    }
    
    public void requestItem(ItemInfo item) throws IOException
    {
        ////add request info
        PrintWriter pw=new PrintWriter("request/"+id+"_request_for_"+item.getName()+".txt");
        pw.println("Employee ID: "+id);
        pw.println("Item ID: "+item.getID());
        pw.println("Item name: "+item.getName());
        pw.println("request quantity: "+item.getQty());
        pw.println("request lenght: "+item.get_how_long()+" days");
        pw.close();
    }
    
    @Override
    public String toString()
    {
        return info();
    }
}
