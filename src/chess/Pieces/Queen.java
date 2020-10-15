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
public class Queen extends Piece{
    
        Type type;
            
         public Queen(int x, int y, Colour c){
         super(x, y, c);
         type = Type.QUEEN;
                 if (colour == Colour.BLACK) {
            try {
                img = ImageIO.read(getClass().getResource("/images/BQ.gif"));
            } catch (IOException e) {
            }
        } else if (colour == Colour.WHITE) {
            try {
                img = ImageIO.read(getClass().getResource("/images/WQ.gif"));
            } catch (IOException e) {
            }
        }
         }
         
         public Queen(Queen copy){
         
         this(copy.x, copy.y, copy.colour);
         }
         
         
         // ensures move is in a line
             @Override
    public boolean validMove(int newX, int newY) {
        return diagonalValid(newX, newY) || straightValid(newX, newY);
    }

    public boolean diagonalValid(int newX, int newY){
    int xChange = Math.abs(newX-this.x);
        int yChange = Math.abs(newY-this.y);
        
        return xChange == yChange;
    }
    
    public boolean straightValid(int newX, int newY){
            int xChange = Math.abs(newX-this.x);
        int yChange = Math.abs(newY-this.y);
        
        return xChange == 0 || yChange == 0;
    
    }
    @Override
    public Type getType() {
        return type;
    }
    
        @Override
    public String toString() {
        String pieceString = "";
        if(colour== Colour.BLACK){
            pieceString = "♛";
        }
        else if(colour== Colour.WHITE){
            pieceString = "♕";
        }
        return  pieceString;
    }

    @Override
    public Colour getColour() {
        return colour;
    }
}
