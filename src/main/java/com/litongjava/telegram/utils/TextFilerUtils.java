package com.litongjava.telegram.utils;

public class TextFilerUtils {

  // 方法：过滤表情符号
  public static String filterEmoji(String source) {
    if (source == null) {
      return null;
    }
    // 正则匹配表情符号的模式
    String emojiPattern = "[\\p{So}\\p{Cn}]+";
    return source.replaceAll(emojiPattern, "");
  }

  // 方法：截取字符串并添加省略号
  public static String truncateWithEllipsis(String source, int maxLength) {
    if (source == null || source.length() <= maxLength) {
      return source;
    }
    return source.substring(0, maxLength) + "...";
  }
}
