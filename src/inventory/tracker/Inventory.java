/*
 *Class handles interaction with the main program and the database "inventory"
 */
package inventory.tracker;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author shamary williams
 */
public class Inventory 
{
    private static final String db_name="inventory";///database name
    private static Connection connect;
    
    //private static PreparedStatement pstate;//////used to pass statements to the database such as queries or updates
    
    private static final String uname="root"///database username (if any)
                                ,password="shamster#tech";///database password (if any)
    
    private static boolean dfound=false;
    
    private static String uid,upassword;
    
    public static void init(String id,String upass)
    {
        try
        {
            uid=id;
            upassword=upass;
            
            Class.forName("com.mysql.jdbc.Driver");
            
            /////setup connection with database
            connect=DriverManager.getConnection("jdbc:mysql://localhost/mysql?user="+uname+"&password="+password);
            
            /////////////check for 'inventory' database///////////////
            ResultSet dnames=connect.getMetaData().getCatalogs();
            String db;
            
            while(dnames.next())
            {
                db=dnames.getString("TABLE_CAT");
                //System.out.println(db);
                
                if(db.contains(db_name))
                {
                   dfound=true;
                   
                   System.out.println("inventory already exists...");
                   
                   break;
                }
            }
            
           
            if(!dfound)
            {
                Statement state=connect.createStatement();
                state.executeUpdate("CREATE DATABASE "+db_name);///create database 'inventory'
                
                connect=DriverManager.getConnection("jdbc:mysql://localhost/"+db_name+"?user="+uname+"&password="+password);
                state=connect.createStatement();
                
                ////////create table 'item'
                state.executeUpdate("CREATE TABLE item(id varchar(15) NOT NULL,name varchar(25) NOT NULL,"
                            + "quantity int NOT NULL, PRIMARY KEY (id))");
                
                //state.executeUpdate("ALTER TABLE item ADD UNIQUE(id)");
                state.executeUpdate("ALTER TABLE item ADD UNIQUE(name)");
            }
            else
            {
              connect=DriverManager.getConnection("jdbc:mysql://localhost/"+db_name+"?user="+uname+"&password="+password);  
            }
            
            InventReport();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
       
    }
    
    public static boolean UpdateInvent(ItemInfo item)////Adds an item to the database
    {
        try
        {
            if(Authorization.authorize(uid,upassword))
            {
                PreparedStatement pstate=prep_add();

                pstate.setString(1,item.getID());
                pstate.setString(2,item.getName());
                pstate.setInt(3,item.getQty());
                pstate.executeUpdate();

                return true;
            }
            else
            {
                return false;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public static boolean update(String id,int amt)
    {
        try
        {
            PreparedStatement pstate=prep_update();
            
            pstate.setString(1,""+amt);
            pstate.setString(2,id);
            
            pstate.executeUpdate();
            
            return true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public static String searchItem(String id)/////searches for an item in the database
    {
        String result="not found\tnot found";
        
        try
        {
            if(Authorization.authorize(uid,upassword))
            {
                PreparedStatement pstate=prep_search();

                pstate.setString(1,id);
                ResultSet r=pstate.executeQuery();

                while(r.next())
                {
                    //result="Name: "+r.getString("name")+"\n";
                    //result+="Quantity: "+r.getInt("quantity");
                    
                    result=r.getString("name")+"\t";
                    result+=r.getInt("quantity");
                }
            }
            else
            {
                return "validation error";
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public static String showAll()
    {
        String result="";
        
        try
        {
            if(Authorization.authorize(uid,upassword))
            {
                PreparedStatement pstate=prep_showAll();

                ResultSet r=pstate.executeQuery();

                boolean isEmpty=true;

                result="ID\tName\tQuantity"+System.lineSeparator()+System.lineSeparator();

                while(r.next())
                {
                    if(isEmpty)
                    {
                        isEmpty=false;
                    }

                    result+=r.getString("id")+"\t";
                    result+=r.getString("name")+"\t";
                    result+=r.getInt("quantity")+System.lineSeparator()+System.lineSeparator();

                    System.out.println(result);
                }

                if(isEmpty)
                {
                    result="empty";
                }
            }
            else
            {
                return "validation error";
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            
            result="empty";
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public static void delete(String id)////delete item with id='id' from inventory
    {
        try
        {
            PreparedStatement pstate=prep_delete();
            
            pstate.setString(1,id);
            
            pstate.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private static PreparedStatement prep_add() throws SQLException //////prepare statement for insert
    {
        return connect.prepareStatement("insert into inventory.item values (?,?,?)");//parameters (id,name,quantity)
    }
    
    private static PreparedStatement prep_update() throws SQLException //////prepare statement for update
    {
        //pstate=connect.prepareStatement("UPDATE inventory.item SET quantity= quantity + "+amt+" WHERE id= "+id);//parameters (id,name,quantity)
        return connect.prepareStatement("UPDATE inventory.item SET quantity=? WHERE id=?");//parameters quantity, id
    }
    
    private static PreparedStatement prep_search() throws SQLException /////prepare statement for queries
    {
        return connect.prepareStatement("select * from inventory.item where id=?");//parameter item id
    }
    
    private static PreparedStatement prep_showAll() throws SQLException //////prepare statement for displaying all content
    {
        return connect.prepareStatement("SELECT * FROM inventory.item");//parameters (id,name,quantity)
    }
    
    private static PreparedStatement prep_delete() throws SQLException //////prepare statement for deleting content
    {
        return connect.prepareStatement("DELETE FROM inventory.item WHERE id=?");//parameters (id,name,quantity)
    }
    
    
    private static void InventReport()//////Generate report weekly
    {      
        Timer timer=new Timer();
        
        Calendar c=Calendar.getInstance();
       
        TimerTask task=new TimerTask()
        {
            @Override
            public void run()
            {   
                Calendar c=Calendar.getInstance();
                
                //System.out.println("In report "+c.get(Calendar.DAY_OF_WEEK)+" "+Calendar.SUNDAY);
                
                if(c.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)////if the current day is Sunday generate report 
                {
                    String rep=showAll();

                    System.out.println(rep);

                    String file_name=Authorization.getDir("report")+c.get(Calendar.DAY_OF_MONTH)/*+"_"+c.get(Calendar.WEEK_OF_MONTH)*/+"_"+
                                    (c.get(Calendar.MONTH)+1)+"_"+c.get(Calendar.YEAR)+".txt";

                    File file=new File(file_name);

                    if(!file.exists())///if report already exists
                    {
                        try
                        { 
                           //rep=rep.replaceAll("\n", System.lineSeparator());
                           PrintWriter pw=new PrintWriter(file_name);
                           pw.println(rep);
                           pw.close();
                        }
                        catch(IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                } 
            }
        };
        
        long period=1*24*60*60*1000;//delay 1 day (24 hours)
        
        //System.out.println(c.get(Calendar.YEAR)+" "+c.get(Calendar.DAY_OF_MONTH));
            
        c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH),17,0);///day of install at 5 pm
        //System.out.println(c.getTime());
        timer.scheduleAtFixedRate(task,c.getTime(),period);
    }
}
