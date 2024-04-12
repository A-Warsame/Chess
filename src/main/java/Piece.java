public abstract class Piece {
    private boolean taken = false;
    private boolean white = false;

    public Piece(boolean white) {
        this.white = white;
    }

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
