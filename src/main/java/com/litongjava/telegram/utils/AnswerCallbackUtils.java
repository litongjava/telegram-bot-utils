package com.litongjava.telegram.utils;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;

public class AnswerCallbackUtils {

  public static AnswerCallbackQuery alert(String callbackQueryId, String message) {
    AnswerCallbackQuery answer = AnswerCallbackQuery.builder().callbackQueryId(callbackQueryId)
        //
        .text(message).showAlert(true).build();
    return answer;
  }
}
