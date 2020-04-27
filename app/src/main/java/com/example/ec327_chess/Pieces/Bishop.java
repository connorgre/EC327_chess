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

    //goes through coordinates diagonally until it reaches an occupied square
    //continues one more if the square is the other team
    @Override
    public ArrayList<Coordinate> Moves(Board[][] b) {
        ArrayList<Coordinate> openMoves = new ArrayList<>();
        Coordinate openC;
        for(int i = 1; i < (Math.min(8-position.getX(),8-position.getY())); i++){
            if(b[position.getX()+i][position.getY()+i] == null){
                openC = new Coordinate(position.getX()+i,position.getY()+i);
                openMoves.add(openC);
            }else if(b[position.getX()+i][position.getY()+i].getPiece().getWhite() != white){
                openC = new Coordinate(position.getX()+i,position.getY()+i);
                openMoves.add(openC);
                break;
                }
        }

        for(int i = 1; i < (Math.min(position.getX(),position.getY())+1); i++){
            if(b[position.getX()-i][position.getY()-i] == null){
                openC = new Coordinate(position.getX()+i,position.getY()+i);
                openMoves.add(openC);
            }else if(b[position.getX()+i][position.getY()+i].getPiece().getWhite() != white){
                openC = new Coordinate(position.getX()+i,position.getY()+i);
                openMoves.add(openC);
                break;
            }
        }
        for(int i = 1; i < (Math.min(8-position.getX(),position.getY()+1)); i++){
            if(b[position.getX()+i][position.getY()-i] == null){
                openC = new Coordinate(position.getX()+i,position.getY()+i);
                openMoves.add(openC);
            }else if(b[position.getX()+i][position.getY()+i].getPiece().getWhite() != white){
                openC = new Coordinate(position.getX()+i,position.getY()+i);
                openMoves.add(openC);
                break;
            }
        }
        for(int i = 1; i < (Math.min(position.getX()+1,8-position.getY())); i++){
            if(b[position.getX()-i][position.getY()+i] == null){
                openC = new Coordinate(position.getX()+i,position.getY()+i);
                openMoves.add(openC);
            }else if(b[position.getX()+i][position.getY()+i].getPiece().getWhite() != white){
                openC = new Coordinate(position.getX()+i,position.getY()+i);
                openMoves.add(openC);
                break;
            }
        }
        return openMoves;
    }
}
