package ru.otus.l21;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static long getMemory() throws InterruptedException {
        System.gc();
        Thread.sleep(10);
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    public static long multiplicityByEight(long value) {
        while (value % 8 != 0) {
            value++;
        }
        return value;
    }

    public static long calculateEmptyStringSize() throws InterruptedException {
        int size = 20_000_000;
        String[] array = new String[size];
        long memoryBefore = getMemory();
        for (int i = 0; i < size; i++) {
            array[i] = new String(new char[0]);
        }
        long memoryAfter = getMemory();
        return multiplicityByEight((memoryAfter - memoryBefore) / size);
    }

    public static long calculateObjectSize() throws InterruptedException {
        int size = 20_000_000;
        Object[] array = new Object[size];
        long memoryBefore = getMemory();
        for (int i = 0; i < size; i++) {
            array[i] = new Object();
        }
        long memoryAfter = getMemory();
        return multiplicityByEight((memoryAfter - memoryBefore) / size);
    }

    public static long calculateEmptyContainersSize() throws InterruptedException {
        int size = 20_000_000;
        List[] array = new List[size];
        long memoryBefore = getMemory();
        for (int i = 0; i < size; i++) {
            array[i] = new ArrayList();
        }
        long memoryAfter = getMemory();
        return multiplicityByEight((memoryAfter - memoryBefore) / size);
    }

    public static long calculateTheGrowthOfTheContainerSize(int size) throws InterruptedException {
        if (size == 0) {
            return calculateEmptyContainersSize();
        }
        int value = 0;
        List list = new ArrayList();
        long memoryBefore = getMemory();
        for (int i = 0; i < size; i++) {
            list.add(value++);
        }
        long memoryAfter = getMemory();
        return multiplicityByEight(memoryAfter - memoryBefore);
    }
}
