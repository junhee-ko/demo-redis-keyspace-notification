package me.jko.demorediskeyspacenoti.client.subscriber;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RedisMessageSubscriber implements MessageListener {

  @Override
  public void onMessage(Message message, byte[] pattern) {
    log.info("key : {}, event : {}", new String(message.getBody()), new String(message.getChannel()));
  }
}
