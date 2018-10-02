package com.example.exercise;

import com.example.data.Movie;

import java.time.LocalDate;
import java.util.*;

public class ComparatorExercise {

    private static final List<Movie> movies;

    static {
        movies = Arrays.asList(
            new Movie("Lord of the rings","New Line Cinema", LocalDate.of(2001, 12, 19),8.8),
            new Movie("Back to the future","Universal Pictures", LocalDate.of(1985, 7, 3),8.5),
            new Movie("Carlito's way","Universal Pictures", LocalDate.of(1993, 11, 12), 7.9),
            new Movie("Pulp fiction",null/*"Miramax Films"*/, LocalDate.of(1994, 5, 12), 8.9),
            new Movie("Fight Club", null/*20th Century Fox*/, LocalDate.of(1999, 10, 15), 8.8)
        );
    }

    public static void main(String[] args) {
        ComparatorExercise sample1 = new ComparatorExercise();
        sample1.demoComparator();
        sample1.demoComparator2();
        sample1.demoComparator3();
    }

    public void demoComparator() {
        List<Movie> list = new ArrayList<>(movies);

        list.sort(Comparator.comparing(Movie::getReleaseDate)
                            .thenComparing(Movie::getTitle));

        list.forEach(System.out::println);
    }

    public void demoComparator2() {
        List<Movie> list = new ArrayList<>(movies);

        list.sort(Comparator.comparing(Movie::getDistributed, Comparator.nullsLast(Comparator.naturalOrder()))
                            .thenComparing(Movie::getTitle));

        list.forEach(System.out::println);
    }

    public void demoComparator3() {
        List<Movie> list = new ArrayList<>(movies);

        // Comparable.compareTo
        Collections.sort(list);

        list.forEach(System.out::println);
    }

}
