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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class View extends JFrame implements Observer {

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Options");
    private JMenuItem newGameItem = new JMenuItem("New Game");
    private JMenuItem leaderboardItem = new JMenuItem("Leaderboard");
    private JMenuItem homeItem = new JMenuItem("Home");

    private JPanel homePanel = new HomePanel();
    private JPanel userPanel = new JPanel(new GridLayout(0, 1));
    BoardPanel boardPanel = new BoardPanel();
    JPanel gamePanel = new JPanel(new CardLayout());
    JPanel piecePanel = new JPanel();
    private JPanel leaderboardPanel = new JPanel();

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
    //private JButton testButton = new JButton("test");
    private JButton nextButton = new JButton("Next");
    private JButton quitButton = new JButton("Quit");
    private JButton loginButton = new JButton("Log in");
    private JButton bishopButton = new JButton("Bishop");
    private JButton queenButton = new JButton("Queen");
    private JButton rookButton = new JButton("Rook");
    private JButton knightButton = new JButton("Knight");

    public JLabel wMessage = new JLabel("White Player Enter Details", JLabel.CENTER);
    public JLabel bMessage = new JLabel("Black Player Enter Details", JLabel.CENTER);
    public JTextField calcSolution = new JTextField(10);
    private Image homeBG;

    public DefaultTableModel leaderboardModel = new DefaultTableModel(new Object[]{"Rank", "Player", "Wins", "Losses", "W/L Ratio"}, 0);
    public JTable leaderboardTable = new JTable(leaderboardModel);

    private boolean started = false; // To identify if the game part starts.
    private JOptionPane frame;
    private JDialog promotion;

    JTableHeader header = leaderboardTable.getTableHeader();

   
    public View() {
        promotion = new JDialog(this, "Pawn Promotion");
        promotion.setAlwaysOnTop(true);
        promotion.setSize(300, 200);
        promotion.setLocationRelativeTo(null);
        promotion.getContentPane().setLayout(new BoxLayout(promotion.getContentPane(), BoxLayout.Y_AXIS));
        promotion.add(new JLabel("Choose a piece to be promoted to"));

        promotion.add(queenButton);
        promotion.add(rookButton);
        promotion.add(knightButton);
        promotion.add(bishopButton);

        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // Make the frame located at the absolute center of the screen.
        this.userPanel.add(wMessage);
        this.userPanel.add(wUName);
        this.userPanel.add(wUNInput);

        this.userPanel.add(bMessage);
        this.userPanel.add(bUName);
        this.userPanel.add(bUNInput);

        this.userPanel.add(loginButton);

        this.menu.add(homeItem);
        this.menu.add(newGameItem);
        this.menu.add(leaderboardItem);
        this.menuBar.add(menu);
        this.setJMenuBar(menuBar);

        title.setPreferredSize(new Dimension(200, 200));
        title.setFont(new Font("Arial Black", Font.PLAIN, 48));
        title.setForeground(Color.WHITE);
        newGameButton.setPreferredSize(new Dimension(200, 40));
        //testButton.setPreferredSize(new Dimension(200, 40));
        leaderboardButton.setPreferredSize(new Dimension(200, 40));
        this.homePanel.add(title, BorderLayout.PAGE_START);
        //this.homePanel.add(testButton, BorderLayout.PAGE_END);
        this.homePanel.add(newGameButton, BorderLayout.PAGE_END);
        this.homePanel.add(leaderboardButton, BorderLayout.PAGE_END);
        home();

        gamePanel = createPanel();
        this.gamePanel.add(piecePanel);

        this.leaderboardPanel.setLayout(new BorderLayout());
        this.leaderboardPanel.add(header, BorderLayout.NORTH);
        this.leaderboardPanel.add(leaderboardTable, BorderLayout.CENTER);
        this.leaderboardPanel.setSize(800, 800);
        this.leaderboardPanel.add(leaderboardTable);
        this.leaderboardTable.setEnabled(false);
        

        this.setVisible(true);
    }

    public void home() {
        this.getContentPane().removeAll();

        homePanel.setVisible(true);
        this.add(this.homePanel);
        this.revalidate();
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

    public void setPieceString(Board b) {
        String s;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (b.getPiece(i, j) == null) {
                    s = "";
                } else {
                    s = b.getPiece(i, j).toString();
                }
                JLabel label = ((JLabel) ((JPanel) View.piecePanels[8 * (7 - i) + j].getComponent(0)).getComponent(0));
                label.setText(s);
            }
        }

    }

    public void addActionListener(ActionListener listener) {
        queenButton.addActionListener(listener);
        rookButton.addActionListener(listener);
        bishopButton.addActionListener(listener);
        knightButton.addActionListener(listener);
        this.homeItem.addActionListener(listener);
        this.newGameItem.addActionListener(listener);
        this.leaderboardItem.addActionListener(listener);
        this.newGameButton.addActionListener(listener);
        //this.testButton.addActionListener(listener);
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


    @Override
    public void update(Observable o, Object arg) {

        if (arg instanceof String) {
            if (arg == "PROMOTION") {
                promotion();
            } else if(arg == "You are checked"){
            this.checkNotice((String)arg);
            }else {
                this.wrongMove((String) arg);
            }
        } else if (arg instanceof Data) {
            this.gameOver(((Data) arg).winner);
        } else if (arg instanceof Board) {
            setPieceString((Board) arg);
        } else if (arg instanceof ResultSet) {
            updateLeaderboard((ResultSet) arg);
        } else {
            this.started = true;
            this.game();
        }

    }

    public void leaderboard() {
        this.getContentPane().removeAll();
        leaderboardPanel.setVisible(true);
        this.add(this.leaderboardPanel);
        this.revalidate();
        this.repaint();
    }

    public void wrongMove(String s) {
        JOptionPane.showMessageDialog(frame,
                s,
                "Invalid Move",
                JOptionPane.ERROR_MESSAGE);

    }

    public void gameOver(String winner) {
        JOptionPane.showMessageDialog(frame,
                "Checkmate, " + winner + " wins!",
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE);
        this.leaderboard();

    }

    public void errorMessage(String error) {
        JOptionPane.showMessageDialog(frame,
                error,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private void promotion() {
        promotion.setVisible(true);

    }

    public void promoOver() {
        promotion.setVisible(false);
    }

    private void updateLeaderboard(ResultSet rs) {
        while (leaderboardTable.getRowCount() > 0) {
            leaderboardModel.removeRow(0);
        }
        try {

            int i = 1;
            while (rs.next()) {
                Object[] row = new Object[5];
                row[0] = i++;
                row[1] = rs.getString("userid");
                row[2] = rs.getInt("wins");
                row[3] = rs.getInt("losses");
                row[4] = rs.getInt("wins") / (double) rs.getInt("losses");
                leaderboardModel.addRow(row);

            }
        } catch (SQLException ex) {

        }

    }

    private void checkNotice(String s) {
                JOptionPane.showMessageDialog(frame,
                s,
                "Attention!",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
