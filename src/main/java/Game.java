import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class Game extends JFrame implements MouseListener {

    private static ChessBoard board = new ChessBoard();
    public static Square previousSquare = null;
    public static Square sourceSquare = null;
    public static String pieceColor = "";
    public static String whoseMove = "";
    public static boolean active = false;
    public static boolean capture = false;

    //Game object
    public Game() {
        //Initializes the game
        setLayout(new GridBagLayout());
        //Sets Board
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        add(board, c);
        board.initializeChessBoard();
        whoseMove = "white";
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) board.cell[i][j].addMouseListener(this);
        }

    }
    Color selected;
    //Mouse Clicked/Allows pieces to move when the user clicks the mouse.
    @Override
    public void mouseClicked(MouseEvent e) {
        sourceSquare = (Square) e.getSource();

        if(!sourceSquare.isOpen()) pieceColor = sourceSquare.getPiece().getColor();

        if(!active && !sourceSquare.isOpen()) {
            active = true;
            previousSquare = sourceSquare;
            selected = previousSquare.getBackground();
            previousSquare.setBackground(new Color(175, 0, 0));
            if (previousSquare.getPiece().getColor() != whoseMove) clear();
        }
        else if (active && previousSquare.getPiece().getColor() == whoseMove) {
            String pieceType = previousSquare.getPiece().getType();
            String pieceColor = previousSquare.getPiece().getColor();
            //-----------------------------Pawn Movement-----------------------------
            if (pieceType.equalsIgnoreCase("pawn")) {
                int move = 1; int preCell = 6;
                if (pieceColor.equalsIgnoreCase("black")) { move = -1; preCell = 1; }
                //Move
                if(previousSquare.getRow() == preCell && previousSquare.getRow() == sourceSquare.getRow() + move + move && previousSquare.getCol() == sourceSquare.getCol()) {
                    movePiece();
                }
                else if(previousSquare.getRow() == sourceSquare.getRow() +  move && previousSquare.getCol() == sourceSquare.getCol()) {
                    movePiece();
                }
                //Kill
                else if(!sourceSquare.isOpen() && previousSquare.getRow() == sourceSquare.getRow() + move && Math.abs(previousSquare.getCol() - sourceSquare.getCol()) == 1) {
                    capture = true;
                    movePiece();
                }
                else{clear();}
            }
            //-----------------------------Knight Movement-----------------------------
            else if(pieceType.equalsIgnoreCase("knight")) {

                //Move/Kill
                if((previousSquare.getRow() == sourceSquare.getRow() + 1 && previousSquare.getCol() == sourceSquare.getCol() + 2)
                        || (previousSquare.getRow() == sourceSquare.getRow() + 1 && previousSquare.getCol() == sourceSquare.getCol() - 2)
                        || (previousSquare.getRow() == sourceSquare.getRow() - 1 && previousSquare.getCol() == sourceSquare.getCol() + 2)
                        || (previousSquare.getRow() == sourceSquare.getRow() - 1 && previousSquare.getCol() == sourceSquare.getCol() - 2)
                        || (previousSquare.getRow() == sourceSquare.getRow() + 2 && previousSquare.getCol() == sourceSquare.getCol() + 1)
                        || (previousSquare.getRow() == sourceSquare.getRow() - 2 && previousSquare.getCol() == sourceSquare.getCol() + 1)
                        || (previousSquare.getRow() == sourceSquare.getRow() + 2 && previousSquare.getCol() == sourceSquare.getCol() - 1)
                        || (previousSquare.getRow() == sourceSquare.getRow() - 2 && previousSquare.getCol() == sourceSquare.getCol() - 1)
                ) {
                    capture = true;
                    movePiece();
                }
                else{clear();}
            }
            //-----------------------------Rook Movement-----------------------------
            else if(pieceType.equalsIgnoreCase("rook")) {

                if(previousSquare.getRow() == sourceSquare.getRow() || previousSquare.getCol() == sourceSquare.getCol()) {
                    capture = true;
                    movePiece();
                }
                else{clear();}
            }
            //-----------------------------Bishop Movement-----------------------------
            else if(pieceType.equalsIgnoreCase("bishop")) {

                //Move/Kill
                int rowOffset = previousSquare.getRow() - sourceSquare.getRow();
                int colOffset = previousSquare.getCol() - sourceSquare.getCol();
                if(Math.abs(rowOffset) == Math.abs(colOffset)) {
                    capture = true;
                    movePiece();
                }
                else{clear();}
            }
            //-----------------------------Queen Movement-----------------------------
            else if(pieceType.equalsIgnoreCase("queen")) {

                //Move/Kill
                int rowOffset = previousSquare.getRow() - sourceSquare.getRow();
                int colOffset = previousSquare.getCol() - sourceSquare.getCol();
                if(Math.abs(rowOffset) == Math.abs(colOffset)
                        || previousSquare.getRow() == sourceSquare.getRow()
                        || previousSquare.getCol() == sourceSquare.getCol()
                ) {
                    capture = true;
                    movePiece();
                }
                else{clear();}
            }
            //-----------------------------King Movement-----------------------------
            else if(pieceType.equalsIgnoreCase("king")) {

                //Move/Kill
                int rowOffset = previousSquare.getRow() - sourceSquare.getRow();
                int colOffset = previousSquare.getCol() - sourceSquare.getCol();
                if(Math.abs(rowOffset) == 1 ||  Math.abs(colOffset) == 1)  {
                    capture = true;
                    movePiece();
                }
                //White Castle
                else if(pieceColor == "white" && previousSquare.getRow() == 7 && previousSquare.getCol() == 4) {
                    //King Side
                    if (sourceSquare.getRow() == 7 && sourceSquare.getCol() == 6 &&
                            board.cell[7][5].isOpen() && board.cell[7][6].isOpen() && board.cell[7][7].getPiece().getType() == "rook" && board.cell[7][7].getPiece().getColor() == "white") {
                        movePiece();
                        sourceSquare = board.cell[7][5];
                        previousSquare = board.cell[7][7];
                        whoseMove = "white";
                        selected = board.cell[7][5].getBackground();
                        movePiece();
                    }
                    //Queen Side
                    else if (sourceSquare.getRow() == 7 && sourceSquare.getCol() == 2 &&
                            board.cell[7][1].isOpen() && board.cell[7][2].isOpen() && board.cell[7][3].isOpen() && board.cell[7][0].getPiece().getType() == "rook" && board.cell[7][0].getPiece().getColor() == "white") {
                        movePiece();
                        sourceSquare = board.cell[7][3];
                        previousSquare = board.cell[7][0];
                        whoseMove = "white";
                        movePiece();
                    }
                }
                //Black Castle
                else if(pieceColor == "black" && previousSquare.getRow() == 0 && previousSquare.getCol() == 4) {
                    //King Side
                    if (sourceSquare.getRow() == 0 && sourceSquare.getCol() == 6 &&
                            board.cell[0][5].isOpen() && board.cell[0][6].isOpen() && board.cell[0][7].getPiece().getType() == "rook" && board.cell[0][7].getPiece().getColor() == "black") {
                        movePiece();
                        sourceSquare = board.cell[0][5];
                        previousSquare = board.cell[0][7];
                        whoseMove = "black";
                        selected = board.cell[0][5].getBackground();
                        movePiece();
                    }
                    //Queen Side
                    else if (sourceSquare.getRow() == 0 && sourceSquare.getCol() == 2 &&
                            board.cell[0][1].isOpen() && board.cell[0][2].isOpen() && board.cell[0][3].isOpen() && board.cell[0][0].getPiece().getType() == "rook" && board.cell[0][0].getPiece().getColor() == "black") {
                        movePiece();
                        sourceSquare = board.cell[0][3];
                        previousSquare = board.cell[0][0];
                        whoseMove = "black";
                        movePiece();
                    }
                }
            }
            else{clear();}
        }

    }

    //Function moves the pieces
    public void movePiece() {
        System.out.println("Piece was moved");
        if(capture && !sourceSquare.isOpen() && !previousSquare.getPiece().getColor().equalsIgnoreCase(pieceColor)) {
            sourceSquare.remove(sourceSquare.getPiece());
            sourceSquare.setPiece(null);
            capture = false;
        }
        previousSquare.setBackground(selected);
        sourceSquare.add(previousSquare.getPiece());
        sourceSquare.setPiece(previousSquare.getPiece());
        sourceSquare.revalidate();
        board.repaint();
        active = false;
        previousSquare.setStatus(true);
        previousSquare.setPiece(null);
        sourceSquare.setStatus(false);
        previousSquare = null;
        if (whoseMove == "white") whoseMove = "black";
        else whoseMove = "white";
    }

    public void clear() {
        System.out.println("Piece was not moved");
        previousSquare.setBackground(selected);
        active = false;
        capture = false;
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

    //To make sure user input is a priority
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
}
