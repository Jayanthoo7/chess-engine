package com.jayanth.chess.model.piece;

import com.jayanth.chess.model.Board;
import com.jayanth.chess.model.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public List<Position> getValidMoves(Board board, Position currentPosition) {
        List<Position> validMoves = new ArrayList<>();
        int x = currentPosition.getX();
        int y = currentPosition.getY();

        // The 8 adjacent squares a King can move to
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
        };

        for (int[] dir : directions) {
            int targetX = x + dir[0];
            int targetY = y + dir[1];

            // 1. Is it on the board?
            if (!board.isOutOfBounds(targetX, targetY)) {
                Piece targetPiece = board.getPiece(targetX, targetY);
                // 2. Is it empty, or is an enemy there?
                if (targetPiece == null || targetPiece.getColor() != this.getColor()) {
                    validMoves.add(new Position(targetX, targetY));
                }
            }
        }
        return validMoves;
    }
}