package me.gabytm.adventofcode.days;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 extends Day<Integer> {

    public Day3() {
        super(3);
    }

    public static void main(String[] args) {
        Day.solve(new Day3());
    }

    private List<String> getDiagnosticReport() {
        return Arrays.stream(getInput(1).split("\n"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @Override
    public Integer solveFirstPart() {
        final var report = getDiagnosticReport();
        final var onesArray = new int[report.get(0).length()];

        for (final var line : report) {
            final var chars = line.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '1') {
                    onesArray[i]++;
                }
            }
        }

        var gamma = new StringBuilder();
        var epsilon = new StringBuilder();

        final var reportLength = report.size();

        for (final int n : onesArray) {
            if (reportLength - n < n) {
                gamma.append(1);
                epsilon.append(0);
            } else {
                gamma.append(0);
                epsilon.append(1);
            }
        }

        return Integer.parseInt(gamma.toString(), 2) * Integer.parseInt(epsilon.toString(), 2);
    }

    @Override
    public Integer solveSecondPart() {
        return null;
    }
}
