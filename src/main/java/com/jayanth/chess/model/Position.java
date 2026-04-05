package com.jayanth.chess.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {
    private int x; // Row (0-7)
    private int y; // Column (0-7)
}
