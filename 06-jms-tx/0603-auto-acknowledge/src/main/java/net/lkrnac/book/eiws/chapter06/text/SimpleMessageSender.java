package net.lkrnac.book.eiws.chapter06.text;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleMessageSender {
  private static final String SIMPLE_MESSAGE = "simple message";
  private static final String SIMPLE_MESSAGE_LOST = "simple message lost";
  private JmsTemplate jmsTemplate;

  @Autowired
  public SimpleMessageSender(JmsTemplate jmsTemplate) {
    super();
    this.jmsTemplate = jmsTemplate;
  }

  @Scheduled(fixedRate = 1000)
  public void send() {
    log.info("Sending message: {}", SIMPLE_MESSAGE);
    jmsTemplate.convertAndSend("messageQueue", SIMPLE_MESSAGE);
  }

  @Scheduled(fixedRate = 1000)
  public void sendLost() {
    log.info("Sending message: {}", SIMPLE_MESSAGE_LOST);
    jmsTemplate.convertAndSend("messageQueueLost", SIMPLE_MESSAGE_LOST);
  }
}