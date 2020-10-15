/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import images.HomePanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JToggleButton;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

/**
 *
 * @author Shiqing Wu
 */
public class View extends JFrame implements Observer {

    private JPanel homePanel = new HomePanel();
    private JPanel userPanel = new JPanel(new GridLayout(0, 1));
    BoardPanel boardPanel = new BoardPanel();
    JPanel gamePanel = new JPanel(new CardLayout());
    JPanel piecePanel = new JPanel();
    private JLabel wUName = new JLabel("Username: ");
    private JLabel wPWord = new JLabel("Password: ");
    public JTextField wUNInput = new JTextField(10);
    public JTextField wPWInput = new JTextField(10);
    private JLabel bUName = new JLabel("Username: ");
    private JLabel bPWord = new JLabel("Password: ");
    public JTextField bUNInput = new JTextField(10);
    public JTextField bPWInput = new JTextField(10);
    private JLabel wrongName = new JLabel("Wrong username or password!");

    public static JPanel[] piecePanels = new JPanel[64];
    public static JToggleButton[] buttons = new JToggleButton[64];

    private JLabel title = new JLabel("Chess", JLabel.CENTER);
    private JLabel firstNumber = new JLabel();
    private JLabel secondNumber = new JLabel();
    private JLabel additionLabel = new JLabel("+");
    //private JTextField secondNumber = new JTextField(10);
    private JButton newGameButton = new JButton("New Game");
    private JButton leaderboardButton = new JButton("LeaderBoard");
    private JButton testButton = new JButton("test");
    private JButton nextButton = new JButton("Next");
    private JButton quitButton = new JButton("Quit");
    private JButton loginButton = new JButton("Log in");

    public JLabel wMessage = new JLabel("White Player Enter Details", JLabel.CENTER);
    public JLabel bMessage = new JLabel("Black Player Enter Details", JLabel.CENTER);
    public JTextField calcSolution = new JTextField(10);
    private Image homeBG;
    //private Graphics g;

    private boolean started = false; // To identify if the game part starts.

    /**
     * Step 1: The constructor initializes the frame window as well as the login
     * interface.
     *
     * Note: We need to define the events of ActionListener in the Controller
     * class. Go to Model.java for Step 2.
     */
    public View() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // Make the frame located at the absolute center of the screen.
        this.userPanel.add(wMessage);
        this.userPanel.add(wUName);
        this.userPanel.add(wUNInput);
//        this.userPanel.add(wPWord);
//        this.userPanel.add(wPWInput);
        this.userPanel.add(bMessage);
        this.userPanel.add(bUName);
        this.userPanel.add(bUNInput);
//        this.userPanel.add(bPWord);
//        this.userPanel.add(bPWInput);
        
        this.userPanel.add(loginButton);

        title.setPreferredSize(new Dimension(200, 200));
        title.setFont(new Font("Arial Black", Font.PLAIN, 48));
        title.setForeground(Color.WHITE);
        newGameButton.setPreferredSize(new Dimension(200, 40));
        testButton.setPreferredSize(new Dimension(200, 40));
        leaderboardButton.setPreferredSize(new Dimension(200, 40));
        this.homePanel.add(title, BorderLayout.PAGE_START);
        this.homePanel.add(testButton, BorderLayout.PAGE_END);
        this.homePanel.add(newGameButton, BorderLayout.PAGE_END);
        this.homePanel.add(leaderboardButton, BorderLayout.PAGE_END);
        home();
        
        gamePanel = createPanel();
        this.gamePanel.add(piecePanel);

        this.setVisible(true);
    }

