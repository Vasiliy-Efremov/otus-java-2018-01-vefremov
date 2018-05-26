package ru.otus;

public class xUtils {

    public static int[] getArray(int length) {
        int[] array = new int[length];
        return array;
    }

    public static void fillTheArray(int[] array) {
        int j = array.length - 1;
        for (int i = 0; i < array.length; ++i) {
            array[i] = j--;
        }
    }

    public static void display(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
