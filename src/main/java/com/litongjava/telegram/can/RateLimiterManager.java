package com.litongjava.telegram.can;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RateLimiterManager {
  // 全局速率限制：每秒最多30条消息
  private static final Semaphore globalSemaphore = new Semaphore(30);

  // 每个群组的速率限制：每分钟最多20条消息
  private static final ConcurrentMap<String, Semaphore> perChatSemaphores = new ConcurrentHashMap<>();

  private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

  static {
    // 定期恢复全局许可，每秒恢复30个许可
    scheduler.scheduleAtFixedRate(() -> {
      int permitsToRelease = 30 - globalSemaphore.availablePermits();
      if (permitsToRelease > 0) {
        globalSemaphore.release(permitsToRelease);
      }
    }, 1, 1, TimeUnit.SECONDS);
  }

  // 获取或创建特定群组的速率限制器
  private static Semaphore getPerChatSemaphore(String chatId) {
    return perChatSemaphores.computeIfAbsent(chatId, id -> {
      Semaphore semaphore = new Semaphore(20);
      // 每分钟恢复20个许可
      scheduler.scheduleAtFixedRate(() -> {
        int permitsToRelease = 20 - semaphore.availablePermits();
        if (permitsToRelease > 0) {
          semaphore.release(permitsToRelease);
        }
      }, 1, 1, TimeUnit.MINUTES);
      return semaphore;
    });
  }

  // 获取速率许可，如果许可不足，则阻塞直到许可可用
  public static void acquire(String chatId) {
    try {
      globalSemaphore.acquire();
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
    }
    Semaphore chatSemaphore = getPerChatSemaphore(chatId);
    try {
      chatSemaphore.acquire();
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
    }
  }

  public static Semaphore getGlobalSemaphore() {
    return globalSemaphore;
  }

  // 关闭调度器
  public static void shutdown() {
    scheduler.shutdown();
    try {
      if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
        scheduler.shutdownNow();
      }
    } catch (InterruptedException e) {
      scheduler.shutdownNow();
      Thread.currentThread().interrupt();
    }
  }
}
