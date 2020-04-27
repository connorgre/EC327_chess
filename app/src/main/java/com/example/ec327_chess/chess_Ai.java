package com.example.ec327_chess;

import com.example.ec327_chess.Pieces.*;
import com.example.ec327_chess.Board;
import com.example.ec327_chess.Coordinate;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class chess_Ai {
    
    private Piece bestP;
    private Coordinate bestMove;
    private int totalValue;
    private ArrayList<Piece> blackPieces;

    public chess_Ai(Board[][] b){
        bestP = null;
        bestMove = null;
        totalValue = 0;
        blackPieces = getBlackPieces(b);
    }

    public Piece getBestP(Board[][] b){
        blackPieces = getBlackPieces(b);
        totalValue = getTotalValue(b);
        Piece bestP = blackPieces.get(0);
        Board[][] tempBoard;
        for (int i = 0; i < blackPieces.size(); i ++) {
            tempBoard = b;
            Piece p = blackPieces.get(i);
            ArrayList<Coordinate> openMoves = p.Moves(b);

            for(int j = 0; j < openMoves.size(); j++){
                tempBoard[p.getPosition().getX()][p.getPosition().getY()].setPiece(null);
                tempBoard[openMoves.get(j).getX()][openMoves.get(j).getY()].setPiece(p);

                if(getTotalValue(tempBoard) < totalValue) {
                    totalValue = getTotalValue(tempBoard);
                    bestP = p;
                }
            }
        }
        return bestP;
    }

    public int getTotalValue(Board[][] b){
        int tempTotal = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                tempTotal += b[i][j].getPiece().getValue();
            }
        }
        return tempTotal;
    }

    public ArrayList<Piece> getBlackPieces(Board[][] b) {
        blackPieces.clear();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(b[i][j] != null) {
                    if (!b[i][j].getPiece().getWhite()) {
                        blackPieces.add(b[i][j].getPiece());
                    }
                }
            }
        }
        return blackPieces;
    }
}
