/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Faisal
 */
public class DataBase implements DataBaseI{

    private ResultSet rs;
   public void establish_Connection() throws SQLException
{
        
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            String Url="jdbc:ucanaccess://C:\\Users\\Faisal\\Desktop\\7th Semester\\Software Design Analysis\\Project\\savegame.accdb";
            Connection con=DriverManager.getConnection(Url);
            String sql="Select * from saveStates";
            PreparedStatement pst;
            
                pst = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs=pst.executeQuery();
        
}
public void saveStates(String name,String x,String y) throws SQLException
{            
             rs.moveToInsertRow();
             rs.updateString("StateName", name);
             rs.updateString("X_Coordinates", x);
             rs.updateString("Y_Coordinates", y);
             rs.insertRow();
}
public void deleteSaveStates(String name) throws SQLException
{            
             rs.beforeFirst();
             while(rs.next())
             {
                 if(name.equals(rs.getString("StateName")))
                 {
                     rs.deleteRow();
                     break;
                 }
             }
}

public int count()throws SQLException
{
    int counter=0;
    while(rs.next())
    {
        counter++;
    }
    return counter;
}

public String[] viewSavedStates(int count) throws SQLException
{   int i=0;
    String[]states=new String[count];
    rs.beforeFirst();
    while(rs.next())
    {
        states[i]=rs.getString("StateName");
    }
    return states;
}

public String[] load(String name)throws SQLException
{
    int i=0;
    String[] chosen=new String[2];
    rs.beforeFirst();
    while(rs.next())
    {
        
        if(name.equals(rs.getString("StateName")))
        {
            chosen[0]=rs.getString("X_Coordinates");
            chosen[1]=rs.getString("X_Coordinates");
            break;
        }
        i++;
    }
    return chosen;
}
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        DataBase b=new DataBase();
       
      //  b.deleteSaveStates(0, 0);
      String x="1,2,3,4";
      String y="2,3,3,4";
      String name="State1";
     // for(int i=0;i<5;i++)
     // {
        b.establish_Connection();
        b.saveStates(name,x,y);
      // b.deleteSaveStates(name);
     // }
      //  b.viewSavedStates();
     /* int size=b.count();
      int[]states=new int[size];
      states=b.viewSavedStates(size);
      for(int i=0;i<size;i++)
      {
       System.out.print(states[i]);
      }
      Scanner fs=new Scanner(System.in);
      System.out.print("Enter x");
      String x=fs.nextLine();
      System.out.print("Enter y");
      String y=fs.nextLine();
      
      int x1=Integer.parseInt(x);
      int y1=Integer.parseInt(y);
      int[]Lstates=new int[2];
      Lstates=b.load(states, x1, y1);
      for(int i=0;i<2;i++)
      {
       System.out.print(Lstates[i]);
      }
*/
    }
}
