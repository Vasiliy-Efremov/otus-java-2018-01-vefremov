package ru.otus.l81;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class LabRat {
    private String name = "TestSubjectOne";
    private int age = 1;
    private double weight = 200.00;
    private boolean isAlive = true;
    private char code = 'Z';
    private int[] survivingExperiments = {15,21};

    private byte aByte = 1;
    private short aShort = 2;
    private float aFloat = 3.1f;

    List<String> strings = asList("foo", "bar", "baz");
    Object[] array = strings.toArray();

    @Override
    public boolean equals(Object obj) {
        LabRat r = (LabRat) obj;
        if (this.name.equals(r.name) && this.age == r.age && this.weight == r.weight &&
                this.isAlive == r.isAlive && this.code == r.code &&
                Arrays.equals(this.survivingExperiments, r.survivingExperiments) && this.aByte == r.aByte &&
                this.aShort == r.aShort && this.aFloat == r.aFloat && this.strings.containsAll(r.strings) &&
                Arrays.equals(this.array, r.array)) {
            return true;
        }
        return false;
    }

   public class A {
        int i = 1;
   }

    public class B {
        A a = new A();
    }
}
