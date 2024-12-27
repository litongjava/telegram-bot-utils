package com.litongjava.telegram.utils;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaAnimation;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaAudio;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaDocument;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaVideo;

import com.litongjava.telegram.vo.TelegramMedia;

public class TelegramMediaUtils {

  public static List<TelegramMedia> getMedias(List<Update> groupUpdates) {
    List<TelegramMedia> medias = new ArrayList<>();

    for (Update update2 : groupUpdates) {
      if (update2.getMessage().hasVideo()) {
        String mediaId = update2.getMessage().getVideo().getFileId();
        TelegramMedia media = TelegramMedia.buildVideo(mediaId);
        String caption = update2.getMessage().getCaption();
        if (caption != null) {
          media.setCaption(caption);
        }
        medias.add(media);

      } else if (update2.getMessage().hasAudio()) {
        String mediaId = update2.getMessage().getAudio().getFileId();
        TelegramMedia media = TelegramMedia.buildAudio(mediaId);
        String caption = update2.getMessage().getCaption();
        if (caption != null) {
          media.setCaption(caption);
        }
        medias.add(media);

      } else if (update2.getMessage().hasPhoto()) {
        List<PhotoSize> photos = update2.getMessage().getPhoto();
        PhotoSize lastPhoto = photos.get(photos.size() - 1);
        String mediaId = lastPhoto.getFileId();

        TelegramMedia media = TelegramMedia.buildPhoto(mediaId);
        String caption = update2.getMessage().getCaption();
        if (caption != null) {
          media.setCaption(caption);
        }
        medias.add(media);

      } else if (update2.getMessage().hasDocument()) {
        String mediaId = update2.getMessage().getAudio().getFileId();

        TelegramMedia media = TelegramMedia.buildDocument(mediaId);
        String caption = update2.getMessage().getCaption();
        if (caption != null) {
          media.setCaption(caption);
        }
        medias.add(media);

      } else if (update2.getMessage().hasAnimation()) {
        String mediaId = update2.getMessage().getAnimation().getFileId();
        TelegramMedia media = TelegramMedia.buildAnimation(mediaId);

        String caption = update2.getMessage().getCaption();
        if (caption != null) {
          media.setCaption(caption);
        }
        medias.add(media);
      }
    }
    return medias;

  }

  public static List<InputMedia> getInputMedias(List<TelegramMedia> medias) {
    List<InputMedia> inputMedias = new ArrayList<>();

    for (TelegramMedia m : medias) {
      if (m.hasVideo()) {
        String mediaId = m.getFileId();
        InputMedia inputMediaVideo = new InputMediaVideo(mediaId);
        String caption = m.getCaption();
        if (caption != null) {
          inputMediaVideo.setCaption(caption);
        }
        inputMedias.add(inputMediaVideo);

      } else if (m.hasAudio()) {
        String mediaId = m.getFileId();
        InputMedia inputMedia = new InputMediaAudio(mediaId);
        String caption = m.getCaption();
        if (caption != null) {
          inputMedia.setCaption(caption);
        }
        inputMedias.add(inputMedia);

      } else if (m.hasPhoto()) {
        String mediaId = m.getFileId();
        InputMedia inputMedia = new InputMediaPhoto(mediaId);
        String caption = m.getCaption();
        if (caption != null) {
          inputMedia.setCaption(caption);
        }
        inputMedias.add(inputMedia);

      } else if (m.hasDocument()) {
        String mediaId = m.getFileId();
        InputMedia inputMedia = new InputMediaDocument(mediaId);
        String caption = m.getCaption();
        if (caption != null) {
          inputMedia.setCaption(caption);
        }
        inputMedias.add(inputMedia);

      } else if (m.hasAnimation()) {
        String mediaId = m.getFileId();
        InputMedia inputMedia = new InputMediaAnimation(mediaId);
        String caption = m.getCaption();
        if (caption != null) {
          inputMedia.setCaption(caption);
        }
        inputMedias.add(inputMedia);
      }

    }
    return inputMedias;
  }

  public static List<InputMedia> getInputMediasByUpdate(List<Update> groupUpdates) {
    List<InputMedia> medias = new ArrayList<>();

    for (Update update2 : groupUpdates) {
      if (update2.getMessage().hasVideo()) {
        String mediaId = update2.getMessage().getVideo().getFileId();
        InputMedia inputMediaVideo = new InputMediaVideo(mediaId);
        String caption = update2.getMessage().getCaption();
        if (caption != null) {
          inputMediaVideo.setCaption(caption);
        }
        medias.add(inputMediaVideo);

      } else if (update2.getMessage().hasAudio()) {
        String mediaId = update2.getMessage().getAudio().getFileId();
        InputMedia inputMedia = new InputMediaAudio(mediaId);
        String caption = update2.getMessage().getCaption();
        if (caption != null) {
          inputMedia.setCaption(caption);
        }
        medias.add(inputMedia);

      } else if (update2.getMessage().hasPhoto()) {
        List<PhotoSize> photos = update2.getMessage().getPhoto();
        PhotoSize lastPhoto = photos.get(photos.size() - 1);
        String mediaId = lastPhoto.getFileId();
        InputMedia inputMedia = new InputMediaPhoto(mediaId);
        String caption = update2.getMessage().getCaption();
        if (caption != null) {
          inputMedia.setCaption(caption);
        }
        medias.add(inputMedia);

      } else if (update2.getMessage().hasDocument()) {
        String mediaId = update2.getMessage().getAudio().getFileId();
        InputMedia inputMedia = new InputMediaDocument(mediaId);
        String caption = update2.getMessage().getCaption();
        if (caption != null) {
          inputMedia.setCaption(caption);
        }
        medias.add(inputMedia);

      } else if (update2.getMessage().hasAnimation()) {
        String mediaId = update2.getMessage().getAnimation().getFileId();
        InputMedia inputMedia = new InputMediaAnimation(mediaId);
        String caption = update2.getMessage().getCaption();
        if (caption != null) {
          inputMedia.setCaption(caption);
        }
        medias.add(inputMedia);
      }

    }
    return medias;
  }

}
