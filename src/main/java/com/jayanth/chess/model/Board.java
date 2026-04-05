package com.jayanth.chess.model;

import com.jayanth.chess.model.piece.Piece;

public class Board {
    // Our core Data Structure: an 8x8 grid of Pieces
    private final Piece[][] grid;

    public Board() {
        this.grid = new Piece[8][8];
    }

    public Piece getPiece(int x, int y) {
        if (isOutOfBounds(x, y)) return null;
        return grid[x][y];
    }

    public void setPiece(int x, int y, Piece piece) {
        if (!isOutOfBounds(x, y)) {
            grid[x][y] = piece;
        }
    }

    public boolean isOutOfBounds(int x, int y) {
        return x < 0 || x > 7 || y < 0 || y > 7;
    }
}
