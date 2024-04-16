import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;

//The pawn piece
@SuppressWarnings("serial")
public class Pawn extends Piece {
    //A pawn
    public Pawn(String color) {
        if (color == "white") {
            this.color = "white";
            icon = new ImageIcon("images/white_pawn.png");
            setIcon(icon);
        }
        else {
            this.color = "black";
            icon = new ImageIcon("images/black_pawn.png");
            setIcon(icon);
        }
    }
    //Returns color
    @Override
    public String getColor()  {
        return this.color;
    }
    //Returns the piece type
    @Override
    public String getType()  {
        return "pawn";
    }
    @Override
    public List<Square> getValidMoves(Square[][] board, int row, int col) {
        List<Square> validMoves = new ArrayList<>();

        // Determine the direction of movement based on pawn color
        int direction = getColor().equals("white") ? -1 : 1;

        // Check if the square in front of the pawn is empty
        int nextRow = row + direction;
        if (isValidSquare(nextRow, col) && board[nextRow][col].isOpen()) {
            validMoves.add(board[nextRow][col]);

            // If it's the pawn's initial move, check if it can move two squares forward
            if ((direction == -1 && row == 6) || (direction == 1 && row == 1)) {
                int doubleMoveRow = row + 2 * direction;
                if (isValidSquare(doubleMoveRow, col) && board[doubleMoveRow][col].isOpen()) {
                    validMoves.add(board[doubleMoveRow][col]);
                }
            }
        }

        // Check diagonal squares for potential captures
        for (int colOffset : new int[] {-1, 1}) {
            int captureRow = row + direction;
            int captureCol = col + colOffset;
            if (isValidSquare(captureRow, captureCol) && !board[captureRow][captureCol].isOpen() &&
                    !board[captureRow][captureCol].getPiece().getColor().equals(getColor())) {
                validMoves.add(board[captureRow][captureCol]);
            }
        }

        // Add logic for en passant and promotion

        return validMoves;
    }

    private boolean isValidSquare(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
}
