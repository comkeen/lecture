package kr.ac.uos.jms;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import kr.ac.uos.software_project.aeat.network.ActiveMQConsumer;
import kr.ac.uos.software_project.aeat.network.ActiveMQProducer;
import org.junit.Test;

public class JMS_Test {

    @Test
    public void testSendReceiveMessage() {
        try {
            String DESTIANTION_EXAM = "TEST.FOO"; // 데스티네이션 이름. 메시지의 목적지 이름.
            // 메시지 브로커의 주소. vm://localhost는 테스트를 위해 가상의 브로커를 가정한다.
            // 실제 구동중인 브로커를 사용하려면 "tcp://브로커의ip:61616" 값을 넣자
            String MQ_ADDRESS = "vm://localhost";
            ActiveMQProducer producer = new ActiveMQProducer(MQ_ADDRESS);
            ActiveMQConsumer consumer = new ActiveMQConsumer(MQ_ADDRESS);
            // 메시지 수신을 위한 리스너 정의
            MessageListener listener = new MessageListener() {
                // 메시지를 수신했을 때 호출되는 메소드
                @Override
                public void onMessage(Message message) {
                    // JMS에서는 TextMessage 타입의 메시지를 교환한다.
                    // 수신한 message가 TextMessage의 인스턴스인 경우 실행 하는 조건문
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        try { System.out.println("Received Message:\n" + textMessage.getText()); }
                        catch (JMSException e) { e.printStackTrace(); }}}};
            // Set consumer Destination. consumer에게 메시지의 목적지와, 메시지 수신시을 위한 리스너를 알려준다.
            consumer.setConsumerDestinationAndListener(DESTIANTION_EXAM, listener);
            // Send Message to Destination. producer가 text라는 메시지를 목적지로 보낸다.
            String text = "Hello world!";
            producer.sendMessageTo(text, DESTIANTION_EXAM);
            
            // 테스트는 실행이 완료된 후에 자동 종료되기 때문에, 메시지 송수신 딜레이를 기다리기 위해 2초 뒤에 종료한다. 
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(JMS_Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
