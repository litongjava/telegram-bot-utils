package com.litongjava.telegram.utils;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

public class InlineKeyboardMarkupUtils {

  // 创建带有单个按钮的内联键盘
  public static InlineKeyboardMarkup createInlineKeyboard(String buttonText, String url) {
    InlineKeyboardButton button = InlineKeyboardButton.builder().text(buttonText).url(url).build();
    InlineKeyboardRow inlineKeyboardRow = new InlineKeyboardRow(button);
    List<InlineKeyboardRow> rows = List.of(inlineKeyboardRow);
    return InlineKeyboardMarkup.builder().keyboard(rows).build();
  }

  // Create multiple buttons inline keyboard
  public static InlineKeyboardMarkup createInlineKeyboard(List<String> buttonTexts, List<String> callbackData) {

    List<InlineKeyboardRow> rows = new ArrayList<>();
    InlineKeyboardRow row = new InlineKeyboardRow();
    for (int i = 0; i < buttonTexts.size(); i++) {
      InlineKeyboardButton button = InlineKeyboardButtonUtils.callback(buttonTexts.get(i), buttonTexts.get(i));
      row.add(button);
    }
    rows.add(row);
    InlineKeyboardMarkup markup = new InlineKeyboardMarkup(rows);
    return markup;
  }

  public static InlineKeyboardMarkup create(InlineKeyboardButton... buttons) {
    List<InlineKeyboardRow> rows = new ArrayList<>();
    InlineKeyboardRow row = new InlineKeyboardRow();
    for (InlineKeyboardButton button : buttons) {
      row.add(button);
    }
    rows.add(row);
    InlineKeyboardMarkup markup = new InlineKeyboardMarkup(rows);
    return markup;
  }

  public static InlineKeyboardMarkup createRow(InlineKeyboardButton... buttons) {
    return create(buttons);
  }
}
