package jgdabc.yingli.mode.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvestRankDto {

    private String phone;
    private BigDecimal bidMoney;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date bidTime;

    private BigDecimal  rate;

    private BigDecimal leftProductMoney;
    private String productName;
    private Integer cycle;

    private BigDecimal productMoney;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date releaseTime;


}

