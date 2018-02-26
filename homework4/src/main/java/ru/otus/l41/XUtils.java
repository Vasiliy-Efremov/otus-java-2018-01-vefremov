package ru.otus.l41;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class XUtils {

    public static void fillTheList(List<String> list, int addCount, int removeCount) throws InterruptedException {
        for (int i = 0; i < addCount; i++) {
            list.add(new String(new char[]{'s', 'o', 'm', 'e'}));
        }
        for (int i = 0; i < removeCount; i++) {
            list.remove(list.size() - 1);
        }
    }

    public static void getNotifications() {
        System.out.println("Count of minor garbage collections: " + Notification.getCountMinorCleanUp() +
                ". Total duration: " + Notification.getDurationMinorCleanUp());
        System.out.println("Count of major garbage collections: " + Notification.getCountMajorCleanUp() +
                ". Total duration: " + Notification.getDurationMajorCleanUp());
        System.out.println("------------------------------------------------------------");
    }

    public static void saveNotifations() {
        Notification.addValueToList("Count of minor garbage collections: " + Notification.getCountMinorCleanUp() +
                ". Total duration: " + Notification.getDurationMinorCleanUp());
        Notification.addValueToList("Count of major garbage collections: " + Notification.getCountMajorCleanUp() +
                ". Total duration: " + Notification.getDurationMajorCleanUp());
        Notification.addValueToList("------------------------------------------------------------");
    }

    public static void printLogs() throws IOException {
        System.out.println("!!!ATTENTION!!!");
        System.out.println("Specify the path and name the file to write logs, for example: d:/report1.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filePath = reader.readLine();
        FileWriter fileWriter = new FileWriter(filePath);

        ArrayList<String> GCNames = GCMonitor.getList();
        for (int i = 0; i < GCNames.size(); i++) {
            fileWriter.write("The name of using GC in application: " + GCNames.get(i));
            fileWriter.write(13);
            fileWriter.write(10);
        }
        fileWriter.write("------------------------------------------------------------");
        fileWriter.write(13);
        fileWriter.write(10);

        ArrayList<String> listWithLogs = Notification.getList();
        for (int i = 0; i < listWithLogs.size(); i++) {
            fileWriter.write(listWithLogs.get(i));
            fileWriter.write(13);
            fileWriter.write(10);
        }
        reader.close();
        fileWriter.flush();
        fileWriter.close();
    }
}
