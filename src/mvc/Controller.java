/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

/**
 *
 * @author Shiqing Wu
 */
public class Controller implements ActionListener {

    public View view;
    public Model model;
    public boolean first;
    public JToggleButton firstButton;
    public String start;
    public String end;
    public boolean white;

    /**
     * Step 4: Assign view and model to attributes in the constructor, and add
     * ActionListener(this) to the instance of View.
     *
     * @param view
     * @param model
     */
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        this.view.addActionListener(this); // Add Actionlistener (the instance of this class) to View.
        first = true;
    }

    /**
     * Step 5: Define ActionEvents based on the text displayed on the each
     * button. Note: You must change the property of relating components to
     * public for external access.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // Obtain the text displayed on the component.
        if (command.equals("Log in")) {// Login button
            String wUsername = this.view.wUNInput.getText(); // Obtain username.
            //String wPassword = this.view.wPWInput.getText(); // Obtain password.
            String bUsername = this.view.bUNInput.getText(); // Obtain username.
           // String bPassword = this.view.bPWInput.getText(); // Obtain password.
           if(!wUsername.equals(bUsername)){
            this.model.checkName(wUsername, bUsername);
            this.view.game();
            this.updateBoard();
           }else{
           //errorsamenames
           this.view.errorMessage("Names Can't Be The Same!");
           }// Pass above variables to model. Go to the checkName() of Model.java for step 6.
//            if(!white){
//            this.view.game();
//            this.updateBoard();
//            }
            //white = !white;
        } else if (command.equals("Home")) { // Next button
            // Step 8:
            // Go to the checkAnswer() and quitGame() of Model.java.
            
            
            
            this.view.home(); // Check user's answer.
        }else if (command.equals("New Game")) { // Next button
            // Step 8:
            // Go to the checkAnswer() and quitGame() of Model.java.
            
            this.resetModel();
            this.view.login(); // Check user's answer.
        } else if (command.equals("Leaderboard")) { // Next button
            this.model.leaderboard();
            this.view.leaderboard();
        } else if (command.equals("Quit")) { // Quit button
            //this.model.quitGame(); // Record user's current score.
        } else if (command.equals("test")) { // Quit button
            //this.model.newGame();
            this.view.game();
            this.updateBoard();
            
            //this.model.board.drawBoard(this.view.piecePanel.getGraphics());
            // Record user's current score.
        } else if (e.getSource() instanceof JToggleButton) {
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
                this.updateBoard();
            }
        }

    }

    private void updateBoard() {
        String s;
            
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if(this.model.board.getPiece(i, j) == null){
                    s = "";
                    }else{
                    s = this.model.board.getPiece(i, j).toString();
                    }
                    this.view.setPieceString(s, i, j);
                }
            }
    }

    private void resetModel() {
        this.model = new Model();
        model.addObserver(view);
    }

}