//    public void startQuiz() {
//        calcPanel.add(firstNumber);
//        calcPanel.add(additionLabel);
//        calcPanel.add(secondNumber);
//
//        calcPanel.add(calcSolution);
//        calcPanel.add(nextButton);
//        calcPanel.add(quitButton);
//
//        this.getContentPane().removeAll();
//        calcPanel.setVisible(true);
//        this.add(calcPanel);
//        this.revalidate();
//        this.repaint();
//
//    }
    public void home() {

        this.add(this.homePanel);
        this.repaint();

    }

    public void login() {
        this.getContentPane().removeAll();
        userPanel.setVisible(true);
        this.add(this.userPanel);
        this.revalidate();
        this.repaint();
    }

    public void game() {
        
        
        this.getContentPane().removeAll();
        gamePanel.setVisible(true);
        
        this.add(this.gamePanel);

        this.revalidate();
        this.repaint();

    }

    private static JPanel createPanel() {

        Color light = new Color(255, 223, 158);
        Color dark = new Color(138, 101, 54);
        JPanel mainPanel = new JPanel() {
            @Override
            public boolean isOptimizedDrawingEnabled() {
                return false;
            }
        };
        mainPanel.setLayout(new OverlayLayout(mainPanel));
        mainPanel.setSize(1000, 1000);

        JPanel buttonPanel = new JPanel(new GridLayout(8, 8));
        JPanel popPanel = new JPanel(new GridLayout(8, 8));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String s = Character.toString((char) (j + 65)) + (8 - i);
                JToggleButton button = new JToggleButton(s);
                button.setVerticalAlignment(SwingConstants.BOTTOM);

                JPanel popupPanel = createPopupPanel(button);
                piecePanels[8 * i + j] = popupPanel;
                buttons[8 * i + j] = button;
                popupPanel.setVisible(true);

                
                if (i % 2 == 1) {
                    if (j % 2 == 0) {
                        
                        button.setBackground(dark);
                    } else {
                        
                        button.setBackground(light);
                    }
                } else {
                    if (j % 2 == 0) {
                        
                        button.setBackground(light);
                    } else {
                        
                        button.setBackground(dark);
                    }
                }
                button.setOpaque(true);
                button.setBorderPainted(false);
                buttonPanel.add(button);
                popPanel.add(popupPanel);
                popPanel.setOpaque(false);
                mainPanel.add(popPanel);
                mainPanel.add(buttonPanel);
            }
        }

        return mainPanel;
    }

    private static JPanel createPopupPanel(JComponent overlapComponent) {
        JPanel popupPanel = new JPanel(new BorderLayout());
        popupPanel.setOpaque(false);
        popupPanel.setMaximumSize(new Dimension(800, 800));
        

        JLabel label = new JLabel("");

        label.setFont(new Font("Arial", Font.PLAIN, 50));

        popupPanel.add(wrapInPanel(label), BorderLayout.NORTH);

        
        return popupPanel;
    }

    private static JPanel wrapInPanel(JComponent component) {
        JPanel jPanel = new JPanel();
        jPanel.setBackground(new Color(50, 210, 250, 0));
        jPanel.add(component);
        return jPanel;
    }

    public void setPieceString(String s, int x, int y) {
        JLabel label = ((JLabel) ((JPanel) View.piecePanels[8 * (7 - x) + y].getComponent(0)).getComponent(0));
        label.setText(s);
    }

    public void addActionListener(ActionListener listener) {
        this.newGameButton.addActionListener(listener);
        this.testButton.addActionListener(listener);
        this.leaderboardButton.addActionListener(listener);
        this.loginButton.addActionListener(listener);
        this.nextButton.addActionListener(listener);
        this.quitButton.addActionListener(listener);
        for (int i = 0; i < 64; i++) {
            JToggleButton button = this.buttons[i];
            button.addActionListener(listener);
        }

    }

    private void quitGame(int score) {
        JPanel quitPanel = new JPanel();
        JLabel scoreLabel = new JLabel("Your score: " + score);
        quitPanel.add(scoreLabel);
        this.getContentPane().removeAll();
        
        this.add(quitPanel);
        this.revalidate();
        this.repaint();
    }

    /**
     * Step 7: Define the event when model has been modified.
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
//        Data data = (Data) arg; // Obtain the instance of data.
//        if (!data.whiteLoginFlag) { // If loginFlage is false, then ask the user to input again.
//            this.wUNInput.setText("");
//            this.wPWInput.setText("");
//            this.wMessage.setText("Invalid username or password.");
//        }
//        else if (!data.blackLoginFlag) { // If loginFlage is false, then ask the user to input again.
//            this.bUNInput.setText("");
//            this.bPWInput.setText("");
//            this.bMessage.setText("Invalid username or password.");
//        } else if (!this.started) { // If the game has not started, then start the game.
            //this.startQuiz(); // Change the interface of the frame.
            this.started = true;
            this.game();
            //this.setQuestion(data.num1, data.num2); // Show the question on the interface.
            /**
             * You need to define ActionEvent for the next and the quit buttons
             * in the Controller.java. Back to Controller.java for Step 8.
             *
             * After you finish Step 9, complete last two conditions.
             */
//        } else if (data.quitFlag) { // If user quits the game, display user's current score.
//            //this.quitGame(data.currentScore);
//        } else { // Otherwise, update a new question for the user.
//            //this.setQuestion(data.num1, data.num2);
//        }
    }

}
