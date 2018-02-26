package ru.otus.l41;

import java.io.IOException;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {
    private int count;

    @Override
    public void run() {
        XUtils.getNotifications();
        XUtils.saveNotifations();
        count++;
        if (count == 5) {
            try {
                XUtils.printLogs();
            } catch (IOException e) {
                e.printStackTrace();
            }
            cancel();
        }
    }
}
