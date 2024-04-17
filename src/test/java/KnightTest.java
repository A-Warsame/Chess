import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class KnightTest {

    @Test
    public void testValidMovesForWhiteKnight() {
        // Create a chessboard
        ChessBoard board = new ChessBoard();
        // Create a white knight
        Knight whiteKnight = new Knight("white");
        // Set the knight on the board
        board.squares[3][3].setPiece(whiteKnight);

        // Get valid moves for the white knight at position (3, 3)
        List<Square> validMoves = whiteKnight.getValidMoves(board.squares, 3, 3);

        // Assert the number of valid moves
        assertEquals(8, validMoves.size());
        // Assert the position of the valid moves
        assertTrue(validMoves.contains(board.squares[1][2])); // Up 2, Left 1
        assertTrue(validMoves.contains(board.squares[1][4])); // Up 2, Right 1
        assertTrue(validMoves.contains(board.squares[2][1])); // Up 1, Left 2
        assertTrue(validMoves.contains(board.squares[2][5])); // Up 1, Right 2
        assertTrue(validMoves.contains(board.squares[4][1])); // Down 1, Left 2
        assertTrue(validMoves.contains(board.squares[4][5])); // Down 1, Right 2
        assertTrue(validMoves.contains(board.squares[5][2])); // Down 2, Left 1
        assertTrue(validMoves.contains(board.squares[5][4])); // Down 2, Right 1
    }

    @Test
    public void testInvalidMovesBeyondBoardBoundaries() {
        // Create a chessboard
        ChessBoard board = new ChessBoard();
        // Create a black knight
        Knight blackKnight = new Knight("black");
        // Set the knight on the board at the edge
        board.squares[0][7].setPiece(blackKnight);

        // Get valid moves for the black knight at position (0, 7)
        List<Square> validMoves = blackKnight.getValidMoves(board.squares, 0, 7);

        // Assert the number of valid moves
        assertEquals(2, validMoves.size()); // Black knight has only two valid moves at the edge of the board
    }
}
