package com.jayanth.chess.dto;

import lombok.Data;

@Data
public class MoveRequest {
    private int fromX;
    private int fromY;
    private int toX;
    private int toY;
}
