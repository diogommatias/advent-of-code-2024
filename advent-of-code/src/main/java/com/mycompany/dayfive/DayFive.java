package com.mycompany.dayfive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayFive {
    private Map<Integer, List<Integer>> pageOrder = new HashMap<>();
    private List<List<Integer>> updates;

    public DayFive(Stream<String> lines) {
        processFile(lines);
    }

    private void processFile(Stream<String> lines) {
        List<String> tmp = lines.collect(Collectors.toList());

        tmp
        .stream()
        .takeWhile(line -> !line.isEmpty())
        .forEach(line -> {
            Pattern pattern = Pattern.compile("(\\d+)\\|(\\d+)");
            Matcher matcher = pattern.matcher(line);

            if (matcher.matches()) {
                int key = Integer.parseInt(matcher.group(1));
                int value = Integer.parseInt(matcher.group(2));
                
                /*
                List<Integer> list = pageOrder.get(key);
                if (list == null) {
                    list = new ArrayList<>();
                    pageOrder.put(key, list);
                }
                list.add(value);
                */

                pageOrder.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
            }
        });
        
        updates = tmp
            .stream()
            .dropWhile(line -> !line.isEmpty())
            .skip(1)
            .map(line -> line.split(","))
            .map(list -> Arrays.stream(list)
                .map(Integer::parseInt)
                .collect(Collectors.toList()))
            .collect(Collectors.toList());

        System.out.println();
    }

    public int correctlyOrderedUpdates() {
        int total = 0;

        List<List<Integer>> correctUpdates = getCorrectUpdates();

        for (List<Integer> update: correctUpdates) {
            total += middlePageNumber(update);
        }

        return total;
    }

    private int middlePageNumber(List<Integer> update) {
        return update.get(update.size()/2);
    }

    private List<List<Integer>> getCorrectUpdates() {
        List<List<Integer>> correctUpdates = new ArrayList<>();

        for (List<Integer> update: updates) {
            if (isUpdateCorrect(update)) correctUpdates.add(update);
        }

        return correctUpdates;
    }

    private boolean isUpdateCorrect(List<Integer> update) {
        for (int i = 0; i < update.size(); i++) {
            int key = update.get(i);

            for (int j = i+1; j < update.size(); j++) {
                List<Integer> value = pageOrder.get(key);

                if (value == null) return false;

                if (!value.contains(update.get(j))) return false;
            }

        }

        return true;
    }


    
}
