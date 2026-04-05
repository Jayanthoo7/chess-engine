package com.jayanth.chess.service;

import com.jayanth.chess.model.Board;
import com.jayanth.chess.model.GameEngine;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {

    // Thread-safe map to hold all active games. Key = Game ID, Value = The Game Engine.
    private final ConcurrentHashMap<String, GameEngine> activeGames = new ConcurrentHashMap<>();

    // 1. Create a new game and return its unique ID
    public String createNewGame() {
        Board newBoard = new Board();
        // In a real app, you'd write a method to place all 32 pieces in their starting spots here
        
        GameEngine engine = new GameEngine(newBoard);
        String gameId = UUID.randomUUID().toString(); // Generate a random ID (e.g., "550e8400-e29b...")
        
        activeGames.put(gameId, engine);
        return gameId;
    }

    // 2. Process a move for a specific game
    public String makeMove(String gameId, int fromX, int fromY, int toX, int toY) {
        GameEngine engine = activeGames.get(gameId);
        
        if (engine == null) {
            return "Error: Game not found!";
        }

        // Here you would hook into your piece.getValidMoves() logic
        // For now, we will return a success message so you can test the API
        return "Move executed from (" + fromX + "," + fromY + ") to (" + toX + "," + toY + ") in game " + gameId;
    }
}