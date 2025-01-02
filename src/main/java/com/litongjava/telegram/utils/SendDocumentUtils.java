package com.litongjava.telegram.utils;

import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;

public class SendDocumentUtils {

  public static SendDocument file(String chatId, String fileId, String caption) {
    InputFile inputFile = new InputFile(fileId);
    return SendDocument.builder().chatId(chatId).document(inputFile).caption(caption).build();
  }

}
