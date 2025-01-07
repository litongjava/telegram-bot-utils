package com.litongjava.telegram.can;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.CopyMessage;
import org.telegram.telegrambots.meta.api.methods.CopyMessages;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.GetMe;
import org.telegram.telegrambots.meta.api.methods.groupadministration.CreateChatInviteLink;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChat;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.groupadministration.LeaveChat;
import org.telegram.telegrambots.meta.api.methods.groupadministration.PromoteChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessages;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.ChatInviteLink;
import org.telegram.telegrambots.meta.api.objects.MessageId;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.chat.ChatFullInfo;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TelegramClientCan {
  public static TelegramClient main;
  public static Long botId;

  /**
   * 异步发送消息类方法使用基于 chatId 的速率限制
   * @param input The SendMessage object containing message details
   * @return A CompletableFuture representing the pending result of the send operation
   */
  public static CompletableFuture<Message> executeAsync(SendMessage input) {
    String chatId = input.getChatId();
    RateLimiterManager.acquire(chatId);
    try {
      return main.executeAsync(input);
    } catch (TelegramApiException ex) {
      throw new RuntimeException("Error sending message to chatId: " + chatId + ". " + ex.getMessage(), ex);
    }
  }

  /**
   * 异步发送消息类方法使用基于 chatId 的速率限制
   * @param input The SendMessage object containing message details
   * @return A CompletableFuture representing the pending result of the send operation
   */
  public static CompletableFuture<Message> executeAsync(SendDocument input) {
    String chatId = input.getChatId();
    RateLimiterManager.acquire(chatId);
    return main.executeAsync(input);
  }

  /**
   * 异步发送消息类方法使用基于 chatId 的速率限制
   * @param input The SendMessage object containing message details
   * @return A CompletableFuture representing the pending result of the send operation
   */
  public static CompletableFuture<Message> executeAsync(SendVideo input) {
    String chatId = input.getChatId();
    RateLimiterManager.acquire(chatId);
    return main.executeAsync(input);
  }

  /**
   * 异步发送消息类方法使用基于 chatId 的速率限制
   * @param input The SendMessage object containing message details
   * @return A CompletableFuture representing the pending result of the send operation
   */
  public static CompletableFuture<Message> executeAsync(SendPhoto input) {
    String chatId = input.getChatId();
    RateLimiterManager.acquire(chatId);
    return main.executeAsync(input);
  }

  public static CompletableFuture<List<Message>> executeAsync(SendMediaGroup input) {
    String chatId = input.getChatId();
    RateLimiterManager.acquire(chatId);
    return main.executeAsync(input);
  }

  /**
   * 发送消息类方法使用基于 chatId 的速率限制
   * @return 
   */
  public static Message execute(SendMessage input) {
    String chatId = input.getChatId();
    RateLimiterManager.acquire(chatId);
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException("Failed to send message to chatId: " + chatId, e);
    }
  }

  public static Message execute(SendDocument input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.acquire(chatId);
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + chatId, e);
    }
  }

  public static Message execute(SendVideo input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.acquire(chatId);
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + chatId, e);
    }
  }

  public static Message execute(SendPhoto input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.acquire(chatId);
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + chatId, e);
    }
  }

  public static List<Message> execute(SendMediaGroup input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.acquire(chatId);
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + chatId, e);
    }
  }

  /**
   * GetMe 无须进行速率限制
   * @param input
   * @return
   */
  public static User execute(GetMe input) {
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  /**
   * AnswerCallbackQuery 使用全局速率限制
   * @param input
   * @return
   */
  public static Boolean execute(AnswerCallbackQuery input) {
    try {
      RateLimiterManager.semaphore100.acquire();
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
      Thread.currentThread().interrupt(); // 恢复中断状态
    }
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  /**
   * 编辑消息文本使用全局速率限制
   */
  public static Serializable execute(EditMessageText input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.semaphore100.acquire(); // 全局 API 调用速率限制
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
      Thread.currentThread().interrupt();
    }
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + chatId, e);
    }
  }

  /**
   * 创建聊天邀请链接使用全局速率限制
   */
  public static ChatInviteLink execute(CreateChatInviteLink input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.semaphore100.acquire(); // 全局 API 调用速率限制
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
      Thread.currentThread().interrupt();
    }
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + chatId, e);
    }
  }

  /**
   * 删除消息类方法使用全局速率限制
   */
  public static Boolean execute(DeleteMessage input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.semaphore100.acquire();
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
      Thread.currentThread().interrupt();
    }
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + chatId, e);
    }
  }

  /**
   * 删除消息类方法使用全局速率限制
   */
  public static CompletableFuture<Boolean> executeAsync(DeleteMessage input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.semaphore100.acquire();
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
      Thread.currentThread().interrupt();
    }
    try {
      return main.executeAsync(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + chatId, e);
    }
  }

  public static Boolean execute(DeleteMessages input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.semaphore100.acquire(); // 全局 API 调用速率限制
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
      Thread.currentThread().interrupt();
    }
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + chatId, e);
    }
  }

  /**
   * 退出聊天使用全局速率限制
   */
  public static Boolean execute(LeaveChat input) {
    try {
      RateLimiterManager.semaphore100.acquire(); // 全局 API 调用速率限制
      return main.execute(input);
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
      Thread.currentThread().interrupt();
      throw new RuntimeException("Interrupted while acquiring semaphore for LeaveChat", e);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  /**
   * 提升聊天成员使用全局速率限制
   */
  public static Boolean execute(PromoteChatMember input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.semaphore100.acquire(); // 全局 API 调用速率限制
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
      Thread.currentThread().interrupt();
    }
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + chatId, e);
    }
  }

  /**
   * 获取聊天信息使用全局速率限制
   */
  public static ChatFullInfo execute(GetChat input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.semaphore100.acquire(); // 全局 API 调用速率限制
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
      Thread.currentThread().interrupt();
    }
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + chatId, e);
    }
  }

  public static ChatFullInfo getChat(String chatId) {
    GetChat input = new GetChat(chatId);
    return execute(input);
  }

  /**
   * 复制消息使用全局速率限制
   */
  public static MessageId execute(CopyMessage input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.semaphore100.acquire(); // 全局 API 调用速率限制
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
      Thread.currentThread().interrupt();
    }
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + chatId, e);
    }
  }

  /**
   * 转发消息使用全局速率限制
   */
  public static Message execute(ForwardMessage input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.semaphore100.acquire(); // 全局 API 调用速率限制
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
      Thread.currentThread().interrupt();
    }
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + chatId, e);
    }
  }

  /**
   * 复制多条消息使用全局速率限制
   */
  public static ArrayList<MessageId> execute(CopyMessages input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.semaphore100.acquire(); // 全局 API 调用速率限制
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
      Thread.currentThread().interrupt();
    }
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + chatId, e);
    }
  }

  /**
   * 获取聊天成员使用全局速率限制
   */
  public static ChatMember execute(GetChatMember input) {
    try {
      RateLimiterManager.semaphore100.acquire(); // 全局 API 调用速率限制
      return main.execute(input);
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
      Thread.currentThread().interrupt();
      throw new RuntimeException("Interrupted while acquiring semaphore for GetChatMember", e);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  /**
   * 删除消息工具方法使用全局速率限制
   */
  public static Boolean deleteMessage(Long chatId, int messageId) {
    DeleteMessage deleteMessage = new DeleteMessage(chatId.toString(), messageId);
    return TelegramClientCan.execute(deleteMessage);
  }

  public static Boolean deleteMessage(String chatId, int messageId) {
    DeleteMessage deleteMessage = new DeleteMessage(chatId, messageId);
    return TelegramClientCan.execute(deleteMessage);
  }

  public static CompletableFuture<Boolean> deleteMessageAsync(Long chatId, int messageId) {
    DeleteMessage deleteMessage = new DeleteMessage(chatId.toString(), messageId);
    return TelegramClientCan.executeAsync(deleteMessage);
  }

  /**
   * 复制消息工具方法使用全局速率限制
   */
  public static Long copyMessage(String toChatId, String fromChatId, Integer messageId) {
    CopyMessage copyMessage = new CopyMessage(toChatId, fromChatId, messageId);
    Long copiedMessageId = TelegramClientCan.execute(copyMessage).getMessageId();
    return copiedMessageId;
  }

  /**
   * 复制多条消息工具方法使用全局速率限制
   */
  public static List<MessageId> copyMessages(String toChatId, String fromChatId, List<Integer> messageIds) {
    CopyMessages copyMessages = new CopyMessages(toChatId, fromChatId, messageIds);
    return TelegramClientCan.execute(copyMessages);
  }

  /**
   * 获取聊天成员工具方法使用全局速率限制
   */
  public static ChatMember getChatMember(Long chatId, Long userId) {
    GetChatMember getChatMember = GetChatMember.builder().chatId(chatId).userId(userId).build();
    return execute(getChatMember);
  }

  /**
   * 退出聊天工具方法使用全局速率限制
   */
  public static Boolean leaveChat(String chatId) {
    LeaveChat leaveChat = LeaveChat.builder().chatId(chatId).build();
    return execute(leaveChat);
  }

  /**
   * 获取 bot 信息无须进行速率限制
   */
  public static User getMe() {
    GetMe getMe = new GetMe();
    return execute(getMe);
  }
}