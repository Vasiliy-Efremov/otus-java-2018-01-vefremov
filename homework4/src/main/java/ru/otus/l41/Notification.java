package ru.otus.l41;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.util.ArrayList;
import java.util.List;

public class Notification implements NotificationListener {
    private static int countMinorCleanUp;
    private static int durationMinorCleanUp;
    private static int countMajorCleanUp;
    private static int durationMajorCleanUp;
    private static ArrayList<String> list = new ArrayList<>();

    @Override
    public void handleNotification(javax.management.Notification notification, Object handback) {
        GarbageCollectionNotificationInfo gcni = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());

        if (gcni.getGcAction().equals("end of minor GC")) {
            countMinorCleanUp++;
            durationMinorCleanUp += gcni.getGcInfo().getDuration();
        } else {
            countMajorCleanUp++;
            durationMajorCleanUp += gcni.getGcInfo().getDuration();
        }
    }

    public static void addValueToList(String value) {
        list.add(value);
    }

    public static int getCountMinorCleanUp() {
        return countMinorCleanUp;
    }

    public static int getDurationMinorCleanUp() {
        return durationMinorCleanUp;
    }

    public static int getCountMajorCleanUp() {
        return countMajorCleanUp;
    }

    public static int getDurationMajorCleanUp() {
        return durationMajorCleanUp;
    }

    public static ArrayList<String> getList() {
        return list;
    }

}
