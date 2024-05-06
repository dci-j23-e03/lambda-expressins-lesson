package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class Main {

  public static void main(String[] args) {
    // simple lambda expression, implementing Consumer
    List<String> names = new ArrayList<>();
    names.add("Patrick");
    names.add("Albert");
    names.add("John");

    for (String name: names) {
      System.out.println(name);
    }

    Consumer<String> printConsumer = new Consumer<String>() {
      @Override
      public void accept(String string) {
        System.out.println(string);
      }
    };

    names.forEach(printConsumer);
    names.forEach(string -> System.out.println(string + " " + Thread.currentThread().getName()));

    Consumer<String> printConsumer1 = x -> System.out.println(x);
//    Consumer<String> testConsumer = () -> System.out.println("Test");
//    not a Consumer, it needs a parameter (void return type and no-args is Runnable's run method signature)

    printConsumer1.accept("Calling accept method of the Consumer manually");

    // Runnable
    Runnable simpleRunnable = new Runnable() {
      @Override
      public void run() {
        System.out.println("New thread created! " + Thread.currentThread().getName());
      }
    };

//    new Thread(simpleRunnable).start();
//    new Thread(() -> System.out.println("Very new thread created! " + Thread.currentThread().getName())).start();
//
//    // lambda with multiple expressions (code block)
//    new Thread( () -> {
//      System.out.println("Some text " + Thread.currentThread().getName());
//      System.out.println("Some other text " + Thread.currentThread().getName());
//      System.out.println("Printing some numbers " + (3 + 5) + " " + Thread.currentThread().getName());
//    }).start();

    // Predicate
    Predicate<Integer> positiveIntegerPredicate = new Predicate<Integer>() {
      @Override
      public boolean test(Integer integer) {
        return integer > 0;
      }
    };

    Predicate<Integer> positiveIntegerPredicate2 = i -> i > 0;
    System.out.println("Is 5 positive number: " + positiveIntegerPredicate.test(5));
    System.out.println("+++Is 5 positive number: " + positiveIntegerPredicate2.test(5));
//    System.out.println("Short way: Is 5 positive number: " + (i -> i > 0).test(5)); // can't be done like this
    // but we can provide lambda expression directly where Predicate object is expected
    names.stream().filter(s -> s.startsWith("P")).forEach(printConsumer1);
    names.stream().filter(string -> string.length() > 5)
        .forEach(arg -> System.out.println("New consumer object used: " + arg));

    names.stream().filter(new Predicate<String>() {
      @Override
      public boolean test(String string) {
        return string != null;
      }
    });

    names.stream().filter(string -> string != null && !string.isEmpty() && !string.isBlank());
    names.stream().filter((String s) -> !s.isEmpty());
    names.stream().filter(s -> s.length() > 0);
//    names.stream().filter(string -> string); // can't have String as return type, boolean is expected by test method

    // Supplier
    Supplier<String> supplierWithLambda = () -> "Some return string";
    Stream.generate(() -> 5).limit(3).forEach(five -> System.out.println(five));

    // Comparator
    Comparator<String> stringLengthComparator = new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return Integer.compare(o1.length(), o2.length());
      }
    };

    names.stream().sorted(stringLengthComparator).forEach(sortedName -> System.out.println(sortedName));
    // Comaprator with lambda, using String as type in Consumer (optional)
    names.stream()
        .sorted((name1, name2) -> Integer.compare(name1.length(), name2.length()))
        .forEach((String sortedNameByLength) -> System.out.println(sortedNameByLength));

//    (a, b) -> { int a = 0; return 5;} // will not compile

    List<String> cats= new ArrayList<>();
    cats.add("fluffy");
    cats.add("grumpy");
    cats.add("playful");
    System.out.println(cats);
    cats.sort((b1, b2) -> b1.compareTo(b2));
    System.out.println(cats);

    cats.removeIf(c -> c.startsWith("g"));
    System.out.println(cats);

    Function<String, Integer> lambdaFunction = s -> s.length();
//    Function<String, Integer> lambdaFunction2 = s -> s.length() > 0;

    UnaryOperator<String> firstLetterUO = s -> String.valueOf(s.charAt(0));
  }

//  public void variables(int a) { // will not compile
//    int b = 1;
//    Predicate<Integer> p1 = a -> {
//      int b = 0;
//      int c = 0;
//      return b == c;};
//  }

}

