package com.bitmart.api.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum WalletOperationType {
  DEPOSIT("deposit"),
  WITHDRAW("withdraw");

  @Getter
  @JsonValue
  private final String name;

  WalletOperationType(String name) {
    this.name = name;
  }
}
