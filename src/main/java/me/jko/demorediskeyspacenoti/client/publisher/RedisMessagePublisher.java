package me.jko.demorediskeyspacenoti.client.publisher;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedisMessagePublisher {

  private final RedisTemplate<String, String> redisTemplate;

  public void publish() {
    String key = "hello";
    String value = "world";
    long expireTime = 3L;

    log.info("key : {}, value : {}, expire seconds : {}", key, value, expireTime);
    redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
  }
}