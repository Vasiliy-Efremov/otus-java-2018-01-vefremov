package ru.otus.l41;

import javax.management.NotificationEmitter;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

class GCMonitor {
    private static Notification myNotificationListener = new Notification();
    private static ArrayList<String> list = new ArrayList<>();

    void getGC() {
        List<GarbageCollectorMXBean> garbageCollectorMXBeanList = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean mxBean : garbageCollectorMXBeanList) {
            list.add(mxBean.getName());
            NotificationEmitter emitter = (NotificationEmitter) mxBean;
            emitter.addNotificationListener(myNotificationListener, null, null);
        }
    }

    public static ArrayList<String> getList() {
        return list;
    }
}
