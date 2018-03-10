package ru.otus.l51;


public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Run by calling a static method with the class name:");
            MyTestFramework.run(TestClass.class);
            System.out.println("---------------------------------------------------------");
            System.out.println("Run by calling a static method with the package name:");
            MyTestFramework.run("ru.otus.l51");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
