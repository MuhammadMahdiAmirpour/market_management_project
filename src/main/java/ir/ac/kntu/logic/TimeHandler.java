package ir.ac.kntu.logic;

import ir.ac.kntu.CLI.Panel;

import java.util.Timer;
import java.util.TimerTask;

public class TimeHandler extends Thread {
    public TimeHandler() {
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Panel.fakeDate.addToSeconds(1);
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 2 * 3600);
    }
}
