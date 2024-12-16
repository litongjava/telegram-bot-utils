package com.litongjava.telegram.utils;

import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class SendMessageUtils {

  public static SendMessage html(String chatId, String content) {
    SendMessage sendMessage = new SendMessage(chatId, content);
    sendMessage.setParseMode(ParseMode.HTML);
    sendMessage.setDisableWebPagePreview(true);
    return sendMessage;
  }

  public static SendMessage html(Long chatId, String content) {
    SendMessage sendMessage = new SendMessage(chatId.toString(), content);
    sendMessage.setParseMode(ParseMode.HTML);
    sendMessage.setDisableWebPagePreview(true);
    return sendMessage;
  }

  public static SendMessage html(String chatId, String content, ReplyKeyboard keybaord) {
    return SendMessage.builder().chatId(chatId).text(content).replyMarkup(keybaord)
        //
        .parseMode(ParseMode.HTML).disableWebPagePreview(true).build();
  }
}
