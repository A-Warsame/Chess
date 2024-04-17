import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class RookTest {

    @Test
    public void testValidMovesForWhiteRook() {
        // Create a chessboard
        ChessBoard board = new ChessBoard();
        // Create a white rook
        Rook whiteRook = new Rook("white");
        // Set the rook on the board
        board.squares[3][3].setPiece(whiteRook);

        // Get valid moves for the white rook at position (3, 3)
        List<Square> validMoves = whiteRook.getValidMoves(board.squares, 3, 3);

        // Assert the number of valid moves
        assertEquals(14, validMoves.size()); // 14 valid moves for a rook in the center of the board

        // Assert the pos of the valid moves
        assertTrue(validMoves.contains(board.squares[3][0])); // Horizontal left
        assertTrue(validMoves.contains(board.squares[3][1])); // Horizontal left
        assertTrue(validMoves.contains(board.squares[3][2])); // Horizontal left
        assertTrue(validMoves.contains(board.squares[3][4])); // Horizontal right
        assertTrue(validMoves.contains(board.squares[3][5])); // Horizontal right
        assertTrue(validMoves.contains(board.squares[3][6])); // Horizontal right
        assertTrue(validMoves.contains(board.squares[3][7])); // Horizontal right
        assertTrue(validMoves.contains(board.squares[0][3])); // Vertical up
        assertTrue(validMoves.contains(board.squares[1][3])); // Vertical up
        assertTrue(validMoves.contains(board.squares[2][3])); // Vertical up
        assertTrue(validMoves.contains(board.squares[4][3])); // Vertical down
        assertTrue(validMoves.contains(board.squares[5][3])); // Vertical down
        assertTrue(validMoves.contains(board.squares[6][3])); // Vertical down
        assertTrue(validMoves.contains(board.squares[7][3])); // Vertical down
    }

    @Test
    public void testInvalidMovesBeyondBoardBoundaries() {
        // Create a chessboard
        ChessBoard board = new ChessBoard();
        // Create a black rook
        Rook blackRook = new Rook("black");
        // Set the rook on the board at the edge
        board.squares[0][7].setPiece(blackRook);

        // valid moves for the black rook at square (0, 7)
        List<Square> validMoves = blackRook.getValidMoves(board.squares, 0, 7);

        // Assert NO. of valid moves
        assertEquals(14, validMoves.size()); // 14 valid moves for a rook at the edge of the board
    }
}
