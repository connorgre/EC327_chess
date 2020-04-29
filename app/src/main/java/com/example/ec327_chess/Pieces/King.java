package com.example.ec327_chess.Pieces;

import com.example.ec327_chess.Coordinate;
import java.util.ArrayList;
import com.example.ec327_chess.Board;
public class King extends Piece {
    public King(Coordinate p, Boolean white) {
        super(p, white);
        if(white){
            value = 50;
        }else{
            value = -50;
        }
    }
    public King(King k){
        super(k);
        if(white){
            value = 50;
        }else{
            value = -50;
        }
    }
    @Override
    public King clone() {
        return new King(this);
    }
@Override
    public ArrayList<Coordinate> Moves(Board[][] b){
        ArrayList<Coordinate> openMoves = new ArrayList<>();
        openMoves.clear();

        for(int i = -1; i < 2; i++){
            for(int j = -1; j < 2; j++){
                if(j != 0 || i != 0){
                    if((position.getY() + j <= 7) && (position.getY() + j >= 0) && (position.getX() + i >= 0) && (position.getX() + i <= 7)) {
                        if ((b[position.getX() + i][position.getY() + j].getPiece() == null) || b[position.getX() + i][position.getY() + j].getPiece().getWhite() != white)
                            openMoves.add(new Coordinate(position.getX()+i,position.getY()+j));
                    }
                }
            }
        }
        return openMoves;
    }
}
