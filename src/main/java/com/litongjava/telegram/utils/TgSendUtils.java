package com.litongjava.telegram.utils;

import com.litongjava.telegram.can.TelegramClientCan;

public class TgSendUtils {
  public static void sendMarkdown(Long chatId, String markdown) {
    TelegramClientCan.execute(SendMessageUtils.markdown(chatId, markdown));
  }

  public static void sendText(Long chatId, String text) {
    TelegramClientCan.execute(SendMessageUtils.text(chatId, text));
  }

  public static void sendHtml(Long chatId, String html) {
    TelegramClientCan.execute(SendMessageUtils.html(chatId, html));
  }
}
