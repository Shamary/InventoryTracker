/*
 * Used to validate current user by user ID and password.
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
    
    //private static boolean authorized=false;
    
    private static final String valid_dir="valid";
    
    private static String valid_file_name=valid_dir+"/6.txt";
    
    public static void init()/////setup details for authorization
    {
        try
        {
            Scanner scan=new Scanner(new File(valid_file_name));
            System.out.println("File already created.");
        }
        catch(FileNotFoundException fne)
        {
            //fne.printStackTrace();
            //System.out.println();

            new File(valid_dir).mkdir();////create valid employee directory
            
            //id=67234Q1JB
            EmployeeInfo emp=new EmployeeInfo("6","Kevin","Thomas");
            
            valid_file_name=valid_dir+"/"+emp.getID()+".txt";/////set the file name to the employee's ID
            
            try
            {
                PrintWriter pw=new PrintWriter(valid_file_name);
                pw.println(emp.getID());
                pw.println(emp.getFirstName());
                pw.println(emp.getLastName());
                pw.println(pass);
                pw.close();
                
               
                System.out.println("File "+valid_file_name +" created");
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
    
    
    public static boolean authorize(String uid,String password)/////validate user ID and password
    {
        try
        {
            Scanner scan=new Scanner(new File(valid_file_name));
            
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
                //authorized=true;
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
    
    /*
    public static boolean is_auth()
    {
        return authorized;
    }
    */
}
