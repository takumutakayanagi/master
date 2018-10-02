package com.example.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionExercise {

    public static void main(String[] args) {
        new FunctionExercise().tryAll();
    }

    public void tryAll() {
        demoFunc();
        demoFunc2();
    }

    private Function<Integer, String> func1 = i -> {
        Integer temp = i * i;
        return temp.toString();
    };

    private Consumer<Integer> cons1 = i -> System.out.println(i * i);

    private Predicate<Integer> pred1 = i -> i >= 0;

    private void demoFunc() {
        System.out.println(func1.apply(11));
        cons1.accept(22);
        System.out.println(pred1.test(0));
        System.out.println(pred1.test(-1));
    }


    private String func2(Integer i, Function<Integer, String> mapper) {
        return mapper.apply(i).toString();
    }

    private void cons2(Integer i, Consumer<Integer> action) {
        action.accept(i);
    }

    private Boolean pred2(Integer i, Predicate<Integer> predicate) {
        return predicate.test(i);
    }

    private void demoFunc2() {
        List<String> list = Arrays.asList("A", "B" , "C");
        list.forEach(System.out::println);

        func2(33, new Function<Integer, String>() {
            @Override
            public String apply(Integer i) {
                Integer temp = i * i;
                return temp.toString();
            }
        });

        cons2(44, new Consumer<Integer>() {
            @Override
            public void accept(Integer i) {
                System.out.println(i * i);
            }
        });

        pred2(55, new Predicate<Integer>() {
            @Override
            public boolean test(Integer i) {
                return i >= 0;
            }
        });
    }

    public static Predicate<String> checkIfStartsWith(final String letter) {
        return name -> name.startsWith(letter);
    }

}
