/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.SQLException;

/**
 *
 * @author Faisal
 */
public interface DataBaseI {
    public void establish_Connection()throws SQLException;
    public void saveStates(String name,String x,String y)throws SQLException;
    public void deleteSaveStates(String name)throws SQLException;
    public String[] viewSavedStates(int count)throws SQLException;
    public String[] load(String name)throws SQLException;
    public int count()throws SQLException; //return total number of coloured cells
}
