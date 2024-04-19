import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class KingTest {

    @Test
    public void testValidMovesForWhiteKing() {
        ChessBoard board = new ChessBoard();
        King whiteKing = new King("white");
        board.squares[3][3].setPiece(whiteKing);

        // Get valid moves for the white king at position (3, 3)
        List<Square> validMoves = whiteKing.getValidMoves(board.squares, 3, 3);

        assertEquals(8, validMoves.size()); // 8 valid moves for a king in the center of the board

        // Assert the position of the valid moves
        assertTrue(validMoves.contains(board.squares[2][3])); // Up
        assertTrue(validMoves.contains(board.squares[4][3])); // Down
        assertTrue(validMoves.contains(board.squares[3][2])); // Left
        assertTrue(validMoves.contains(board.squares[3][4])); // Right
        assertTrue(validMoves.contains(board.squares[2][2])); // Diagonal Up-Left
        assertTrue(validMoves.contains(board.squares[2][4])); // Diagonal Up-Right
        assertTrue(validMoves.contains(board.squares[4][2])); // Diagonal Down-Left
        assertTrue(validMoves.contains(board.squares[4][4])); // Diagonal Down-Right
    }

    @Test
    public void testInvalidMovesBeyondBoardBoundaries() {
        ChessBoard board = new ChessBoard();
        King blackKing = new King("black");
        board.squares[0][7].setPiece(blackKing);

        List<Square> validMoves = blackKing.getValidMoves(board.squares, 0, 7);

        assertEquals(3, validMoves.size()); // 3 valid moves for a king at the edge of the board
    }
}
