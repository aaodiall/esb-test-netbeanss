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
 * @author Oussama
 */
public class LogHandler {

    private boolean firstMessage;
    private long firstMessageTime;
    private long lastMessageTime;
    // link, threadId, logHelper
    private HashMap<String, HashMap<String, logHelper>> theLog;

    public LogHandler() {
        theLog = new HashMap<String, HashMap<String, logHelper>>();
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
    
    public Result fillInResultForm() {
        long maxRespTime = 0;
        int minRespTime = Integer.MAX_VALUE;
        int[] avRespTime = new int[theLog.size()];
        int lostReq = 0;
        
        long maxTemp;
        
//        ResponseTime responseTime = new ResponseTime("sec", "10", "6");
//        TotalResult totalResult = new TotalResult("8", "10", responseTime);
//
//        LinkConsumerProvider lcp1 = new LinkConsumerProvider("10", "1", "2");
//        LinkConsumerProvider lcp2 = new LinkConsumerProvider("20", "3", "4");
//        LinkConsumerProvider linkConsProv[] = {lcp1, lcp2};
//
//        Result result = new Result(totalResult, linkConsProv);

        
        // Access to link Cons Prov
        for (Entry<String, HashMap<String, logHelper>> subLog : theLog.entrySet()) {
            // Access to thread
            for (Entry<String, logHelper> smallLog : subLog.getValue().entrySet()) {
                if (smallLog.getValue().getProcessingTime() != -1) {
                    maxTemp = smallLog.getValue().getRecievedTime()
                             - smallLog.getValue().getSentTime()
                             - smallLog.getValue().getProcessingTime();
                     if (maxTemp > maxRespTime)
                         maxTemp = maxRespTime;
                }
            }
        }
        
        return null;
    
    }

    @Override
    public String toString() {
        String message = new String();
        message = theLog.toString();
        return message;
    }
    
    
}
