/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Prisoner;
import java.util.Date;
import java.util.LinkedList;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dominik
 */
public class DBAccessTest {
    
    public DBAccessTest() {
    }
  

    /**
     * Test of getPrisoners method, of class DBAccess.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPrisoners() throws Exception {
        System.out.println("getPrisoners");
        DBAccess instance = new DBAccess();
        LinkedList<Prisoner> expResult = null;
        LinkedList<Prisoner> result = instance.getPrisoners();
        assertEquals(expResult, result);        
    }

    /**
     * Test of getPrisoner method, of class DBAccess.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPrisoner() throws Exception {
        System.out.println("getPrisoner");
        int ID = 0;
        DBAccess dba = new DBAccess();
        Prisoner expResult = null;
        Prisoner result = dba.getPrisoner(ID);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of checkLogin method, of class DBAccess.
     */
    @Test
    public void testCheckLogin() throws Exception {
        System.out.println("checkLogin");
        String username = "";
        String password = "";
        DBAccess instance = new DBAccess();
        boolean expResult = false;
        boolean result = instance.checkLogin(username, password);
        assertEquals(expResult, result);

    }

    /**
     * Test of addPrisoner method, of class DBAccess.
     */
    @Test
    public void testAddPrisoner() throws Exception {
        System.out.println("addPrisoner");
        String vorname = "Test";
        String nachname = "Testson";
        Date gebDate = new Date(1992, 3, 15);
        Date inDate = new Date();
        Date outDate = new Date(2050, 12, 11);
        int priority = 1;
        int cellID = 2;
        DBAccess instance = new DBAccess();
        instance.addPrisoner(vorname, nachname, gebDate, inDate, outDate, priority, cellID);

    }

    /**
     * Test of getPrisonersinCell method, of class DBAccess.
     */
    @Test
    public void testGetPrisonersinCell() throws Exception {
        System.out.println("getPrisonersinCell");
        int CID = 2;
        DBAccess instance = new DBAccess();
        LinkedList<Prisoner> expResult = null;
        LinkedList<Prisoner> result = instance.getPrisonersinCell(CID);
        assertEquals(expResult, result);
    }
    
}
