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
public class KingTest {
    
    private King king;
    
    public KingTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    

    
    @Before
    public void setUp() {
        king = new King(4, 0, Colour.WHITE);
    }
    
    @After
    public void tearDown() {
        king = null;
    }

    /**
     * Test of validMove method, of class King.
     */
    @Test
    public void testValidMove() {
        // horizontal
        int newX = 4;
        int newY = 1;
        boolean expResult = true;
        boolean result = king.validMove(newX, newY);
        assertEquals(expResult, result);
        // diagonal
        newX = 3;
        newY = 1;
        expResult = true;
        result = king.validMove(newX, newY);
        assertEquals(expResult, result);
        // illegal
        newX = 4;
        newY = 2;
        expResult = false;
        result = king.validMove(newX, newY);
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class King.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        
        Type expResult = Type.KING;
        Type result = king.getType();
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of getColour method, of class King.
     */
    @Test
    public void testGetColour() {
        System.out.println("getColour");
        
        Colour expResult = Colour.WHITE;
        Colour result = king.getColour();
        assertEquals(expResult, result);
        
    }


    
}
