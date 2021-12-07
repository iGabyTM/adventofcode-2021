package me.gabytm.adventofcode.days;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Day6 extends Day<Long> {

    public Day6() {
        super(6);
    }

    public static void main(String[] args) {
        Day.showAnswer(new Day6());
    }

    @Override
    public Long solveFirstPart() {
        final var fish = Arrays.stream(getInput(1, ","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        for (int i = 0; i < 80; i++) {
            final var size = fish.size();

            for (int j = 0; j < size; j++) {
                final var daysLeft = fish.get(j);

                if (daysLeft == 0) {
                    fish.set(j, 6);
                    fish.add(8);
                } else {
                    fish.set(j, daysLeft - 1);
                }
            }
        }

        return (long) fish.size();
    }

    @Override
    public Long solveSecondPart() {
        final var array = new long[9];
        final var fish = Arrays.stream(getInput(1, ","))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        for (final var it : fish) {
            array[it]++;
        }

        for (int i = 0; i < 256; i++) {
            final var toAdd = new long[9];

            for (int j = 0; j < 9; j++) {
                if (j == 0) {
                    toAdd[8] += array[0];
                    toAdd[6] += array[0];
                    array[0] = 0;
                } else {
                    toAdd[j - 1] += array[j];
                    array[j] = 0;
                }
            }

            for (int j = 0; j < 9; j++) {
                array[j] += toAdd[j];
            }
        }

        return Arrays.stream(array).sum();
    }

}
