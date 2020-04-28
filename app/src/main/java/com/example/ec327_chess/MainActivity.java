package com.example.ec327_chess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
//literally no idea what this does, internet said to have it but idrk...
//import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ec327_chess.Pieces.*;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Boolean firstPlayerTurn;
    public Boolean pieceSelected;
    public Board[][] board = new Board[8][8];
    public TextView[][] DisplayBoard = new TextView[8][8];
    public TextView[][] DisplayBoardBackground = new TextView[8][8];
    public Coordinate clickedPosition = new Coordinate(0,0);
    public Coordinate currentPosition = new Coordinate(0,0);
    public ArrayList<Coordinate> allowedMoves;
    public Boolean bKingCheck;
    public Boolean wKingCheck;

    //different because a clicked spot might be invalid, and it requires a valid click after
    //selecting a piece to make a move, so we don't want to change that.
    public Piece pClicked;
    public Piece pSelect;

    // creating all the pieces

        Piece bKing;
        Piece wKing;

        Piece bQueen;
        Piece wQueen;

        Piece bKnight1;
        Piece bKnight2;
        Piece wKnight1;
        Piece wKnight2;

        Piece bRook1;
        Piece bRook2;
        Piece wRook1;
        Piece wRook2;

        Piece bBishop1;
        Piece bBishop2;
        Piece wBishop1;
        Piece wBishop2;

        Piece bPawn1;
        Piece bPawn2;
        Piece bPawn3;
        Piece bPawn4;
        Piece bPawn5;
        Piece bPawn6;
        Piece bPawn7;
        Piece bPawn8;

        Piece wPawn1;
        Piece wPawn2;
        Piece wPawn3;
        Piece wPawn4;
        Piece wPawn5;
        Piece wPawn6;
        Piece wPawn7;
        Piece wPawn8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Making notification bar transparent
        //honestly not 100% sure what this is, but the internet said to do it lol
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_main);

        initializeboard();
    }

        //initializes the board by putting all the pieces where they should be.

    private void initializeboard() {
            bKing = new King(new Coordinate(7,4),false);
            wKing = new King(new Coordinate(0,4),true);

            bQueen = new Queen(new Coordinate(7,3),false);
            wQueen = new Queen(new Coordinate(0,3),true);

            bRook1 = new Rook(new Coordinate(7,7),false);
            bRook2 = new Rook(new Coordinate(7,0),false);
            wRook1 = new Rook(new Coordinate(0,0),true);
            wRook2 = new Rook(new Coordinate(0,7),true);

            bKnight1 = new Knight(new Coordinate(7,1),false);
            bKnight2 = new Knight(new Coordinate(7,6),false);
            wKnight1 = new Knight(new Coordinate(0,1),true);
            wKnight2 = new Knight(new Coordinate(0,6),true);

            bBishop1 = new Bishop(new Coordinate(7,2),false);
            bBishop2 = new Bishop(new Coordinate(7,5),false);
            wBishop1 = new Bishop(new Coordinate(0,2),true);
            wBishop2 = new Bishop(new Coordinate(0,3),true);


            bPawn1 = new Pawn(new Coordinate(6,0),false);
            bPawn2 = new Pawn(new Coordinate(6,1),false);
            bPawn3 = new Pawn(new Coordinate(6,2),false);
            bPawn4 = new Pawn(new Coordinate(6,3),false);
            bPawn5 = new Pawn(new Coordinate(6,4),false);
            bPawn6 = new Pawn(new Coordinate(6,5),false);
            bPawn7 = new Pawn(new Coordinate(6,6),false);
            bPawn8 = new Pawn(new Coordinate(6,7),false);

            wPawn1 = new Pawn(new Coordinate(1,0),true);
            wPawn2 = new Pawn(new Coordinate(1,1),true);
            wPawn3 = new Pawn(new Coordinate(1,2),true);
            wPawn4 = new Pawn(new Coordinate(1,3),true);
            wPawn5 = new Pawn(new Coordinate(1,4),true);
            wPawn6 = new Pawn(new Coordinate(1,5),true);
            wPawn7 = new Pawn(new Coordinate(1,6),true);
            wPawn8 = new Pawn(new Coordinate(1,7),true);

            //creates the null pieces in the middle of the board
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    board[i][j] = new Board(null);
                }
             }

            /*sets the values of the board to the values of the pieces.
            the implementation of needing coordinates for the board AND pieces could
            definitely be improved, but I kinda switched up how I was implementing the
            pieces halfway through.  I might come back and change this, but it would require
            re-writing quite a lot of things...
             */

            board[0][7].setPiece(wRook1);
            board[1][7].setPiece(wKnight1);
            board[2][7].setPiece(wBishop1);
            board[3][7].setPiece(wQueen);
            board[4][7].setPiece(wKing);
            board[5][7].setPiece(wBishop2);
            board[6][7].setPiece(wKnight2);
            board[7][7].setPiece(wRook2);

            board[0][6].setPiece(wPawn1);
            board[1][6].setPiece(wPawn2);
            board[2][6].setPiece(wPawn3);
            board[3][6].setPiece(wPawn4);
            board[4][6].setPiece(wPawn5);
            board[5][6].setPiece(wPawn6);
            board[6][6].setPiece(wPawn7);
            board[7][6].setPiece(wPawn8);

            board[0][0].setPiece(bRook1);
            board[1][0].setPiece(bKnight1);
            board[2][0].setPiece(bBishop1);
            board[3][0].setPiece(bQueen);
            board[4][0].setPiece(bKing);
            board[5][0].setPiece(bBishop2);
            board[6][0].setPiece(bKnight2);
            board[7][0].setPiece(bRook2);

            board[0][1].setPiece(bPawn1);
            board[1][1].setPiece(bPawn2);
            board[2][1].setPiece(bPawn3);
            board[3][1].setPiece(bPawn4);
            board[4][1].setPiece(bPawn5);
            board[5][1].setPiece(bPawn6);
            board[6][1].setPiece(bPawn7);
            board[7][1].setPiece(bPawn8);

            /*
            DisplayBoard[][] displays the piece that is on a particular board
            location and
            DisplayBoardBackground[][] displays the tile that is underneath.
            Again, probably a better way to implement this, but I am already kinda
            far into this and don't feel like re-writing quite a bit of code that is
            already done.  I might if there is time at the end.

            The main issue with implementing this in a different way comes when getting
            the clicked position as pieces move around the board.
             */

                DisplayBoard[0][0] = findViewById(R.id.R00);
                DisplayBoardBackground[0][0] = findViewById(R.id.R000);
                DisplayBoard[1][0] = findViewById(R.id.R10);
                DisplayBoardBackground[1][0] = findViewById(R.id.R010);
                DisplayBoard[2][0] = findViewById(R.id.R20);
                DisplayBoardBackground[2][0] = findViewById(R.id.R020);
                DisplayBoard[3][0] = findViewById(R.id.R30);
                DisplayBoardBackground[3][0] = findViewById(R.id.R030);
                DisplayBoard[4][0] = findViewById(R.id.R40);
                DisplayBoardBackground[4][0] = findViewById(R.id.R040);
                DisplayBoard[5][0] = findViewById(R.id.R50);
                DisplayBoardBackground[5][0] = findViewById(R.id.R050);
                DisplayBoard[6][0] = findViewById(R.id.R60);
                DisplayBoardBackground[6][0] = findViewById(R.id.R060);
                DisplayBoard[7][0] = findViewById(R.id.R70);
                DisplayBoardBackground[7][0] = findViewById(R.id.R070);

                DisplayBoard[0][1] = findViewById(R.id.R01);
                DisplayBoardBackground[0][1] = findViewById(R.id.R001);
                DisplayBoard[1][1] = findViewById(R.id.R11);
                DisplayBoardBackground[1][1] = findViewById(R.id.R011);
                DisplayBoard[2][1] = findViewById(R.id.R21);
                DisplayBoardBackground[2][1] = findViewById(R.id.R021);
                DisplayBoard[3][1] = findViewById(R.id.R31);
                DisplayBoardBackground[3][1] = findViewById(R.id.R031);
                DisplayBoard[4][1] = findViewById(R.id.R41);
                DisplayBoardBackground[4][1] = findViewById(R.id.R041);
                DisplayBoard[5][1] = findViewById(R.id.R51);
                DisplayBoardBackground[5][1] = findViewById(R.id.R051);
                DisplayBoard[6][1] = findViewById(R.id.R61);
                DisplayBoardBackground[6][1] = findViewById(R.id.R061);
                DisplayBoard[7][1] = findViewById(R.id.R71);
                DisplayBoardBackground[7][1] = findViewById(R.id.R071);

                DisplayBoard[0][2] = findViewById(R.id.R02);
                DisplayBoardBackground[0][2] = findViewById(R.id.R002);
                DisplayBoard[1][2] = findViewById(R.id.R12);
                DisplayBoardBackground[1][2] = findViewById(R.id.R012);
                DisplayBoard[2][2] = findViewById(R.id.R22);
                DisplayBoardBackground[2][2] = findViewById(R.id.R022);
                DisplayBoard[3][2] = findViewById(R.id.R32);
                DisplayBoardBackground[3][2] = findViewById(R.id.R032);
                DisplayBoard[4][2] = findViewById(R.id.R42);
                DisplayBoardBackground[4][2] = findViewById(R.id.R042);
                DisplayBoard[5][2] = findViewById(R.id.R52);
                DisplayBoardBackground[5][2] = findViewById(R.id.R052);
                DisplayBoard[6][2] = findViewById(R.id.R62);
                DisplayBoardBackground[6][2] = findViewById(R.id.R062);
                DisplayBoard[7][2] = findViewById(R.id.R72);
                DisplayBoardBackground[7][2] = findViewById(R.id.R072);

                DisplayBoard[0][3] = findViewById(R.id.R03);
                DisplayBoardBackground[0][3] = findViewById(R.id.R003);
                DisplayBoard[1][3] = findViewById(R.id.R13);
                DisplayBoardBackground[1][3] = findViewById(R.id.R013);
                DisplayBoard[2][3] = findViewById(R.id.R23);
                DisplayBoardBackground[2][3] = findViewById(R.id.R023);
                DisplayBoard[3][3] = findViewById(R.id.R33);
                DisplayBoardBackground[3][3] = findViewById(R.id.R033);
                DisplayBoard[4][3] = findViewById(R.id.R43);
                DisplayBoardBackground[4][3] = findViewById(R.id.R043);
                DisplayBoard[5][3] = findViewById(R.id.R53);
                DisplayBoardBackground[5][3] = findViewById(R.id.R053);
                DisplayBoard[6][3] = findViewById(R.id.R63);
                DisplayBoardBackground[6][3] = findViewById(R.id.R063);
                DisplayBoard[7][3] = findViewById(R.id.R73);
                DisplayBoardBackground[7][3] = findViewById(R.id.R073);

                DisplayBoard[0][4] = findViewById(R.id.R04);
                DisplayBoardBackground[0][4] = findViewById(R.id.R004);
                DisplayBoard[1][4] = findViewById(R.id.R14);
                DisplayBoardBackground[1][4] = findViewById(R.id.R014);
                DisplayBoard[2][4] = findViewById(R.id.R24);
                DisplayBoardBackground[2][4] = findViewById(R.id.R024);
                DisplayBoard[3][4] = findViewById(R.id.R34);
                DisplayBoardBackground[3][4] = findViewById(R.id.R034);
                DisplayBoard[4][4] = findViewById(R.id.R44);
                DisplayBoardBackground[4][4] = findViewById(R.id.R044);
                DisplayBoard[5][4] = findViewById(R.id.R54);
                DisplayBoardBackground[5][4] = findViewById(R.id.R054);
                DisplayBoard[6][4] = findViewById(R.id.R64);
                DisplayBoardBackground[6][4] = findViewById(R.id.R064);
                DisplayBoard[7][4] = findViewById(R.id.R74);
                DisplayBoardBackground[7][4] = findViewById(R.id.R074);

                DisplayBoard[0][5] = findViewById(R.id.R05);
                DisplayBoardBackground[0][5] = findViewById(R.id.R005);
                DisplayBoard[1][5] = findViewById(R.id.R15);
                DisplayBoardBackground[1][5] = findViewById(R.id.R015);
                DisplayBoard[2][5] = findViewById(R.id.R25);
                DisplayBoardBackground[2][5] = findViewById(R.id.R025);
                DisplayBoard[3][5] = findViewById(R.id.R35);
                DisplayBoardBackground[3][5] = findViewById(R.id.R035);
                DisplayBoard[4][5] = findViewById(R.id.R45);
                DisplayBoardBackground[4][5] = findViewById(R.id.R045);
                DisplayBoard[5][5] = findViewById(R.id.R55);
                DisplayBoardBackground[5][5] = findViewById(R.id.R055);
                DisplayBoard[6][5] = findViewById(R.id.R65);
                DisplayBoardBackground[6][5] = findViewById(R.id.R065);
                DisplayBoard[7][5] = findViewById(R.id.R75);
                DisplayBoardBackground[7][5] = findViewById(R.id.R075);

                DisplayBoard[0][6] = findViewById(R.id.R06);
                DisplayBoardBackground[0][6] = findViewById(R.id.R006);
                DisplayBoard[1][6] = findViewById(R.id.R16);
                DisplayBoardBackground[1][6] = findViewById(R.id.R016);
                DisplayBoard[2][6] = findViewById(R.id.R26);
                DisplayBoardBackground[2][6] = findViewById(R.id.R026);
                DisplayBoard[3][6] = findViewById(R.id.R36);
                DisplayBoardBackground[3][6] = findViewById(R.id.R036);
                DisplayBoard[4][6] = findViewById(R.id.R46);
                DisplayBoardBackground[4][6] = findViewById(R.id.R046);
                DisplayBoard[5][6] = findViewById(R.id.R56);
                DisplayBoardBackground[5][6] = findViewById(R.id.R056);
                DisplayBoard[6][6] = findViewById(R.id.R66);
                DisplayBoardBackground[6][6] = findViewById(R.id.R066);
                DisplayBoard[7][6] = findViewById(R.id.R76);
                DisplayBoardBackground[7][6] = findViewById(R.id.R076);

                DisplayBoard[0][7] = findViewById(R.id.R07);
                DisplayBoardBackground[0][7] = findViewById(R.id.R007);
                DisplayBoard[1][7] = findViewById(R.id.R17);
                DisplayBoardBackground[1][7] = findViewById(R.id.R017);
                DisplayBoard[2][7] = findViewById(R.id.R27);
                DisplayBoardBackground[2][7] = findViewById(R.id.R027);
                DisplayBoard[3][7] = findViewById(R.id.R37);
                DisplayBoardBackground[3][7] = findViewById(R.id.R037);
                DisplayBoard[4][7] = findViewById(R.id.R47);
                DisplayBoardBackground[4][7] = findViewById(R.id.R047);
                DisplayBoard[5][7] = findViewById(R.id.R57);
                DisplayBoardBackground[5][7] = findViewById(R.id.R057);
                DisplayBoard[6][7] = findViewById(R.id.R67);
                DisplayBoardBackground[6][7] = findViewById(R.id.R067);
                DisplayBoard[7][7] = findViewById(R.id.R77);
                DisplayBoardBackground[7][7] = findViewById(R.id.R077);



            pieceSelected = false;
            firstPlayerTurn= true;
            wKingCheck = false;
            bKingCheck = false;
            drawPieces();
    }

    public void drawPieces(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Piece p;
                if(board[i][j] != null){
                    p = board[i][j].getPiece();

                    if(p instanceof Pawn){
                        if (p.getWhite()) {
                            DisplayBoard[i][j].setBackgroundResource(R.drawable.wpawn);
                        } else {
                            DisplayBoard[i][j].setBackgroundResource(R.drawable.bpawn);
                        }
                    } else if(p instanceof King){
                        if (p.getWhite()) {
                            DisplayBoard[i][j].setBackgroundResource(R.drawable.wking);
                        } else {
                            DisplayBoard[i][j].setBackgroundResource(R.drawable.bking);
                        }
                    } else if(p instanceof Queen){
                        if (p.getWhite()) {
                            DisplayBoard[i][j].setBackgroundResource(R.drawable.wqueen);
                        } else {
                            DisplayBoard[i][j].setBackgroundResource(R.drawable.bqueen);
                        }
                    } else if(p instanceof Knight){
                        if (p.getWhite()) {
                            DisplayBoard[i][j].setBackgroundResource(R.drawable.wknight);
                        } else {
                            DisplayBoard[i][j].setBackgroundResource(R.drawable.bknight);
                        }
                    } else if(p instanceof Bishop){
                        if (p.getWhite()) {
                            DisplayBoard[i][j].setBackgroundResource(R.drawable.wbishop);
                        } else {
                            DisplayBoard[i][j].setBackgroundResource(R.drawable.bbishop);
                        }
                    } else  if(p instanceof Rook){
                        if (p.getWhite()) {
                            DisplayBoard[i][j].setBackgroundResource(R.drawable.wrook);
                        } else {
                            DisplayBoard[i][j].setBackgroundResource(R.drawable.brook);
                        }
                    }
                } else {
                    DisplayBoard[i][j].setBackgroundResource(0);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {

        //this sets the clickedPosition as a Coordinate object of the clicked position
        //I cant think of a better way to do this.  There might be though
        switch (v.getId()) {
            case R.id.R00:
                clickedPosition.setX(0);
                clickedPosition.setY(0);
                break;
            case R.id.R10:
                clickedPosition.setX(1);
                clickedPosition.setY(0);
                break;
            case R.id.R20:
                clickedPosition.setX(2);
                clickedPosition.setY(0);
                break;
            case R.id.R30:
                clickedPosition.setX(3);
                clickedPosition.setY(0);
                break;
            case R.id.R40:
                clickedPosition.setX(4);
                clickedPosition.setY(0);
                break;
            case R.id.R50:
                clickedPosition.setX(5);
                clickedPosition.setY(0);
                break;
            case R.id.R60:
                clickedPosition.setX(6);
                clickedPosition.setY(0);
                break;
            case R.id.R70:
                clickedPosition.setX(7);
                clickedPosition.setY(0);
                break;

            case R.id.R01:
                clickedPosition.setX(0);
                clickedPosition.setY(1);
                break;
            case R.id.R11:
                clickedPosition.setX(1);
                clickedPosition.setY(1);
                break;
            case R.id.R21:
                clickedPosition.setX(2);
                clickedPosition.setY(1);
                break;
            case R.id.R31:
                clickedPosition.setX(3);
                clickedPosition.setY(1);
                break;
            case R.id.R41:
                clickedPosition.setX(4);
                clickedPosition.setY(1);
                break;
            case R.id.R51:
                clickedPosition.setX(5);
                clickedPosition.setY(1);
                break;
            case R.id.R61:
                clickedPosition.setX(6);
                clickedPosition.setY(1);
                break;
            case R.id.R71:
                clickedPosition.setX(7);
                clickedPosition.setY(1);
                break;

            case R.id.R02:
                clickedPosition.setX(0);
                clickedPosition.setY(2);
                break;
            case R.id.R12:
                clickedPosition.setX(1);
                clickedPosition.setY(2);
                break;
            case R.id.R22:
                clickedPosition.setX(2);
                clickedPosition.setY(2);
                break;
            case R.id.R32:
                clickedPosition.setX(3);
                clickedPosition.setY(2);
                break;
            case R.id.R42:
                clickedPosition.setX(4);
                clickedPosition.setY(2);
                break;
            case R.id.R52:
                clickedPosition.setX(5);
                clickedPosition.setY(2);
                break;
            case R.id.R62:
                clickedPosition.setX(6);
                clickedPosition.setY(2);
                break;
            case R.id.R72:
                clickedPosition.setX(7);
                clickedPosition.setY(2);
                break;

            case R.id.R03:
                clickedPosition.setX(0);
                clickedPosition.setY(3);
                break;
            case R.id.R13:
                clickedPosition.setX(1);
                clickedPosition.setY(3);
                break;
            case R.id.R23:
                clickedPosition.setX(2);
                clickedPosition.setY(3);
                break;
            case R.id.R33:
                clickedPosition.setX(3);
                clickedPosition.setY(3);
                break;
            case R.id.R43:
                clickedPosition.setX(4);
                clickedPosition.setY(3);
                break;
            case R.id.R53:
                clickedPosition.setX(5);
                clickedPosition.setY(3);
                break;
            case R.id.R63:
                clickedPosition.setX(6);
                clickedPosition.setY(3);
                break;
            case R.id.R73:
                clickedPosition.setX(7);
                clickedPosition.setY(3);
                break;

            case R.id.R04:
                clickedPosition.setX(0);
                clickedPosition.setY(4);
                break;
            case R.id.R14:
                clickedPosition.setX(1);
                clickedPosition.setY(4);
                break;
            case R.id.R24:
                clickedPosition.setX(2);
                clickedPosition.setY(4);
                break;
            case R.id.R34:
                clickedPosition.setX(3);
                clickedPosition.setY(4);
                break;
            case R.id.R44:
                clickedPosition.setX(4);
                clickedPosition.setY(4);
                break;
            case R.id.R54:
                clickedPosition.setX(5);
                clickedPosition.setY(4);
                break;
            case R.id.R64:
                clickedPosition.setX(6);
                clickedPosition.setY(4);
                break;
            case R.id.R74:
                clickedPosition.setX(7);
                clickedPosition.setY(4);
                break;

            case R.id.R05:
                clickedPosition.setX(0);
                clickedPosition.setY(5);
                break;
            case R.id.R15:
                clickedPosition.setX(1);
                clickedPosition.setY(5);
                break;
            case R.id.R25:
                clickedPosition.setX(2);
                clickedPosition.setY(5);
                break;
            case R.id.R35:
                clickedPosition.setX(3);
                clickedPosition.setY(5);
                break;
            case R.id.R45:
                clickedPosition.setX(4);
                clickedPosition.setY(5);
                break;
            case R.id.R55:
                clickedPosition.setX(5);
                clickedPosition.setY(5);
                break;
            case R.id.R65:
                clickedPosition.setX(6);
                clickedPosition.setY(5);
                break;
            case R.id.R75:
                clickedPosition.setX(7);
                clickedPosition.setY(5);
                break;

            case R.id.R06:
                clickedPosition.setX(0);
                clickedPosition.setY(6);
                break;
            case R.id.R16:
                clickedPosition.setX(1);
                clickedPosition.setY(6);
                break;
            case R.id.R26:
                clickedPosition.setX(2);
                clickedPosition.setY(6);
                break;
            case R.id.R36:
                clickedPosition.setX(3);
                clickedPosition.setY(6);
                break;
            case R.id.R46:
                clickedPosition.setX(4);
                clickedPosition.setY(6);
                break;
            case R.id.R56:
                clickedPosition.setX(5);
                clickedPosition.setY(6);
                break;
            case R.id.R66:
                clickedPosition.setX(6);
                clickedPosition.setY(6);
                break;
            case R.id.R76:
                clickedPosition.setX(7);
                clickedPosition.setY(6);
                break;

            case R.id.R07:
                clickedPosition.setX(0);
                clickedPosition.setY(7);
                break;
            case R.id.R17:
                clickedPosition.setX(1);
                clickedPosition.setY(7);
                break;
            case R.id.R27:
                clickedPosition.setX(2);
                clickedPosition.setY(7);
                break;
            case R.id.R37:
                clickedPosition.setX(3);
                clickedPosition.setY(7);
                break;
            case R.id.R47:
                clickedPosition.setX(4);
                clickedPosition.setY(7);
                break;
            case R.id.R57:
                clickedPosition.setX(5);
                clickedPosition.setY(7);
                break;
            case R.id.R67:
                clickedPosition.setX(6);
                clickedPosition.setY(7);
                break;
            case R.id.R77:
                clickedPosition.setX(7);
                clickedPosition.setY(7);
                break;
        }

        /////////////////////////////////////////////////////////////////////////////
        ///here is the actual implementation of the game (moving pieces and stuff)///
        /////////////////////////////////////////////////////////////////////////////

        //the piece of the current clicked position, regardless whether or not it is a valid piece or not
        pClicked = board[clickedPosition.getX()][clickedPosition.getY()].getPiece();

        if(!pieceSelected){
            if(pClicked == null){
                return;
            }else if(pClicked.getWhite() == firstPlayerTurn){
                pSelect = pClicked;
                pieceSelected = true;
                allowedMoves = pSelect.Moves(board);
                currentPosition = clickedPosition;
            }
        } else {
            //if clicked piece is blank
            if(pClicked == null){
                if(isMoveAllowed(allowedMoves,clickedPosition)){
                    board[clickedPosition.getX()][clickedPosition.getY()].setPiece(pSelect);
                    board[currentPosition.getX()][currentPosition.getY()].setPiece(null);
                    pClicked.setPosition(new Coordinate(clickedPosition.getX(),clickedPosition.getY()));
                    pieceSelected = false;
                    firstPlayerTurn = !firstPlayerTurn;
                }
                //if clicked piece is the opposite color of selected piece
            } else if(pClicked.getWhite() == !firstPlayerTurn){
                if(isMoveAllowed(allowedMoves,clickedPosition)){
                    if(pClicked instanceof King){
                        /////////////////////////////////////////////////////////////////
                        /////////////////////////////////////////////////////////////////
                        //GAME OVER - need to add screen and methods for this
                        /////////////////////////////////////////////////////////////////
                        /////////////////////////////////////////////////////////////////
                        return;
                    }
                    board[clickedPosition.getX()][clickedPosition.getY()].setPiece(pSelect);
                    board[currentPosition.getX()][currentPosition.getY()].setPiece(null);
                    pClicked.setPosition(new Coordinate(clickedPosition.getX(),clickedPosition.getY()));
                    pieceSelected = false;
                    firstPlayerTurn = !firstPlayerTurn;
                }
                //if clicked piece is the same color as selected piece
            } else if(pClicked.getWhite() == firstPlayerTurn){
                pSelect = pClicked;
                allowedMoves = pSelect.Moves(board);
                currentPosition = clickedPosition;
            }
        }
        if (castleMove(board, capture, xfrom, yfrom, xto, yto)) {
            int kDest, rDest;
            boolean fromKing;
            if (from instanceof King) {
                fromKing = true;
                kDest = (xto == 7) ? 6 : 2;
                rDest = (xto == 7) ? 5 : 3;
            } else {
                fromKing = false;
                kDest = (xfrom == 7) ? 6 : 2;
                rDest = (xfrom == 7) ? 5 : 3;
            }

            board[yfrom][xfrom] = null;
            board[yto][xto] = null;

            board[yfrom][kDest] = (fromKing) ? from : to;
            board[yto][rDest] = (fromKing) ? to : from;

            endTurn(board, from, to, true);
            return true;

        }if (castleMove(board, capture, xfrom, yfrom, xto, yto)) {
            int kDest, rDest;
            boolean fromKing;
            if (from instanceof King) {
                fromKing = true;
                kDest = (xto == 7) ? 6 : 2;
                rDest = (xto == 7) ? 5 : 3;
            } else {
                fromKing = false;
                kDest = (xfrom == 7) ? 6 : 2;
                rDest = (xfrom == 7) ? 5 : 3;
            }

            board[yfrom][xfrom] = null;
            board[yto][xto] = null;

            board[yfrom][kDest] = (fromKing) ? from : to;
            board[yto][rDest] = (fromKing) ? to : from;

            endTurn(board, from, to, true);
            return true;

        }
        drawPieces();
    }

    public void checkEndGame(){
        wKingCheck = false;
        bKingCheck = false;
        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if(board[i][j].getPiece() instanceof King){
                    if (board[i][j].getPiece().getWhite()){
                        wKingCheck = true;
                        }
                    if (board[i][j].getPiece().getWhite()){
                        bKingCheck = true;
                        }
                    }
                }
            }
        }


    private static boolean castleMove(Piece[][] board, boolean capture, int xfrom, int yfrom, int xto, int yto) {
        Piece from = board[yfrom][xfrom];
        Piece to = board[yto][xto];

        return (from != null && to != null &&
                correctTurn(from.white) &&								// A valid castle move:
                from.white == to.white &&								// From and to are same color,
                (from instanceof King && to instanceof Rook || 			// a king and rook are switching,
                        from instanceof Rook && to instanceof King) &&
                from.firstMove && to.firstMove &&						// it is both pieces' first move,
                notBlocked(board, xfrom, yfrom, xto, yto));				// and there is nothing blocking them.
    }

    private static boolean correctTurn(boolean white) {
        // Testing flag turns off correctTurn validations
        return (testing) ? true : white == firstPlayerTurn
    }
    private static boolean notBlocked(Piece[][] board, int xfrom, int yfrom, int xto, int yto) {


        Piece from = board[yfrom][xfrom];
        Piece to = board[yto][xto];

        int dx = (xfrom < xto) ? 1 : ((xfrom == xto) ? 0 : -1);
        int dy = (yfrom < yto) ? 1 : ((yfrom == yto) ? 0 : -1);

        int steps = Math.max(Math.abs(xfrom - xto), Math.abs(yfrom - yto));

        if (xfrom == xto || yfrom == yto || Math.abs(xfrom - xto) == Math.abs(yfrom - yto)) {
            for (int i = 1; i < steps; i++) {
                int x = xfrom + i * dx;
                int y = yfrom + i * dy;
                if (isBlocked(board, from, to, x, y)) {
                    return false;
                }
            }
        }
        return true;
    }


    private Boolean isMoveAllowed(ArrayList<Coordinate> list, Coordinate c) {
        Boolean Allowed = false;
        for(int i =0;i<list.size();i++){
            if(list.get(i).getX() == c.getX()  &&  list.get(i).getY() == c.getY()){
                Allowed = true;
                break;
            }
        }
        return Allowed;
    }




}


