package com.example.ec327_chess;

import com.example.ec327_chess.Pieces.*;
import com.example.ec327_chess.Board;
import com.example.ec327_chess.Coordinate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

//in main, bestP must be called BEFORE bestMove, because bestP also calculates the best move.

public class chess_Ai {
    
    private Piece bestP;
    private Coordinate bestMove;
    private int totalValue;
    private ArrayList<Piece> blackPieces;
    private ArrayList<Piece> whitePieces;
    private int level;

    public chess_Ai(int level, Board[][] b){
        bestP = null;
        bestMove = new Coordinate(0,0);
        totalValue = 0;
        blackPieces = getBlackPieces(b);
        whitePieces = getWhitePieces(b);
        this.level = level;
    }

    public Piece getBestP(Board[][] b){
        Random rand = new Random();
        blackPieces = getBlackPieces(b);
        whitePieces = getWhitePieces(b);
        totalValue = getTotalValue(b);

        Piece bestP = blackPieces.get(rand.nextInt(blackPieces.size()));
        bestMove.setX(bestP.Moves(b).get(rand.nextInt(bestP.Moves(b).size())).getX());
        bestMove.setY(bestP.Moves(b).get(rand.nextInt(bestP.Moves(b).size())).getY());

        int alpha = 1000;
        int beta = -1000;

        //plays completely randomly
        if(level == 0){
            bestP = blackPieces.get(rand.nextInt(blackPieces.size()));
            bestMove.setX(bestP.Moves(b).get(rand.nextInt(bestP.Moves(b).size())).getX());
            bestMove.setY(bestP.Moves(b).get(rand.nextInt(bestP.Moves(b).size())).getY());
        } else {
            int d = level*2-1;
            minimax(b,d,alpha,beta,false);
        }
        //initial attempt at finding the best position
        /*
        if(level == 1) {
            Board[][] tempBoard;
            for (int i = 0; i < blackPieces.size(); i++) {
                tempBoard = b;
                Piece p = blackPieces.get(i);
                ArrayList<Coordinate> openMoves = p.Moves(b);

                for (int j = 0; j < openMoves.size(); j++) {
                    tempBoard[p.getPosition().getX()][p.getPosition().getY()].setPiece(null);
                    tempBoard[openMoves.get(j).getX()][openMoves.get(j).getY()].setPiece(p);

                    if (getTotalValue(tempBoard) < totalValue) {
                        totalValue = getTotalValue(tempBoard);
                        bestP = p;
                        bestMove.setX(openMoves.get(j).getX());
                        bestMove.setY(openMoves.get(j).getY());
                    }
                }
            }
        }
        */
        //uses minimax and alpha beta pruning.
        /*if(level == 2){
            Board[][] tempBoard;
            Board[][] tempBoard2;
            Board[][] tempBoard3;
            for (int i = 0; i < blackPieces.size(); i++) {
                tempBoard = b;
                Piece p = blackPieces.get(i);
                ArrayList<Coordinate> openMoves = p.Moves(b);

                for (int j = 0; j < openMoves.size(); j++) {
                    tempBoard[p.getPosition().getX()][p.getPosition().getY()].setPiece(null);
                    tempBoard[openMoves.get(j).getX()][openMoves.get(j).getY()].setPiece(p);
                    whitePieces = getWhitePieces(b);

                    for(int x = 0; x < whitePieces.size(); x++){
                        tempBoard2 = tempBoard;
                        Piece p2 = whitePieces.get(x);
                        ArrayList<Coordinate> openMoves2 = p2.Moves(tempBoard);

                        for (int y = 0; y < openMoves2.size(); y++) {
                            tempBoard2[p.getPosition().getX()][p.getPosition().getY()].setPiece(null);
                            tempBoard2[openMoves.get(j).getX()][openMoves.get(j).getY()].setPiece(p);
                            blackPieces = getBlackPieces(tempBoard2);

                            for (int c = 0; c < blackPieces.size(); c++) {
                                tempBoard3 = tempBoard2;
                                Piece p3 = blackPieces.get(c);
                                ArrayList<Coordinate> openMoves3 = p.Moves(tempBoard2);

                                for (int d = 0; d < openMoves.size(); d++) {
                                    tempBoard3[p3.getPosition().getX()][p3.getPosition().getY()].setPiece(null);
                                    tempBoard3[openMoves.get(d).getX()][openMoves.get(d).getY()].setPiece(p);
                                    if(getTotalValue(tempBoard3) < alpha) {
                                        alpha = getTotalValue(tempBoard3);
                                    }
                                    if(getTotalValue(tempBoard3) > beta){
                                        beta = getTotalValue(tempBoard3);
                                    }
                                }
                            }
                        }
                    }

                }
            }

        }*/
        return bestP;
    }

