/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import chess.Pieces.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import static mvc.Model.board;


public class Controller implements ActionListener {

    public View view;
    public Model model;
    public boolean first;
    public JToggleButton firstButton;
    public String start;
    public String end;
    public boolean white;


    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        this.view.addActionListener(this); // Add Actionlistener (the instance of this class) to View.
        first = true;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // Obtain the text displayed on the component.
        if (command.equals("Log in")) {// Login button
            String wUsername = this.view.wUNInput.getText(); // Obtain username.
           
            String bUsername = this.view.bUNInput.getText(); // Obtain username.
           
           if(!wUsername.equals(bUsername)){
            this.model.checkName(wUsername, bUsername);
            this.view.game();
            this.model.newGame();
           }else{
           this.view.errorMessage("Names Can't Be The Same!");
           }
        } else if (command.equals("Home")) { 
            this.view.home(); 
        }else if (command.equals("New Game")) { 
            this.resetModel();
            this.view.login(); 
        } else if (command.equals("LeaderBoard")) { 
            this.model.leaderboard();
            this.view.leaderboard();
        }else if (e.getSource() instanceof JToggleButton) {
            if (first) {
                start = ((JToggleButton) e.getSource()).getText();
                first = false;
                firstButton = ((JToggleButton) e.getSource());
            } else if (!first) {
                end = ((JToggleButton) e.getSource()).getText();
                first = true;
                ((JToggleButton) e.getSource()).setSelected(false);
                firstButton.setSelected(false);
                this.model.move(start, end);
                
            }
        }
        else if(command.equals("Queen")){
            Piece piece = this.model.lastPiece;
        Piece newPiece = new Queen(piece.x, piece.y, piece.colour);
                board.squares[piece.y][piece.x].setPiece(newPiece);
                this.model.updateBoard();
                this.view.promoOver();
        }
        else if(command.equals("Bishop")){
        Piece piece = this.model.lastPiece;
        Piece newPiece = new Bishop(piece.x, piece.y, piece.colour);
                board.squares[piece.y][piece.x].setPiece(newPiece);
                this.model.updateBoard();
                this.view.promoOver();
        }
        else if(command.equals("Rook")){
        Piece piece = this.model.lastPiece;
        Piece newPiece = new Rook(piece.x, piece.y, piece.colour);
                board.squares[piece.y][piece.x].setPiece(newPiece);
                this.model.updateBoard();
                this.view.promoOver();
        }
        else if(command.equals("Knight")){
        Piece piece = this.model.lastPiece;
        Piece newPiece = new Knight(piece.x, piece.y, piece.colour);
                board.squares[piece.y][piece.x].setPiece(newPiece);
                this.model.updateBoard();
                this.view.promoOver();
        }

    }



    private void resetModel() {
        this.model = new Model();
        model.addObserver(view);
    }

}
