/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.tp3g1.esbsimulator.view;

import callConfig.Exception_Exception;
import com.insa.tp3g1.esbsimulator.model.scenario.Scenario;
import com.insa.tp3g1.esbsimulator.presenter.LogHandler;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alpha
 */
public class MessageHandler extends Thread implements MessageHandlerReceiver, MessageHandlerSender {

    private static final String EXCHANGE_NAME = "configuration";
    private static final String EXCHANGE_NAME_START = "start";
    private static final String LOG_NAME = "logs";
    private static Integer count = 0;

//    public static void main(String[] args) {
//        try {
//            logGetter();
//            //  String message= callConfigProvider(1);
//            //System.out.println(sendConfig1("provider1", "10;5000"));
//        } catch (Exception ex) {
//            Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    @Override
    public void run() {
        try {
            logGetter();
        } catch (Exception ex) {
            Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void providerHandler(final Scenario scenario) {

        //do wait the prodider
        for (int i = 0; i < scenario.getNumberConsumerProvider(); i++) {
            new Thread("" + (i + 1)) {
                @Override
                public void run() {
                    try {

                        String message = callConfigProvider(Integer.parseInt(getName()));

                    } catch (Exception_Exception ex) {
                        Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (callConfigP2.Exception_Exception ex) {
                        Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (callConfigP3.Exception_Exception ex) {
                        Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (callConfigP4.Exception_Exception ex) {
                        Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (callConfigP5.Exception_Exception ex) {
                        Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }.start();

        }
        //send config to the provider
        for (int i = 0; i < scenario.getNumberConsumerProvider(); i++) {
            new Thread("" + (i + 1)) {
                @Override
                public void run() {
                    try {
                        sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String config = "" + scenario.getProviders().get(Integer.parseInt(getName()) - 1).getDataExchangeSize().getContent() + ";"
                            + scenario.getProviders().get(Integer.parseInt(getName()) - 1).getProcessingTime().getContent();
                    System.out.println("config: " + config);
                    try {

                        System.out.println(sendConfig1("provider" + getName(), config));
                    } catch (Exception ex) {
                        Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }.start();
        }
    }

    public static void consumerHandler(final Scenario scenario) {

        //do wait the prodider
        for (int i = 0; i < scenario.getNumberConsumerProvider(); i++) {
            new Thread("" + (i + 1)) {
                @Override
                public void run() {
                    try {
                        String message = callConfigConsumer(Integer.parseInt(getName()));
                    } catch (configC1.Exception_Exception ex) {
                        Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (callConfigC2.Exception_Exception ex) {
                        Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (callConfigC3.Exception_Exception ex) {
                        Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (callConfigC4.Exception_Exception ex) {
                        Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (callConfigC5.Exception_Exception ex) {
                        Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }.start();

        }
        //send config to the provider
        for (int i = 0; i < scenario.getNumberConsumerProvider(); i++) {
            new Thread("" + (i + 1)) {
                @Override
                public void run() {
                    try {
                        sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String config = "" + scenario.getConsumers().get(Integer.parseInt(getName()) - 1).getRequestBySeconde();
                    try {
                        System.out.println(sendConfig1("consumer" + getName(), config));
                    } catch (Exception ex) {
                        Logger.getLogger(MessageHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("fin thread " + getName());
                }

            }.start();
        }

    }

    private static String callConfigProvider(int id) throws Exception_Exception, callConfigP2.Exception_Exception, callConfigP3.Exception_Exception, callConfigP4.Exception_Exception, callConfigP5.Exception_Exception {
        String message = "";
        switch (id) {
            case 1:
                message = callP1();
                break;
            case 2:
                message = callP2();
                break;
            case 3:
                message = callP3();
                break;
            case 4:
                message = callP4();
                break;
            case 5:
                message = callP5();
                break;
        }

        return message;

    }

    private static String callConfigConsumer(int id) throws configC1.Exception_Exception, callConfigC2.Exception_Exception, callConfigC3.Exception_Exception, callConfigC4.Exception_Exception, callConfigC5.Exception_Exception {
        String message = "";
        switch (id) {
            case 1:
                message = callC1();
                break;
            case 2:
                message = callC2();
                break;
            case 3:
                message = callC3();
                break;
            case 4:
                message = callC4();
                break;
            case 5:
                message = callC5();
                break;
        }

        return message;
    }

    public static String sendConfig1(String who, String config) throws Exception {

        /* calling connectionFactory to create a custome connexion with
         * rabbitMQ server information.
         */
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("146.148.27.98");
        factory.setUsername("admin");
        factory.setPassword("adminadmin");
        factory.setPort(5672);
        System.out.println("connection ok");
        // establish the connection with RabbitMQ server using our factory.
        Connection connection = factory.newConnection();
        // System.out.println("connection ok1");
        // We're connected now, to the broker on the cloud machine.
        // If we wanted to connect to a broker on a the local machine we'd simply specify "localhost" as IP adresse.
        // creating a "configuration" direct channel/queue
        Channel channel = connection.createChannel();
        //  System.out.println("connection ok2");
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        //  System.out.println("exchangeDeclare");
        // the message and the distination
        String forWho = who;
        String message = config;

        // publish the message
        channel.basicPublish(EXCHANGE_NAME, forWho, null, message.getBytes());
        //  System.out.println("basicPublish");
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

    @Override
    public String sendConfig(String who, String config) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static String callC1() throws configC1.Exception_Exception {
        configC1.Consumer1_Service service = new configC1.Consumer1_Service();
        configC1.Consumer1 port = service.getConsumer1Port();
        return port.test();
    }

    private static String callC2() throws callConfigC2.Exception_Exception {
        callConfigC2.Consumer2_Service service = new callConfigC2.Consumer2_Service();
        callConfigC2.Consumer2 port = service.getConsumer2Port();
        return port.test();
    }

    private static String callC3() throws callConfigC3.Exception_Exception {
        callConfigC3.Consumer3_Service service = new callConfigC3.Consumer3_Service();
        callConfigC3.Consumer3 port = service.getConsumer3Port();
        return port.test();
    }

    private static String callC4() throws callConfigC4.Exception_Exception {
        callConfigC4.Consumer4_Service service = new callConfigC4.Consumer4_Service();
        callConfigC4.Consumer4 port = service.getConsumer4Port();
        return port.test();
    }

    private static String callC5() throws callConfigC5.Exception_Exception {
        callConfigC5.Consumer5_Service service = new callConfigC5.Consumer5_Service();
        callConfigC5.Consumer5 port = service.getConsumer5Port();
        return port.test();
    }

    public static void logGetter() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        LogHandler logHandler = new LogHandler();
        factory.setHost("146.148.27.98");
        factory.setUsername("admin");
        factory.setPassword("adminadmin");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(LOG_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, LOG_NAME, "");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);
        count = 0;
        boolean over = false;
        while (!over) {

            QueueingConsumer.Delivery delivery = null;
            try {
                delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                //System.out.println(" [x] Received '" + message + "'");
                synchronized (count) {
                    count++;
                }
                logHandler.add(message);

                System.out.println(logHandler);
            } catch (InterruptedException interruptedException) {
                System.out.println("finished");
                Thread.currentThread().stop();
                over = true;
            } catch (ShutdownSignalException shutdownSignalException) {
                System.out.println("finished");
                Thread.currentThread().stop();
                over = true;
            } catch (ConsumerCancelledException consumerCancelledException) {
                System.out.println("finished");
                Thread.currentThread().stop();
                over = true;
            } catch (IllegalMonitorStateException e) {
                System.out.println("finished");
                Thread.currentThread().stop();
                over = true;
            }

        }
        System.out.println("finished handling");
        return;
    }

    public static Integer getCount() {
        return count;
    }

}
