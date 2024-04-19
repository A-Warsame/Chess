import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class Game extends JFrame implements MouseListener {

    private static ChessBoard board = new ChessBoard();
    public static Square previousSelectedSquare = null;
    public static Square sourceSquare = null;
    public static String pieceColor = "";
    public static String whoseMove = "";
    public static boolean canTake = false;
    private List<Square> validMoves;
    private Piece piece;
//    public static boolean taken = false;

//    public Game() {
//    }

    // Static factory method to create a new instance of Game
    public static Game createGame() {
        Game game = new Game();
        game.initializeGame();
        return game;
    }

    // Method to set up the initial state of the game
    private void initializeGame() {
        board = new ChessBoard();
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        add(board, c);
        board.initializeChessBoard();
        whoseMove = "white";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) board.squares[i][j].addMouseListener(this);
        }
    }

    Color selected;
    List<Color> selectedTiles;

    // Mouse Clicked/Allows pieces to move when the user clicks the mouse.
    @Override
    public void mouseClicked(MouseEvent e) {
        sourceSquare = (Square) e.getSource();

        if (!sourceSquare.isOpen()) pieceColor = sourceSquare.getPiece().getColor();

        if (!canTake && !sourceSquare.isOpen()) {
            canTake = true;
            previousSelectedSquare = sourceSquare;
            selected = previousSelectedSquare.getBackground();
            previousSelectedSquare.setBackground(new Color(175, 0, 0));
            piece = previousSelectedSquare.getPiece();
            validMoves = piece.getValidMoves(board.squares, sourceSquare.getRow(), sourceSquare.getCol());
            selectedTiles = new ArrayList<>();
            for (Square validMove : validMoves) {
                Color test = validMove.getBackground();
                selectedTiles.add(test);
                validMove.setBackground(new Color( 204, 97, 75));
            }
            if (!previousSelectedSquare.getPiece().getColor().equals(whoseMove)) clear();
        } else if (canTake && previousSelectedSquare.getPiece().getColor().equals(whoseMove)) {
            if (isValidMove(sourceSquare)) {
                movePiece();
            } else {
                clear();
            }
        }
    }
    private boolean isValidMove(Square destination) {
        Piece piece = previousSelectedSquare.getPiece();
        List<Square> validMoves = piece.getValidMoves(board.squares, previousSelectedSquare.getRow(), previousSelectedSquare.getCol());
        return validMoves.contains(destination);
    }
    // Function moves the pieces
    public void movePiece() {
//        System.out.println("Piece was moved");
        System.out.println(whoseMove + " moved their piece");
//        capture = true;
//        if(sourceSquare.isOpen()) {
//            capture = true;
//        }
        if (!sourceSquare.isOpen() && !previousSelectedSquare.getPiece().getColor().equalsIgnoreCase(pieceColor)) {
            sourceSquare.remove(sourceSquare.getPiece());
            sourceSquare.setPiece(null);
//            capture = false;
        }
        previousSelectedSquare.setBackground(selected);
        int i = 0;
        for (Square validMove : validMoves) {
            validMove.setBackground(selectedTiles.get(i));
            i++;
        }
        sourceSquare.add(previousSelectedSquare.getPiece());
        sourceSquare.setPiece(previousSelectedSquare.getPiece());
        sourceSquare.revalidate();
        board.repaint();
        canTake = false;
        previousSelectedSquare.setStatus(true);
        previousSelectedSquare.setPiece(null);
        sourceSquare.setStatus(false);
        previousSelectedSquare = null;
        whoseMove = whoseMove.equals("white") ? "black" : "white";
    }

    //resets colour and selected square
    public void clear() {
//        System.out.println("Piece was not moved");
        System.out.println("Current move: " + whoseMove);
        previousSelectedSquare.setBackground(selected);
        int i = 0;
        for (Square validMove : validMoves) {
            validMove.setBackground(selectedTiles.get(i));
            i++;
        }
        canTake = false;
//        taken = false;
        previousSelectedSquare = null;
        sourceSquare = null;
    }

    public static void main(String[] args) {
        Game game = Game.createGame();
        game.setSize(540, 555);
        game.setLocationRelativeTo(null);
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.setVisible(true);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
