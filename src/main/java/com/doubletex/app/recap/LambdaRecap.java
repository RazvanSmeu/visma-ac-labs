package com.doubletex.app.recap;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaRecap {

    public static String yodaQuote(String originalSentence) {
        List<String> words = Arrays.asList(originalSentence.split(" "));
        Collections.shuffle(words);

        return String.join(" ", words);
    }

    public static void basics() {
        Function<Integer, Integer> doubleNumber = (x) -> x * 2;

        System.out.println(doubleNumber.apply(10));

        Function<String, String> yodaQuoter = LambdaRecap::yodaQuote;

        System.out.println(yodaQuoter.apply("you shall not pass"));
    }

    public static void moreTypes() {
        Predicate<Integer> isPositive = (x) -> x >= 0; // Same as Function<Integer, Boolean>
        Consumer<Integer> showIt = (x) -> System.out.println(x);
        Consumer<Integer> showItRef = System.out::println;

        Supplier<Integer> gimme5 = () -> 5;

        System.out.println(isPositive.test(-5));

        showIt.accept(42);
        showItRef.accept(42);

        System.out.println(gimme5.get());
        showIt.accept(gimme5.get());
    }


    public static List<String> transformEachElement(List<String> originalList, Function<String, String> transformer) {
        List<String> newList = new LinkedList<>();
        for(String originalString : originalList) {
            newList.add(transformer.apply(originalString));
        }
        return newList;
    }

    public static Function<String, Function<String, String> > dialogue =
        (personName) -> (whatTheySaid) -> personName.toUpperCase() + ": " + whatTheySaid;

    public static int costlyComputation() {
        int sum = 0;
        for(int i = 1; i <= 150000000; i += 2 * i * i * i) {
            sum += i;
        }
        return sum;
    }

    public static void positiveOrCompute(int myValue, Supplier<Integer> supplier) {
        if(myValue > 0) {
            System.out.println(myValue);
        } else {
            System.out.println(supplier.get());
        }
    }

    public static void previewIteration() {
        // Iteration
        List<String> myList = List.of("You", "are", "a", "wizard", "Harry");
        List<String> otherList = transformEachElement(myList, String::toUpperCase);

        System.out.println(otherList);
    }

    public static void partialApplication() {
        // Partial application
        Function<String, String> frodoSaid = dialogue.apply("Frodo");
        Function<String, String> gandalfSaid = dialogue.apply("Gandalf");

        System.out.println( frodoSaid.apply("You are late")                                );
        System.out.println( gandalfSaid.apply("A wizard is never late...")                 );
        System.out.println( gandalfSaid.apply("...he arrives precisely when he means to.") );
        System.out.println( frodoSaid.apply("Just say sorry, man")                         );
    }

    public static void lazyComputation() {
        // Performance
        int someValue = -55;
        System.out.println("start");
        positiveOrCompute(someValue, LambdaRecap::costlyComputation);
    }

    public static void main(String[] args) {
        lazyComputation();
    }
}