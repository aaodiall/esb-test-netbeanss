/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.insa.tp3g1.esbsimulator.view;

import callConfig.Exception_Exception;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 *
 * @author alpha
 */
public class MessageHandler implements MessageHandlerReceiver, MessageHandlerSender{
    
    private static final String EXCHANGE_NAME = "configuration";
     private static final String EXCHANGE_NAME_START = "start";
     
    private String callConfigProvider(int id) throws Exception_Exception, callConfigP2.Exception_Exception, callConfigP3.Exception_Exception, callConfigP4.Exception_Exception, callConfigP5.Exception_Exception{
        String message;
        switch (id){
            case 1: message=callP1();
                break;
            case 2: message=callP2();
                break;
            case 3: message=callP3();
                break;
            case 4: message=callP4();
                break;
            case 5: message=callP5();
                break;
        }
        
        return null;
        
    }
    
    @Override
    public String sendConfig(String who, String config)   throws Exception {
        
        /* calling connectionFactory to create a custome connexion with
        * rabbitMQ server information.
        */
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("146.148.27.98");
        factory.setUsername("admin");
        factory.setPassword("adminadmin");
        factory.setPort(5672);
        
        // establish the connection with RabbitMQ server using our factory.
        Connection connection = factory.newConnection();
        
        // We're connected now, to the broker on the cloud machine.
        // If we wanted to connect to a broker on a the local machine we'd simply specify "localhost" as IP adresse.
        // creating a "configuration" direct channel/queue
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        
        // the message and the distination
        String forWho = who;
        String message = config;
        
        // publish the message
        channel.basicPublish(EXCHANGE_NAME, forWho, null, message.getBytes());
        
        // close the queue and the connexion
        channel.close();
        connection.close();
        return " [x] Sent '" + forWho + "':'" + message + "'";
    }
    
  /*  @Override
    public void start(String message)  throws Exception{
        
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("146.148.27.98");
        factory.setUsername("admin");
        factory.setPassword("adminadmin");
        factory.setPort(5672);
        
        // establish the connection with RabbitMQ server using our factory.
        Connection connection = factory.newConnection();
        
        
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME_START, "fanout");
        
        channel.basicPublish(EXCHANGE_NAME_START, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }*/
    
    private static String callP1() throws Exception_Exception {
        callConfig.Provider1_Service service = new callConfig.Provider1_Service();
        callConfig.Provider1 port = service.getProvider1Port();
        return port.test();
    }

    private static String callP2() throws callConfigP2.Exception_Exception {
        callConfigP2.Provider2_Service service = new callConfigP2.Provider2_Service();
        callConfigP2.Provider2 port = service.getProvider2Port();
        return port.test();
    }

    private static String callP3() throws callConfigP3.Exception_Exception {
        callConfigP3.Provider3_Service service = new callConfigP3.Provider3_Service();
        callConfigP3.Provider3 port = service.getProvider3Port();
        return port.test();
    }

    private static String callP4() throws callConfigP4.Exception_Exception {
        callConfigP4.Provider4_Service service = new callConfigP4.Provider4_Service();
        callConfigP4.Provider4 port = service.getProvider4Port();
        return port.test();
    }

    private static String callP5() throws callConfigP5.Exception_Exception {
        callConfigP5.Provider5_Service service = new callConfigP5.Provider5_Service();
        callConfigP5.Provider5 port = service.getProvider5Port();
        return port.test();
    }
    
    
    
}
