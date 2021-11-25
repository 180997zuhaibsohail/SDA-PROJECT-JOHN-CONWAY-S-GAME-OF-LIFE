package database;

import java.sql.SQLException;

/**
 *
 * @author Usman
 */
public interface DataBaseI {
    public void establish_Connection()throws SQLException;
    public void saveStates(int x,int y)throws SQLException;
    public void deleteSaveStates(int x,int y)throws SQLException;
    public void viewSavedStates()throws SQLException;
    public int[] load(int count)throws SQLException; //here count is number of coloured cells
    public int count()throws SQLException; //return total number of coloured cells
}
