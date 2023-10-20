package jgdabc.yingli.mode.vo;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class AppBaseInfoVo {

    private BigDecimal prodctAvgRate;
    //    累计投资金额的和
    private BigDecimal sumBidMoney;


    //    注册人数
    private Long userCount;
}
