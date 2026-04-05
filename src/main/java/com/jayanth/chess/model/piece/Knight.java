package com.jayanth.chess.model.piece;

import com.jayanth.chess.model.Board;
import com.jayanth.chess.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public List<Position> getValidMoves(Board board, Position currentPosition) {
        List<Position> validMoves = new ArrayList<>();
        int x = currentPosition.getX();
        int y = currentPosition.getY();

        // The 8 possible directional jumps for a Knight
        int[][] jumps = {
            {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
            {1, -2}, {1, 2}, {2, -1}, {2, 1}
        };

        for (int[] jump : jumps) {
            int targetX = x + jump[0];
            int targetY = y + jump[1];

            // 1. Is the jump still on the board?
            if (!board.isOutOfBounds(targetX, targetY)) {
                Piece targetPiece = board.getPiece(targetX, targetY);
                
                // 2. Is the square empty, OR does it have an enemy piece?
                if (targetPiece == null || targetPiece.getColor() != this.getColor()) {
                    validMoves.add(new Position(targetX, targetY));
                }
            }
        }
        return validMoves;
    }
}
