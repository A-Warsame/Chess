import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    //A queen
    public Queen(String color) {
        if (color == "white") {
            this.color = "white";
            icon = new ImageIcon("images/white_queen.png");
            setIcon(icon);
        }
        else {
            this.color = "black";
            icon = new ImageIcon("images/black_queen.png");
            setIcon(icon);
        }
    }
    //Returns the color
    @Override
    public String getColor()  {
        return this.color;
    }
    //Returns the type of piece
    @Override
    public String getType()  {
        return "queen";
    }

    @Override
    public List<Square> getValidMoves(Square[][] board, int row, int col) {
        List<Square> validMoves = new ArrayList<>();

        // Check horizontally, vertically, and diagonally in all eight directions
        int[][] moveOffsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

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
