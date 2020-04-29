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
    protected int value;
    protected Boolean hasMoved;


    public Piece(Coordinate p, boolean white){
        this.white = white;
        this.position = p;
        this.value = 0;
        this.hasMoved = false;
    }

    public Piece(Piece piece){
        if(piece != null) {
            this.white = piece.getWhite();
            this.position = new Coordinate(piece.getPosition());
            this.value = piece.getValue();
            this.hasMoved = piece.getHasMoved();
        }
    }

    public Boolean getHasMoved(){return hasMoved;}

    public void setHasMoved(){hasMoved = true;}

    public Boolean getWhite(){return white;}

    public void setPosition(Coordinate p){
        this.position = new Coordinate(p);
    }
    public Piece clone() {
        return new Piece(this);
    }

    public ArrayList<Coordinate> Moves(Board[][] b) {
        ArrayList<Coordinate> openMoves = new ArrayList<>();
        openMoves.add(position);
        return openMoves;
    }

    public Coordinate getPosition(){
        return new Coordinate(position);
    }
    public int getValue(){
        return value;
    }
}
