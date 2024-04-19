import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class QueenTest {

    @Test
    public void testValidMovesForWhiteQueen() {
        ChessBoard board = new ChessBoard();
        Queen whiteQueen = new Queen("white");
        board.squares[3][3].setPiece(whiteQueen);

        List<Square> validMoves = whiteQueen.getValidMoves(board.squares, 3, 3);

        assertEquals(27, validMoves.size()); // 27 valid moves for a queen in the center of the board

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
        assertTrue(validMoves.contains(board.squares[2][2])); // Diagonal Up-Left
        assertTrue(validMoves.contains(board.squares[1][1])); // Diagonal Up-Left
        assertTrue(validMoves.contains(board.squares[0][0])); // Diagonal Up-Left
        assertTrue(validMoves.contains(board.squares[4][4])); // Diagonal Down-Right
        assertTrue(validMoves.contains(board.squares[5][5])); // Diagonal Down-Right
        assertTrue(validMoves.contains(board.squares[6][6])); // Diagonal Down-Right
        assertTrue(validMoves.contains(board.squares[7][7])); // Diagonal Down-Right
        assertTrue(validMoves.contains(board.squares[2][4])); // Diagonal Up-Right
        assertTrue(validMoves.contains(board.squares[1][5])); // Diagonal Up-Right
        assertTrue(validMoves.contains(board.squares[0][6])); // Diagonal Up-Right
        assertTrue(validMoves.contains(board.squares[4][2])); // Diagonal Down-Left
        assertTrue(validMoves.contains(board.squares[5][1])); // Diagonal Down-Left
        assertTrue(validMoves.contains(board.squares[6][0])); // Diagonal Down-Left
    }

    @Test
    public void testInvalidMovesBeyondBoardBoundaries() {
        ChessBoard board = new ChessBoard();
        Queen blackQueen = new Queen("black");
        board.squares[0][7].setPiece(blackQueen);

        List<Square> validMoves = blackQueen.getValidMoves(board.squares, 0, 7);

        assertEquals(21, validMoves.size());
    }
}
