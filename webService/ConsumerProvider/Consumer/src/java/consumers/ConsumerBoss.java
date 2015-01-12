package consumers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 *
 * @author zaki
 */
public class ConsumerBoss {
    
    private static final String EXCHANGE_NAME = "configuration";
    private ConsumerBean bean;
    
    
    public void setBean(ConsumerBean bean) {
        this.bean = bean ;
    }
    
    public String getConfig(Integer ID) throws Exception {

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
        Channel channel = connection.createChannel();

        // linking with the queue 
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String queueName = channel.queueDeclare().getQueue();

        // define the reciever 
        String who = "consumer" + ID;
        channel.queueBind(queueName, EXCHANGE_NAME, who);

        QueueingConsumer cons = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, cons);
        
        // waiting for configuration
        while (true) {

            // retrieve the message
            QueueingConsumer.Delivery delivery = cons.nextDelivery();
            String message = new String(delivery.getBody());
            String routingKey = delivery.getEnvelope().getRoutingKey();
            
            if (message.isEmpty()) {
                return null;
            } else {
                // ReqNumber
                listenner(Integer.parseInt(message));
            }

            return " [x] Received '" + routingKey + "':'" + message + "'";
        }
        
    }
    
    public Boolean listenner(Integer ReqNumber) {

        this.bean.setReqNumber(ReqNumber);
        return true;
    }
}
