import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.List;

public abstract class Piece extends JLabel {

    public String color;
    public ImageIcon icon;		//The piece image
    public String pieceType;

    public abstract String getColor();
    public abstract String getType();
    public abstract List<Square> getValidMoves(Square[][] board, int row, int col);
    public boolean isValidSquare(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }


}
