/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.tracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author shamary williams
 */
public class Authorization 
{
    
    private static final String pass="12345";
    
    public static void init()
    {
        try
        {
            Scanner scan=new Scanner(new File("vemp.txt"));
            System.out.println("File already created.");
        }
        catch(FileNotFoundException fne)
        {
            //fne.printStackTrace();
            //System.out.println();

            //id=67234Q1JB
            EmployeeInfo emp=new EmployeeInfo("6","Kevin","Thomas");

            try
            {
                PrintWriter pw=new PrintWriter("vemp.txt");
                pw.println(emp.getID());
                pw.println(emp.getFirstName());
                pw.println(emp.getLastName());
                pw.println(pass);
                pw.close();
                
               
                System.out.println("File created");
            }
            catch(IOException io)
            {
                io.printStackTrace();
            }
        }
        
        File f=new File("request");
        if(!f.exists()&&!f.isDirectory())
        {
            f.mkdir();///create directory for requests
        }
    }
    
    
    public static boolean authorize(String uid,String password)
    {
        try
        {
            Scanner scan=new Scanner(new File("vemp.txt"));
            
            String fname,lname,id,epass="";
            
            int line=2;
            
            id=scan.nextLine();
            while(scan.hasNext())
            {
                if(line==2)
                {
                    fname=scan.nextLine();
                }
                else if(line==3)
                {
                    lname=scan.nextLine();
                }
                else
                {
                    epass=scan.nextLine();
                }
                
                line++;
            }
            
            if(uid.equals(id)&&password.equals(epass))
            {
                return true;
            }
        }
        catch(FileNotFoundException fne)
        {
            fne.printStackTrace();
            //System.out.println();
        }
        
        return false;
    }
}
