package com.mycompany;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.mycompany.dayone.DayOne;
import com.mycompany.daytwo.DayTwo;

public class Main {

    public static void main(String[] args) {
        Path path = Path.of("advent-of-code\\src\\main\\java\\com\\mycompany\\daytwo\\input.txt");
        
        try (Stream<String> lines = readFile(path)) {
            var day = new DayTwo(lines);
            System.out.println(day.findTotalSafeReports());
        } catch (IOException e) {
            System.err.format("IOException %s\n", e);
        }
    }

    public static Stream<String> readFile(Path path) throws IOException {
        return Files.lines(path);
    }
}