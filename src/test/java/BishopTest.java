import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class BishopTest {

    ChessBoard board;
    @Before
    public void instantiateChessBoard() {
        board = new ChessBoard();

    }

    @Test
    public void testValidMovesForWhiteBishop() {

        // Create a white bishop
        Bishop whiteBishop = new Bishop("white");
        // Set the bishop on the board
        board.squares[3][3].setPiece(whiteBishop);

        // Get valid moves for the white bishop at position (3, 3)
        List<Square> validMoves = whiteBishop.getValidMoves(board.squares, 3, 3);

        // Assert the number of valid moves
        assertEquals(13, validMoves.size()); // 13 valid moves for a bishop in the center of the board

        // Assert the position of the valid moves
        assertTrue(validMoves.contains(board.squares[0][0])); // Diagonal Up-Left
        assertTrue(validMoves.contains(board.squares[1][1])); // Diagonal Up-Left
        assertTrue(validMoves.contains(board.squares[2][2])); // Diagonal Up-Left
        assertTrue(validMoves.contains(board.squares[4][4])); // Diagonal Down-Right
        assertTrue(validMoves.contains(board.squares[5][5])); // Diagonal Down-Right
        assertTrue(validMoves.contains(board.squares[6][6])); // Diagonal Down-Right
        assertTrue(validMoves.contains(board.squares[7][7])); // Diagonal Down-Right
        assertTrue(validMoves.contains(board.squares[0][6])); // Diagonal Up-Right
        assertTrue(validMoves.contains(board.squares[1][5])); // Diagonal Up-Right
        assertTrue(validMoves.contains(board.squares[2][4])); // Diagonal Up-Right
        assertTrue(validMoves.contains(board.squares[4][2])); // Diagonal Down-Left
        assertTrue(validMoves.contains(board.squares[5][1])); // Diagonal Down-Left
        assertTrue(validMoves.contains(board.squares[6][0])); // Diagonal Down-Left
    }

    @Test
    public void testInvalidMovesBeyondBoardBoundaries() {

        board = new ChessBoard();
        Bishop blackBishop = new Bishop("black");

        board.squares[0][7].setPiece(blackBishop);

        // valid moves for the black bishop at 0,7
        List<Square> validMoves = blackBishop.getValidMoves(board.squares, 0, 7);

        // Assert the number of valid moves
        assertEquals(7, validMoves.size()); // 7 valid moves for a bishop at the edge of the board
    }
}
