package com.litongjava.telegram.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class TelegramCallback {
  private String type;
  private Long chatId;
  private String message;

  public TelegramCallback(String type, Long chatId) {
    this.type = type;
    this.chatId = chatId;
  }

  public String toString() {
    return type + "&" + chatId + "&" + message;
  }

}
