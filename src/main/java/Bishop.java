import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bishop extends Piece {
    private int x;
    private int y;

    public Bishop(int x, int y, boolean isWhite, String type) {
        super(x, y, isWhite, type);
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }
    @Override
    public int getY() {
        return y;
    }
//    public Image getImage(){
//
//    }


    @Override
    public boolean legalMove(ChessBoard chessBoard, Square beforeMove, Square afterMove) {
        if (beforeMove.getPiece() != null && beforeMove.getPiece().isWhite() == this.isWhite()) {
            return false;
        }
        int x = Math.abs(beforeMove.getX() - afterMove.getX());
        int y = Math.abs(beforeMove.getY() - afterMove.getY());

        return x == y; //moves diagonally
    }


//    @Override
//    public Image getPieceImage(String imagePath) {
//        //Load Bishop image
//        BufferedImage originalImage = null;
//        try {
//            originalImage = ImageIO.read(new File("black-bishop.png"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }


}

