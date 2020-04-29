package com.example.ec327_chess.Pieces;

import com.example.ec327_chess.Coordinate;
import com.example.ec327_chess.Board;
import java.lang.Math;
import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(Coordinate p, Boolean white) {
        super(p, white);
        if(white){
            value = 10;
        }else{
            value = -10;
        }
    }
    @Override
    public Queen clone() {
        return new Queen(this);
    }

    public Queen(Queen q){
        super(q);
        if(white){
            value = 10;
        }else{
            value = -10;
        }
    }
    //Just combining the openMove statements for the rook and Bishop
    @Override
    public ArrayList<Coordinate> Moves(Board[][] b){
        ArrayList<Coordinate> openMoves = new ArrayList<>();
        openMoves.clear();
        Coordinate openC;
       //rook move statements
        for(int i = (position.getX()+1); i<8 ; i++){
            if(b[i][position.getY()].getPiece() == null){
                openMoves.add(new Coordinate(i,position.getY()));
            }else{
                //checks if the next square up is a different color
                //bc then it is a valid move to capture
                if(b[i][position.getY()].getPiece().getWhite() != white){
                    openMoves.add(new Coordinate(i,position.getY()));
                }
                break;
            }
        }
        //Just repeats above three more times to get all directions
        for(int i = (position.getX()-1); i>=0 ; i--){
            if(b[i][position.getY()].getPiece() == null){
                openMoves.add(new Coordinate(i,position.getY()));
            }else{
                //checks if the next square up is a different white
                //bc then it is a valid move to capture
                if(b[i][position.getY()].getPiece().getWhite() != white){
                    openMoves.add(new Coordinate(i,position.getY()));
                }
                break;
            }
        }

        for(int i = (position.getY()+1); i<8 ; i++){
            if(b[position.getX()][i].getPiece() == null){
                openMoves.add(new Coordinate(position.getX(),i));
            }else{
                //checks if the next square up is a different white
                //bc then it is a valid move to capture
                if(b[position.getX()][i].getPiece().getWhite() != white){
                    openMoves.add(new Coordinate(position.getX(),i));
                }
                break;
            }
        }

        for(int i = (position.getY()-1); i>=0 ; i--){
            if(b[position.getX()][i].getPiece() == null){
                openMoves.add(new Coordinate(position.getX(),i));
            }else{
                //checks if the next square up is a different white
                //bc then it is a valid move to capture
                if(b[position.getX()][i].getPiece().getWhite() != white){
                    openMoves.add(new Coordinate(position.getX(),i));
                }
                break;
            }
        }
        //now the bishop move statements for diagonal

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
