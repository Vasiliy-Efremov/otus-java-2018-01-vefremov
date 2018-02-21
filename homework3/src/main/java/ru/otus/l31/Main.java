package ru.otus.l31;

import static java.util.Collections.*;

public class Main {
        MyArrayList<String> myArrayList = new MyArrayList();
        addAll(myArrayList, "Apple", "Cherry", "Orange", "Strawberry");
        System.out.println(Arrays.toString(myArrayList.toArray()));

        MyArrayList<String> myArrayList2 = new MyArrayList();
        addAll(myArrayList2, "Cucumber", "Potato", "Tomato", "Onion");
        myArrayList.addAll(2, myArrayList2);

        copy(myArrayList, myArrayList);
        sort(myArrayList2, reverseOrder());
        }
    }
}
