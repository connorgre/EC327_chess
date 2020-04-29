package com.example.ec327_chess.Pieces;

import com.example.ec327_chess.Coordinate;
import com.example.ec327_chess.Board;
import java.lang.Math;
import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(Coordinate p, Boolean white) {
        super(p, white);
        if(white){
            value = 3;
        }else{
            value = -3;
        }
    }
    @Override
    public Bishop clone() {
        return new Bishop(this);
    }

    public Bishop(Bishop b){
        super(b);
        if(white){
            value = 3;
        }else{
            value = -3;
        }
    }

    //goes through coordinates diagonally until it reaches an occupied square
    //continues one more if the square is the other team
    @Override
    public ArrayList<Coordinate> Moves(Board[][] b) {
        ArrayList<Coordinate> openMoves = new ArrayList<>();
        openMoves.clear();

        for(int i = 1; i < 8; i++){
            if(position.getX() + i <= 7 && position.getY() + i <= 7) {
                if (b[position.getX() + i][position.getY() + i].getPiece() == null) {
                    openMoves.add(new Coordinate(position.getX() + i, position.getY() + i));
                } else if (b[position.getX() + i][position.getY() + i].getPiece().getWhite() != white) {
                    openMoves.add(new Coordinate(position.getX() + i, position.getY() + i));
                    break;
                } else if (b[position.getX() + i][position.getY() + i].getPiece().getWhite() == white) {
                    break;
                }
            }
        }
        for(int i = 1; i < 8; i++){
            if(position.getX() - i >= 0 && position.getY() -i >= 0) {
                if (b[position.getX() - i][position.getY() - i].getPiece() == null) {
                    openMoves.add(new Coordinate(position.getX() - i, position.getY() - i));
                } else if (b[position.getX() - i][position.getY() - i].getPiece().getWhite() != white) {
                    openMoves.add(new Coordinate(position.getX() - i, position.getY() - i));
                    break;
                } else if (b[position.getX() - i][position.getY() - i].getPiece().getWhite() == white) {
                    break;
                }
            }
        }
        for(int i = 1; i < 8; i++){
            if(position.getX() + i <= 7 && position.getY() - i >= 0) {
                if (b[position.getX() + i][position.getY() - i].getPiece() == null) {
                    openMoves.add(new Coordinate(position.getX() + i, position.getY() - i));
                } else if (b[position.getX() + i][position.getY() - i].getPiece().getWhite() != white) {
                    openMoves.add(new Coordinate(position.getX() + i, position.getY() - i));
                    break;
                } else if (b[position.getX() + i][position.getY() - i].getPiece().getWhite() == white) {
                    break;
                }
            }
        }
        for(int i = 1; i < 8; i++) {
            if (position.getX() - i >= 0 && position.getY() + i <= 7) {
                if (b[position.getX() - i][position.getY() + i].getPiece() == null) {
                    openMoves.add(new Coordinate(position.getX() - i, position.getY() + i));
                } else if (b[position.getX() - i][position.getY() + i].getPiece().getWhite() != white) {
                    openMoves.add(new Coordinate(position.getX() - i, position.getY() + i));
                    break;
                } else if (b[position.getX() - i][position.getY() + i].getPiece().getWhite() == white) {
                    break;
                }
            }
        }
        return openMoves;
    }
}
