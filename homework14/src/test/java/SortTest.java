import org.junit.Assert;
import org.junit.Test;
import ru.otus.MergeSort;
import ru.otus.xUtils;

import java.util.concurrent.ForkJoinPool;

public class SortTest {

    @Test
    public void testSort() throws IllegalAccessException {
        int[] array = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        int[] checkedArray = xUtils.getArray(10);
        xUtils.fillTheArray(checkedArray);

        MergeSort mergeSort = new MergeSort(checkedArray, 0, checkedArray.length);
        ForkJoinPool pool = mergeSort.getForkJoinPool(4);
        pool.submit(mergeSort).join();

        Assert.assertArrayEquals(array, checkedArray);
    }
}
