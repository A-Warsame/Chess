import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;


public class Bishop extends Piece {
    //A standard bishop (Defaults to white)
    public Bishop() {
        icon = new ImageIcon("images/white_bishop.png");
        setIcon(icon);
    }
    //A bishop with color
    public Bishop(String color) {
        if (color == "white") {
            this.color = "white";
            icon = new ImageIcon("images/white_bishop.png");
            setIcon(icon);
        }
        else {
            this.color = "black";
            icon = new ImageIcon("images/black_bishop.png");
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
        return "bishop";
    }
    @Override
    public List<Square> getValidMoves(Square[][] board, int row, int col) {
        List<Square> validMoves = new ArrayList<>();

        // Check diagonally in all four directions
        int[][] moveOffsets = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        for (int[] offset : moveOffsets) {
            int newRow = row + offset[0];
            int newCol = col + offset[1];

            // Move until we reach the edge of the board or encounter a piece
            while (isValidSquare(newRow, newCol) && (board[newRow][newCol].isOpen() ||
                    !board[newRow][newCol].getPiece().getColor().equals(getColor()))) {
                validMoves.add(board[newRow][newCol]);
                if (!board[newRow][newCol].isOpen()) {
                    break;  // Stop if a piece is encountered
                }
                newRow += offset[0];
                newCol += offset[1];
            }
        }

        return validMoves;
    }

//    private boolean isValidSquare(int row, int col) {
//        return row >= 0 && row < 8 && col >= 0 && col < 8;
//    }
}
