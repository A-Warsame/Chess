import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Piece {
    private int x;
    private int y;
    private boolean taken = false;
    private boolean white = false;
    private boolean isWhite;
    private String type;

    private BufferedImage image;

    public Piece(int x, int y, boolean isWhite, String type) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
        this.type = type;
        loadImage();
    }

    private void loadImage() {
        // Load the image based on the type of piece
        try {
            String color = isWhite ? "white" : "black";
            String imageName = "src/main/resources/" + color + "-" + type + ".png";
            image = ImageIO.read(new File(imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public abstract int getX();

    public abstract int getY();
//    public abstract Image getImage();


    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public boolean isWhite() {
        return white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }
    public abstract boolean legalMove(ChessBoard chessBoard, Square start, Square end);
}
