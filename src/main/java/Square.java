import javax.swing.JPanel;
import java.awt.*;

@SuppressWarnings("serial")
public class Square extends JPanel {
    private Piece piece; 						//What piece in the square
    private boolean open = true;				//Cell Status (Open?)
    private int row;
    private int col;


    //A cell on the board
    public Square (int row, int col) {

        this.row = row;
        this.col = col;
    }
    public boolean isOpen() {
        return this.open;
    }
    public void setStatus(boolean status) {
        this.open = status;
    }
    public Piece getPiece() {
        return this.piece;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public int getRow() {
        return this.row;
    }
    public int getCol() {
        return this.col;
    }
}
