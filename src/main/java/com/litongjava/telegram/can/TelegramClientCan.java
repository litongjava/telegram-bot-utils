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
import org.telegram.telegrambots.meta.api.methods.groupadministration.LeaveChat;
import org.telegram.telegrambots.meta.api.methods.groupadministration.PromoteChatMember;
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
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class TelegramClientCan {

  public static TelegramClient main;

  public static Message execute(SendMessage input) {
    try {
      // 通过TelegramClient执行发送消息请求
      Message message = main.execute(input);
      return message;
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static Serializable execute(EditMessageText input) {
    try {
      // 通过TelegramClient执行发送消息请求
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }

  }

  public static Boolean execute(AnswerCallbackQuery input) {
    try {
      // 通过TelegramClient执行发送消息请求
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage(), e);
    }

  }

  public static ChatInviteLink execute(CreateChatInviteLink input) {
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static Boolean execute(DeleteMessage input) {
    try {
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

  public static Boolean execute(DeleteMessages input) {
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static Boolean execute(LeaveChat input) {
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static Boolean execute(PromoteChatMember input) {
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static Message execute(SendVideo input) {
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static Message execute(SendPhoto input) {
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static ChatFullInfo execute(GetChat input) {
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static ChatFullInfo getChat(String chatId) {
    GetChat input = new GetChat(chatId);
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static MessageId execute(CopyMessage input) {
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static Message execute(ForwardMessage input) {
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static ArrayList<MessageId> execute(CopyMessages input) {
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static List<Message> execute(SendMediaGroup input) {
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage() + " " + input.getChatId(), e);
    }
  }

  public static User execute(GetMe input) {
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

}