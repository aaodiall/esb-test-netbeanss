package com.insa.tp3g1.esbsimulator.view;

/**
 *
 * @author zaki
 */
public class Observer {

    static Thread runna;
    static Integer lastCount;
    static final Integer toleranceTime = 1000;
    static boolean firstTime = true;
    static boolean over = false;

    public static void main(String[] args) throws InterruptedException {

        MessageHandler messageHandler = new MessageHandler();
        lastCount = -1;
        runna = new Thread(new MessageHandler());
        runna.start();

        while (!over) {
            if (firstTime) {
                Thread.sleep(5000);
                firstTime = false;
            }
            if (lastCount == MessageHandler.getCount()) {
                    over = true;
                    runna.stop();
            }
            lastCount = MessageHandler.getCount();
            Thread.sleep(toleranceTime);
            System.out.println(over);
        }
        System.out.println("finished Oberserver");
        return;
    }

}
