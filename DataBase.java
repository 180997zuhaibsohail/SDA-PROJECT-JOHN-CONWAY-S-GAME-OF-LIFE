package database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usman
 */
public class DataBase implements DataBaseI
{

    private ResultSet rs;
   public void establish_Connection() throws SQLException
{
        
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            String Url="jdbc:ucanaccess://C:\\Users\\Software Design Analysis\\Project\\savedgame.accdb";
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
public void viewSavedStates() throws SQLException
{   System.out.print("X Y"+"\n");
             while(rs.next())
             {
                 System.out.print(rs.getString("x")+" "+rs.getString("y")+"\n");
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
public int[] load(int count)throws SQLException
{
    int i=0;
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
    public static void main(String[] args) throws SQLException 
	{
        DataBase b=new DataBase();
        b.establish_Connection();
 
      int size=b.count();
      int[]states=new int[size];
      states=b.load(size);
      for(int i=0;i<size;i++)
      {
       System.out.print(states[i]);
      } 
    }
}
