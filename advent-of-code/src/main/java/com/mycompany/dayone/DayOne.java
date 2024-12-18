package com.mycompany.dayone;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayOne {
    private static List<Integer> leftList = new ArrayList<>();
    private static List<Integer> rightList = new ArrayList<>();

    public DayOne(Stream<String> lines) {
        processFile(lines);
    }

    private void processFile(Stream<String> lines) {
        lines
            .map(line -> line.split("\\s+"))
            .filter(parts -> parts.length == 2)
            .forEach(parts -> {
                leftList.add(Integer.parseInt(parts[0]));
                rightList.add(Integer.parseInt(parts[1]));
            });
    }

    public int findTotalDistance() {
        int totalDistance = 0;
        // sort each list in ascending order
        List<Integer> leftListSorted = leftList.stream().sorted().collect(Collectors.toList());
        List<Integer> rightListSorted = rightList.stream().sorted().collect(Collectors.toList());
        // absolute difference between the numbers
        for (int i = 0; i < leftListSorted.size(); i++) {
            totalDistance += Math.abs(leftListSorted.get(i) - rightListSorted.get(i));
        }
        return totalDistance;
    }

    public int findSimilarityScore() {
        int totalSimilarityScore = 0;
        for (Integer number : leftList) {
            int count = (int) rightList.stream().filter(n -> n.equals(number)).count();
            totalSimilarityScore += number * count;
        }
        return totalSimilarityScore;
    }
    
}
