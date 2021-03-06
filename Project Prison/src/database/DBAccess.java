package database;

import beans.Prisoner;
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
 * @author Dominik Roth, ProjectPrison, 10.06.2015
 */
public class DBAccess {

    private DataBase db;
    private Connection con;
    private PreparedStatement PreStmt = null;

    public DBAccess() throws Exception {
        db = DataBase.getInstance();
        con = db.getCon();
    }

    /**
     * Liest alle Häftlinge, die in der PrisonDB in der prisoner Tabelle stehen aus.
     * @return LinkedList<Person>
     * @throws Exception 
     */   
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
        } while (rs.next());

        return list;
    }

    /**
     * Gibt einen einzelnen Prisoner aus der Prisoner Tabelle aus, mit der gegebenen ID
     * 
     * @param PrisonerID
     * @return Prisoner
     * @throws Exception 
     */
    public Prisoner getPrisoner(int ID) throws Exception {
        Statement stat = db.getStatement();

        String sqlString = "SELECT vorname, nachname, gebDate, inDate, outDate, pID, cellID "
                + "FROM prisoner "
                + "WHERE prID =" + ID + ";";

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

    /**
     * Überprüft ob der Login name und passwort richtig sind
     *
     * @param username
     * @param password
     * @return boolean
     * @throws Exception 
     */
    public boolean checkLogin(String username, String password) throws Exception {
        boolean check = false;

        Statement stat = db.getStatement();

        String sqlString = "SELECT username, passwort "
                + "FROM guard "
                + "WHERE username='"+username+"';";

        ResultSet rs = stat.executeQuery(sqlString);
        rs.next();

        if (password.equals(rs.getString("passwort"))) {
                check = true;
                return check;
            }            
        

        return check;
    }

    /**
     * Fügt einen neuen Häftling zur Datenbank hinzu.
     * 
     * @param vorname
     * @param nachname
     * @param gebDate
     * @param inDate
     * @param outDate
     * @param priority
     * @param cellID
     * @throws Exception 
     */
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

        stat.executeQuery(sqlString);
    }
    
    /**
     * Entfernt den prisoner, dessen ID angegeben wird
     * @param ID
     * @throws Exception 
     */
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
  
    /**
     * Aktuallisiert die Daten des Häftlinges. Nur das Entlassungsdatum, die Priority oder die Zelle kann geändert werden.
     * 
     * @param ID
     * @param outdate
     * @param priority
     * @param cellid
     * @throws Exception 
     */
    public void updatePrisoner(int ID, Date outdate, int priority, int cellid) throws Exception
    {
        Statement stat = db.getStatement();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        String sqlString = "UPDATE prisoner "
                + "SET outdate=TO_DATE('" + sdf.format(outdate) + "','dd.mm.yyyy'), pid="+priority+", cellid="+cellid+" "
                + "WHERE prid="+ID;

            stat.execute(sqlString);

    }
    
    /**
     * Gibt die Häftlinge in der gegebenen Zelle zurück.
     * 
     * @param CID
     * @return LinkedList<Prisoner>
     * @throws Exception 
     */
    public LinkedList<Prisoner> getPrisonersinCell(int CID) throws Exception {
        LinkedList<Prisoner> list = new LinkedList<>();
        Statement stat = db.getStatement();

        String sqlString = "SELECT prID, vorname, nachname, gebDate, inDate, outDate, pID, cellID "
                + "FROM prisoner "
                + "WHERE cellID=" + CID + ";";

        ResultSet rs = stat.executeQuery(sqlString);
        //rs.next();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        do {
            rs.next();
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

    /**
     * Ändert die anzahl an Zellen in der Datenbank.
     * 
     * @param cellCnt
     * @throws Exception 
     */
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
    
    /**
     * Gibt die Nummer der einzelnen Zellen zurück.
     * @return LinkedList<Integer>
     * @throws Exception 
     */
    public LinkedList<String> getCells() throws Exception
    {
        LinkedList<String> cellList = new LinkedList<>();
         Statement stat = db.getStatement();

        String sqlString = "SELECT cellid "
                + "FROM cell";

        ResultSet rs = stat.executeQuery(sqlString);
        rs.next();        

        do {
            cellList.add(rs.getString("cellid"));
            rs.next();
        } while (!rs.isLast());
        
        return cellList;
    }
    
    /**
     * Gibt die berechtigung eines Users zurück.
     * 
     * @param username
     * @return int
     * @throws Exception 
     */
    public int getAuthortiy(String username) throws Exception
    {        
        Statement stat = db.getStatement();

        String sqlString = "SELECT aid "
                + "FROM guard "
                + "WHERE username = '" + username+"';";

        ResultSet rs = stat.executeQuery(sqlString);
        rs.next();
        return rs.getInt("aid");
    }
    
    /**
     * Überprüft ob in der gegebenen Zelle nur Personen mit der Gleichen Priorität sind.
     * @param cID
     * @param priority
     * @param prID
     * @return boolean
     * @throws Exception 
     */
    public boolean checkPriority(int cID, int priority, int prID) throws Exception
    {
        boolean check = false;

        Statement stat = db.getStatement();        

         String sqlString = "SELECT COUNT(*) AS \"Count\" " +
                            "FROM prisoner " +
                            "WHERE cellid='"+cID+"'";
         
        ResultSet rs = stat.executeQuery(sqlString);
        rs.next();
        if(rs.getInt("Count") <= 1)
        {
            check = true;
            return check;
        }
        
         
       sqlString = "SELECT pid "
                + "FROM prisoner "
                + "WHERE cellid='"+cID+"' AND prid <> "+prID+";";

        ResultSet rs2 = stat.executeQuery(sqlString);
        rs2.next();

        do{            
            if(rs2.getInt("pid")==priority)
            {
                check = true;
            }
            else
            {
                check = false;                
                return check;
            }                    
        }while(rs2.next());          

        return check;
    }
    
    /**
     * Schließt die verbindung zur Datenbank
     * @throws SQLException 
     */
    public void close() throws SQLException
    {        
       db.disconnect();
    }
}
