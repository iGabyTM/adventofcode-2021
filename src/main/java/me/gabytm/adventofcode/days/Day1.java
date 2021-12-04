package me.gabytm.adventofcode.days;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 extends Day<Integer> {

    public Day1() {
        super(1);
    }

    public static void main(String[] args) {
        Day.solve(new Day1());
    }

    private List<Integer> getValues(final int part) {
        return Arrays.stream(getInput(part).split("\n"))
                .map(String::strip)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @Override
    public Integer solveFirstPart() {
        final var values = getValues(1);
        var n = 0;

        for (int i = 1; i < values.size(); i++) {
            if (values.get(i) > values.get(i - 1)) {
                n++;
            }
        }

        return n;
    }

    @Override
    public Integer solveSecondPart() {
        final var values = getValues(2);
        var lastSum = values.get(0) + values.get(1) + values.get(2);
        int n = 0;

        for (int i = 1; i < values.size() - 1; i++) {
            final var sum = values.get(i - 1) + values.get(i) + values.get(i + 1);

            if (sum > lastSum) {
                n++;
            }

            lastSum = sum;
        }

        return n;
    }

}
