package com.example.ec327_chess;
import com.example.ec327_chess.Coordinate;
import com.example.ec327_chess.Pieces.*;
public class movePair {
    private Coordinate position;
    private Coordinate move;

    public movePair(Coordinate p, Coordinate m){
        this.position = new Coordinate(p);
        this.move = new Coordinate(m);
    }
    public Coordinate getP(){return position;}
    public Coordinate getMove(){return move;}
}
