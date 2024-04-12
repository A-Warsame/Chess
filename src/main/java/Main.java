import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame gameFrame = new JFrame("Chess");
        ChessBoard chessBoard = new ChessBoard();
        gameFrame.add(chessBoard);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null); // Center the frame
        gameFrame.setVisible(true);
    }
}
