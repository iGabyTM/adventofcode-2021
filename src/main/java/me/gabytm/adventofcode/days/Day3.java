package me.gabytm.adventofcode.days;

import java.util.ArrayList;
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

    private char findCommonBit(final List<String> list, final int index, final boolean mostCommon) {
        var ones = 0;
        var zeros = 0;

        for (final var it : list) {
            if (it.charAt(index) == '1') {
                ones++;
            } else {
                zeros++;
            }
        }

        // oxygen generator
        if (mostCommon) {
            if (ones == zeros) {
                return '1';
            }

            return ones > zeros ? '1' : '0';
        }

        // CO2 scrubber
        if (ones == zeros) {
            return '0';
        }

        return ones < zeros ? '1' : '0';
    }

    private Integer findRating(final List<String> diagnosticReport, final boolean oxygenGenerator) {
        final var copy = new ArrayList<>(diagnosticReport);
        final var itemLength = copy.get(0).length();

        for (int i = 0; i < itemLength; i++) {
            final var commonBit = findCommonBit(copy, i, oxygenGenerator);
            final var index = i;

            copy.removeIf(it -> it.charAt(index) != commonBit);

            if (copy.size() == 1) {
                return Integer.parseInt(copy.get(0), 2);
            }
        }

        throw new IllegalArgumentException("Could not find rating");
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

        final var gamma = new StringBuilder();
        final var epsilon = new StringBuilder();

        final var reportLength = report.size();

        for (final int one : onesArray) {
            // reportLength - one = zeros
            if (reportLength - one < one) {
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
        final var report = getDiagnosticReport();
        return findRating(report, true) * findRating(report, false);
    }

}