    //must have called getBestP immediately beforehand!!!!
    public Coordinate getBestMove(){
        return bestMove;
    }

    public int getTotalValue(Board[][] b){
        int tempTotal = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Piece p = b[i][j].getPiece();
                if(p != null) {
                    int midAdd = 0;
                    //midAdd gives extra weight for advancing pieces up the board

                    if(p.getWhite()){
                        if(j == 2){
                            midAdd = 1;
                        }else if(j >2){
                            midAdd = 2;
                        }
                    }else {
                        if(j == 5){
                            midAdd = -1;
                        }else if(j < 5){
                            midAdd = -2;
                        }
                    }

                    tempTotal += b[i][j].getPiece().getValue() + midAdd;
                }
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

    public ArrayList<Piece> getWhitePieces(Board[][] b) {
        whitePieces.clear();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(b[i][j] != null) {
                    if (b[i][j].getPiece().getWhite()) {
                        whitePieces.add(b[i][j].getPiece());
                    }
                }
            }
        }
        return whitePieces;
    }

    public int minimax(Board[][] b, int depth, int alpha, int beta, Boolean white){
        ArrayList<Piece> bPieces;
        ArrayList<Piece> wPieces;
        ArrayList<Coordinate> moves;
        int eval;

        if(depth == 0){
            return getTotalValue(b);
        }
        if(white){
            int maxEval = -100;
            wPieces = getWhitePieces(b);
            for(Piece p:wPieces){
                moves = p.Moves(b);
                for (Coordinate coord:moves) {
                    if(b[coord.getX()][coord.getY()].getPiece() instanceof King){
                        beta = -1000;
                    }
                    b[p.getPosition().getX()][p.getPosition().getY()].setPiece(null);
                    p.setPosition(coord);
                    b[coord.getX()][coord.getY()].setPiece(p);

                    eval = minimax(b,depth -1,alpha,beta,false);
                    maxEval = Math.max(eval,maxEval);

                    alpha = Math.max(eval,alpha);
                    if(beta <= alpha){
                        break;
                    }
                }
                if(beta <= alpha){
                    break;
                }
            }
            return maxEval;
        }else{
            int minEval = 100;
            bPieces = getBlackPieces(b);

            for(Piece p:bPieces){
                moves = p.Moves(b);

                for (Coordinate coord:moves) {
                    if(b[coord.getX()][coord.getY()].getPiece() instanceof King){
                        beta = -1000;
                    }

                    b[p.getPosition().getX()][p.getPosition().getY()].setPiece(null);
                    p.setPosition(coord);
                    b[coord.getX()][coord.getY()].setPiece(p);

                    eval = minimax(b,depth -1,alpha,beta,false);
                    minEval = Math.min(eval,minEval);

                    if(eval < beta){
                        bestP = p;
                        bestMove.setY(coord.getY());
                        bestMove.setX(coord.getX());
                        beta = eval;
                    }

                    if(beta <= alpha){
                        break;
                    }
                }
                if(beta <= alpha){
                    break;
                }
            }
        return minEval;
        }
    }
}
