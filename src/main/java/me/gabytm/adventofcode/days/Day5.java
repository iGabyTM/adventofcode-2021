package me.gabytm.adventofcode.days;

import me.gabytm.adventofcode.utils.Tuple;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day5 extends Day<Integer> {

    private static final Pattern PATTERN = Pattern.compile("(?<x1>\\d+),(?<y1>\\d+) -> (?<x2>\\d+),(?<y2>\\d+)");

    public Day5() {
        super(5);
    }

    public static void main(String[] args) {
        Day.showAnswer(new Day5());
    }

    private Tuple<Integer, Integer, List<Line>> getLines(final boolean diagonals) {
        final var maxX = new AtomicInteger();
        final var maxY = new AtomicInteger();
        final var lines = Arrays.stream(getInput(1, "\n"))
                .map(it -> {
                    final var matcher = PATTERN.matcher(it);

                    if (!matcher.find()) {
                        return null;
                    }

                    final var line = new Line(
                            Integer.parseInt(matcher.group("x1")),
                            Integer.parseInt(matcher.group("y1")),
                            Integer.parseInt(matcher.group("x2")),
                            Integer.parseInt(matcher.group("y2"))
                    );

                    if (!diagonals && line.isDiagonal()) {
                        return null;
                    }

                    maxX.set(Math.max(maxX.get(), line.x1));
                    maxY.set(Math.max(maxX.get(), line.x2));

                    return line;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return Tuple.of(maxX.incrementAndGet(), maxY.incrementAndGet(), lines);
    }

    @Override
    public Integer solveFirstPart() {
        final var data = getLines(false);
        final var board = new int[data.getFirst()][data.getSecond()];

        for (final var line : data.getThird()) {
            for (int x = Math.min(line.x1, line.x2); x <= Math.max(line.x1, line.x2); x++) {
                for (int y = Math.min(line.y1, line.y2); y <= Math.max(line.y1, line.y2) ; y++) {
                    board[x][y]++;
                }
            }
        }

        var n = 0;

        for (int x = 0; x < data.getFirst(); x++) {
            for (int y = 0; y < data.getSecond(); y++) {
                if (board[x][y] > 1) {
                    n++;
                }
            }
        }

        return n;
    }

    @Override
    public Integer solveSecondPart() {
        final var data = getLines(true);
        final var board = new int[data.getFirst()][data.getSecond()];
        System.out.println(data.getFirst());
        System.out.println(data.getSecond());

        for (final var line : data.getThird()) {
            if (line.isDiagonal()) {
                var y = Math.min(line.y1, line.y2);

                for (int x = Math.min(line.x1, line.x2); x < Math.max(line.x1, line.x2); x++) {
                    board[x][y++]++;
                    //y++;
                }
            } else {
                for (int x = Math.min(line.x1, line.x2); x <= Math.max(line.x1, line.x2); x++) {
                    for (int y = Math.min(line.y1, line.y2); y <= Math.max(line.y1, line.y2) ; y++) {
                        board[x][y]++;
                    }
                }
            }
        }

        var n = 0;

        for (int x = 0; x < data.getFirst(); x++) {
            for (int y = 0; y < data.getSecond(); y++) {
                if (board[x][y] > 1) {
                    n++;
                }
            }
        }

        System.out.println(Arrays.deepToString(board));
        return n;
    }

    private record Line(int x1, int y1, int x2, int y2) {

        public boolean isDiagonal() {
            return x1 != x2 && y1 != y2;
        }

    }

}
