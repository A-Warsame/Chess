public class Rook extends Piece {
    private int startX;
    private int startY;
    private int x;
    private int y;
    private boolean moved = false;


    public Rook(int x, int y, boolean isWhite, String type) {
        super(x, y, isWhite, type);
        this.x = x;
        this.y = y;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    @Override
    public int getX() {
        return x;
    }
    @Override
    public int getY() {
        return y;
    }
    // Method to check if the rook has moved from its starting position
    public boolean isRookMoved() {
        return moved && (getX() != startX || getY() != startY);
    }

    @Override
    public boolean legalMove(ChessBoard chessBoard, Square beforeMove, Square afterMove) {
        if (beforeMove.getPiece() != null && beforeMove.getPiece().isWhite() == this.isWhite()) {
            return false;
    }


        int x = Math.abs(beforeMove.getX() - afterMove.getX());
        int y = Math.abs(beforeMove.getY() - afterMove.getY());

        // Rook moves horizontally or vertically
        return x == 0 || y == 0;
    }


}
