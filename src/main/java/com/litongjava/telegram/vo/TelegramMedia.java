package com.litongjava.telegram.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelegramMedia {

  public static final String video = "video";
  public static final String audio = "audio";
  public static final String photo = "photo";
  public static final String document = "document";
  public static final String animation = "animation";

  
  private String caption;
  private String fileId;
  private String type;

  public TelegramMedia(String fileId) {
    this.fileId = fileId;
  }

  public TelegramMedia(String fileId, String type) {
    this.fileId = fileId;
    this.type = type;
  }


  public boolean hasVideo() {
    return video.equals(this.type);
  }
  public boolean hasAudio() {
    return audio.equals(this.type);
  }
  public boolean hasPhoto() {
    return photo.equals(this.type);
  }
  public boolean hasDocument() {
    return document.equals(this.type);
  }
  public boolean hasAnimation() {
    return animation.equals(this.type);
  }
  
  public static TelegramMedia buildVideo(String fileId) {
    return new TelegramMedia(fileId, video);
  }

  public static TelegramMedia buildAudio(String fileId) {
    return new TelegramMedia(fileId, audio);
  }
  
  public static TelegramMedia buildPhoto(String fileId) {
    return new TelegramMedia(fileId, photo);
  }
  
  public static TelegramMedia buildDocument(String fileId) {
    return new TelegramMedia(fileId, document);
  }
  
  public static TelegramMedia buildAnimation(String fileId) {
    return new TelegramMedia(fileId, animation);
  }
}
