package com.mycompany;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.mycompany.dayone.DayOne;

public class Main {

    public static void main(String[] args) {
        Path path = Path.of("advent-of-code\\src\\main\\java\\com\\mycompany\\dayone\\input.txt");
        
        try (Stream<String> lines = readFile(path)) {
            var dayOne = new DayOne(lines);
            System.out.printf("Total distance: %d\n", dayOne.findTotalDistance());  
            System.out.printf("Total similarity score: %d\n", dayOne.findSimilarityScore());
        } catch (IOException e) {
            System.err.format("IOException %s\n", e);
        }
    }

    public static Stream<String> readFile(Path path) throws IOException {
        return Files.lines(path);
    }
}