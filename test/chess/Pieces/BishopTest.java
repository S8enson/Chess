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
public class BishopTest {
    
    private Bishop bishop;
    
    public BishopTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    

    
    @Before
    public void setUp() {
        bishop = new Bishop(2, 0, Colour.WHITE);
    }
    
    @After
    public void tearDown() {
        bishop = null;
    }

    /**
     * Test of validMove method, of class Bishop.
     */
    @Test
    public void testValidMove() {
        
        int newX = 0;
        int newY = 2;
        boolean expResult = true;
        boolean result = bishop.validMove(newX, newY);
        assertEquals(expResult, result);
        newX = 2;
        newY = 2;
        expResult = false;
        result = bishop.validMove(newX, newY);
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class Bishop.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        
        Type expResult = Type.BISHOP;
        Type result = bishop.getType();
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of getColour method, of class Bishop.
     */
    @Test
    public void testGetColour() {
        System.out.println("getColour");
        
        Colour expResult = Colour.WHITE;
        Colour result = bishop.getColour();
        assertEquals(expResult, result);
        
    }


    
}
