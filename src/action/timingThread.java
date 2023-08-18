package action;

import java.util.Random;

public class timingThread extends Thread {
    private int rnd;
    private int moment;
    public static boolean success;


    public void run() {
        loop1:
        while (true) {
            try {
                Thread.sleep(1);
            } catch (Exception e) {
            }
            Random rand = new Random();
            rnd = rand.nextInt(30);
            for (int j = 0; j < rnd + 10; j++) {
                System.out.print(" ");
            }
            System.out.println("▼");
            for (moment = 0; moment < 50; moment++) {
                try {
                    Thread.sleep(50);
                    if (moment == 49) {
                        System.out.println("■");
                    } else {
                        System.out.print("■");
                    }
                } catch (InterruptedException e) {
                    if(moment-(rnd+10) <= 4 && moment-(rnd+10) >= -3) {
                        System.out.println("성공!");
                        success = true;
                    }
                    else{
                        System.out.println("실패!");
                        success = false;
                    }
                    break loop1;
                }
            }
        }
    }

    public static boolean isSuccess() {
        return success;
    }

    public static void setSuccess(boolean success) {
        timingThread.success = success;
    }
}
