package com.litongjava.telegram.utils;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

public class SendPhotoUtils {

  public static SendPhoto file(String channelId, String fileId, String fileTitle) {
    InputFile inputFile = new InputFile(fileId);
    return SendPhoto.builder().chatId(channelId).photo(inputFile).caption(fileTitle).build();
  }

}
