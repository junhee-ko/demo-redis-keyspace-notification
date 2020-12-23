package me.jko.demorediskeyspacenoti.client.subscriber;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RedisMessageSubscriber implements MessageListener {

  @Override
  public void onMessage(Message message, byte[] pattern) {
    String key = new String(message.getBody());
    String event = new String(message.getChannel());
    log.info("key : {}, channel : {}", key, event);
  }
}
