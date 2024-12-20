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
            .map(line -> regexMatch(line, "mul\\(\\d{1,3},\\d{1,3}\\)"))
            .flatMap(List::stream)
            .collect(Collectors.toList());
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

        for (String m : mults) {
            int multiplication = 1;

            Pattern pattern = Pattern.compile("\\d{1,3}");
            Matcher matcher = pattern.matcher(m);
            while (matcher.find()) {
                multiplication *= Integer.parseInt(matcher.group());
            }

            totalMult += multiplication;
        }

        return totalMult;
    }

    
    
}
