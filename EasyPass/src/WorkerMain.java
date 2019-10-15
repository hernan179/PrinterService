

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

public class WorkerMain {

    final static Logger logger = Logger.getLogger(WorkerMain.class);

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(System.getenv("CLOUDAMQP_URL"));
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String queueName = "work-queue-1";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("x-ha-policy", "all");
        channel.queueDeclare(queueName, true, false, false, params);
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, false, consumer);
       
        while (true) {
                logger.info("Message Received: ");
            QueueingConsumer.Delivery delivery = consumer.nextDelivery(); 
            if (delivery != null) {
                String msg = new String(delivery.getBody(), "UTF-8");

                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        }

    }

}
