public class Pawn extends Piece {
    private boolean hasMoved = false;

    private int x;
    private int y;

    public Pawn(int x, int y, boolean isWhite, String type) {
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

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean hasMoved() {
        return this.hasMoved;
    }

    @Override
    public boolean legalMove(ChessBoard chessBoard, Square start, Square end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }
        int x = Math.abs(start.getX() - end.getX());
        int y = start.getY() - end.getY(); // For white pawns, y is positive; for black pawns, it's negative


        // Pawn move 1 or 2 squares on first move, and one square forward afterwards
        if (x == 0 && y == (this.isWhite() ? -1 : 1)) {
            return true;
        } else if (x == 0 && y == (this.isWhite() ? -2 : 2) && !this.hasMoved()) {
            // Pawn's first move: it can move two squares forward
            return true;
        }

        // Pawn can capture diagonally
        return x == 1 && y == (this.isWhite() ? -1 : 1);
    }
}
