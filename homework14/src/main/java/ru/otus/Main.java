package ru.otus;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        int[] array = xUtils.getArray(10);
        xUtils.fillTheArray(array);
        MergeSort mergeSort = new MergeSort(array, 0, array.length);
        xUtils.display(array);
        System.out.println("After sorting");
        ForkJoinPool pool = mergeSort.getForkJoinPool(4);
        pool.submit(mergeSort).join();
        xUtils.display(array);
    }
}
