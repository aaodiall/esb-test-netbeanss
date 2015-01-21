package com.insa.tp3g1.esbsimulator.presenter;

import com.insa.tp3g1.esbsimulator.model.result.LinkConsumerProvider;
import com.insa.tp3g1.esbsimulator.model.result.ResponseTime;
import com.insa.tp3g1.esbsimulator.model.result.Result;
import com.insa.tp3g1.esbsimulator.model.result.TotalResult;
import com.insa.tp3g1.esbsimulator.model.result.logHelper;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author Oussama, belliot
 */
public class LogHandler {

    private boolean firstMessage;
    private long firstMessageTime;
    private long lastMessageTime;
    private final int  PARAM=1000000;
    // link, threadId, logHelper
    private HashMap<String, HashMap<String, logHelper>> theLog;

    public LogHandler() {
        theLog = new HashMap<String, HashMap<String, logHelper>>();
    }

    public HashMap<String, HashMap<String, logHelper>> getTheLog() {
        return theLog;
    }

    public void add(String log) {
        String[] data = log.split(";");
        // 0 : Id linkProviderConsumer
        // 1 : sent or recieved
        // 2 : TO DO : processing time  
        // 3 : message
        // 4 : id thread
        // 5 : timestamp

        HashMap<String, logHelper> lineOfLog;

        // if not found, create row for word and save reference
        // if able to find row, save reference to hash map of data
        if ((lineOfLog = theLog.get(data[0])) == null) {
            theLog.put(data[0], new HashMap<String, logHelper>());
            lineOfLog = theLog.get(data[0]);
        }

        logHelper littleHelper;

        if ((littleHelper = lineOfLog.get(data[4])) == null) {
            lineOfLog.put(data[4], new logHelper(-1));
            littleHelper = lineOfLog.get(data[4]);
        }

        if (data[1].equals("sent")) {
            littleHelper.setSentTime(Long.parseLong(data[5]));
        } else if (data[1].equals("recieved")) {
            littleHelper.setProcessingTime(Integer.parseInt(data[2]));
            littleHelper.setRecievedTime(Long.parseLong(data[5]));
        }
    }
    
    public Result fillInResultForm(HashMap<String, HashMap<String, logHelper>> theLog) {
        long maxRespTime = 0;
        long minRespTime = Integer.MAX_VALUE;
        long[] avRespTime = new long[theLog.size()];
        int totalLostReq = 0;
        int lostReqLink;
        
        long timeTemp=0;
         long timeTempMin=0;
        long averageTemp;
        
        int counter = 0;
        int emptyLink = 0;
        
        ArrayList<LinkConsumerProvider> listConsProv = new ArrayList<LinkConsumerProvider>();
        LinkConsumerProvider lcp = null;

        /***** Calcul of the result variables *****/
        
        // Access to link Cons Prov
        for (Entry<String, HashMap<String, logHelper>> subLog : theLog.entrySet()) {
            // Access to thread
            averageTemp = 0;
            lostReqLink = 0;
            for (Entry<String, logHelper> smallLog : subLog.getValue().entrySet()) {
                // Test if the request is lost
                if (smallLog.getValue().getProcessingTime() != -1) {
                    timeTemp = smallLog.getValue().getRecievedTime()
                             - smallLog.getValue().getSentTime()
                             - smallLog.getValue().getProcessingTime()*1000000;
                  // timeTempMin= timeTemp;
                    // If it is the maximum time
                  /// System.out.println(timeTemp/PARAM);
                    if (timeTemp > maxRespTime){
                       maxRespTime= timeTemp;
                    }
                    // If it is the minimum time
                    if (timeTemp < minRespTime){
                        minRespTime= timeTemp;
                    }
                    
                    averageTemp += timeTemp;
                     
                }
                else {
                    // The request is lost
                    totalLostReq++;
                    lostReqLink++;
//                    System.out.println("Lost req " + totalLostReq);
                }
                
            }
            
            // Calcul of the average time for on link Cons Prov
            if (subLog.getValue().size() - lostReqLink > 0){
//                System.out.println(subLog.getValue().size());
                averageTemp = averageTemp / (subLog.getValue().size() - lostReqLink);
            }
            counter = Integer.parseInt(subLog.getKey());
//            System.out.println("Hey hey !" + counter);
            avRespTime[counter-1] = averageTemp;
            
            // Add the current link Cons Prov to the ArrayList
            lcp = new LinkConsumerProvider(String.valueOf((averageTemp/PARAM)),
                    String.valueOf(counter), String.valueOf(counter));
            listConsProv.add(lcp);
            
            // Use to fill the average-response-time table
//            counter++;
        }

        // Calcul of the global average response time
        averageTemp = 0;
        for (int i = 0; i < avRespTime.length; i++){
            if (avRespTime[i] != 0){
                averageTemp += avRespTime[i];
            }
            else {
                emptyLink++;
            }
        }
        averageTemp = averageTemp / ((long) (avRespTime.length - emptyLink));
        
        /******************************************/
        
        
        /***** Creation of the Result instance *****/
        
        // /!\ Not sure for the "sec" parameter
        ResponseTime responseTime = new ResponseTime("s", String.valueOf((maxRespTime/PARAM)), String.valueOf((minRespTime/PARAM)));
        TotalResult totalResult = new TotalResult(String.valueOf((averageTemp/PARAM)), String.valueOf(totalLostReq), responseTime);

        Result result = new Result(totalResult,
                listConsProv.toArray(new LinkConsumerProvider[listConsProv.size()]));
        
        /*******************************************/
        
        return result;
    
    }

    @Override
    public String toString() {
        String message = new String();
        message = theLog.toString();
        return message;
    }
    
    
}
