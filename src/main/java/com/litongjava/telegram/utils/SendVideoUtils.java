package com.litongjava.telegram.utils;

import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;

public class SendVideoUtils {

  public static SendVideo file(String chatId, String fileId, String fileTitle) {
    InputFile inputFile = new InputFile(fileId);
    SendVideo sendVideo = SendVideo.builder().chatId(chatId).video(inputFile).caption(fileTitle).build();
    return sendVideo;
  }

  public static SendVideo url(String chatId, String titile, String videoUrl) {
    InputFile inputFile = new InputFile(videoUrl);
    SendVideo sendVideo = SendVideo.builder().chatId(chatId).video(inputFile).caption(titile).build();
    return sendVideo;
  }

}
