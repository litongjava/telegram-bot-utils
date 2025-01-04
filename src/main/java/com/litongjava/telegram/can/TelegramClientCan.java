package com.litongjava.telegram.can;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
   * AnswerCallbackQuery 无须进行速率限制
   * @param input
   * @return
   */
  public static Boolean execute(AnswerCallbackQuery input) {
    try {
      RateLimiterManager.getGlobalSemaphore().acquire();
    } catch (InterruptedException e) {
      log.error(e.getMessage(), e);
    }
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public static Message execute(SendMessage input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.acquire(chatId);
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + chatId, e);
    }
  }

  public static Message execute(SendDocument input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.acquire(chatId);
      Message message = main.execute(input);
      return message;
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static Serializable execute(EditMessageText input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.acquire(chatId);
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }

  }

  public static ChatInviteLink execute(CreateChatInviteLink input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.acquire(chatId);
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static Boolean execute(DeleteMessage input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.acquire(chatId);
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static Boolean execute(DeleteMessages input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.acquire(chatId);
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static Boolean execute(LeaveChat input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.acquire(chatId);
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static Boolean execute(PromoteChatMember input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.acquire(chatId);
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static Message execute(SendVideo input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.acquire(chatId);
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static Message execute(SendPhoto input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.acquire(chatId);
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static ChatFullInfo execute(GetChat input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.acquire(chatId);
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static ChatFullInfo getChat(String chatId) {
    GetChat input = new GetChat(chatId);
    try {
      RateLimiterManager.acquire(chatId);
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static MessageId execute(CopyMessage input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.acquire(chatId);
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static Message execute(ForwardMessage input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.acquire(chatId);
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static ArrayList<MessageId> execute(CopyMessages input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.acquire(chatId);
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static List<Message> execute(SendMediaGroup input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.acquire(chatId);
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static ChatMember execute(GetChatMember input) {
    String chatId = input.getChatId();
    try {
      RateLimiterManager.acquire(chatId);
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static Boolean deleteMessage(Long chatId, int messageId) {
    DeleteMessage deleteMessage = new DeleteMessage(chatId.toString(), messageId);
    return TelegramClientCan.execute(deleteMessage);
  }

  public static Boolean deleteMessage(String chatId, int messageId) {
    DeleteMessage deleteMessage = new DeleteMessage(chatId, messageId);
    return TelegramClientCan.execute(deleteMessage);
  }

  public static Long copyMessage(String toChatId, String fromChatId, Integer messageId) {
    CopyMessage copyMessage = new CopyMessage(toChatId, fromChatId, messageId);
    Long copiedMessageId = TelegramClientCan.execute(copyMessage).getMessageId();
    return copiedMessageId;
  }

  public static List<MessageId> copyMessages(String toChatId, String fromChatId, List<Integer> messageIds) {
    CopyMessages copyMessages = new CopyMessages(toChatId, fromChatId, messageIds);
    List<MessageId> execute = TelegramClientCan.execute(copyMessages);
    return execute;
  }

  public static ChatMember getChatMemeber(Long chatId, Long userId) {
    GetChatMember getChatMemeber = GetChatMember.builder().chatId(chatId).userId(userId).build();
    return execute(getChatMemeber);
  }

  public static User getMe() {
    GetMe getMe = new GetMe();
    return execute(getMe);
  }

}