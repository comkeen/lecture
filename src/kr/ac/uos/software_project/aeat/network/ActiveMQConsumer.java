/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.network;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author comkeen
 */
public class ActiveMQConsumer implements ExceptionListener {

    private Connection connection;

    public ActiveMQConsumer(String address) {
        init(address);
    }

    @Override
    public void onException(JMSException jmse) {
        System.out.println("JMS Exception occured. SHutting down client");
    }

    private void init(String address) {
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(address);
            this.connection = connectionFactory.createConnection();
            connection.start();
            connection.setExceptionListener(this);
        } catch (JMSException e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }
    
    public void setConsumerDestinationAndListener(String destinationName, MessageListener listener ) {
        try {
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue(destinationName);
            // Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(listener);
        } catch (JMSException ex) {
            Logger.getLogger(ActiveMQConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    public void closeConnection() {
        try {
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(ActiveMQProducer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

