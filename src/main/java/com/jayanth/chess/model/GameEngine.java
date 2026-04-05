package com.jayanth.chess.model;

import com.jayanth.chess.model.piece.Color;
import com.jayanth.chess.model.piece.King; // Assuming you make a basic King class
import com.jayanth.chess.model.piece.Piece;

import java.util.List;

public class GameEngine {

    private Board board;

    public GameEngine(Board board) {
        this.board = board;
    }

    // This is a classic SDE interview algorithm!
    public boolean isKingInCheck(Color kingColor) {
        Position kingPosition = findKing(kingColor);
        if (kingPosition == null) return false; // Safety check

        // Scan the board for enemy pieces
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = board.getPiece(x, y);
                
                // If it's an enemy piece...
                if (piece != null && piece.getColor() != kingColor) {
                    // Get all the places this enemy can attack
                    List<Position> enemyMoves = piece.getValidMoves(board, new Position(x, y));
                    
                    // Does the enemy's attack path hit our King?
                    for (Position move : enemyMoves) {
                        if (move.getX() == kingPosition.getX() && move.getY() == kingPosition.getY()) {
                            return true; // CHECK!
                        }
                    }
                }
            }
        }
        return false;
    }
    // Add this inside GameEngine.java
    public boolean isCheckmate(Color color) {
        // 1. If the King isn't currently in check, it's not checkmate (it might be stalemate, but we'll focus on checkmate)
        if (!isKingInCheck(color)) {
            return false;
        }

        // 2. Loop through the entire board to find all of OUR pieces
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = board.getPiece(x, y);
                
                if (piece != null && piece.getColor() == color) {
                    // Get every possible move this piece can make
                    List<Position> validMoves = piece.getValidMoves(board, new Position(x, y));

                    for (Position targetMove : validMoves) {
                        // --- BACKTRACKING: DO, TEST, UNDO ---
                        
                        // Step A: Save the piece we are about to overwrite (in case we capture an enemy)
                        Piece capturedPiece = board.getPiece(targetMove.getX(), targetMove.getY());
                        
                        // Step B: DO the move temporarily
                        board.setPiece(targetMove.getX(), targetMove.getY(), piece);
                        board.setPiece(x, y, null);

                        // Step C: TEST the board state. Are we still in check?
                        boolean stillInCheck = isKingInCheck(color);

                        // Step D: UNDO the move (put everything back exactly as it was)
                        board.setPiece(x, y, piece);
                        board.setPiece(targetMove.getX(), targetMove.getY(), capturedPiece);

                        // 3. The Result: If even ONE move resulted in the King being safe, it's NOT checkmate!
                        if (!stillInCheck) {
                            return false; 
                        }
                    }
                }
            }
        }
        
        // 4. If we tried every single move for every single piece and we are STILL in check...
        return true; // CHECKMATE!
    }

    // Helper method to find where the King is currently sitting
    private Position findKing(Color color) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece piece = board.getPiece(x, y);
                // In a real app, ensure you create a King class that extends Piece
                if (piece != null && piece.getColor() == color && piece.getClass().getSimpleName().equals("King")) {
                    return new Position(x, y);
                }
            }
        }
        return null;
    }
}