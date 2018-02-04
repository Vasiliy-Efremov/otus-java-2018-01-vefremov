package ru.otus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.ImmutableList;

public class l011 {
   private static int theNumber;
   private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.out.print("Enter a number: ");
        theNumber = getInt();
        int calculation = calculatingTriangleNumber(theNumber);
        List<Integer> list = new ArrayList<>();
        list.add(calculation);
        List<Integer> immutableList = transformToImmutable(list);
        System.out.println("Triangle number = " + immutableList.get(0));
    }

    public static int calculatingTriangleNumber (int n) {
        if (n == 1) {
            return 1;
        }
        else {
            list.add(n);
            return (n + calculatingTriangleNumber(n - 1));
        }
    }

    public static int getInt() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int i = Integer.parseInt(reader.readLine());
        return  i;
    }

    public static List<Integer> transformToImmutable(List<Integer> list) {
        return ImmutableList.copyOf(list);
    }
}

