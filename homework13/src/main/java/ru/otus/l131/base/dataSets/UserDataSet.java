package ru.otus.l131.base.dataSets;

import javax.persistence.*;

@Entity
@Table(name = "[User]")
public class UserDataSet extends DataSet {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet address;

    public UserDataSet(String name, int age, AddressDataSet address) {
        this.setId(-1);
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public UserDataSet() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("UserDataSet{name: %s, age: %d, address: %s}", name, age, address);
    }
}

