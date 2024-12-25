package com.litongjava.telegram.utils;

import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;

public class SendVideoUtils {

  public static SendVideo file(String channelId, String fileId, String fileTitle) {
    InputFile inputFile = new InputFile(fileId);
    SendVideo sendVideo = SendVideo.builder().chatId(channelId).video(inputFile).caption(fileTitle).build();
    return sendVideo;
  }

}
