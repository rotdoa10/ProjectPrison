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
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public LinkedList<Prisoner> getPrisoners() throws Exception {
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

    public Prisoner getPrisoner(int ID) throws Exception {
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

    public boolean checkLogin(String username, String password) throws Exception {
        boolean check = false;

        Statement stat = db.getStatement();

        String sqlString = "SELECT username, passwort "
                + "FROM guard;";

        ResultSet rs = stat.executeQuery(sqlString);
        rs.next();

        while (!rs.isLast()) {
            if (username.equals(rs.getString("username")) && password.equals(rs.getString("passwort"))) {
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
                + ",'" + nachname + "'"
                + ", TO_DATE('" + sdf.format(gebDate) + "','dd.mm.yyyy')"
                + ", TO_DATE('" + sdf.format(inDate) + "','dd.mm.yyyy')"
                + ", TO_DATE('" + sdf.format(gebDate) + "','dd.mm.yyyy')"
                + "," + priority
                + "," + cellID + ")";

        ResultSet rs = stat.executeQuery(sqlString);
        rs = stat.executeQuery(sqlString);
    }
    
        public void removePrisoner(int ID) throws Exception {
        Statement stat = db.getStatement();

        String sqlString = "DELETE FROM prisoner "
                + "WHERE prID = " + ID;

        try {
            stat.execute(sqlString);
        } catch (SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LinkedList<Prisoner> getPrisonersinCell(int CID) throws Exception {
        LinkedList<Prisoner> list = new LinkedList<>();
        Statement stat = db.getStatement();

        String sqlString = "SELECT prID, vorname, nachname, gebDate, inDate, outDate, pID, cellID "
                + "FROM prisoner "
                + "WHERE cellID=" + CID + ";";

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

    public void updateCells(int cellCnt) throws Exception {
        Statement stat = db.getStatement();
        String sqlString;

        sqlString = "SELECT COUNT(*) FROM cell";

        ResultSet rs = stat.executeQuery(sqlString);
        rs.next();

        int dbCnt = Integer.parseInt(rs.getString(1));

        if (cellCnt > dbCnt) {
            int cnt = cellCnt - dbCnt+1;

            for (int i = 1; i < cnt; i++) {
                int cell = dbCnt + i;
                sqlString = "INSERT INTO cell(cellID, bez, maxAnzahl) "
                        + "VALUES (" + cell + ", 'Zelle" + cell + "' , 4)";
                System.out.println(sqlString);
                try {
                    rs = stat.executeQuery(sqlString);
                } catch (Exception e) {
                }
            }
        } else if (cellCnt < dbCnt) {
            // cc = 10 dc = 20 -> cnt = 10
            int cnt = dbCnt - cellCnt;
            
            for (int i = cnt; i >= 0 + 1; i--) {
                
                int cell = cellCnt + i;
                System.out.println(i +"," + cell);
                sqlString = "DELETE FROM cell"
                        + " WHERE cellID=" + cell + ";";
                System.out.println(sqlString);
                try {
                    rs = stat.executeQuery(sqlString);
                } catch (Exception e) {
                }
            }
        }

    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException, Exception {
//        DBAccess dba = new DBAccess();
//       
    }
}
