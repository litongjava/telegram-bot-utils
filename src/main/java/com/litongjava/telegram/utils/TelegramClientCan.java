package com.litongjava.telegram.utils;

import java.io.Serializable;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.groupadministration.CreateChatInviteLink;
import org.telegram.telegrambots.meta.api.methods.groupadministration.LeaveChat;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.ChatInviteLink;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class TelegramClientCan {

  public static TelegramClient main;

  public static Message execute(SendMessage send) {
    try {
      // 通过TelegramClient执行发送消息请求
      Message message = main.execute(send);
      return message;
    } catch (TelegramApiException e) {
      throw new RuntimeException("Failed to send message:", e);
    }
  }

  public static Serializable execute(EditMessageText send) {
    try {
      // 通过TelegramClient执行发送消息请求
      return main.execute(send);
    } catch (TelegramApiException e) {
      throw new RuntimeException("Failed to send message:", e);
    }

  }

  public static Boolean execute(AnswerCallbackQuery input) {
    try {
      // 通过TelegramClient执行发送消息请求
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException("Failed to send message:", e);
    }

  }

  public static ChatInviteLink execute(CreateChatInviteLink input) {
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException("Failed to send message:", e);
    }
  }

  public static void execute(DeleteMessage input) {
    try {
      main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException("Failed to send message:", e);
    }
  }

  public static Boolean execute(LeaveChat input) {
    try {
      return main.execute(input);
    } catch (TelegramApiException e) {
      throw new RuntimeException("Failed to send message:", e);
    }
  }
}