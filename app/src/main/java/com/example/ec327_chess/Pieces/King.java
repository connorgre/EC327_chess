package com.example.ec327_chess.Pieces;

import com.example.ec327_chess.Coordinate;
import java.util.ArrayList;
import com.example.ec327_chess.Board;
public class King extends Piece {
    public King(Coordinate p, Boolean white) {
        super(p, white);
        if(white){
            value = 4;
        }else{
            value = -4;
        }
    }
@Override
    public ArrayList<Coordinate> Moves(Board[][] b){
        ArrayList<Coordinate> openMoves = new ArrayList<>();
        Coordinate openC;
        openC = new Coordinate(0,0);
        for(int i = -1; i < 2; i++){
            openC.setX(position.getX()+i);
                for(int j = -1; j < 2; j++){
                    openC.setY(position.getY()+j);
                            if(!openC.equals(position)){
                                if(b[openC.getX()][openC.getY()].getPiece().getWhite()!=white)
                                    openMoves.add(openC);
                            }
                }
        }
        return openMoves;
    }
}
