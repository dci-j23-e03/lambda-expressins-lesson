package org.example;

import java.util.function.Consumer;

public class Cat {
  private String color;

  public Cat(String color) {
    this.color = color;
  }
  public void meaw(final String name) {
    String volume = "loudly";
    color = "whatever";
//    volume = "quietly";
//    name = "somethig else";
    // method parameters and local variables referenced from lambda body need to be final, or effectively final
    Consumer<String> consumer = s ->
        System.out.println(name + " says " + volume + " that she is " + color);
    consumer.accept(name);
  }

  public static void main(String[] args) {
    Cat cat = new Cat("Black");
    cat.meaw("Tiger");
  }
}

