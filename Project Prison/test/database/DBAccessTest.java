/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Prisoner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Dominik Roth, ProjectPrison, 10.06.2015
 */
public class DBAccessTest {

    public DBAccessTest() {
    }

    static DBAccess instance;
    static Prisoner p1;
    static Prisoner p2;

    /**
     * Here, the used variables are initialized for use in the Test methods
     *
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        instance = new DBAccess();

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date d1 = sdf.parse("26.11.1996");
        Date d2 = sdf.parse("01.03.2013");
        Date d3 = sdf.parse("22.05.2015");


        p1 = new Prisoner(1, "Patrizia", "Neubauer", d1, d2, d3, 4, 5);
        
        d1 = sdf.parse("03.01.1996");
        d2 = sdf.parse("04.05.2015");
        d3 = sdf.parse("01.01.1996");

        p2 = new Prisoner(2, "Dominik", "Roth", d1, d2, d3, 4, 5);
    }

    /**
     * Test of getPrisoner method, of class DBAccess.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPrisoner() throws Exception {
        System.out.println("getPrisoner");
        int ID = 1;

        Prisoner expResult = p1;
        Prisoner result = instance.getPrisoner(ID);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkLogin method, of class DBAccess.
     */
    @Test
    public void testCheckLogin() throws Exception {
        System.out.println("checkLogin");
        String username = "bernd";
        String password = "Berni3";
        boolean expResult = true;
        boolean result = instance.checkLogin(username, password);
        assertEquals(expResult, result);
    }
    
        /**
     * Test of checkLogin method, of class DBAccess.
     */
    @Test
    public void testCheckLoginFalse() throws Exception {
        System.out.println("checkLogin");
        String username = "bernd";
        String password = "Berni2";
        boolean expResult = false;
        boolean result = instance.checkLogin(username, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrisonersinCell method, of class DBAccess.
     */
    @Test
    public void testGetPrisonersinCell() throws Exception {
        System.out.println("getPrisonersinCell");
        int CID = 5;

        LinkedList<Prisoner> prl = new LinkedList<>();

        prl.add(p1);
        //prl.add(p2);

        LinkedList<Prisoner> expResult = prl;

        LinkedList<Prisoner> result = instance.getPrisonersinCell(CID);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCells method, of class DBAccess.
     */
    @Test
    public void testGetCells() throws Exception {
        System.out.println("getCells");
        LinkedList<Integer> expResult = new LinkedList<>();
        for (int i = 1; i < 10; i++) {
            expResult.add(i);
        }

        LinkedList<String> result = instance.getCells();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAuthortiy method, of class DBAccess.
     */
    @Test
    public void testGetAuthortiyRead() throws Exception {
        System.out.println("getAuthortiy");
        String username = "heino";
        int expResult = 1;
        int result = instance.getAuthortiy(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAuthortiy method, of class DBAccess.
     */
    @Test
    public void testGetAuthortiyWrite() throws Exception {
        System.out.println("getAuthortiy");
        String username = "bernd";
        int expResult = 2;
        int result = instance.getAuthortiy(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkPriority method, of class DBAccess.
     */
    @Test
    public void testCheckPriority() throws Exception {
        System.out.println("checkPriority");
        int cID = 4;
        int priority = 3;
        int prID = 5;

        boolean expResult = true;
        boolean result = instance.checkPriority(cID, priority, prID);
        assertEquals(expResult, result);

    }
}
