import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;


public class Knight extends Piece{
    public Knight(String color) {
        if (color == "white") {
            this.color = "white";
            icon = new ImageIcon("images/white_knight.png");
            setIcon(icon);
        }
        else {
            this.color = "black";
            icon = new ImageIcon("images/black_knight.png");
            setIcon(icon);
        }
    }
    //Returns the color
    @Override
    public String getColor() {
        return this.color;
    }
    //Returns the type
    @Override
    public String getType() {
        return "knight";
    }
    @Override
    public List<Square> getValidMoves(Square[][] board, int row, int col) {
        List<Square> validMoves = new ArrayList<>();

        int[][] moveOffsets = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

        for (int[] offset : moveOffsets) {
            int newRow = row + offset[0];
            int newCol = col + offset[1];

            if (isValidSquare(newRow, newCol) && (board[newRow][newCol].isOpen() ||
                    !board[newRow][newCol].getPiece().getColor().equals(getColor()))) {
                validMoves.add(board[newRow][newCol]);
            }
        }

        return validMoves;
    }

//    private boolean isValidSquare(int row, int col) {
//        return row >= 0 && row < 8 && col >= 0 && col < 8;
//    }
}
