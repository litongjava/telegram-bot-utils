package com.litongjava.telegram.vo;

import java.io.Serializable;

public class TelegramChatInfo implements Serializable {
  private static final long serialVersionUID = 9024755417170069740L;
  private String chatName;
  private String chatDescription;
  private int chatCount;
  private String chatType;

  public TelegramChatInfo(String chatName, String chatDescription, int chatCount, String chatType) {
    this.chatName = chatName;
    this.chatDescription = chatDescription;
    this.chatCount = chatCount;
    this.chatType = chatType;
  }

  // Getters 和 Setters
  public String getChatName() {
    return chatName;
  }

  public void setChatName(String chatName) {
    this.chatName = chatName;
  }

  public String getChatDescription() {
    return chatDescription;
  }

  public void setChatDescription(String chatDescription) {
    this.chatDescription = chatDescription;
  }

  public int getChatCount() {
    return chatCount;
  }

  public void setChatCount(int chatCount) {
    this.chatCount = chatCount;
  }

  public String getChatType() {
    return chatType;
  }

  public void setChatType(String chatType) {
    this.chatType = chatType;
  }

  @Override
  public String toString() {
    return "ChatInfo{" + "chatName='" + chatName + '\'' + ", chatDescription='" + chatDescription + '\'' + ", chatCount=" + chatCount + ", chatType='" + chatType + '\'' + '}';
  }
}
