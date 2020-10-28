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
public class KnightTest {
    
    private Knight knight;
    
    public KnightTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    

    
    @Before
    public void setUp() {
        knight = new Knight(1, 0, Colour.WHITE);
    }
    
    @After
    public void tearDown() {
        knight = null;
    }

    /**
     * Test of validMove method, of class Knight.
     */
    @Test
    public void testValidMove() {
        
        int newX = 2;
        int newY = 2;
        boolean expResult = true;
        boolean result = knight.validMove(newX, newY);
        assertEquals(expResult, result);
        newX = 1;
        newY = 2;
        expResult = false;
        result = knight.validMove(newX, newY);
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class Knight.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        
        Type expResult = Type.KNIGHT;
        Type result = knight.getType();
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of getColour method, of class Knight.
     */
    @Test
    public void testGetColour() {
        System.out.println("getColour");
        
        Colour expResult = Colour.WHITE;
        Colour result = knight.getColour();
        assertEquals(expResult, result);
        
    }


    
}
