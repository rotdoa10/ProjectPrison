package beans;

import java.util.Date;

/**
 *
 * @author 
 */
public class Prisoner 
{

    @Override
    public String toString() {
        return "ID: "+ id + " VN: " + vorname +" NN: "+nachname+" Geb: "+gebDate+" in:  "+ inDate+" out: "+outDate+" Cell: "+cellID;
    }

    private int id;
    private String vorname;
    private String nachname;
    private Date gebDate;
    private Date inDate;
    private Date outDate;
    private int pID;
    private int cellID;

    public Prisoner(int id, String vorname, String nachname, Date gebDate, Date inDate, Date outDate, int pID, int cellID) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.gebDate = gebDate;
        this.inDate = inDate;
        this.outDate = outDate;
        this.pID = pID;
        this.cellID = cellID;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Date getGebDate() {
        return gebDate;
    }

    public void setGebDate(Date gebDate) {
        this.gebDate = gebDate;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public int getCellID() {
        return cellID;
    }

    public void setCellID(int cellID) {
        this.cellID = cellID;
    }

    

    
    
    
}
