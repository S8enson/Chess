/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            String username = this.view.unInput.getText(); // Obtain username.
            String password = this.view.pwInput.getText(); // Obtain password.
            this.model.checkName(username, password); // Pass above variables to model. Go to the checkName() of Model.java for step 6.
        } else if (command.equals("New Game")) { // Next button
            // Step 8:
            // Go to the checkAnswer() and quitGame() of Model.java.
            this.view.login(); // Check user's answer.
        } else if (command.equals("Leaderboard")) { // Next button
            // Step 8:
            // Go to the checkAnswer() and quitGame() of Model.java.
            //this.model.checkAnswer(this.view.calcSolution.getText()); // Check user's answer.
        } else if (command.equals("Quit")) { // Quit button
            //this.model.quitGame(); // Record user's current score.
        } else if (command.equals("test")) { // Quit button
            //this.model.newGame();
            this.view.test();
            this.model.board.drawBoard(this.view.boardPanel.getGraphics());
            // Record user's current score.
        } else if (e.getSource() instanceof JToggleButton) {
            if (first) {
                String start = ((JToggleButton) e.getSource()).getText();
                first = false;
                firstButton = ((JToggleButton) e.getSource());
            } else if (!first) {
                String end = ((JToggleButton) e.getSource()).getText();
                first = true;
                ((JToggleButton) e.getSource()).setSelected(false);
                firstButton.setSelected(false);
            }
        }

    }

}
