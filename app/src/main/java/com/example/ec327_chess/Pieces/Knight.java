package com.example.ec327_chess.Pieces;

import com.example.ec327_chess.Coordinate;
import com.example.ec327_chess.Board;
import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(Coordinate p, Boolean white) {
        super(p, white);
        if(white){
            value = 3;
        } else{
            value = -3;
        }
    }
    @Override
    public Knight clone() {
        return new Knight(this);
    }
    public Knight(Knight k){
        super(k);
        if(white){
            value = 3;
        } else{
            value = -3;
        }
    }

    @Override
    public ArrayList<Coordinate> Moves(Board[][] b) {
        ArrayList<Coordinate> openMoves = new ArrayList<>();
        openMoves.clear();
        int x = position.getX();
        int y = position.getY();

        //moves knight in L shape and check that it remains inbounds
        //and that its not going to a piece of the same color
        if(x+2 < 8) {
            if((y+1 < 8) && ((b[x+2][y+1].getPiece() == null) || (b[x+2][y+1].getPiece().getWhite() != white))){openMoves.add(new Coordinate(x + 2, y + 1));}
            if((y-1 >=0) && ((b[x+2][y-1].getPiece() == null) || (b[x+2][y-1].getPiece().getWhite() != white))){openMoves.add(new Coordinate(x + 2, y - 1));}
        }
        if(x-2 >=0){
            if((y+1 < 8) && ((b[x-2][y+1].getPiece() == null) || (b[x-2][y+1].getPiece().getWhite() != white))){openMoves.add(new Coordinate(x - 2, y + 1));}
            if((y-1 >=0) && ((b[x-2][y-1].getPiece() == null) || (b[x-2][y-1].getPiece().getWhite() != white))){openMoves.add(new Coordinate(x - 2, y - 1));}
        }
        if(y+2 < 8){
            if((x+1 < 8) && ((b[x+1][y+2].getPiece() == null) || (b[x+1][y+2].getPiece().getWhite() != white))){openMoves.add(new Coordinate(x+1,y+2));}
            if((x-1 >=0) && ((b[x-1][y+2].getPiece() == null) || (b[x-1][y+2].getPiece().getWhite() != white))){openMoves.add(new Coordinate(x-1,y+2));}
        }
        if(y-2 >=0){
            if((x+1 < 8) && ((b[x+1][y-2].getPiece() == null) || (b[x+1][y-2].getPiece().getWhite() != white))){openMoves.add(new Coordinate(x+1,y-2));}
            if((x-1 >=0) && ((b[x-1][y-2].getPiece() == null) || (b[x-1][y-2].getPiece().getWhite() != white))){openMoves.add(new Coordinate(x-1,y-2));}
        }

        return openMoves;
    }
}
