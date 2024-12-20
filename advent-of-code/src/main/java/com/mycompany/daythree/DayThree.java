package com.mycompany.daythree;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DayThree {
    private List<String> mults;

    public DayThree(Stream<String> lines) {
        processFile(lines);
    }

    private void processFile(Stream<String> lines) {
        mults = lines
            .map(line -> regexMatch(line, "mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)"))
            .flatMap(List::stream)
            .collect(Collectors.toList());
        System.out.println("hello");
    }

    private List<String> regexMatch(String line, String regex) {
        List<String> multsFound = new ArrayList<>();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            multsFound.add(matcher.group());
        }

        return multsFound;
    }

    public int scanMemory() {
        int totalMult = 0;
        boolean isToMultiply = true;

        for (String m : mults) {
            if (m.equals("don't()")) {
                isToMultiply = false;
                continue;
            }
            if (m.equals("do()")) {
                isToMultiply = true;
                continue;
            }

            if (isToMultiply && m.startsWith("mul(")) {
                Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
                Matcher matcher = pattern.matcher(m);

                if (matcher.matches()) {
                    int x = Integer.parseInt(matcher.group(1));
                    int y = Integer.parseInt(matcher.group(2));
                    totalMult += x * y;
                }
            }
        }

        return totalMult;
    }

    
    
}
