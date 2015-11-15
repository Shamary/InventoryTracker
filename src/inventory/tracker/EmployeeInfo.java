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
public class EmployeeInfo 
{
    private String id,fname,lname;//employee id, first and last name
    
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
    
    public String getLastName()
    {
        return lname;
    }
    
    @Override
    public String toString()
    {
        return getFirstName()+" "+getLastName()+" ("+getID()+")";
    }
}
