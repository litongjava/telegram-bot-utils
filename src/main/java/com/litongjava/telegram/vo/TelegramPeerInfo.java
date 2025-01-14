package com.litongjava.telegram.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelegramPeerInfo implements Serializable {
  private static final long serialVersionUID = 9024755417170069740L;
  private String name;
  private String description;
  private int count;
  private String type;
}
