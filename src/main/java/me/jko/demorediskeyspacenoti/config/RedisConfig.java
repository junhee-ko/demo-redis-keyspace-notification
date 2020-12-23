package me.jko.demorediskeyspacenoti.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lombok.extern.slf4j.Slf4j;
import me.jko.demorediskeyspacenoti.client.subscriber.RedisMessageSubscriber;

@Slf4j
@Configuration
public class RedisConfig {

  @Bean
  RedisMessageListenerContainer redisContainer(RedisMessageSubscriber redisMessageSubscriber) {
    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(redisConnectionFactory());
    container.addMessageListener(messageListener(redisMessageSubscriber), new PatternTopic("__key*__:*"));

    return container;
  }

  @Bean
  MessageListenerAdapter messageListener(RedisMessageSubscriber redisMessageSubscriber) {
    return new MessageListenerAdapter(redisMessageSubscriber);
  }

  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory(new RedisStandaloneConfiguration("localhost", 6379));
  }

  @Bean
  public RedisTemplate<String, String> redisTemplate() {
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory());
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new StringRedisSerializer());

    return redisTemplate;
  }
}
