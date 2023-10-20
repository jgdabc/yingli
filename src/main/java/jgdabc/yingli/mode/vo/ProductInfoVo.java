package jgdabc.yingli.mode.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductInfoVo {
    /**
     * 周期
     */
    private Integer cycle;
    /**
     * 产品id
     */
    private Integer id;
    /**
     * 剩余金额
     */
    private BigDecimal leftMoney;
    /**
     * 最大购买金额
     */
    private BigDecimal maxLimit;
    /**
     * 最小购买金额
     */
    private BigDecimal minLimit;
    /**
     * 产品金额
     */
    private BigDecimal money;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品编号
     */
    private String productNo;
    /**
     * 利率
     */
    private BigDecimal rate;
    /**
     * 类型
     */
    private Integer type;

}
