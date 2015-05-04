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
import java.util.LinkedList;

/**
 *
 * @author Dominik
 */
public class DBAccess {

    private DataBase db;
    private Connection con;
    private PreparedStatement PreStmt = null;

    public DBAccess() throws Exception {
        db = DataBase.getInstance();
        con = db.getCon();
    }

    public LinkedList<Prisoner> getPrisoners() throws Exception
    {
        LinkedList<Prisoner> list = new LinkedList<>();
        Statement stat = db.getStatement();

        String sqlString = "SELECT prID, vorname, nachname, gebDate, inDate, outDate, pID, cellID "
                + "FROM prisoner;";

        ResultSet rs = stat.executeQuery(sqlString);
        rs.next();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        do {
            int ID = Integer.parseInt(rs.getString("prID"));
            String vorname = rs.getString("vorname");
            String nachname = rs.getString("nachname");
            Date gebDate = sdf.parse(rs.getString("gebDate"));
            Date inDate = sdf.parse(rs.getString("inDate"));
            Date outDate = sdf.parse(rs.getString("outDate"));
            int pID = Integer.parseInt(rs.getString("pID"));
            int cellID = Integer.parseInt(rs.getString("cellID"));

            Prisoner pr = new Prisoner(ID, vorname, nachname, gebDate, inDate, outDate, pID, cellID);
            list.add(pr);
        } while (!rs.isLast());

        return list;
    }

    public Prisoner getPrisoner(int ID) throws Exception  {
        Statement stat = db.getStatement();

        String sqlString = "SELECT vorname, nachname, gebDate, inDate, outDate, pID, cellID "
                + "FROM prisoner "
                + "WHERE prID ==" + ID + ";";

        ResultSet rs = stat.executeQuery(sqlString);
        rs.next();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String vorname = rs.getString("vorname");
        String nachname = rs.getString("nachname");
        Date gebDate = sdf.parse(rs.getString("gebDate"));
        Date inDate = sdf.parse(rs.getString("inDate"));
        Date outDate = sdf.parse(rs.getString("outDate"));
        int pID = Integer.parseInt(rs.getString("pID"));
        int cellID = Integer.parseInt(rs.getString("cellID"));

        Prisoner pr = new Prisoner(ID, vorname, nachname, gebDate, inDate, outDate, pID, cellID);

        return pr;
    }
    
    public boolean checkLogin(String username, String password) throws Exception
    {
        boolean check = false;
        
        Statement stat = db.getStatement();

        String sqlString = "SELECT username, passwort "
                        + "FROM guard;";

        ResultSet rs = stat.executeQuery(sqlString);
        rs.next();
        
        while(!rs.isLast())
        {
            if(username.equals(rs.getString("username"))&&password.equals(rs.getString("passwort")))
            {
                check = true;
                return check;
            }
            rs.next();
        }
        
        return check;
    }

    public void addPrisoner(String vorname, String nachname, Date gebDate, Date inDate, Date outDate, int priority, int cellID) throws Exception {
        Statement stat = db.getStatement();
        String sqlString;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        sqlString = "INSERT INTO prisoner(prID, vorname, nachname, gebDate, inDate, outDate, pID, cellID) "
                + "VALUES(nextval('sql_pID')"
                + ",'" + vorname + "'"
                + ",'" + nachname +"'"
                + ", TO_DATE('" + sdf.format(gebDate) + "','dd.MM.yyyy')"
                + ", TO_DATE('" + sdf.format(inDate) + "','dd.MM.yyyy')"
                + ", TO_DATE('" + sdf.format(gebDate) + "','dd.MM.yyyy')"
                + "," + priority 
                + "," + cellID + ")";
        
        System.out.println(sqlString);
        ResultSet rs = stat.executeQuery(sqlString);
        rs = stat.executeQuery(sqlString);
    }

    public LinkedList<Prisoner> getPrisonersinCell(int CID) throws Exception {
        LinkedList<Prisoner> list = new LinkedList<>();
        Statement stat = db.getStatement();

        String sqlString = "SELECT prID, vorname, nachname, gebDate, inDate, outDate, pID, cellID "
                + "FROM prisoner "
                + "WHERE cellID="+ CID + ";";

        ResultSet rs = stat.executeQuery(sqlString);
        rs.next();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        do {
            int ID = Integer.parseInt(rs.getString("prID"));
            String vorname = rs.getString("vorname");
            String nachname = rs.getString("nachname");
            Date gebDate = sdf.parse(rs.getString("gebDate"));
            Date inDate = sdf.parse(rs.getString("inDate"));
            Date outDate = sdf.parse(rs.getString("outDate"));
            int pID = Integer.parseInt(rs.getString("pID"));
            int cellID = Integer.parseInt(rs.getString("cellID"));

            Prisoner pr = new Prisoner(ID, vorname, nachname, gebDate, inDate, outDate, pID, cellID);
            list.add(pr);
        } while (!rs.isLast());

        return list;
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException, Exception {
       
        DBAccess dba = new DBAccess();
        SimpleDateFormat sdf = new SimpleDateFormat("DD.MM.yyyy");
        Date gebDate = sdf.parse("03.06.1996");
        Date outDate = sdf.parse("15.05.2050");
        
        dba.addPrisoner("Dominik", "Roth", gebDate, new Date(), outDate, 2, 5);
    }
}
