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


//    public boolean containsBook(LinkedList<Book> list, String isbn) {
//        for (Book b : list) {
//            if (b.getIsbn().equals(isbn)) {
//                return true;
//            }
//        }
//        return false;
//    }
//  
//    public void addPrisoner(int ID, String vorname, String nachname, Date gebDate, Date inDate, Date outDate, int priority) throws Exception
//    {
//        Statement stat = db.getStatement();
//        String sqlString;
//        
//        sqlString = "SELECT p.publisher_id, p.url "
//                  + "FROM publishers p "
//                  + "WHERE LOWER(p.name)=LOWER('"+pub+"')";
//        ResultSet rs = stat.executeQuery(sqlString);
//        rs.next();
//        String pub_id = rs.getString("publisher_id");
//        String url = rs.getString("url");
//        
//        sqlString = "SELECT * "
//                  + "FROM authors "
//                  + "WHERE name ='"+autor+"'";
//        
//        rs = stat.executeQuery(sqlString);
//        rs.next();
//        
//        String aut_id;
//        
//        if(rs.equals(null))
//        {
//            aut_id = autor.substring(0, 3).trim().toUpperCase();
//            sqlString = "INSERT INTO authors(name, author_id) "
//                      + "VALUES('"+autor+", '"+ aut_id +"')";
//            rs = stat.executeQuery(sqlString);
//        }
//        else
//        {
//            aut_id = rs.getString("author_id");
//        }
//        
//        
//        
//        sqlString = "INSERT INTO booksauthors(isbn, author_id) "
//                  + "VALUES("+isbn+", UPPER("+ aut_id +"))";
//        rs = stat.executeQuery(sqlString);
//        
//        
//        
//        sqlString = "INSERT INTO books(title, isbn, publisher_id, url, price) "
//                  + "VALUES('"+title+"', "+isbn+", "+pub_id+", '"+url+"/"+title+"')";
//        rs = stat.executeQuery(sqlString);
//    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, SQLException, Exception {
        DBAccess dba = new DBAccess();     
    }
}
