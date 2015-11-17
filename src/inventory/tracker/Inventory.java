/*
 *Class handles interaction with the main program and the database "inventory"
 */
package inventory.tracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author shamary williams
 */
public class Inventory 
{
    private static final String db_name="inventory";///database name
    private static Connection connect;
    
    private static PreparedStatement pstate;//////used to pass statements to the database such as queries or updates
    
    private static final String uname="root"///database username (if any)
                                ,password="shamster#tech";///database password (if any)
    
    private static boolean dfound=false;
    
    public static void init()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
            /////setup connection with database
            connect=DriverManager.getConnection("jdbc:mysql://localhost/mysql?user="+uname+"&password="+password);
            
            /////////////check for inventory database///////////////
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
                
                state.executeUpdate("ALTER TABLE item ADD UNIQUE(id)");
                state.executeUpdate("ALTER TABLE item ADD UNIQUE(name)");
            }
            else
            {
              connect=DriverManager.getConnection("jdbc:mysql://localhost/"+db_name+"?user="+uname+"&password="+password);  
            }
           
            /*//////check if table 'item' exists in database 'inventory'
            pstate=connect.prepareStatement("SELECT table_name FROM information_schema.tables where table_schema='inventory'");
            ResultSet r=pstate.executeQuery();
            
            while(r.next())
            {
                String s=r.getString("table_name");
                
                System.out.println(s);
                
                if(!s.equals("item"))
                {
                    //////Then create table 'item'
                    pstate=connect.prepareStatement("CREATE TABLE item(id varchar(15) NOT NULL,name varchar(25) NOT NULL,"
                            + "quantity int NOT NULL, PRIMARY KEY (id), UNIQUE (id),UNIQUE (name));");
                    
                    pstate.executeUpdate();
                }
            }*/
            
            //statement= connect.createStatement();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static boolean UpdateInvent(ItemInfo item)////Adds an item to the database
    {
        try
        {
            prep_add();
            
            pstate.setString(1,item.getID());
            pstate.setString(2,item.getName());
            pstate.setInt(3,item.getQty());
            pstate.executeUpdate();
            
            return true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public static String searchItem(String id)/////searches for an item in the database
    {
        String result="not found";
        
        try
        {
            prep_search();
            
            pstate.setString(1,id);
            ResultSet r=pstate.executeQuery();
            
            while(r.next())
            {
                result="Name: "+r.getString("name")+"\n";
                result+="Quantity: "+r.getInt("quantity");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return result;
    }
    
    private static void prep_add() throws SQLException //////prepare statement for insert
    {
        pstate=connect.prepareStatement("insert into inventory.item values (?,?,?)");//parameters (id,name,quantity)
    }
    
    private static void prep_search() throws SQLException /////prepare statement for queries
    {
        pstate=connect.prepareStatement("select * from inventory.item where id=?");//parameter item id
    }
}
