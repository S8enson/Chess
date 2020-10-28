/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.Pieces;

import mvc.Colour;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sam
 */
public class RookTest {
    
    private Rook rook;
    
    public RookTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    

    
    @Before
    public void setUp() {
        rook = new Rook(0, 0, Colour.WHITE);
    }
    
    @After
    public void tearDown() {
        rook = null;
    }

    /**
     * Test of validMove method, of class Rook.
     */
    @Test
    public void testValidMove() {
        
        int newX = 0;
        int newY = 2;
        boolean expResult = true;
        boolean result = rook.validMove(newX, newY);
        assertEquals(expResult, result);
        newX = 2;
        newY = 0;
        expResult = true;
        result = rook.validMove(newX, newY);
        assertEquals(expResult, result);
        newX = 2;
        newY = 2;
        expResult = false;
        result = rook.validMove(newX, newY);
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class Rook.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        
        Type expResult = Type.ROOK;
        Type result = rook.getType();
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of getColour method, of class Rook.
     */
    @Test
    public void testGetColour() {
        System.out.println("getColour");
        
        Colour expResult = Colour.WHITE;
        Colour result = rook.getColour();
        assertEquals(expResult, result);
        
    }


    
}
