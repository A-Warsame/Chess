public class King extends Piece {
    private boolean kingCastled = false;

    private int x;
    private int y;

    public King(int x, int y, boolean isWhite, String type) {
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

    public boolean isKingCastled() {
        return this.kingCastled;
    }

    public void setKingCastled(boolean kingCastled) {
        this.kingCastled = kingCastled;
    }

    @Override
    public boolean legalMove(ChessBoard chessBoard, Square beforeMove, Square afterMove) {
        if (afterMove.getPiece() != null && afterMove.getPiece().isWhite() == this.isWhite()) {
            return false; // Cannot move to a square occupied by a piece of the same color
        }

        int x = Math.abs(beforeMove.getX() - afterMove.getX());
        int y = Math.abs(beforeMove.getY() - afterMove.getY());
        if (x + y == 1) {
            return true; // Move is legal if it's adjacent to the current position
        }

        return legalCastling(chessBoard, beforeMove, afterMove);
    }

    public boolean legalCastling(ChessBoard chessBoard, Square beforeMove, Square afterMove) {
        if (isKingCastled()) {
            return false; // Castling already performed
        }
        if (!isCastlingValid(chessBoard, beforeMove, afterMove)) {
            return false; // Castling conditions not met
        }
        // Additional logic for checking if castling is valid
        return true;
    }

    // Check if castling is valid - king not in check, king and rook not moved yet, king moves 2 squares along x axis
    public boolean isCastlingValid(ChessBoard chessBoard, Square beforeMove, Square afterMove) {
        // Retrieve the pieces involved in the castling move
        Piece beforePiece = beforeMove.getPiece();
        Piece afterPiece = afterMove.getPiece();

        // Ensure both squares contain pieces and they are instances of King and Rook respectively
        if (beforePiece instanceof King && afterPiece instanceof Rook) {
            // Check if the conditions for castling are met
            if (!((King) beforePiece).isKingCastled() && !((Rook) afterPiece).isRookMoved() &&
                    beforeMove.getY() == afterMove.getY() && Math.abs(beforeMove.getX() - afterMove.getX()) == 2) {
                // Check if there are no pieces between the king and the rook
                int startY = Math.min(beforeMove.getX(), afterMove.getX());
                int endY = Math.max(beforeMove.getX(), afterMove.getX());
                for (int i = startY + 1; i < endY; i++) {
                    if (chessBoard.getSquare(i, beforeMove.getY()).getPiece() != null) {
                        return false; // Pieces block castling path
                    }
                }
                // Check if the king is not in check
                if (!chessBoard.isKingInCheck(this.isWhite())) {
                    return true; // Castling is valid
                }
            }
        }
        return false; // Default case: castling not valid
    }
}




//delete under

//public class King extends Piece{
//    private boolean kingCastled = false;
//
//    public King(boolean white) {
//        super(white);
//    }
//
//    public boolean isKingCastled() {
//        return this.kingCastled;
//    }
//
//    public void setKingCastled(boolean kingCastled) {
//        this.kingCastled = kingCastled;
//    }
//
//    @Override
//    public boolean legalMove(ChessBoard chessBoard, Square beforeMove, Square afterMove) {
//        if (afterMove.getPiece().isWhite() == this.isWhite()) {
//            return false; //not in occupied square
//        }
//
//        int x = Math.abs(beforeMove.getX() - afterMove.getX());
//        int y = Math.abs(beforeMove.getY() - afterMove.getY());
//        if (x + y == 1) {
//            return true; //not being attacked by piece in end square
//        }
//        return this.legalCastling(chessBoard, beforeMove, afterMove);
//    }
//    public boolean legalCastling(ChessBoard chessBoard, Square beforeMove, Square afterMove) {
//        if (this.isKingCastled()) {
//            return false;
//        }
//        if (!isCastlingValid(beforeMove, afterMove)) {
//            return false;
//        }
//    }
//    //check if its valid - king not in check, king and rook not moved yet, king moves 2 squares along x axis
//    public boolean isCastlingValid(Square beforeMove, Square afterMove) {
//        if (beforeMove.getPiece())
//    }
//}
