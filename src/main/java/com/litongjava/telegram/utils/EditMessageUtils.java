package com.litongjava.telegram.utils;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class EditMessageUtils {

  public static EditMessageText html(Long chatId, Integer messageId, String messageText, InlineKeyboardMarkup inlineKeyboard) {
    EditMessageText editMessage = EditMessageText.builder()//
        .chatId(chatId.toString()).messageId(messageId).text(messageText)
        //
        .replyMarkup(inlineKeyboard).parseMode(ParseMode.HTML).disableWebPagePreview(true)
        //
        .build();
    return editMessage;
  }

  public static EditMessageText html(String chatId, Integer messageId, String messageText) {
    EditMessageText editMessage = EditMessageText.builder()//
        .chatId(chatId).messageId(messageId).text(messageText)
        //
        .parseMode(ParseMode.HTML).disableWebPagePreview(true)
        //
        .build();
    return editMessage;
  }

  public static EditMessageText html(Long chatId, Integer messageId, String messageText) {
    EditMessageText editMessage = EditMessageText.builder()//
        .chatId(chatId).messageId(messageId).text(messageText)
        //
        .parseMode(ParseMode.HTML).disableWebPagePreview(true)
        //
        .build();
    return editMessage;
  }

  public static EditMessageText text(String chatId, int messageId, String text) {
    EditMessageText editMessage = EditMessageText.builder()//
        .chatId(chatId).messageId(messageId).text(text).build();
    return editMessage;
  }

}
