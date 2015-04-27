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

        String sqlString = "SELECT username, password"
                        + "FROM guard;";

        ResultSet rs = stat.executeQuery(sqlString);
        rs.next();
        
        while(!rs.isLast())
        {
            if(username.equals(rs.getString("username"))&&password.equals(rs.getString("password")))
            {
                check = true;
                return check;
            }
            rs.next();
        }
        
        return check;
    }

    public void addPrisoner(String vorname, String nachname, Date gebDate, Date inDate, Date outDate, int priority) throws Exception {
//        Statement stat = db.getStatement();
//        String sqlString;
//        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//
//        sqlString = "INSERT INTO prisoner(prID, vorname, nachname, gebDate, inDate, outDate, pID, cellID) "
//                + "VALUES(nextval('sql_pID')"
//                + "," + vorname
//                + "," + nachname + ", "
//                + "TO_DATE('" + sdf.format(gebDate) + ",'dd.MM.yyyy'), "
//                + "TO_DATE('" + sdf.format(inDate) + ",'dd.MM.yyyy'),"
//                + "TO_DATE('" + sdf.format(gebDate) + ",'dd.MM.yyyy'),"
//                + priority + "')";
//
//        ResultSet rs = stat.executeQuery(sqlString);
//        rs = stat.executeQuery(sqlString);
    }

    public LinkedList<Prisoner> getPrisonersinCell(int CID) {
        LinkedList<Prisoner> pList = new LinkedList<>();

        return pList;
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException, Exception {
       
        DBAccess dba = new DBAccess();
        
        LinkedList<Prisoner> l = dba.getPrisoners();
        
        for (int i = 0; i < l.size(); i++) 
        {
            System.out.println(l.get(i).toString());
        }
        
    }
}
