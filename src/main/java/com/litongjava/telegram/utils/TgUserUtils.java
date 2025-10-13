package com.litongjava.telegram.utils;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.message.MaybeInaccessibleMessage;
import org.telegram.telegrambots.meta.api.objects.message.Message;

public class TgUserUtils {

  public static Long getUserId(Update update) {
    Message message = update.getMessage();
    if (message != null) {
      return message.getChatId();
    }
    MaybeInaccessibleMessage callbackMessage = update.getCallbackQuery().getMessage();
    return callbackMessage.getChatId();
  }

  public static String getUsername(Update update) {
    Message message = update.getMessage();
    if (message != null) {
      return message.getFrom() != null ? message.getFrom().getUserName() : null;
    }
    User user = update.getCallbackQuery().getFrom();
    return user != null ? user.getUserName() : null;
  }

}
