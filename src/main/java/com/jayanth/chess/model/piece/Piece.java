package com.jayanth.chess.model.piece;

import com.jayanth.chess.model.Board;
import com.jayanth.chess.model.Position;
import lombok.Getter;
import java.util.List;

@Getter
public abstract class Piece {
    private final Color color;
    private boolean hasMoved; // Important for Pawns, Castling, etc.

    public Piece(Color color) {
        this.color = color;
        this.hasMoved = false;
    }

    public void setMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    // POLYMORPHISM: Every piece must implement this method differently!
    public abstract List<Position> getValidMoves(Board board, Position currentPosition);
}