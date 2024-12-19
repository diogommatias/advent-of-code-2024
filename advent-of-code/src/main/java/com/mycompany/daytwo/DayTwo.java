package com.mycompany.daytwo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayTwo {
    private static final int INVALID = 0;
    private static final int VALID = 1;

    private List<List<Integer>> listOfReports;

    public DayTwo(Stream<String> lines) {
        processFile(lines);
    }

    private void processFile(Stream<String> lines) {
        listOfReports = lines
            .map(line -> line.split("\\s+"))    // split the string by spaces into a String[]
            .map(line -> Arrays.stream(line)    // transform String[] into a Stream<String>
                .map(Integer::parseInt)         // convert each String to Integer
                .collect(Collectors.toList())   // collect stream of Integer to List<Integer>
            )
            .collect(Collectors.toList());      // collect stream of List<Integer> to List<List<Integer>>
    }

    public int findTotalSafeReports() {
        // the levels are either all increasing or all decreasing
        var tmp = listOfReports
            .stream()
            .filter(report -> levelsGrowth(report) == VALID)
            .collect(Collectors.toList());
        // (part 2) tolerate a single bad level

        // any two adjacent levels differ by at least one and at most three
        int totalSafeReports = (int) tmp
            .stream()
            .filter(report -> controlledGrowth(report) == VALID)
            .count();
        return totalSafeReports;
    }

    /*
     * Check if all levels are increasing or decreasing
     * Returns int VALID or INVALID
     */
    private int levelsGrowth(List<Integer> report) {
        boolean isIncreasing = true; // assumption made
        boolean growthFound = false; // the boolean above can't be changed if this is true
        int previousLevel = Integer.MIN_VALUE;
        for (int level : report) {
            if (previousLevel == Integer.MIN_VALUE) {
                previousLevel = level;
                continue;
            }
            if (!growthFound) {
                if (level > previousLevel) {
                    isIncreasing = true;
                } else {
                    isIncreasing = false;
                }
                growthFound = true;
                previousLevel = level;
                continue;
            }
            if ((level > previousLevel && !isIncreasing) ||
                (level < previousLevel && isIncreasing)) {
                return INVALID;
            }
            previousLevel = level;
        }
        return VALID;
    }

    /*
     * Check if two adjacent levels differ by at least one and at most three
     * Returns int VALID or INVALID
     */
    private int controlledGrowth(List<Integer> report) {
        if (report.size() < 2) {
            return VALID;
        }
        for (int i = 0; i < report.size()-1; i++) {
            if (Math.abs(report.get(i) - report.get(i+1)) > 3 ||
                Math.abs(report.get(i) - report.get(i+1)) < 1) {
                return INVALID;
            }
        }
        return VALID;
    }
    
}
