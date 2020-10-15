package chess.Pieces;

import mvc.Colour;
import chess.Player;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sam
 */
public class Pawn extends Piece {

    Type type;

    public Pawn(int x, int y, Colour c) {
        super(x, y, c);
        type = Type.PAWN;
                if (colour == Colour.BLACK) {
            try {
                img = ImageIO.read(getClass().getResource("/images/BP.gif"));
            } catch (IOException e) {
            }
        } else if (colour == Colour.WHITE) {
            try {
                img = ImageIO.read(getClass().getResource("/images/WP.gif"));
            } catch (IOException e) {
            }
        }

    }
    
    public Pawn(Pawn copy){
         this(copy.x, copy.y, copy.colour);
         }

    //ensures move is forward 1 or 2(only if first move)
    @Override
    public boolean validMove(int newX, int newY) {

        //standard move
        if (colour == Colour.WHITE) {
            if ((newX == x && newY == y + 1) || (newX == x && newY == y + 2 && y == 1)) {
                return true;
            }

        }
        if (colour == Colour.BLACK) {
            if ((newX == x && newY == y - 1) || (newX == x && newY == y - 2 && y == 6)) {
                return true;
            }
        }
        // take piece
        
        // en passant

        return false;
    }
    
    public boolean validTake(int newX, int newY){
    int xChange = Math.abs(newX-this.x);
        int yChange = Math.abs(newY-this.y);
        
        return xChange == yChange && xChange == 1;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        String pieceString = "";
        if (colour == Colour.BLACK) {
            pieceString = "♟";
        } else if (colour == Colour.WHITE) {
            pieceString = "♙";
        }
        return pieceString;
    }

    @Override
    public Colour getColour() {
        return colour;
    }
}
