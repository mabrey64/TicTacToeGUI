import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JFrame
{
    // Panels for the game board
    JPanel mainPanel;
    JPanel gameBoardPanel;
    JPanel buttonPanel;

    // Buttons for the game
    TicTacToeButton[][] buttons;
    JButton exitButton;

    JOptionPane resultPane;
    JOptionPane illegalMovePane;

    private int moveCount = 0;
    private static final int MovesForWin = 5;
    private static final int MovesForTie = 7;

    public TicTacToeFrame()
    {
        // Initialize the components
        gameBoardPanel = new JPanel();
        buttonPanel = new JPanel();
        mainPanel = new JPanel();


        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(gameBoardPanel, BorderLayout.CENTER);

        setTitle("Tic Tac Toe");

        createButtonPanel();

        buttons = new TicTacToeButton[3][3];
        exitButton = new JButton("Exit");

        // Set the layout for the panels
        gameBoardPanel.setLayout(new java.awt.GridLayout(3, 3));
        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                buttons[row][col] = new TicTacToeButton(row, col);
                gameBoardPanel.add(buttons[row][col]);

            }
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Set the size of the frame
        setSize(500, 500);

        add(mainPanel);

        setVisible(true);
        game();
    }

    private void createButtonPanel()
    {
        buttonPanel = new JPanel();
        exitButton = new JButton("Exit");
        buttonPanel.add(exitButton);
        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "The user has exited the game", "Exit", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        });
        add(buttonPanel, java.awt.BorderLayout.SOUTH);
    }
    public void game (){
        for(int row=0; row < 3; row++)
        {
            for(int col=0; col < 3; col++)
            {
                buttons[row][col].clear();
            }
        }
        moveCount = 0;
    }

    private void handleMove(int row, int col) {
        if (!buttons[row][col].getText().equals("")) {
            JOptionPane.showMessageDialog(this, "This is an illegal move", "Illegal Move", JOptionPane.ERROR_MESSAGE);
            return;
        }

        buttons[row][col].setText((moveCount % 2 == 0) ? "X" : "O");
        moveCount++;

        if (moveCount >= MovesForWin) {

        }
    }

}
