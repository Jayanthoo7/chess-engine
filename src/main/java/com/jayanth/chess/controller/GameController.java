package com.jayanth.chess.controller;

import com.jayanth.chess.dto.MoveRequest;
import com.jayanth.chess.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/match")
// We add CORS so a future frontend can talk to this API securely (just like your E-Commerce project!)
@CrossOrigin(origins = "*") 
public class GameController {

    @Autowired
    private GameService gameService;

    // Endpoint: POST /api/match/create
    @PostMapping("/create")
    public ResponseEntity<String> startNewMatch() {
        String gameId = gameService.createNewGame();
        return ResponseEntity.ok("New game started! Game ID: " + gameId);
    }

    // Endpoint: POST /api/match/{gameId}/move
    @PostMapping("/{gameId}/move")
    public ResponseEntity<String> submitMove(@PathVariable String gameId, @RequestBody MoveRequest moveRequest) {
        String response = gameService.makeMove(
                gameId, 
                moveRequest.getFromX(), 
                moveRequest.getFromY(), 
                moveRequest.getToX(), 
                moveRequest.getToY()
        );
        return ResponseEntity.ok(response);
    }
}
