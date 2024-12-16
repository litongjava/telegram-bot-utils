package com.litongjava.telegram.utils;

import com.litongjava.tio.utils.environment.EnvUtils;

public class TelegramBotUtils {

  public static long getSelfId() {
    String botAuthToken = EnvUtils.getStr("bot.authToken");
    return Long.valueOf(botAuthToken.split(":")[0]);
  }

  
  /**
   * 
   * @return
   */
  public static String getBotUrl() {
    String bot_name = EnvUtils.getStr("bot.name");
    String bot_url = "https://t.me/" + bot_name;
    return bot_url;
  }

  public static String getBotStartUrl() {
    String url = getBotUrl() + "?startchannel=true";
    return url;
  }


}
