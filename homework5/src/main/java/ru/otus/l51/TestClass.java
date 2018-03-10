package ru.otus.l51;

import ru.otus.l51.annotations.After;
import ru.otus.l51.annotations.Before;
import ru.otus.l51.annotations.Test;

import java.util.Arrays;

import static ru.otus.l51.XUtils.*;

public class TestClass {

    private static int[] arrayAfterSort = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    @Before
    public static void checkBubbleSort() {
        int[] array = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        bubbleSort(array);
        if (Arrays.equals(array, arrayAfterSort)) {
            System.out.println("BubbleSort test passed");
        }
    }

    @Test
    public static void checkSelectionSort() {
        int[] array = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        selectionSort(array);
        if (Arrays.equals(array, arrayAfterSort)) {
            System.out.println("SelectionSort test passed");
        }
    }

    @After
    public static void checkInsertionSort() {
        int[] array = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        insertionSort(array);
        System.out.println("InsertionSort test passed");
    }
}
