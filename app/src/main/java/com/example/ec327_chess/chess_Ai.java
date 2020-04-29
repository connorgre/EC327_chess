package com.example.ec327_chess;

import com.example.ec327_chess.Pieces.*;
import com.example.ec327_chess.Board;
import com.example.ec327_chess.Coordinate;
import com.example.ec327_chess.movePair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

//in main, bestP must be called BEFORE bestMove, because bestP also calculates the best move.

public class chess_Ai {

    protected Coordinate bestP;
    protected Coordinate bestMove;
    protected ArrayList<movePair> pair;
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
        pair = new ArrayList<>();
        Random rand = new Random();
        blackPieces = getBlackPieces(b);
        whitePieces = getWhitePieces(b);
        totalValue = getTotalValue(b);

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
            int d = level*2;
            minimax(b,d,-10000,10000,false);
        }
        int randInt = rand.nextInt(pair.size());
        bestP = new Coordinate(pair.get(randInt).getP());
        bestMove = new Coordinate(pair.get(randInt).getMove());
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

        if(depth == 0){
            return getTotalValue(b);
        }

        for(int i = 0; i< 8 ; i++){
            for(int j = 0; j < 8; j++){
                newB[i][j] = new Board(null);
                if(b[i][j].getPiece() != null){
                    newB[i][j].setPiece(b[i][j].getPiece().clone());
                }
            }
        }

        if(white){
            ArrayList<Piece> wPieces = getBlackPieces(newB);
            int eval;
            int maxEval = -1000;
            for(Piece p:wPieces){
                for(Coordinate coord:p.Moves(newB)){
                    if(beta <= alpha){
                        return alpha;
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

                    eval = minimax(newB,depth - 1,alpha,beta,false);

                    maxEval = Math.max(maxEval,eval);
                    alpha = Math.max(eval,alpha);

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

                    //sets the best Piece for the next move
                    if(depth == level*2){
                        if(eval < minEval){
                            if(pair.size() > 0){
                                pair.clear();
                            }
                            pair.add(new movePair(old,coord));
                        }
                        if(eval == minEval){
                            pair.add(new movePair(old,coord));
                        }
                    }

                    minEval = Math.min(minEval,eval);
                    beta = Math.min(beta,eval);

                    if(nextP != null) {
                        newB[coord.getX()][coord.getY()].setPiece(nextP.clone());
                    }else{
                        newB[coord.getX()][coord.getY()].setPiece(null);
                    }
                    newB[old.getX()][old.getY()].setPiece(oldP.clone());
                    newB[old.getX()][old.getY()].getPiece().setPosition(new Coordinate(p.getPosition()));
                }
            }
            if(beta <= alpha){
                return beta;
            }
            return minEval;
        }
    }

    public boolean checkCheck(Board[][] board,boolean white){
        ArrayList<Piece> pieces = new ArrayList<>();
        ArrayList<Coordinate> openMoves = new ArrayList<>();
        if(white){
            pieces = getBlackPieces(board);
        }else {
            pieces = getWhitePieces(board);
        }
        for(Piece p:pieces){
            openMoves = new ArrayList<>(p.Moves(board));
            for(Coordinate c:openMoves){
                if(board[c.getX()][c.getY()].getPiece() instanceof King){
                    return true;
                }
            }
        }
        return false;
    }
}