package TicTacToe;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class TTT implements ActionListener{

    // Object declaration + boolean
    boolean player1Move;
    private String textA;
    private String textB;
    private String textC;

    private JButton reset;
    Random random = new Random();
    JButton[] btns = new JButton[9];

    JPanel title = new JPanel();
    JPanel mainPanel = new JPanel();
    JPanel extraPanel = new JPanel();
    JFrame frame = new JFrame();
    JLabel label = new JLabel();





    TTT(){
        // frame set up section
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850,850);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        // label set up section
        label.setBackground(new Color(28,90,119));
        label.setForeground(new Color(255,255,255));
        label.setFont(new Font("Comic Sans MS",Font.BOLD,70));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("Tic Tac Toe");
        label.setOpaque(true);

        // Title set up section
        title.setLayout(new BorderLayout());
        title.add(label);
        frame.add(title, BorderLayout.NORTH);

        // TicTacToe.main panel set up section
        mainPanel.setLayout(new GridLayout(3,3));
        mainPanel.setBackground(new Color(28, 90, 119));
        frame.add(mainPanel);

        // extra panel set up section for reset button
        extraPanel.setLayout(new BoxLayout(extraPanel,BoxLayout.Y_AXIS));
        extraPanel.setBackground(new Color(28,90,119));
        frame.add(extraPanel);


        //configures the game buttons
        for(int i=0; i<9; i++){
            btns[i] = new JButton();
            mainPanel.add(btns[i]);
            btns[i].setFont(new Font("Comic Sans MS",Font.BOLD,120));
            btns[i].setBackground(new Color(28, 90,119));
            btns[i].setBorder(new LineBorder(Color.white, 2));
            btns[i].setFocusable(false);
            btns[i].addActionListener(this);

        firstMove();
        }

        // reset button set up
        reset = new JButton("Reset");
        reset.addActionListener(this);
        reset.setFont(new Font("Comic Sans MS",Font.PLAIN,40));
        reset.setBackground(new Color(250,100,0));
        reset.setForeground(new Color(255,255,255));
        extraPanel.add(reset);
        extraPanel.add(mainPanel);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i=0; i<9; i++){
            if (e.getSource() == btns[i]){
                if(player1Move){
                    if(Objects.equals(btns[i].getText(), "")){
                        btns[i].setForeground(new Color(244, 24, 24));
                        btns[i].setText("O");
                        player1Move = false;
                        label.setText("X's turn");
                        label.setForeground(new Color(0, 255, 250));
                        winCheck();
                    }
                } else{

                    if(Objects.equals(btns[i].getText(), "")){
                        btns[i].setForeground(new Color(0, 255, 250));
                        btns[i].setText("X");
                        player1Move = true;
                        label.setText("O's turn");
                        label.setForeground(new Color(253, 77, 77));
                        winCheck();
                    }

                }
            }
        }

        if(e.getSource() == reset){
            resetGame();
        }

    }

    public void firstMove(){

        if(random.nextInt(2)==0){
            player1Move=true;
            label.setText("O's turn");

        } else{
            player1Move=false;
            label.setText("X's turn");
        }
    }
    public void winCheck() {
       boolean emptySquares = false; // checks for tie in game, and set to false until for loop finds an empty square

        int[][] winConditions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}
        };

        for (int[] x : winConditions) {
            int a = x[0];
            int b = x[1];
            int c = x[2];

             textA = btns[a].getText();
             textB = btns[b].getText();
             textC = btns[c].getText();


            if (textA.equals("X") && textB.equals("X") && textC.equals("X")) {
                p1Wins(a, b, c); //calls p1Wins method that sets label, label color, and calls disableClicks()
                return;

            } else if (textA.equals("O") && textB.equals("O") && textC.equals("O")) {
                p2Wins(a, b, c);
                return;
            }

            //draw condition: checks for empty squares, if true game can continue
            if(textA.isEmpty() || textB.isEmpty()||textC.isEmpty()){
                emptySquares = true;
               }

        }
        //if no remaining empty squares, game results in a tie/draw
        if (!emptySquares) {
            label.setText("GG it's a draw!");
            label.setForeground(new Color(250,250,250));

        }



    }

   // resets the game, and the buttons after disableClicks()
    public void resetGame(){

        for(int i=0; i<9; i++){
            btns[i].setText("");
            btns[i].setEnabled(true);
        }
        firstMove();



    }

   //disables buttons when game ends
    private void disableClicks(){
        for(int i=0; i<9;i++){
            btns[i].setEnabled(false);
        }
    }

    public void p1Wins(int a, int b, int c){
        label.setText("X wins!");
        label.setForeground(new Color(250, 250, 250));
        disableClicks();


    }
    public void p2Wins(int a, int b, int c){
        label.setText("O wins!");
        label.setForeground(new Color(250, 250, 250));
        disableClicks();


    }

}
