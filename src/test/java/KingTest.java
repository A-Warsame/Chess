import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class KingTest {

    @Test
    public void testValidMovesForWhiteKing() {
        // Create a chessboard
        ChessBoard board = new ChessBoard();
        // Create a white king
        King whiteKing = new King("white");
        // Set the king on the board
        board.squares[3][3].setPiece(whiteKing);

        // Get valid moves for the white king at position (3, 3)
        List<Square> validMoves = whiteKing.getValidMoves(board.squares, 3, 3);

        // Assert the number of valid moves
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
        // Create a chessboard
        ChessBoard board = new ChessBoard();
        // Create a black king
        King blackKing = new King("black");
        // Set the king on the board at the edge
        board.squares[0][7].setPiece(blackKing);

        // Get valid moves for the black king at position (0, 7)
        List<Square> validMoves = blackKing.getValidMoves(board.squares, 0, 7);

        // Assert the number of valid moves
        assertEquals(3, validMoves.size()); // 3 valid moves for a king at the edge of the board
    }
}
