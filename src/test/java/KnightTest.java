import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class KnightTest {

    @Test
    public void testValidMovesForWhiteKnight() {
        ChessBoard board = new ChessBoard();
        Knight whiteKnight = new Knight("white");
        board.squares[3][3].setPiece(whiteKnight);

        List<Square> validMoves = whiteKnight.getValidMoves(board.squares, 3, 3);

        assertEquals(8, validMoves.size());
        // Assert the position of the valid moves
        assertTrue(validMoves.contains(board.squares[1][2]));
        assertTrue(validMoves.contains(board.squares[1][4]));
        assertTrue(validMoves.contains(board.squares[2][1]));
        assertTrue(validMoves.contains(board.squares[2][5]));
        assertTrue(validMoves.contains(board.squares[4][1]));
        assertTrue(validMoves.contains(board.squares[4][5]));
        assertTrue(validMoves.contains(board.squares[5][2]));
        assertTrue(validMoves.contains(board.squares[5][4]));
    }

    @Test
    public void testInvalidMovesBeyondBoardBoundaries() {
        ChessBoard board = new ChessBoard();
        Knight blackKnight = new Knight("black");
        board.squares[0][7].setPiece(blackKnight);

        List<Square> validMoves = blackKnight.getValidMoves(board.squares, 0, 7);

        assertEquals(2, validMoves.size()); // Black knight has only two valid moves at the edge of the board
    }
}
