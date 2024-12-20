package com.mycompany.daytwo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayTwo {
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
        int safeReports = 0;

        for (int i = 0; i < listOfReports.size(); i++) {
            var report = listOfReports.get(i);

            if (isReportSafe(report)) {
                safeReports++;
            }
            else {
                // problem dampener
                for (int j = 0; j < report.size(); j++) {
                    var tmp = new ArrayList<>(report); // does this pass a copy or reference?
                    
                    tmp.remove(j);
                    if (isReportSafe(tmp)) {
                        safeReports++;
                        break;
                    }
                }

            }
        }

        return safeReports;
    }

    private boolean isReportSafe(List<Integer> report) {
        if (report.size() < 2) return true;

        boolean isIncreasing = report.get(1) > report.get(0);

        for (int i = 1; i < report.size(); i++) {
            int diff = Math.abs(report.get(i) - report.get(i-1));

            if (diff < 1 || diff > 3) return false;
            if (report.get(i) > report.get(i-1) && !isIncreasing) return false;
            if (report.get(i) < report.get(i-1) && isIncreasing) return false;
        }

        return true;
    }
    
}
