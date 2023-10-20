package jgdabc.yingli.mode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRankDto {
    private String phone;
    private BigDecimal sumBidMoney;
}
