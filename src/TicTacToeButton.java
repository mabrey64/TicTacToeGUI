import javax.swing.*;
import java.awt.*;

public class TicTacToeButton extends JButton {
    private int row;
    private int col;
    private static final ImageIcon X_ICON = resizeIcon(new ImageIcon("x.png"), 100, 100);
    private static final ImageIcon O_ICON = resizeIcon(new ImageIcon("o.png"), 100, 100);
    private static Boolean isX = true;

    public TicTacToeButton(int row, int col) {
        super();
        this.row = row;
        this.col = col;
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

    public void clear() {
        setIcon(null);
    }
    public void turnOrder() {
        if (getIcon() == null) {
            if (isX) {
                setIcon(X_ICON);
            } else {
                setIcon(O_ICON);
            }
            isX = !isX;
        }
    }
    private static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

}

