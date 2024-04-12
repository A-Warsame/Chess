import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class ChessBoard extends JPanel {

    private final JPanel[][] tiles = new JPanel[8][8];
    private final Square[][] squares = new Square[8][8];

    public ChessBoard() {
        Dimension dims = new Dimension(64, 64);
        setLayout(new GridLayout(8, 8));

        // Initialize the squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new Square(null, i, j);
            }
        }

        // Adding squares to panel
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JPanel squarePanel = new JPanel();
                squarePanel.setPreferredSize(dims);
                squarePanel.setMinimumSize(dims);
                if ((i + j + 1) % 2 == 0) {
                    squarePanel.setBackground(Color.LIGHT_GRAY);
                } else {
                    squarePanel.setBackground(Color.BLACK);
                }
                add(squarePanel);
                tiles[i][j] = squarePanel;

                // Link each JPanel with a Spot on the Board
                squares[i][j].setPanel(squarePanel);
            }
        }
    }

    // Method to get the Square at position (x, y)
    public Square getSquare(int x, int y) {
        return squares[x][y];
    }
}
