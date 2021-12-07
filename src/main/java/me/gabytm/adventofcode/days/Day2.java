package me.gabytm.adventofcode.days;

import me.gabytm.adventofcode.utils.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 extends Day<Integer> {

    public Day2() {
        super(2);
    }

    public static void main(String[] args) {
        Day.showAnswer(new Day2());
    }

    private List<Pair<String, Integer>> getCommands(final int part) {
        return Arrays.stream(getInput(part, "\n"))
                .map(String::trim)
                .map(it -> {
                    final var parts = it.split(" ");
                    return Pair.of(parts[0], Integer.parseInt(parts[1]));
                })
                .collect(Collectors.toList());
    }

    @Override
    public Integer solveFirstPart() {
        var horizontalPosition = 0;
        var depth = 0;

        for (final var command : getCommands(1)) {
            switch (command.getFirst()) {
                case "forward" -> horizontalPosition += command.getSecond();
                case "down" -> depth += command.getSecond();
                case "up" -> depth -= command.getSecond();
            }
        }

        return horizontalPosition * depth;
    }

    @Override
    public Integer solveSecondPart() {
        var horizontalPosition = 0;
        var depth = 0;
        var aim = 0;

        for (final var command : getCommands(2)) {
            switch (command.getFirst()) {
                case "forward" -> {
                    horizontalPosition += command.getSecond();
                    depth += aim * command.getSecond();
                }
                case "down" -> aim += command.getSecond();
                case "up" -> aim -= command.getSecond();
            }
        }

        return horizontalPosition * depth;
    }

}
