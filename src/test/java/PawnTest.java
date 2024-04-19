import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class PawnTest {

    @Test
    public void testValidMovesForWhitePawn() {
        ChessBoard board = new ChessBoard();
        Pawn whitePawn = new Pawn("white");
        board.squares[6][3].setPiece(whitePawn);

        List<Square> validMoves = whitePawn.getValidMoves(board.squares, 6, 3);

        assertEquals(2, validMoves.size());
        assertTrue(validMoves.contains(board.squares[5][3])); // White pawn can move one square forward
        assertTrue(validMoves.contains(board.squares[4][3])); // White pawn can move two squares forward as initial move
    }

    @Test
    public void testValidMovesForBlackPawn() {
        ChessBoard board = new ChessBoard();
        Pawn blackPawn = new Pawn("black");
        board.squares[1][4].setPiece(blackPawn);

        // Get valid moves for the black pawn at position (1, 4)
        List<Square> validMoves = blackPawn.getValidMoves(board.squares, 1, 4);

        assertEquals(2, validMoves.size());
        assertTrue(validMoves.contains(board.squares[2][4])); // Black pawn can move one square forward
        assertTrue(validMoves.contains(board.squares[3][4])); // Black pawn can move two squares forward as initial move
    }


    @Test
    public void testInvalidMovesBeyondBoardBoundaries() {
        ChessBoard board = new ChessBoard();
        Pawn whitePawn = new Pawn("white");
        // Set the pawn on the board at the edge
        board.squares[0][7].setPiece(whitePawn);

        // Get valid moves for the white pawn at position (0, 7)
        List<Square> validMoves = whitePawn.getValidMoves(board.squares, 0, 7);

        assertEquals(0, validMoves.size()); // White pawn cannot move beyond the board boundary
    }
}
