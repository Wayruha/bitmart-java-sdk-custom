package com.bitmart.api.response;

import com.bitmart.api.dto.WalletOperationType;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FundingHistoryRecord {
  @JsonAlias("withdraw_id")
  private String withdrawId;
  @JsonAlias("deposit_id")
  private String depositId;
  @JsonAlias("operation_type")
  private WalletOperationType operationType;
  private String currency;
  @JsonAlias("apply_time")
  private Long applyTime;
  @JsonAlias("arrival_amount")
  private BigDecimal arrivalAmount;
  private BigDecimal fee;
  private int status;
  private String address;
  @JsonAlias("address_memo")
  private String addressMemo;
  @JsonAlias("tx_id")
  private String transactionId;
}
