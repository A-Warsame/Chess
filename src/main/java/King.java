import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;

//The king
@SuppressWarnings("serial")
public class King extends Piece {
    //A king
    public King(String color) {
        if (color == "white") {
            this.color = "white";
            icon = new ImageIcon("images/white_king.png");
            setIcon(icon);
        }
        else {
            this.color = "black";
            icon = new ImageIcon("images/black_king.png");
            setIcon(icon);
        }
    }
    //Returns color
    @Override
    public String getColor()  {
        return this.color;
    }
    //Returns type
    @Override
    public String getType()  {
        return "king";
    }
    @Override
    public List<Square> getValidMoves(Square[][] board, int row, int col) {
        List<Square> validMoves = new ArrayList<>();

        // Check in all eight directions
        int[][] moveOffsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        for (int[] offset : moveOffsets) {
            int newRow = row + offset[0];
            int newCol = col + offset[1];

            // Check if the new position is within the board boundaries
            if (isValidSquare(newRow, newCol) &&
                    (board[newRow][newCol].isOpen() || !board[newRow][newCol].getPiece().getColor().equals(getColor()))) {
                validMoves.add(board[newRow][newCol]);
            }
        }

        return validMoves;
    }

    private boolean isValidSquare(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}
