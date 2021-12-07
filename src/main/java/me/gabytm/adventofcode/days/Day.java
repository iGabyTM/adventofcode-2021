package me.gabytm.adventofcode.days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class Day<T> {

    protected final int dayNumber;

    public Day(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public static void solve(final Day<?> day) {
        System.out.println("Day #" + day.dayNumber);
        System.out.println("\tFirst part: " + day.solveFirstPart());
        System.out.println("\tSecond part: " + day.solveSecondPart());
    }

    protected String getInput(final int part) {
        try {
            return new String(Files.newInputStream(Path.of("input", "%d.%d.txt".formatted(this.dayNumber, part))).readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected String[] getInput(final int part, final String regex) {
        final var input = getInput(part);
        return input == null ? null : input.split(regex);
    }

    public abstract T solveFirstPart();

    public abstract T solveSecondPart();

}
