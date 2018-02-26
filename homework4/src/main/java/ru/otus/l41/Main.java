package ru.otus.l41;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/*
-Xms256m
-Xmx256m
 */

public class Main {
    public static void main(String[] args) throws Exception {
        GCMonitor monitor = new GCMonitor();
        monitor.getGC();

        MyTimerTask task = new MyTimerTask();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 60_000, 60_000);

        List<String> list = new ArrayList<>();
        while (true) {
            XUtils.fillTheList(list, 250_000, 125_000);
            Thread.sleep(9_000);
        }
    }
}
