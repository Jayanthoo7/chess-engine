package com.jayanth.chess.model.piece;

import com.jayanth.chess.model.Board;
import com.jayanth.chess.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public List<Position> getValidMoves(Board board, Position currentPosition) {
        List<Position> validMoves = new ArrayList<>();
        int x = currentPosition.getX();
        int y = currentPosition.getY();

        // White moves "up" the grid (x - 1), Black moves "down" (x + 1)
        int direction = (this.getColor() == Color.WHITE) ? -1 : 1;

        // 1. Standard Forward Move
        if (!board.isOutOfBounds(x + direction, y) && board.getPiece(x + direction, y) == null) {
            validMoves.add(new Position(x + direction, y));
            
            // 2. Initial Double Jump (Only if it hasn't moved AND the first square was empty)
            if (!this.isHasMoved() && !board.isOutOfBounds(x + (direction * 2), y) 
                && board.getPiece(x + (direction * 2), y) == null) {
                validMoves.add(new Position(x + (direction * 2), y));
            }
        }

        // 3. Diagonal Captures
        int[] captureOffsets = {-1, 1}; // Left diagonal and Right diagonal
        for (int offset : captureOffsets) {
            int targetX = x + direction;
            int targetY = y + offset;

            if (!board.isOutOfBounds(targetX, targetY)) {
                Piece targetPiece = board.getPiece(targetX, targetY);
                // Can only move diagonally IF there is an enemy piece there
                if (targetPiece != null && targetPiece.getColor() != this.getColor()) {
                    validMoves.add(new Position(targetX, targetY));
                }
            }
        }
        return validMoves;
    }
}
