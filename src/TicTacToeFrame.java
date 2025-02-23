import javax.swing.*;

public class TicTacToeFrame extends JFrame
{
    // Panels for the game board
    JPanel gameBoardPanel;
    JPanel buttonPanel;
    JPanel labelPanel;

    // Buttons for the game
    TicTacToeButton[][] buttons;
    JButton exitButton;

    JOptionPane resultPane;
    JOptionPane illegalMovePane;

    public TicTacToeFrame()
    {
        // Initialize the components
        gameBoardPanel = new JPanel();
        buttonPanel = new JPanel();
        labelPanel = new JPanel();

        createButtonPanel();
        createLabelPanel();

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

        gameBoardPanel.add(buttonPanel);
        gameBoardPanel.add(labelPanel);

        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the frame
        setSize(300, 300);

        setVisible(true);
        add(gameBoardPanel, java.awt.BorderLayout.CENTER);
    }

    private void createButtonPanel()
    {
        buttonPanel = new JPanel();
        exitButton = new JButton("Exit");
        buttonPanel.add(exitButton);
        exitButton.addActionListener(e -> System.exit(0));
        add(buttonPanel, java.awt.BorderLayout.SOUTH);
    }

    private void createLabelPanel()
    {
        resultPane = new JOptionPane();
        illegalMovePane = new JOptionPane();
        labelPanel = new JPanel();
        labelPanel.add(resultPane);
        labelPanel.add(illegalMovePane);
        add(labelPanel, java.awt.BorderLayout.NORTH);
    }
}
