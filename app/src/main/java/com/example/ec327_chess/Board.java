package com.example.ec327_chess;

import com.example.ec327_chess.Pieces.Piece;

//to be made into an array containing all positions on the board
//each position stores the information of what piece is in
//that position

public class Board {
    private Piece p;

    Board(Piece piece){
        this.p = piece;
    }

    public Piece getPiece(){
        return p;
    }

    public void setPiece(Piece piece){
        this.p = piece;
    }

    public void setPieceAi(Piece piece){
        this.p = new Piece(piece);
    }
}
