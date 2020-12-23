package me.jko.demorediskeyspacenoti.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.jko.demorediskeyspacenoti.client.publisher.RedisMessagePublisher;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class RedisMessageController {

  private final RedisMessagePublisher redisMessagePublisher;

  @PostMapping
  public void publish() {
    redisMessagePublisher.publish();
  }
}
