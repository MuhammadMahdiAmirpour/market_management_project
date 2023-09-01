package ir.ac.kntu.threads;

import ir.ac.kntu.CLI.Panel;
import ir.ac.kntu.objects.Costumer;
import ir.ac.kntu.objects.SuperMarket;
import ir.ac.kntu.reporter.Reporter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
//import java.util.Timer;

import static ir.ac.kntu.CLI.Panel.cleanSuperMarkets;

public class AutoRunner extends Thread {
    private final ArrayList<Costumer> costumers;

    @Override
    public void run() {
//        Timer timer = new Timer();
        Random random = new Random();
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("hey");
        while (true) {
            if (Panel.fakeDate.getHour() == 0 && Panel.fakeDate.getMinute() == 0 && Panel.fakeDate.getSecond() == 0) {
                Reporter reporter = new Reporter();
                reporter.report("supermarkets are cleaned now! \n" +
                        "at the time : \n" +
                        Panel.fakeDate);
                cleanSuperMarkets();
            }
            if (LocalDateTime.now().getSecond() % 2 == 0) {
                try {
                    for (Costumer costumer : costumers) {
                        costumer.setWalletBalance(costumer.getWalletBalance() + 1000000);
                    }
                    for (SuperMarket superMarket : Panel.getSuperMarketsInstance()) {
                        superMarket.setBalance(superMarket.getBalance() + 1000000);
                    }
                    costumers.get(random.nextInt(5)).setRandomOrder();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        //               }
//       };
//        timer.schedule(timerTask, 1000, 3600000);
    }

    public AutoRunner(ArrayList<Costumer> costumers) {
        this.costumers = costumers;
    }
}
