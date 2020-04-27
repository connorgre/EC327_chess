package com.example.ec327_chess.Pieces;
import com.example.ec327_chess.Board;
import com.example.ec327_chess.Coordinate;

import java.util.ArrayList;

//parent class Piece that holds the value of color and Current
//Position
public class Piece {

    //for color, white = true, black = false;
    protected boolean white;
    protected Coordinate position;


    public Piece(Coordinate p, boolean white){
        this.white = white;
        this.position = p;
    }

    public Boolean getWhite(){return white;}

    public void setPosition(Coordinate p){
        this.position = p;
    }

    public ArrayList<Coordinate> Moves(Board[][] b) {
        ArrayList<Coordinate> openMoves = new ArrayList<>();
        openMoves.add(position);
        return openMoves;
    }

    public Coordinate getPosition(){
        return position;
    }
}
