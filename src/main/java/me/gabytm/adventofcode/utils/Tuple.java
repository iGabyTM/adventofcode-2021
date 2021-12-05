package me.gabytm.adventofcode.utils;

public class Tuple<F, S, T> {

    private final F first;
    private final S second;
    private final T third;

    private Tuple(F first, S second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public static <F, S, T> Tuple<F, S, T> of(final F first, final S second, final T third) {
        return new Tuple<>(first, second, third);
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public T getThird() {
        return third;
    }

}
