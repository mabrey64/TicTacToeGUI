import javax.swing.*;

public class TicTacToeFrame extends JFrame
{
    // Panels for the game board
    JPanel gameBoardPanel;
    JPanel buttonPanel;
    JPanel labelPanel;

    // Labels for the game
    JLabel playerLabel;
    JLabel gameStatusLabel;

    // Buttons for the game
    JButton[][] buttons;
    JButton exitButton;

    public TicTacToeFrame()
    {
        // Initialize the components
        gameBoardPanel = new JPanel();
        buttonPanel = new JPanel();
        labelPanel = new JPanel();

        playerLabel = new JLabel("Player: X");
        gameStatusLabel = new JLabel("");

        buttons = new JButton[3][3];
        exitButton = new JButton("Exit");

        // Set the layout for the panels
        gameBoardPanel.setLayout(new java.awt.GridLayout(3, 3));
        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                buttons[row][col] = new JButton();
                gameBoardPanel.add(buttons[row][col]);
            }
        }
        
    }
}
