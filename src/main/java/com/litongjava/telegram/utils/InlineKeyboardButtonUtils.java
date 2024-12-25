package com.litongjava.telegram.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class InlineKeyboardButtonUtils {
  
  public static InlineKeyboardButton url(String text,String url) {
    InlineKeyboardButton keyboardButton = InlineKeyboardButton.builder().url(url)
        //
        .text(text).build();
    return keyboardButton;
  }

}
