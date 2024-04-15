public class Knight extends Piece {

    private int x;
    private int y;

    public Knight(int x, int y, boolean isWhite, String type) {
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

    @Override
    public boolean legalMove(ChessBoard chessBoard, Square start, Square end) {
        if (end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }
        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());
        return x * y == 2;
    }
}
