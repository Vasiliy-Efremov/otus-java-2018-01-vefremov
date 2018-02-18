package ru.otus.l31;

import static java.util.Collections.*;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> myArrayList = new MyArrayList();
        addAll(myArrayList, "Apple", "Cherry", "Orange", "Strawberry");

        MyArrayList<String> myArrayList2 = new MyArrayList();
        addAll(myArrayList2, "Cucumber", "Potato", "Tomato", "Onion");
        myArrayList.addAll(2, myArrayList2);

        MyArrayList<String> myArrayList3 = new MyArrayList(myArrayList.size());
        copy(myArrayList3, myArrayList);
        sort(myArrayList3, reverseOrder());
        for (int i = 0; i < myArrayList3.size(); i++) {
            System.out.println(myArrayList3.get(i));
        }
    }
}
