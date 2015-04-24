/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Prisoner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Dominik
 */
public class DBAccess {

    private DataBase db;
    private Connection con;
    private PreparedStatement PreStmt = null;


    public DBAccess() throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {
        db = DataBase.getInstance();
        con = db.getCon();
    }



    public Prisoner getPrisoner(int ID) throws Exception
    {
         Statement stat = db.getStatement();


        String sqlString = "SELECT vorname, nachname, gebDate, inDate, outDate, priority "
                + "FROM prisoner "
                + "WHERE prID ==" + ID+";";

        ResultSet rs = stat.executeQuery(sqlString);
        rs.next();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        
        String vorname = rs.getString("vorname");
        String nachname = rs.getString("nachname");
        Date gebDate = sdf.parse(rs.getString("gebDate"));
        Date inDate = sdf.parse(rs.getString("inDate"));
        Date outDate = sdf.parse(rs.getString("outDate"));
        int priority = Integer.parseInt(rs.getString("priority"));

        Prisoner pr = new Prisoner(ID, vorname, nachname, gebDate, inDate, outDate, priority);

        return pr;
    }    

    public void addPrisoner(String vorname, String nachname, Date gebDate, Date inDate, Date outDate, int priority) throws Exception
    {
        Statement stat = db.getStatement();
        String sqlString;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        
       
        sqlString = "INSERT INTO prisoner(prID, vorname, nachname, gebDate, inDate, outDate, priority) "
                + "VALUES(nextval('sql_pID')"
                + ","+ vorname 
                + ","+nachname+", "
                + "TO_DATE('"+ sdf.format(gebDate)+",'dd.MM.yyyy'), "
                + "TO_DATE('"+ sdf.format(inDate)+",'dd.MM.yyyy'),"
                + "TO_DATE('"+ sdf.format(gebDate)+",'dd.MM.yyyy'),"
                + ""+priority+"')";
        
        ResultSet rs = stat.executeQuery(sqlString);
        rs = stat.executeQuery(sqlString);
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException, Exception {
        DBAccess dba = new DBAccess();     
    }
}
