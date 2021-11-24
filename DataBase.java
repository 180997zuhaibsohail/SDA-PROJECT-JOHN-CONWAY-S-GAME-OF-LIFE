/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
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
public void saveStates(int x,int y) throws SQLException
{            String temp1=Integer.toString(x);
             String temp2=Integer.toString(y);
             rs.moveToInsertRow();
             rs.updateString("x", temp1);
             rs.updateString("y", temp2);
             rs.insertRow();
}
public void deleteSaveStates(int x,int y) throws SQLException
{            String temp1=Integer.toString(x);
             String temp2=Integer.toString(y);
             rs.beforeFirst();
             while(rs.next())
             {
                 if(temp1.equals(rs.getString("x")) && temp2.equals(rs.getString("y")))
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
    return 2*counter; //bcz 2 cordinates make 1 cell
}

public int[] viewSavedStates(int count) throws SQLException
{   int i=0;
    int[]states=new int[count];
    rs.beforeFirst();
    while(rs.next())
    {
        states[i]=Integer.parseInt(rs.getString("x"));
        i++;
        states[i]=Integer.parseInt(rs.getString("y"));
        i++;
    }
    return states;
}

public int[] load(int[]states,int stateCoordinateX,int stateCoordinateY)throws SQLException
{
    int i=0;
    int[] chosen=new int[2];
    rs.beforeFirst();
    while(rs.next())
    {
        states[i]=Integer.parseInt(rs.getString("x"));
        i++;
        states[i]=Integer.parseInt(rs.getString("y"));
        if(states[i-1]==stateCoordinateX && states[i]==stateCoordinateY)
        {
            chosen[0]=states[i-1];
            chosen[1]=states[i];
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
     // for(int i=0;i<5;i++)
     // {
        b.establish_Connection();
     //   b.saveStates(i, i+1);
     // }
      //  b.viewSavedStates();
      int size=b.count();
      int[]states=new int[size];
      states=b.viewSavedStates(size);
      for(int i=0;i<size;i++)
      {
       System.out.print(states[i]);
      }
    }
    
}
