package com.example.ec327_chess.Pieces;

import com.example.ec327_chess.Coordinate;
import com.example.ec327_chess.Board;
import java.util.ArrayList;

public class Pawn extends Piece {

    private boolean hasMoved;
    public Pawn(Coordinate p, Boolean white) {
        super(p, white);
        if(white){
            value = 1;
        } else{
            value = -1;
    }
        hasMoved = false;
    }
@Override
    public ArrayList<Coordinate> Moves(Board[][] b) {
        ArrayList<Coordinate> openMoves = new ArrayList<>();
        int x = position.getX();
        int y = position.getY();

        //could add en passant but I'm not even 100% sure how that works
        //would also need to add a whole new previous board variable to do
        if(white) {
            if ((!hasMoved) && b[x][y + 2].getPiece() == null) {
                openMoves.add(new Coordinate(x, y + 2));
            }
            if (b[x][y + 1].getPiece() == null) {
                openMoves.add(new Coordinate(x, y + 1));
            }
            if (b[x + 1][y + 1].getPiece().getWhite() != white) {
                openMoves.add(new Coordinate(x + 1, y + 1));
            }
            if (b[x - 1][y + 1].getPiece().getWhite() != white) {
                openMoves.add(new Coordinate(x - 1, y + 1));
            }
        }else{
            if ((!hasMoved) && b[x][y - 2].getPiece() == null) {
                openMoves.add(new Coordinate(x, y - 2));
            }
            if (b[x][y - 1].getPiece() == null) {
                openMoves.add(new Coordinate(x, y - 1));
            }
            if (b[x + 1][y - 1].getPiece().getWhite() != white) {
                openMoves.add(new Coordinate(x + 1, y - 1));
            }
            if (b[x - 1][y - 1].getPiece().getWhite() != white) {
                openMoves.add(new Coordinate(x - 1, y - 1));
            }
        }
        return openMoves;
    }
    public void setHasMoved(){
        hasMoved = true;
    }
}
