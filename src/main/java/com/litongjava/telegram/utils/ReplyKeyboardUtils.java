package com.litongjava.telegram.utils;

import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class ReplyKeyboardUtils {
  public static ReplyKeyboardMarkup build(List<KeyboardRow> keyboard) {
    ReplyKeyboardMarkup markup = ReplyKeyboardMarkup.builder().build();
    markup.setKeyboard(keyboard);
    return markup;
  }
}
