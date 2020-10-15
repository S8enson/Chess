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
public class Knight extends Piece {

    Type type;

    public Knight(int x, int y, Colour c) {
        super(x, y, c);
        type = Type.KNIGHT;
        if (colour == Colour.BLACK) {
            try {
                img = ImageIO.read(getClass().getResource("/images/BK.gif"));
            } catch (IOException e) {
            }
        } else if (colour == Colour.WHITE) {
            try {
                img = ImageIO.read(getClass().getResource("/images/WK.gif"));
            } catch (IOException e) {
            }
        }

    }

    public Knight(Knight copy) {
        this(copy.x, copy.y, copy.colour);
    }

    // ensures only L shape movement
    @Override
    public boolean validMove(int newX, int newY) {
        int xChange = Math.abs(newX - this.x);
        int yChange = Math.abs(newY - this.y);

        return (xChange == 2 && yChange == 1) || (yChange == 2 && xChange == 1);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        String pieceString = "";
        if (colour == Colour.BLACK) {
            pieceString = "♞";
        } else if (colour == Colour.WHITE) {
            pieceString = "♘";
        }
        return pieceString;
    }

    @Override
    public Colour getColour() {
        return colour;
    }
}
