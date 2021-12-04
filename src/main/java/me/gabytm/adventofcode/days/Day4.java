package me.gabytm.adventofcode.days;

import me.gabytm.adventofcode.utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 extends Day<Integer> {

    public Day4() {
        super(4);
    }

    public static void main(String[] args) {
        Day.solve(new Day4());
    }

    private Pair<List<Integer>, List<Board>> getNumbersAndBoards() {
        final var input = Arrays.stream(getInput(1).split("\n"))
                .map(String::trim)
                .collect(Collectors.toList());

        final var numbers = Arrays.stream(input.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        final var boards = new ArrayList<Board>();

        final var boardNumbers = new ArrayList<List<Integer>>(5);

        for (int i = 2; i < input.size(); i++) {
            final var line = input.get(i);

            if (line.trim().isEmpty()) {
                boards.add(new Board(boardNumbers));
                boardNumbers.clear();
                continue;
            }

            boardNumbers.add(
                    Arrays.stream(line.split(" "))
                            .map(String::trim)
                            .filter(it -> !it.isEmpty())
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())
            );
        }

        return Pair.of(numbers, boards);
    }

    @Override
    public Integer solveFirstPart() {
        final var numbersAndBoards = getNumbersAndBoards();
        final var boards = numbersAndBoards.getSecond();

        for (final var number : numbersAndBoards.getFirst()) {
            for (final var board : boards) {
                if (board.markNumber(number)) {
                    return board.calculateSum() * number;
                }
            }
        }

        return null;
    }

    @Override
    public Integer solveSecondPart() {
        final var numbersAndBoards = getNumbersAndBoards();
        final var boards = numbersAndBoards.getSecond();

        var lastWinningNumber = 0;
        Board lastWinningBoard = null;

        for (final var number : numbersAndBoards.getFirst()) {
            for (var iterator = boards.iterator(); iterator.hasNext();) {
                final var board = iterator.next();

                if (board.markNumber(number)) {
                    lastWinningNumber = number;
                    lastWinningBoard = board;
                    iterator.remove();
                }
            }
        }

        return lastWinningBoard.calculateSum() * lastWinningNumber;
    }

    private static class Board {

        private static final int DIMENSION = 5;

        private final int[][] board = new int[DIMENSION][DIMENSION];
        private final char[][] markedNumbers = new char[DIMENSION][DIMENSION];

        private Board(List<List<Integer>> board) {
            for (int i = 0; i < DIMENSION; i++) {
                final var line = board.get(i);

                for (int j = 0; j < DIMENSION; j++) {
                    this.markedNumbers[i][j] = '-';
                    this.board[i][j] = line.get(j);
                }
            }
        }

        private boolean hasWon() {
            for (int i = 0; i < DIMENSION; i++) {
                // Check row
                for (int j = 0; j < DIMENSION; j++) {
                    // One number from this row wasn't marked
                    if (this.markedNumbers[i][j] != '+') {
                        break;
                    }

                    // All previous numbers from this row were marked and this is the last one
                    if (j + 1 == DIMENSION) {
                        return true;
                    }
                }

                // Check column
                for (int j = 0; j < DIMENSION; j++) {
                    // One number from this column wasn't marked
                    if (this.markedNumbers[i][j] != '+') {
                        break;
                    }

                    // All previous numbers from this column were marked and this is the last one
                    if (j + 1 == DIMENSION) {
                        return true;
                    }
                }
            }

            return false;
        }

        private boolean markNumber(final int number) {
            var marked = false;

            for (int i = 0; i < DIMENSION; i++) {
                for (int j = 0; j < DIMENSION; j++) {
                    if (this.board[i][j] == number) {
                        this.markedNumbers[i][j] = '+';
                        marked = true;
                    }
                }
            }

            return marked && hasWon();
        }

        public int calculateSum() {
            var sum = 0;

            for (int i = 0; i < DIMENSION; i++) {
                for (int j = 0; j < DIMENSION; j++) {
                    if (this.markedNumbers[i][j] != '+') {
                        sum += this.board[i][j];
                    }
                }
            }

            return sum;
        }

    }

}
