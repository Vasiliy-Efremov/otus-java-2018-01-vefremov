package ru.otus.l51;

public class XUtils {

    private static void swap(int first, int second, int[] array) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public static void bubbleSort(int[] array) {
        int size = array.length;
        int out;
        int in;

        for (out = size - 1; out >= 1; out--) {
            for (in = 0; in < out; in++) {
                if (array[in] > array[in + 1]) {
                    swap(in, in + 1, array);
                }
            }
        }
    }

    public static void selectionSort(int[] array) {
        int size = array.length;
        int out;
        int in;
        int min;

        for (out = 0; out < size - 1; out++) {
            min = out;
            for (in = out + 1; in < size; in++) {
                if (array[in] < array[min]) {
                    min = in;
                }
                swap(out, min, array);
            }
        }
    }

    public static void insertionSort(int[] array) {
        int size = array.length;
        int out;
        int in;

        for (out = 1; out < size; out++) {
            int temp = array[out];
            in = out;
            while (in > 0 && array[in - 1] >= temp) {
                array[in] = array[in - 1];
                --in;
            }
            array[in] = temp;
        }
    }
}
