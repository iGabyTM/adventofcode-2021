package me.gabytm.adventofcode.days;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day7 extends Day<Integer> {

    public Day7() {
        super(7);
    }

    public static void main(String[] args) {
        Day.showAnswer(new Day7());
    }

    private List<Integer> getData() {
        return Arrays.stream(getInput(1, ","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @Override
    public Integer solveFirstPart() {
        final var data = getData();
        final var max = Collections.max(data);
        var shortest = Integer.MAX_VALUE;

        for (int i = 1; i <= max; i++) {
            var total = 0;

            for (final var it : data) {
                total += Math.abs(it - i);
            }

            shortest = Math.min(shortest, total);
        }

        return shortest;
    }

    @Override
    public Integer solveSecondPart() {
        final var data = getData();
        final var max = Collections.max(data);
        var shortest = Integer.MAX_VALUE;

        for (int i = 1; i <= max; i++) {
            var total = 0;

            for (final var it : data) {
                final var distance = Math.abs(it - i);

                for (int j = 1; j <= distance; j++) {
                    total += j;
                }
            }

            shortest = Math.min(shortest, total);
        }

        return shortest;
    }

}
