package consumers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author zaki
 */
public class Invoker1 extends Thread {

    static Thread runna;

    public static void main(String[] args) {
//        runna = new Thread(new InvokerBoss1());
//        runna.start();
        Thread[] invokers = new Thread[5];
        for (Thread invoker : invokers) {
            invoker = new Thread(new Invoker1());
            invoker.start();
        }

    }

    @Override
    public void run() {
        File log = new File("C:\\Users\\samsung\\Desktop\\log.txt");

        try {
            if (!log.exists()) {
                System.out.println("We had to make a new file.");
                log.createNewFile();
            }
            String res = hello();
            FileWriter fileWriter = new FileWriter(log, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("invocation " + Thread.currentThread().getId() + " : " + hello());
            bufferedWriter.newLine();
            bufferedWriter.close();

            System.out.println("Done");
        } catch (IOException e) {
            System.out.println("COULD NOT LOG!!");
        }
    }

    private static String hello() {
        compositeprovider1.CompositeProvider1Service1 service = new compositeprovider1.CompositeProvider1Service1();
        compositeprovider1.Provider1 port = service.getCasaPort1();
        return port.hello();
    }
    
    
}
