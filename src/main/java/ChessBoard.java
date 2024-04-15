import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ChessBoard extends JPanel {

    private final JPanel[][] tiles = new JPanel[8][8];
    private final Square[][] squares = new Square[8][8];
    private final List<Piece> pieces = new ArrayList<>();


    public ChessBoard() {
        Dimension dims = new Dimension(64, 64);
        setLayout(new GridLayout(8, 8));

        // Initialize the squares
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new Square(null, i, j);
            }
        }

        // Adding squares to panel
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JPanel squarePanel = new JPanel();
                squarePanel.setPreferredSize(dims);
                squarePanel.setMinimumSize(dims);
                if ((i + j) % 2 == 0) {
                    squarePanel.setBackground(Color.LIGHT_GRAY);
                } else {
                    squarePanel.setBackground(Color.WHITE);
                }
                add(squarePanel);
                tiles[i][j] = squarePanel;

                // Link each JPanel with a Spot on the Board
                squares[i][j].setPanel(squarePanel);
            }
            // Append all the pieces
        }
        appendAllPieces();

//        //Create and add pieces to list
//        Bishop bbishop = new Bishop(2, 0, false, "bishop");
//        pieces.add(bbishop);
//
//        // Place the pieces on the board
//        JLabel bishopLabel = new JLabel(new ImageIcon(bbishop.getImage()));
//        tiles[bbishop.getX()][bbishop.getY()].add(bishopLabel);

//        BufferedImage originalImage = null;
//        try {
//            originalImage = ImageIO.read(new File("src/main/resources/black-bishop.png"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        // Scale the image to the desired size
//        int newWidth = 60; // New width of the image
//        int newHeight = 60; // New height of the image
//        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
//        ImageIcon scaledIcon = new ImageIcon(scaledImage);
//
//        // Create JLabel with the scaled image and add it to a specific square
//        JLabel picLabel = new JLabel(scaledIcon);
//        tiles[3][4].add(picLabel);

    }
    private void appendAllPieces() {
        // Black pieces
        pieces.add(new Rook(0, 0, false, "rook"));
        pieces.add(new Knight(0, 1, false, "knight"));
        pieces.add(new Bishop(0, 2, false, "bishop"));
        pieces.add(new Queen(0, 3, false, "queen"));
        pieces.add(new King(0, 4, false, "king"));
        pieces.add(new Bishop(0, 5, false, "bishop"));
        pieces.add(new Knight(0, 6, false, "knight"));
        pieces.add(new Rook(0, 7, false, "rook"));
        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(1, i, false, "pawn"));
        }

            // White pieces
        pieces.add(new Rook(7, 0, true, "rook"));
        pieces.add(new Knight(7, 1, true, "knight"));
        pieces.add(new Bishop(7, 2, true, "bishop"));
        pieces.add(new Queen(7, 3, true, "queen"));
        pieces.add(new King(7, 4, true, "king"));
        pieces.add(new Bishop(7, 5, true, "bishop"));
        pieces.add(new Knight(7, 6, true, "knight"));
        pieces.add(new Rook(7, 7, true, "rook"));
        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(6, i, true, "pawn"));
        }

            // Place all the pieces on the board
            for (Piece piece : pieces) {
                System.out.println(piece);
//                JLabel pieceLabel = new JLabel(new ImageIcon(piece.getImage()));
                // Scale the image to the desired size
//                System.out.println("WIDTH: " + piece.getImage().getWidth());
                int newWidth = 60; // New width of the image
                int newHeight = 60; // New height of the image
                Image scaledImage = piece.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                JLabel pieceLabel = new JLabel(scaledIcon);
                tiles[piece.getX()][piece.getY()].add(pieceLabel);

            }
        }

    //Method to get the Square at position (x, y)
    public Square getSquare(int x, int y) {
        return squares[x][y];
    }

    // Method to check if the king of the given color is in check
    public boolean isKingInCheck(boolean isWhite) {
        // Find the position of the king of the given color
        int kingX = -1;
        int kingY = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square square = squares[i][j];
                if (square.getPiece() instanceof King && square.getPiece().isWhite() == isWhite) {
                    kingX = i;
                    kingY = j;
                    break;
                }
            }
        }
        // If the king's position is not found, return false
        if (kingX == -1 || kingY == -1) {
            return false;
        }

        // Check if any opponent's piece can attack the king
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square square = squares[i][j];
                if (square.getPiece() != null && square.getPiece().isWhite() != isWhite) {
                    // Check if the piece can legally move to the king's position
                    if (square.getPiece().legalMove(this, square, squares[kingX][kingY])) {
                        return true; // King is in check
                    }
                }
            }
        }
        // If no opponent's piece can attack the king, return false
        return false;
    }

}
