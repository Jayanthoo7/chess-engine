package com.jayanth.chess.model.piece;

import com.jayanth.chess.model.Board;
import com.jayanth.chess.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public List<Position> getValidMoves(Board board, Position currentPosition) {
        List<Position> validMoves = new ArrayList<>();
        int x = currentPosition.getX();
        int y = currentPosition.getY();

        // 8 directions: 4 orthogonal (Rook) + 4 diagonal (Bishop)
        int[][] directions = { 
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}, // Rook moves
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1} // Bishop moves
        };

        for (int[] dir : directions) {
            int currentX = x + dir[0];
            int currentY = y + dir[1];

            while (!board.isOutOfBounds(currentX, currentY)) {
                Piece targetPiece = board.getPiece(currentX, currentY);
                if (targetPiece == null) {
                    validMoves.add(new Position(currentX, currentY));
                } else {
                    if (targetPiece.getColor() != this.getColor()) {
                        validMoves.add(new Position(currentX, currentY));
                    }
                    break;
                }
                currentX += dir[0];
                currentY += dir[1];
            }
        }
        return validMoves;
    }
}