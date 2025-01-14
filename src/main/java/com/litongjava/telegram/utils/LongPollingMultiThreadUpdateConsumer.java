package com.litongjava.telegram.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;

import com.litongjava.tio.utils.thread.TioThreadUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class LongPollingMultiThreadUpdateConsumer implements LongPollingUpdateConsumer {

  public abstract void consumeGroup(List<Update> groupUpdates);

  public abstract void consume(Update update);

  @Override
  public void consume(List<Update> updates) {
    log.info("received updates size:{}", updates);
    Map<String, List<Update>> mediaGroupUpdatesMap = new HashMap<>();

    for (int i = 0; i < updates.size(); i++) {
      Update update = updates.get(i);
      if (update.hasMessage() && update.getMessage().getMediaGroupId() != null) {
        String mediaGroupId = update.getMessage().getMediaGroupId();
        mediaGroupUpdatesMap.computeIfAbsent(mediaGroupId, k -> new ArrayList<>()).add(update);
      } else {
        TioThreadUtils.execute(() -> consume(update));
      }
    }

    Set<String> keySet = mediaGroupUpdatesMap.keySet();
    if (keySet.size() > 0) {
      for (String mediaGroupId : keySet) {
        List<Update> groupUpdates = mediaGroupUpdatesMap.get(mediaGroupId);
        TioThreadUtils.execute(() -> consumeGroup(groupUpdates));
      }
      mediaGroupUpdatesMap.clear();
    }
  }

  /**
   * 判断消息是否来自私聊
   */
  public static boolean isPrivateChat(Message message) {
    return message.getChat().isUserChat();
  }

  /**
   * 判断消息是否为 /xx 命令
   */
  public static boolean hasCommand(Message message, String command) {
    String text = message.getText();
    return text != null && text.startsWith(command);
  }
}