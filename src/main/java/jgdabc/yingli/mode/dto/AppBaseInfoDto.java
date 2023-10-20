package jgdabc.yingli.mode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppBaseInfoDto {

//理财产品的平均利率
    private BigDecimal prodctAvgRate;
//    累计投资金额的和
    private BigDecimal sumBidMoney;


//    注册人数
    private Long userCount;
}
