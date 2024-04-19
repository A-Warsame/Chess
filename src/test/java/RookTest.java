import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class RookTest {

    @Test
    public void testValidMovesForWhiteRook() {
        ChessBoard board = new ChessBoard();
        Rook whiteRook = new Rook("white");
        board.squares[3][3].setPiece(whiteRook);

        List<Square> validMoves = whiteRook.getValidMoves(board.squares, 3, 3);

        assertEquals(14, validMoves.size()); // 14 valid moves for a rook in the center of the board

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
        ChessBoard board = new ChessBoard();
        Rook blackRook = new Rook("black");
        board.squares[0][7].setPiece(blackRook);

        // valid moves for the black rook at square (0, 7)
        List<Square> validMoves = blackRook.getValidMoves(board.squares, 0, 7);

        // Assert NO. of valid moves
        assertEquals(14, validMoves.size()); // 14 valid moves for a rook at the edge of the board
    }
}
