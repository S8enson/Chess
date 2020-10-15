package chess.Pieces;

import mvc.Colour;
import chess.Player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;
import mvc.Space;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sam
 */
public class Bishop extends Piece {

    Type type;

    public Bishop(int x, int y, Colour c) {
        super(x, y, c);
        type = Type.BISHOP;
        if (colour == Colour.BLACK) {
            try {
                img = ImageIO.read(getClass().getResource("/images/BB.gif"));
            } catch (IOException e) {
            }
        } else if (colour == Colour.WHITE) {
            try {
                img = ImageIO.read(getClass().getResource("/images/WB.gif"));
            } catch (IOException e) {
            }
        }

    }

    public Bishop(Bishop copy) {
        this(copy.x, copy.y, copy.colour);
    }

    // ensures horizontal movement is equal to vertical movement
    @Override
    public boolean validMove(int newX, int newY) {

        int xChange = Math.abs(newX - this.x);
        int yChange = Math.abs(newY - this.y);

        return xChange == yChange;

    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public Colour getColour() {
        return colour;
    }

    @Override
    public String toString() {
        String pieceString = "";
        if (colour == Colour.BLACK) {
            pieceString = "♝";
        } else if (colour == Colour.WHITE) {
            pieceString = "♗";
        }
        return pieceString;
    }


}
