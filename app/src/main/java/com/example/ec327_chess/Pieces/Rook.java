package com.example.ec327_chess.Pieces;

import com.example.ec327_chess.Coordinate;
import com.example.ec327_chess.Board;
import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(Coordinate c, Boolean white){
        super(c,white);
        if(white){
            value = 5;
        }else{
            value = -5;
        }
    }

    //checks for open spots to move to
    @Override
    public ArrayList<Coordinate> Moves(Board[][] b){
        ArrayList<Coordinate> openMoves = new ArrayList<>();
        Coordinate openC;

        for(int i=(position.getX()+1); i<8 ; i++){
            if(b[i][position.getY()].getPiece() == null){
                openC = new Coordinate(i,position.getY());
                openMoves.add(openC);
            }else{
                //checks if the next square up is a different white
                //bc then it is a valid move to capture
                if(b[i][position.getY()].getPiece().getWhite() != white){
                    openC = new Coordinate(i,position.getY());
                    openMoves.add(openC);
                }
                break;
            }
        }
        //Just repeats above three more times to get all directions
        for(int i=(position.getX()-1); i>=0 ; i--){
            if(b[i][position.getY()].getPiece() == null){
                openC = new Coordinate(i,position.getY());
                openMoves.add(openC);
            }else{
                //checks if the next square up is a different white
                //bc then it is a valid move to capture
                if(b[i][position.getY()].getPiece().getWhite() != white){
                    openC = new Coordinate(i,position.getY());
                    openMoves.add(openC);
                }
                break;
            }
        }

        for(int i=(position.getY()+1); i<8 ; i++){
            if(b[position.getX()][i].getPiece() == null){
                openC = new Coordinate(i,position.getY());
                openMoves.add(openC);
            }else{
                //checks if the next square up is a different white
                //bc then it is a valid move to capture
                if(b[position.getX()][i].getPiece().getWhite() != white){
                    openC = new Coordinate(i,position.getY());
                    openMoves.add(openC);
                }
                break;
            }
        }

        for(int i=(position.getY()-1); i>=0 ; i--){
            if(b[position.getX()][i].getPiece() == null){
                openC = new Coordinate(i,position.getY());
                openMoves.add(openC);
            }else{
                //checks if the next square up is a different white
                //bc then it is a valid move to capture
                if(b[position.getX()][i].getPiece().getWhite() != white){
                    openC = new Coordinate(i,position.getY());
                    openMoves.add(openC);
                }
                break;
            }
        }
        return openMoves;
    }
}
