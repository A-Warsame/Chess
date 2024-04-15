public class Queen extends Piece {
    private int x;
    private int y;

    public Queen(int x, int y, boolean isWhite, String type) {
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
    public boolean legalMove(ChessBoard chessBoard, Square beforeMove, Square afterMove) {
        if (beforeMove.getPiece() != null && beforeMove.getPiece().isWhite() == this.isWhite()) {
            return false;
        }
        int x = Math.abs(beforeMove.getX() - afterMove.getX());
        int y = Math.abs(beforeMove.getY() - afterMove.getY());

        return x == 0 || y == 0 || x == y; //move like rook and bishop
    }
}
