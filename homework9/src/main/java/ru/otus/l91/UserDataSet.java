package ru.otus.l91;

public class UserDataSet extends DataSet {
    public String name;
    public int age;

    public UserDataSet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Имя: %s, возраст: %d", name,age);
    }
}
