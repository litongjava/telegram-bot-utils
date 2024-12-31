package com.litongjava.telegram.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelegramChatInfo implements Serializable {
  private static final long serialVersionUID = 9024755417170069740L;
  private String chatName;
  private String chatDescription;
  private int chatCount;
  private String chatType;
}
