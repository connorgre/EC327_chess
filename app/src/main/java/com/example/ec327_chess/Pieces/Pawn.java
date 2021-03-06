package com.example.ec327_chess.Pieces;

import com.example.ec327_chess.Coordinate;
import com.example.ec327_chess.Board;
import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(Coordinate p, Boolean white) {
        super(p, white);
        if(white){
            value = 1;
        } else{
            value = -1;
        }
    }
    @Override
    public Pawn clone() {
        return new Pawn(this);
    }
    public Pawn(Pawn p){
        super(p);
        if(white){
            value = 1;
        } else{
            value = -1;
        }
    }

@Override
    public ArrayList<Coordinate> Moves(Board[][] b) {
        ArrayList<Coordinate> openMoves = new ArrayList<>();
        openMoves.clear();
        int x = position.getX();
        int y = position.getY();

        //could add en passant but I'm not even 100% sure how that works
        //would also need to add a whole new previous board variable to do
        if(!white) {
            if(y + 2 <=7) {
                if ((!hasMoved) && b[x][y + 2].getPiece() == null) {
                    openMoves.add(new Coordinate(x, y + 2));
                }
            }
            if(y + 1 <= 7) {
                if (b[x][y + 1].getPiece() == null) {
                    openMoves.add(new Coordinate(x, y + 1));
                }
                if (x + 1 <= 7 && b[x + 1][y + 1].getPiece() != null) {
                    if (b[x + 1][y + 1].getPiece().getWhite() != white) {
                        openMoves.add(new Coordinate(x + 1, y + 1));
                    }
                }
                if (x - 1 >= 0 && b[x - 1][y + 1].getPiece() != null) {
                    if (b[x - 1][y + 1].getPiece().getWhite() != white) {
                        openMoves.add(new Coordinate(x - 1, y + 1));
                    }
                }
            }
        }else{
            if(y - 2 >= 0) {
                if ((!hasMoved) && b[x][y - 2].getPiece() == null) {
                    openMoves.add(new Coordinate(x, y - 2));
                }
            }
            if(y - 1 >= 0) {
                if (b[x][y - 1].getPiece() == null) {
                    openMoves.add(new Coordinate(x, y - 1));
                }
                if (x + 1 <= 7 && b[x + 1][y - 1].getPiece() != null) {
                    if (b[x + 1][y - 1].getPiece().getWhite() != white) {
                        openMoves.add(new Coordinate(x + 1, y - 1));
                    }
                }
                if (x - 1 >= 0 && b[x - 1][y - 1].getPiece() != null) {
                    if (b[x - 1][y - 1].getPiece().getWhite() != white) {
                        openMoves.add(new Coordinate(x - 1, y - 1));
                    }
                }
            }
        }
        return openMoves;
    }
}
