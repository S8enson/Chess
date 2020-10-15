package chess.Pieces;

import mvc.Colour;
import chess.Player;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/*
 * Abstract class that outlines basic methods shared by all pieces
 */

/**
 *
 * @author Sam
 */
public abstract class Piece {
    
    public int x, y;
    public Colour colour;
    public boolean checking;
    public boolean alive;
    public BufferedImage img;
    
    public Piece(int x, int y, Colour c){
    
       this.x = x;
        this.y = y;
        this.colour = c;
        this.checking = false;
        this.img = null;
    }
    
    public Piece(Piece copy){
    this.x = copy.x;
    this.y = copy.y;
    this.colour = copy.colour;
    this.checking = copy.checking;
    this.img = copy.img;
    }
    

    
    public abstract boolean validMove(int newX, int newY);
    
    public abstract Type getType();
    
    public abstract Colour getColour();
    
    public abstract String toString();
    
    public void drawPiece(Graphics g, int x, int y){
        g.drawImage(img, x, y, null);
    }
    
}
