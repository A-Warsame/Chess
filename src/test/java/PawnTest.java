import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class PawnTest {

    @Test
    public void testValidMovesForWhitePawn() {
        // Create a chessboard
        ChessBoard board = new ChessBoard();
        // Create a white pawn
        Pawn whitePawn = new Pawn("white");
        // Set the pawn on the board
        board.squares[6][3].setPiece(whitePawn);

        // Get valid moves for the white pawn at position (6, 3)
        List<Square> validMoves = whitePawn.getValidMoves(board.squares, 6, 3);

        // Assert the number of valid moves
        assertEquals(2, validMoves.size());
        // Assert the position of the valid moves
        assertTrue(validMoves.contains(board.squares[5][3])); // White pawn can move one square forward
        assertTrue(validMoves.contains(board.squares[4][3])); // White pawn can move two squares forward as initial move
    }

    @Test
    public void testValidMovesForBlackPawn() {
        // Create a chessboard
        ChessBoard board = new ChessBoard();
        // Create a black pawn
        Pawn blackPawn = new Pawn("black");
        // Set the pawn on the board
        board.squares[1][4].setPiece(blackPawn);

        // Get valid moves for the black pawn at position (1, 4)
        List<Square> validMoves = blackPawn.getValidMoves(board.squares, 1, 4);

        // Assert the number of valid moves
        assertEquals(2, validMoves.size());
        // Assert the position of the valid moves
        assertTrue(validMoves.contains(board.squares[2][4])); // Black pawn can move one square forward
        assertTrue(validMoves.contains(board.squares[3][4])); // Black pawn can move two squares forward as initial move
    }


    @Test
    public void testInvalidMovesBeyondBoardBoundaries() {
        // Create a chessboard
        ChessBoard board = new ChessBoard();
        // Create a white pawn
        Pawn whitePawn = new Pawn("white");
        // Set the pawn on the board at the edge
        board.squares[0][7].setPiece(whitePawn);

        // Get valid moves for the white pawn at position (0, 7)
        List<Square> validMoves = whitePawn.getValidMoves(board.squares, 0, 7);

        // Assert the number of valid moves
        assertEquals(0, validMoves.size()); // White pawn cannot move beyond the board boundary
    }
}
