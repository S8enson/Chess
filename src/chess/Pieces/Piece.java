package chess.Pieces;

import chess.Colour;
import chess.Player;
import chess.Type;

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
    
    public Piece(int x, int y, Colour c){
    
       this.x = x;
        this.y = y;
        this.colour = c;
        this.checking = false;
    }
    
    public Piece(Piece copy){
    this.x = copy.x;
    this.y = copy.y;
    this.colour = copy.colour;
    this.checking = copy.checking;
    }
    

    
    public abstract boolean validMove(int newX, int newY);
    
    public abstract Type getType();
    
    public abstract Colour getColour();
    
    public abstract String toString();
    
}
