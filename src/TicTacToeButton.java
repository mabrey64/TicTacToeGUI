import javax.swing.*;
import java.awt.*;
/*
This class is the TicTacToeButton class. It extends JButton and implements the TicTacToeFrame class.
It creates the TicTacToeButton objects that are used in the TicTacToeFrame class.
It also sets the icons for the X and O images.
Certian methods go unused in this class as there was a lot of testing done to get the game to work.
 */
public class TicTacToeButton extends JButton {
    private int row;
    private int col;
    private static final ImageIcon X_ICON = resizeIcon(new ImageIcon("x.png"), 100, 100);
    private static final ImageIcon O_ICON = resizeIcon(new ImageIcon("o.png"), 100, 100);
    private static Boolean isX = true;
    private TicTacToeFrame frame;
    private String currentPlayer = "X";

    public TicTacToeButton(int row, int col, TicTacToeFrame frame) {
        super();
        this.row = row;
        this.col = col;
        this.frame = frame;
        setPreferredSize(new Dimension(100, 100));
        addActionListener(e -> turnOrder());
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setX() {
        setIcon(X_ICON);
    }

    public void setO() {
        setIcon(O_ICON);
    }



    public void turnOrder() {
        if (getIcon() == null) {
            if ("X".equals(frame.getCurrentPlayer())) {
                setX();
            } else {
                setO();
            }
            frame.handleMove(row, col);
        } else {
            JOptionPane.showMessageDialog(this, "Illegal Move", "Illegal Move", JOptionPane.ERROR_MESSAGE);
        }
    }

    // This method makes isX true for the game
    public static void resetIsX() {
        isX = true;
    }

    // This method makes the images for the X and O icons, resizing them to 100x100 pixels.
    private static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

}

