import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("serial")
public class Game extends JFrame implements MouseListener {

    private static ChessBoard board = new ChessBoard();
    public static Square previousSquare = null;
    public static Square sourceSquare = null;
    public static String pieceColor = "";
    public static String whoseMove = "";
    public static boolean active = false;
    private List<Square> validMoves;
    private Piece piece;
//    public static boolean capture = false;

    // Game object
    public Game() {
        // Initializes the game
        setLayout(new GridBagLayout());
        // Sets Board
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        add(board, c);
        board.initializeChessBoard();
        whoseMove = "white";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) board.cell[i][j].addMouseListener(this);
        }
    }

    Color selected;
    List<Color> selectedTiles;

    // Mouse Clicked/Allows pieces to move when the user clicks the mouse.
    @Override
    public void mouseClicked(MouseEvent e) {
        sourceSquare = (Square) e.getSource();

        if (!sourceSquare.isOpen()) pieceColor = sourceSquare.getPiece().getColor();

        if (!active && !sourceSquare.isOpen()) {
            active = true;
            previousSquare = sourceSquare;
            selected = previousSquare.getBackground();
            previousSquare.setBackground(new Color(175, 0, 0));
            piece = previousSquare.getPiece();
            validMoves = piece.getValidMoves(board.cell, sourceSquare.getRow(), sourceSquare.getCol());
            selectedTiles = new ArrayList<>();
            for (Square validMove : validMoves) {
                Color test = validMove.getBackground();
                selectedTiles.add(test);
                validMove.setBackground(new Color( 204, 97, 75));
            }
            if (!previousSquare.getPiece().getColor().equals(whoseMove)) clear();
        } else if (active && previousSquare.getPiece().getColor().equals(whoseMove)) {
            if (isValidMove(sourceSquare)) {
                movePiece();
            } else {
                clear();
            }
        }
    }
    private boolean isValidMove(Square destination) {
        Piece piece = previousSquare.getPiece();
        List<Square> validMoves = piece.getValidMoves(board.cell, previousSquare.getRow(), previousSquare.getCol());
        return validMoves.contains(destination);
    }
    // Function moves the pieces
    public void movePiece() {
        System.out.println("Piece was moved");
//        capture = true;
//        if(sourceSquare.isOpen()) {
//            capture = true;
//        }
        if (!sourceSquare.isOpen() && !previousSquare.getPiece().getColor().equalsIgnoreCase(pieceColor)) {
            sourceSquare.remove(sourceSquare.getPiece());
            sourceSquare.setPiece(null);
//            capture = false;
        }
        previousSquare.setBackground(selected);
        int i = 0;
        for (Square validMove : validMoves) {
            validMove.setBackground(selectedTiles.get(i));
            i++;
        }
        sourceSquare.add(previousSquare.getPiece());
        sourceSquare.setPiece(previousSquare.getPiece());
        sourceSquare.revalidate();
        board.repaint();
        active = false;
        previousSquare.setStatus(true);
        previousSquare.setPiece(null);
        sourceSquare.setStatus(false);
        previousSquare = null;
        whoseMove = whoseMove.equals("white") ? "black" : "white";
    }

    public void clear() {
        System.out.println("Piece was not moved");
        previousSquare.setBackground(selected);
        int i = 0;
        for (Square validMove : validMoves) {
            validMove.setBackground(selectedTiles.get(i));
            i++;
        }
        active = false;
//        capture = false;
        previousSquare = null;
        sourceSquare = null;
    }

    public static void main(String[] args) {
        JFrame frame = new Game();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(isDefaultLookAndFeelDecorated());
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
