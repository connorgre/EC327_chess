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
    
    protected Coordinate bestP;
    protected Coordinate bestMove;
    protected int totalValue;
    protected ArrayList<Piece> blackPieces;
    protected ArrayList<Piece> whitePieces;
    protected int level;
    protected ArrayList<Coordinate> previousMoves;

    public chess_Ai(int level, Board[][] board){
        bestP = new Coordinate(0,0);
        bestMove = new Coordinate(0,0);
        totalValue = 0;
        blackPieces = getBlackPieces(board);
        whitePieces = getWhitePieces(board);
        this.level = level;
    }

    public Coordinate getBestP(Board[][] b){
        Random rand = new Random();
        blackPieces = getBlackPieces(b);
        whitePieces = getWhitePieces(b);
        totalValue = getTotalValue(b);

        int alpha = -10000;
        int beta = 10000;

        //plays completely randomly at level 0
        if(level == 0){
            int randInt = rand.nextInt(blackPieces.size());
            bestP = new Coordinate(blackPieces.get(randInt).getPosition());

            while(b[bestP.getX()][bestP.getY()].getPiece().Moves(b).size() <= 0){
                randInt = rand.nextInt(blackPieces.size());
                bestP = new Coordinate(blackPieces.get(randInt).getPosition());
            }

            randInt = rand.nextInt(b[bestP.getX()][bestP.getY()].getPiece().Moves(b).size());
            bestMove = new Coordinate(b[bestP.getX()][bestP.getY()].getPiece().Moves(b).get(randInt));
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
                if(b[i][j].getPiece() != null){
                        int midAdd = 0;
                        //midAdd gives extra weight for advancing pieces up the board
                        if(b[i][j].getPiece().getWhite()){
                            if(j == 5){
                                midAdd = 1;
                            }else if(j < 5){
                                midAdd = 2;
                            }
                        }else {
                            if(j == 2){
                                midAdd = -1;
                            }else if(j > 2){
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
        ArrayList<Piece>tempPieces = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(b[i][j].getPiece() != null) {
                    if (!b[i][j].getPiece().getWhite()) {
                        tempPieces.add(b[i][j].getPiece());
                    }
                }
            }
        }
        return tempPieces;
    }

    public ArrayList<Piece> getWhitePieces(Board[][] b) {
        ArrayList<Piece> tempPieces = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(b[i][j].getPiece() != null) {
                    if (b[i][j].getPiece().getWhite()) {
                        tempPieces.add(b[i][j].getPiece());
                    }
                }
            }
        }
        return tempPieces;
    }

    public int minimax(Board[][] b, int depth, int alpha, int beta, Boolean white){
        Board[][] newB = new Board[8][8];

        for(int i = 0; i< 8 ; i++){
            for(int j = 0; j < 8; j++){
                newB[i][j] = new Board(null);
                if(b[i][j].getPiece() != null){
                    newB[i][j].setPiece(b[i][j].getPiece().clone());
                }
            }
        }

        if(depth == 0){
            return getTotalValue(b);
        }

        if(white){
            ArrayList<Piece> wPieces = getBlackPieces(newB);
            int eval;
            int maxEval = 1000;
            for(Piece p:wPieces){
                if(beta <= alpha){
                    break;
                }
                for(Coordinate coord:p.Moves(newB)){
                    if(beta <= alpha){
                        break;
                    }
                    Coordinate c = new Coordinate(coord);
                    Piece oldP = p.clone();
                    Piece nextP;
                    if(newB[coord.getX()][coord.getY()].getPiece() != null) {
                        nextP = newB[c.getX()][c.getY()].getPiece().clone();
                    }else{
                        nextP = null;
                    }
                    Coordinate old = new Coordinate(oldP.getPosition());

                    newB[coord.getX()][coord.getY()].setPiece(oldP.clone());
                    newB[coord.getX()][coord.getY()].getPiece().setPosition(new Coordinate(coord));
                    newB[old.getX()][old.getY()].setPiece(null);

                    eval = minimax(newB,depth - 1,alpha,beta,true);

                    maxEval = Math.max(maxEval,eval);
                    alpha = Math.max(maxEval,alpha);

                    if(nextP != null) {
                        newB[coord.getX()][coord.getY()].setPiece(nextP.clone());
                    }else{
                        newB[coord.getX()][coord.getY()].setPiece(null);
                    }
                    newB[old.getX()][old.getY()].setPiece(oldP.clone());
                    newB[old.getX()][old.getY()].getPiece().setPosition(new Coordinate(p.getPosition()));
                }
            }
            return maxEval;
        }else{
            ArrayList<Piece> bPieces = getBlackPieces(newB);
            int eval;
            int minEval = 1000;
            for(Piece p:bPieces){
                if(beta <= alpha){
                    break;
                }
                for(Coordinate coord:p.Moves(newB)){
                    if(beta <= alpha){
                        break;
                    }
                    Coordinate c = new Coordinate(coord);
                    Piece oldP = p.clone();
                    Piece nextP;
                    if(newB[coord.getX()][coord.getY()].getPiece() != null) {
                        nextP = newB[c.getX()][c.getY()].getPiece().clone();
                    }else{
                        nextP = null;
                    }
                    Coordinate old = new Coordinate(oldP.getPosition());

                    newB[coord.getX()][coord.getY()].setPiece(oldP.clone());
                    newB[coord.getX()][coord.getY()].getPiece().setPosition(new Coordinate(coord));
                    newB[old.getX()][old.getY()].setPiece(null);

                    eval = minimax(newB,depth - 1,alpha,beta,true);

                    if(eval <= minEval){
                        Random rand = new Random();
                        if(eval == minEval) {
                            if (rand.nextBoolean()) {
                                if (depth == level * 2 - 1) {
                                    bestP = new Coordinate(old);
                                    bestMove = new Coordinate(coord);
                                }
                                minEval = eval;
                            }
                        }else{
                            if (depth == level * 2 - 1) {
                                bestP = new Coordinate(old);
                                bestMove = new Coordinate(coord);
                            }
                            minEval = eval;
                        }
                    }
                    if(minEval <= beta){
                        beta = minEval;
                    }

                    if(nextP != null) {
                        newB[coord.getX()][coord.getY()].setPiece(nextP.clone());
                    }else{
                        newB[coord.getX()][coord.getY()].setPiece(null);
                    }
                    newB[old.getX()][old.getY()].setPiece(oldP.clone());
                    newB[old.getX()][old.getY()].getPiece().setPosition(new Coordinate(p.getPosition()));
                }
            }
            return minEval;
        }
    }
}
