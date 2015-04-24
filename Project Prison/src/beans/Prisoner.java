/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;

/**
 *
 * @author Dominik
 */
public class Prisoner 
{
    int ID;
    String vorname;
    String nachname;
    Date gebDate;
    Date inDate;
    Date outDate;
    int priority;

    public Prisoner(int ID, String vorname, String nachname, Date gebDate, Date inDate, Date outDate, int priority) {
        this.ID = ID;
        this.vorname = vorname;
        this.nachname = nachname;
        this.gebDate = gebDate;
        this.inDate = inDate;
        this.outDate = outDate;
        this.priority = priority;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    
}
