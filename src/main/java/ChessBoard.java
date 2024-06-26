import java.awt.*;
import javax.swing.*;

public class ChessBoard extends JPanel {
    public Square[][] squares = new Square[8][8];					//Board size
    public Color black = new Color(145,140,125);			//Black cells
    public Color white = new Color(232, 235, 239);			//White Cells

    //Declaration of Black pieces
    public Pawn[] blackPawn = new Pawn[8];
    public Bishop[] blackBishop = new Bishop[2];
    public Knight[] blackKnight = new Knight[2];
    public Rook[] blackRook = new Rook[2];
    public King blackKing = new King("black");
    public Queen blackQueen = new Queen("black");

    //Declaration of White pieces
    public Pawn[] whitePawn = new Pawn[8];
    public Bishop[] whiteBishop = new Bishop[2];
    public Knight[] whiteKnight = new Knight[2];
    public Rook[] whiteRook = new Rook[2];
    public King whiteKing = new King("white");
    public Queen whiteQueen = new Queen("white");

    //Board Object
    public ChessBoard()  {
        initializePieces();
        setLayout(new GridLayout(8,8));
        //Adding the squares
        for (int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if((i + j) % 2 == 0 ) {
                    this.squares[i][j] = new Square(i, j);
                    this.squares[i][j].setBackground(white);
                }
                else {
                    this.squares[i][j] = new Square(i, j);
                    this.squares[i][j].setBackground(black);
                }
                squares[i][j].setPosition(i, j);
                add(squares[i][j]);
            }
        }

    }

    //Initialize the Pieces
    public void initializePieces() {
        //Initialize Pawns
        for(int i = 0; i < 8; i++) {
            blackPawn[i] = new Pawn("black");
            whitePawn[i] = new Pawn("white");
        }
        //Initialize Other Pieces
        for(int i = 0; i < 2; i++) {
            blackBishop[i] = new Bishop("black");
            whiteBishop[i] = new Bishop("white");
            blackKnight[i] = new Knight("black");
            whiteKnight[i] = new Knight("white");
            blackRook[i] = new Rook("black");
            whiteRook[i] = new Rook("white");
        }
    }

    public void initializeChessBoard() {
        //Placing Pawns in the chess board and setting as not open
        for(int i = 0; i < 8; i++) {
            this.squares[1][i].add(blackPawn[i]);
            this.squares[6][i].add(whitePawn[i]);
            this.squares[1][i].setPiece(blackPawn[i]);
            this.squares[6][i].setPiece(whitePawn[i]);
        }
        //Placing and Setting Rook
        this.squares[0][0].add(blackRook[0]);
        this.squares[0][7].add(blackRook[1]);
        this.squares[7][0].add(whiteRook[0]);
        this.squares[7][7].add(whiteRook[1]);
        this.squares[0][0].setPiece(blackRook[0]);
        this.squares[0][7].setPiece(blackRook[1]);
        this.squares[7][0].setPiece(whiteRook[0]);
        this.squares[7][7].setPiece(whiteRook[1]);
        //Placing and Setting Knight
        this.squares[0][1].add(blackKnight[0]);
        this.squares[0][6].add(blackKnight[1]);
        this.squares[7][1].add(whiteKnight[0]);
        this.squares[7][6].add(whiteKnight[1]);
        this.squares[0][1].setPiece(blackKnight[0]);
        this.squares[0][6].setPiece(blackKnight[1]);
        this.squares[7][1].setPiece(whiteKnight[0]);
        this.squares[7][6].setPiece(whiteKnight[1]);
        //Placing and Setting Bishop
        this.squares[0][2].add(blackBishop[0]);
        this.squares[0][5].add(blackBishop[1]);
        this.squares[7][2].add(whiteBishop[0]);
        this.squares[7][5].add(whiteBishop[1]);
        this.squares[0][2].setPiece(blackBishop[0]);
        this.squares[0][5].setPiece(blackBishop[1]);
        this.squares[7][2].setPiece(whiteBishop[0]);
        this.squares[7][5].setPiece(whiteBishop[1]);
        //Placing and Setting King and Queen
        this.squares[0][3].add(blackQueen);
        this.squares[0][4].add(blackKing);
        this.squares[7][3].add(whiteQueen);
        this.squares[7][4].add(whiteKing);
        this.squares[0][3].setPiece(blackQueen);
        this.squares[0][4].setPiece(blackKing);
        this.squares[7][3].setPiece(whiteQueen);
        this.squares[7][4].setPiece(whiteKing);
        setAsNotOpen();
    }

    //Set the square status for a new game
    public void setAsNotOpen() {
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0 || i == 1 || i == 6 || i == 7) {		//Rows with pieces on them
                    this.squares[i][j].setStatus(false);
                }
                else this.squares[i][j].setStatus(true);			//Rows without pieces
            }
        }
    }

}