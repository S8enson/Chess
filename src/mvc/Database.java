/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Database {

    Connection conn = null;
    String url = "jdbc:derby:ChessDB;create=true";  //url of the DB host
    
    String dbusername = "ass2";  //DB username
    String dbpassword = "ass2";   //DB password


    public void dbsetup() {
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();
            String tableName = "UserInfo";

            if (!checkTableExisting(tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName + " (userid VARCHAR(12), wins INT, losses INT)");
            }
            
            statement.close();

        } catch (Throwable e) {
            System.out.println("error");

        }
    }


    public Data checkName(String wUsername, String bUsername) {
        Data data = new Data(); // Initialize an instance of Data.
        data.wUsername = wUsername;
        data.bUsername = bUsername;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT userid, wins, losses FROM UserInfo " + "WHERE userid = '" + wUsername + "'");
            if (rs.next()) {

                data.whiteWins = rs.getInt("wins");
                data.whiteLosses = rs.getInt("losses");

            } else {

                
                statement.executeUpdate("INSERT INTO UserInfo "
                        + "VALUES('" + wUsername + "', 0, 0)");

                data.whiteWins = 0;
                data.whiteLosses = 0;

            }
            rs = statement.executeQuery("SELECT userid, wins, losses FROM UserInfo " + "WHERE userid = '" + bUsername + "'");
            if (rs.next()) {

                data.blackWins = rs.getInt("wins");
                data.blackLosses = rs.getInt("losses");

            } else {

                
                statement.executeUpdate("INSERT INTO UserInfo "
                        + "VALUES('" + bUsername + "', 0, 0)");

                data.blackWins = 0;
                data.blackLosses = 0;


            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data; //Back to checkName() of Model.java.
    }

    private boolean checkTableExisting(String newTableName) {
        boolean flag = false;
        try {

            System.out.println("check existing tables.... ");
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);
            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.compareToIgnoreCase(newTableName) == 0) {
                    System.out.println(tableName + "  is there");
                    flag = true;
                }
            }
            if (rsDBMeta != null) {
                rsDBMeta.close();
            }
        } catch (SQLException ex) {
        }
        return flag;
    }

    public void gameOver(Data data) {
        Statement statement;

        try {
            statement = conn.createStatement();
            statement.executeUpdate("UPDATE UserInfo SET wins=" + data.whiteWins + ", losses=" + data.whiteLosses + " WHERE userid='" + data.wUsername + "'");
            statement.executeUpdate("UPDATE UserInfo SET wins=" + data.blackWins + ", losses=" + data.blackLosses + " WHERE userid='" + data.bUsername + "'");

        } catch (SQLException ex) {
            //Logger.getLogger(model.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet leaderboard() {
        
        
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM UserInfo ORDER BY wins DESC, losses");
            
            
            return rs;
           
        } catch (SQLException ex) {

        }
        return null;
    }
}
