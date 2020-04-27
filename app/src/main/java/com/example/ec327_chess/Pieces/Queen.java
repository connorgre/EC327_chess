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

    //Just combining the openMove statements for the rook and Bishop
    @Override
    public ArrayList<Coordinate> Moves(Board[][] b){
        ArrayList<Coordinate> openMoves = new ArrayList<>();
        Coordinate openC;
       //rook move statements
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
        //now the bishop move statements for diagonal
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
