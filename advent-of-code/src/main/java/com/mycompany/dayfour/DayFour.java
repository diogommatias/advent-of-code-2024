package com.mycompany.dayfour;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayFour {
    private char[][] text;

    public DayFour(Stream<String> lines) {
        processFile(lines);
    }

    private void processFile(Stream<String> lines) {
        text = lines
            .collect(Collectors.toList())
            .stream()
            .map(line -> line.toCharArray())
            .toArray(char[][]::new);
    }

    public int countOccurrences(String word) {
        int count = 0;

        // Define all eight directions: [row_offset, col_offset]
        int[][] directions = {
            {0, 1},  // Horizontal right
            {0, -1}, // Horizontal left
            {1, 0},  // Vertical down
            {-1, 0}, // Vertical up
            {1, 1},  // Diagonal bottom-right
            {1, -1}, // Diagonal bottom-left
            {-1, 1}, // Diagonal top-right
            {-1, -1} // Diagonal top-left
        };

        // Iterate through each cell in the grid
        for (int r = 0; r < text.length; r++) {
            for (int c = 0; c < text[r].length; c++) {
                // Check all directions from this cell
                for (int[] dir : directions) {
                    int dr = dir[0]; // Row offset
                    int dc = dir[1]; // Column offset

                    if (canFormWord(word, r, c, dr, dc)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private boolean canFormWord(String word, int startRow, int startCol, int dr, int dc) {
        int rows = text.length;
        int cols = text[startRow].length;

        // Check each character in the word
        for (int i = 0; i < word.length(); i++) {
            int newRow = startRow + i * dr;
            int newCol = startCol + i * dc;

            // Ensure we're within bounds
            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                return false;
            }

            // Check if the character matches
            if (text[newRow][newCol] != word.charAt(i)) {
                return false;
            }
        }

        return true;
    }
    
}
