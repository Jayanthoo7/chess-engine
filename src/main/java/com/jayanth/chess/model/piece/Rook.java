package com.jayanth.chess.model.piece;

import com.jayanth.chess.model.Board;
import com.jayanth.chess.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public List<Position> getValidMoves(Board board, Position currentPosition) {
        List<Position> validMoves = new ArrayList<>();
        int x = currentPosition.getX();
        int y = currentPosition.getY();

        // The 4 directions a Rook can slide: Up, Down, Left, Right
        int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

        for (int[] dir : directions) {
            int currentX = x + dir[0];
            int currentY = y + dir[1];

            // Keep sliding in this direction until we hit an obstacle or the edge
            while (!board.isOutOfBounds(currentX, currentY)) {
                Piece targetPiece = board.getPiece(currentX, currentY);

                if (targetPiece == null) {
                    // Empty square, we can move here. Keep sliding!
                    validMoves.add(new Position(currentX, currentY));
                } else {
                    // We hit a piece! 
                    // If it's an enemy, we can capture it, but we MUST stop sliding.
                    if (targetPiece.getColor() != this.getColor()) {
                        validMoves.add(new Position(currentX, currentY));
                    }
                    // If it's our own piece, we just stop.
                    break; 
                }
                
                // Move one more square in the same direction
                currentX += dir[0];
                currentY += dir[1];
            }
        }
        return validMoves;
    }
}