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

    /*
    Variables for the game
     */
    // Variable that keeps track of the current player
    private boolean isX = true;

    // Variable that keeps track of the number of moves, moves for a tie, and the current player
    private int moveCount = 0;
    private static final int MovesForTie = 9;
    private String currentPlayer = "X";

    public TicTacToeFrame()
    {
        // Initialize the components
        gameBoardPanel = new JPanel();
        buttonPanel = new JPanel();
        mainPanel = new JPanel();

        // Set the layout for the main panel
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(gameBoardPanel, BorderLayout.CENTER);

        // Set the title of the frame
        setTitle("Tic Tac Toe");

        // Create the button panel
        createButtonPanel();

        // Create the game board panel using buttons in a 3x3 grid
        buttons = new TicTacToeButton[3][3];
        exitButton = new JButton("Exit");

        // Set the layout for the panels
        gameBoardPanel.setLayout(new java.awt.GridLayout(3, 3));
        for(int row = 0; row < 3; row++)
        {
            // Create the buttons for the game board
            for(int col = 0; col < 3; col++)
            {
                buttons[row][col] = new TicTacToeButton(row, col, this);
                gameBoardPanel.add(buttons[row][col]);

            }
        }

        // Create the label for the game that is displayed at the bottom of the frame
        JLabel label = new JLabel("Tic-Tac-Toe! Player vs Player (X goes first)", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(label, BorderLayout.NORTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the frame, add the main panel, and make the frame visible
        // Then starts the game
        setSize(500, 500);
        add(mainPanel);
        setVisible(true);
        game();
    }

    // Create the button panel for the frame
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

    /*
    Method that starts the game
    The board is cleared, the move count is set to 0, and the current player is set to X
     */
    public void game (){
        clearBoard();
        moveCount = 0;
        currentPlayer = "X";
        TicTacToeButton.resetIsX();
    }

    // Gets the current player, which is used to determine the next player
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    /*
    Method that handles the move for the game
    The row and column are passed in as parameters
    If the button is not enabled, the method returns
    If the current player is X, the button is set to X
    If the current player is O, the button is set to O
    The move count is incremented
     */
    public void handleMove(int row, int col) {
        if(!buttons[row][col].isEnabled())
        {
            return;
        }

        if(currentPlayer.equals("X"))
        {
            buttons[row][col].setText("X");
        }
        else
        {
            buttons[row][col].setText("O");
        }
        moveCount++;

        // Check for a win or a tie, displaying a message depending on who wins or if there is a tie, then asks the user if they want to play again
        if (isWin(currentPlayer)) {
            int response = JOptionPane.showConfirmDialog(this,"Do you want to play again?" , "Player " + currentPlayer + " wins!", JOptionPane.YES_NO_OPTION);
            SwingUtilities.invokeLater(() -> {
                disableAllButtons(); // Disable buttons after a win
                newGame();
            });

            if (response == JOptionPane.YES_OPTION) {
                game();
            }
            else {
                JOptionPane.showMessageDialog(this, "The user has exited the game", "Exit", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        } else if (moveCount >= MovesForTie && isTie()) {
            int response = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "The game is a tie!", JOptionPane.YES_NO_OPTION);
            SwingUtilities.invokeLater(() -> {
                disableAllButtons(); // Disable buttons after a win
                newGame();
            });
            if (response == JOptionPane.YES_OPTION) {
                game();
            }
            else {
                JOptionPane.showMessageDialog(this, "The user has exited the game", "Exit", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        }
        else {
            currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
        }
    }

    // Disables all the buttons on the game board
    public void disableAllButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setEnabled(false);
            }
        }
    }

    // Starts a new game
    public void newGame()
    {
        clearBoard();
        currentPlayer = "X";
        isX = true;
    }

    // Clears the board
    public void clearBoard()
    {
        // sets all the board elements to a space
        for(int row=0; row < 3; row++)
        {
            for(int col=0; col < 3; col++)
            {
                buttons[row][col].setEnabled(true);
                buttons[row][col].setIcon(null);
                buttons[row][col].setText("");
            }
        }
        // Reset the move count and the current player
        moveCount = 0;
        currentPlayer = "X";
    }

    // Checks if one of the win conditions is met
    private boolean isWin(String player)
    {
        System.out.println("Checking for win for player: " + player);
        boolean colWin = isColWin(player);
        boolean rowWin = isRowWin(player);
        boolean diagWin = isDiagonalWin(player);
        System.out.println("Col Win: " + colWin + ", Row Win: " + rowWin + ", Diag Win: " + diagWin);
        return colWin || rowWin || diagWin;
    }

    // Checks if there is a win in a column, checking every column for a win
    private boolean isColWin(String player)
    {
        // checks for a col win for specified player
        for(int col=0; col < 3; col++)
        {
        if (player.equals(buttons[0][col].getText()) &&
            player.equals(buttons[1][col].getText()) &&
            player.equals(buttons[2][col].getText()))
        {
                return true;
            }
        }
        return false; // no col win
    }

    // Checks if there is a win in a row, checking every row for a win
    private boolean isRowWin(String player)
    {
        // checks for a row win for the specified player
        for(int row=0; row < 3; row++)
        {
        if (player.equals(buttons[row][0].getText()) &&
            player.equals(buttons[row][1].getText()) &&
            player.equals(buttons[row][2].getText()))
            {
                return true;
            }
        }
        return false; // no row win
    }

    // Checks if there is a win in a diagonal, checking both diagonals for a win
    private boolean isDiagonalWin(String player)
    {
        // checks for a diagonal win for the specified player

        if (player.equals(buttons[0][0].getText()) &&
            player.equals(buttons[1][1].getText()) &&
            player.equals(buttons[2][2].getText()))
        {
            return true;
        }
        if (player.equals(buttons[0][2].getText()) &&
            player.equals(buttons[1][1].getText()) &&
            player.equals(buttons[2][0].getText()))
        {
            return true;
        }
        return false;
    }

    // Checks if there is a tie, checking every possible tie condition after 9 moves
    private boolean isTie() {
        boolean xFlag = false;
        boolean oFlag = false;

        // Check for row ties
        for (int row = 0; row < 3; row++) {
            if ("X".equals(buttons[row][0].getText()) ||
                "X".equals(buttons[row][1].getText()) ||
                "X".equals(buttons[row][2].getText())) {
                xFlag = true; // there is an X in this row
            }
            if ("O".equals(buttons[row][0].getText()) ||
                "O".equals(buttons[row][1].getText()) ||
                "O".equals(buttons[row][2].getText())) {
                oFlag = true; // there is an O in this row
            }

            if (!(xFlag && oFlag)) {
                return false; // No tie can still have a row win
            }

            xFlag = oFlag = false;
        }

        // Check for column ties
        for (int col = 0; col < 3; col++) {
            if ("X".equals(buttons[0][col].getText()) ||
                "X".equals(buttons[1][col].getText()) ||
                "X".equals(buttons[2][col].getText())) {
                xFlag = true; // there is an X in this col
            }
            if ("O".equals(buttons[0][col].getText()) ||
                "O".equals(buttons[1][col].getText()) ||
                "O".equals(buttons[2][col].getText())) {
                oFlag = true; // there is an O in this col
            }

            if (!(xFlag && oFlag)) {
                return false; // No tie can still have a col win
            }
        }

        // Check for diagonal ties
        xFlag = oFlag = false;

        if ("X".equals(buttons[0][0].getText()) ||
            "X".equals(buttons[1][1].getText()) ||
            "X".equals(buttons[2][2].getText())) {
            xFlag = true;
        }
        if ("O".equals(buttons[0][0].getText()) ||
            "O".equals(buttons[1][1].getText()) ||
            "O".equals(buttons[2][2].getText())) {
            oFlag = true;
        }
        if (!(xFlag && oFlag)) {
            return false; // No tie can still have a diag win
        }

        xFlag = oFlag = false;

        if ("X".equals(buttons[0][2].getText()) ||
            "X".equals(buttons[1][1].getText()) ||
            "X".equals(buttons[2][0].getText())) {
            xFlag = true;
        }
        if ("O".equals(buttons[0][2].getText()) ||
            "O".equals(buttons[1][1].getText()) ||
            "O".equals(buttons[2][0].getText())) {
            oFlag = true;
        }
        if (!(xFlag && oFlag)) {
            return false; // No tie can still have a diag win
        }

        // Checked every vector so I know I have a tie
        return true;
    }
}
