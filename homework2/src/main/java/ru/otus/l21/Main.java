package ru.otus.l21;

@SuppressWarnings({"RedundantStringConstructorCall", "InfiniteLoopStatement"})
public class Main {
    public static void main(String... args) throws InterruptedException {
        System.out.println("The size of an empty object: " + Utils.calculateObjectSize());
        System.out.println("The size of an empty string: " + Utils.calculateEmptyStringSize());
        System.out.println("The size of an empty containers: " + Utils.calculateEmptyContainersSize());
        System.out.println("The size of the filled container: " + Utils.calculateTheGrowthOfTheContainerSize(20_000_000));
    }
}
