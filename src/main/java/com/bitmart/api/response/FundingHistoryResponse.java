package com.bitmart.api.response;

import lombok.Data;

import java.util.List;

@Data
public class FundingHistoryResponse {
  List<FundingHistoryRecord> records;
}
