package com.litongjava.telegram.utils;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.litongjava.telegram.vo.TelegramChatInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TelegramChatInfoFetcher {
  public static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.9999.999 Safari/537.36";

  public static TelegramChatInfo getChatUrl(String url) {
    // 设置请求头

    Connection connect = Jsoup.connect(url);
    connect.userAgent(userAgent);
    Document doc = null;
    try {
      doc = connect.get();
    } catch (IOException e) {
      throw new RuntimeException("Failed to get chat info", e);
    }

    // 提取 chat_name
    Element chatNameElement = doc.selectFirst("div.tgme_page_title");
    if (chatNameElement == null) {
      throw new RuntimeException("无法找到 chat_name 元素");
    }
    String chatName = chatNameElement.text().trim();

    // 提取 chat_description
    Element chatDescriptionElement = doc.selectFirst("div.tgme_page_description");
    String chatDescription = chatDescriptionElement != null ? chatDescriptionElement.text().trim() : "";

    // 提取 channel_text
    Element channelTextElement = doc.selectFirst("div.tgme_page_extra");
    if (channelTextElement == null) {
      throw new RuntimeException("无法找到 channel_text 元素");
    }
    String channelText = channelTextElement.text().trim();

    // 解析 chat_type 和 chat_count
    String chatType;
    int chatCount;

    if (channelText.contains("@") && channelText.endsWith("bot")) {
      chatType = "bot";
      chatCount = 0;
    } else if (channelText.contains("member")) {
      chatType = "supergroup";
      String countStr = channelText.replace(" ", "").split("member")[0];
      chatCount = parseCount(countStr);
    } else if (channelText.contains("subscriber")) {
      chatType = "channel";
      String countStr = channelText.replace(" ", "").split("subscriber")[0];
      chatCount = parseCount(countStr);
    } else {
      chatType = "private";
      chatCount = 0;
    }
    TelegramChatInfo chatInfo = new TelegramChatInfo(chatName, chatDescription, chatCount, chatType);
    return chatInfo;

  }

  private static int parseCount(String countStr) {
    // 处理包含逗号的数字，例如 "1,234"
    countStr = countStr.replace(",", "").replace(".", "");
    try {
      return Integer.parseInt(countStr);
    } catch (NumberFormatException e) {
      log.error("无法解析 chat_count: ", e);
      return 0;
    }
  }

}
