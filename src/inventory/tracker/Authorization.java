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
    
    private static final String valid_dir="Valid"//directory for valid employees
                                ,report_dir="Reports"//directory for Reports
                                ,request_dir="Request";
    
    private static String valid_file_name=valid_dir+"/USER123.txt";
    
    public static void init()/////setup details for authorization
    {
        try
        {
            Scanner scan=new Scanner(new File(valid_file_name));
            System.out.println("File already created.");
        }
        catch(FileNotFoundException fne)
        {
            new File(valid_dir).mkdir();////create valid employee directory
            
            new File(report_dir).mkdir();///create directory for reports
            
            //id=USER123
            EmployeeInfo emp=new EmployeeInfo("USER123","Kevin","Thomas");
            
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
        
        File f=new File(request_dir);
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
            
            if(uid.equals(id)&&password.equals(epass))/////login info validated
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
        catch(Exception e)
        {
            e.printStackTrace();
        }        
        return false;
    }
    
    protected static String getDir(String dir)///get directory for reports or requests
    {
        if(dir.toLowerCase().contains("req"))
        {
            return request_dir+"/";
        }
        else if(dir.toLowerCase().contains("rep"))
        {
            return report_dir+"/";
        }        
        
        return "";
    }
}
