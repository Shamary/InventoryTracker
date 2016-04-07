/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.tracker;

import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author shamary williams
 */ 
public class ItemRequest 
{
    private final String emp_ID;
    private final String itemID;
    private final String itemName;
    private final int qty;
    
    private final boolean approval;
    private final int requestLength;
    
    public ItemRequest(String emp_ID,String itemID,String itemName,int qty,boolean approval,int duration)
    {
        this.emp_ID=emp_ID;
        this.itemID=itemID;
        this.itemName=itemName;
        this.qty=qty;
        this.approval=approval;
        this.requestLength=duration;
    }
    
    public void request() throws IOException
    {
         ////add request info
        PrintWriter pw=new PrintWriter(Authorization.getDir("request")+emp_ID+"_request_for_"+itemName+".txt");
        pw.println("Employee ID: "+emp_ID);
        pw.println("Item ID: "+itemID);
        pw.println("Item name: "+itemName);
        pw.println("request quantity: "+qty);
        pw.println("request lenght: "+requestLength+" days");
        pw.println("Approval: "+approval);
        pw.close();
    }
}
