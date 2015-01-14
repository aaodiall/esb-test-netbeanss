package consumers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author zaki
 */
public class Invoker3 extends Thread {
    static Thread runna;
    
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
        compositeprovider3.CompositeProvider3Service1 service = new compositeprovider3.CompositeProvider3Service1();
        compositeprovider3.Provider3 port = service.getCasaPort1();
        return port.hello();
    }
    
    
}