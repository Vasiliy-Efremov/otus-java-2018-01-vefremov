package ru.otus.l91;

public class Main {
    public static void main(String[] args) throws Exception {
        UserDataSet one = new UserDataSet("Иван", 28);
        UserDataSet two = new UserDataSet("Роман", 31);

        try (DBService dbService = new DBService()) {
            dbService.createTable();

            Executor executor = new Executor();
            executor.save(one);
            executor.save(two);

            Executor executor2 = new Executor();
            System.out.println(executor2.load(1,UserDataSet.class));
        }
    }
}
